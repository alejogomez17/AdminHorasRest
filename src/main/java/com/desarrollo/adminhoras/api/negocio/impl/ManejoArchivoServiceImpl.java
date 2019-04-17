package com.desarrollo.adminhoras.api.negocio.impl;

import com.desarrollo.adminhoras.api.datos.dao.AdminHorasDao;
import com.desarrollo.adminhoras.api.datos.dominio.GenEmpl;
import com.desarrollo.adminhoras.api.datos.dominio.AdhInar;
import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.desarrollo.adminhoras.api.negocio.service.ManejoArchivoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Alejo Gomez
 */
public class ManejoArchivoServiceImpl implements ManejoArchivoService {

    @Autowired
    private AdminHorasDao adminHorasDaoImpl;

    private Path rootLocation = null;

    @Override
    public List<GenEmpl> obtenerEmpleados() {
        return this.adminHorasDaoImpl.obtenerListaEmpleados();
    }

    @Override
    public void importarArchivo(MultipartFile file) {
        File archivoExcel = obtenerArchivo(file);
        List<String[]> lineasDeValores = new ArrayList<>();
        try (FileInputStream filefile = new FileInputStream(archivoExcel)) {
            XSSFWorkbook worbook = new XSSFWorkbook(filefile);
            //obtener la hoja que se va leer
            XSSFSheet sheet = worbook.getSheetAt(0);
            //obtener todas las filas de la hoja excel
            Iterator<Row> rowIterator = sheet.iterator();

            AdhInar ingresoArchivo = this.adminHorasDaoImpl.registrarIngresoDeRegistros(new Date(), "");

            if (Objects.isNull(ingresoArchivo)) {
                System.out.println("error creando Ingreso de Registros");
            }

            Row row;
            int fila = 0;
            int[] posicionesDeCabeceras = null;
            while (rowIterator.hasNext()) {

                fila++;
                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell;
                if (fila != 1) {
                    String[] valoresLinea = new String[3];
                    int columna = 0;
                    int agregados = 0;
                    String[] datosDelRegistro = new String[3];
                    while (cellIterator.hasNext()) {
                        columna++;
                        cell = cellIterator.next();
                        if (columna - 1 == posicionesDeCabeceras[0]) {
                            datosDelRegistro[0] = cell.getStringCellValue();
                        }
                        if (columna - 1 == posicionesDeCabeceras[1]) {
                            datosDelRegistro[1] = cell.getStringCellValue();
                        }
                        if (columna - 1 == posicionesDeCabeceras[2]) {
                            datosDelRegistro[2] = cell.getStringCellValue();
                        }
                    }
                    guardarDatosDelRegistro(datosDelRegistro, ingresoArchivo.getIdinar());
                } else {
                    //primera fila
                    String datosCabecera = "";
                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();
                        datosCabecera += cell.getStringCellValue() + ";";
                    }
                    posicionesDeCabeceras = obtenerCabeceras(datosCabecera.split(";"));
                    if (!existenTodasLasCabeceras(posicionesDeCabeceras)) {
                        System.out.println("No existen las cabeceras reales");
                    }
                }

            }
        } catch (Exception ex) {

        }
        System.out.println("vamos bn " + lineasDeValores.size());

    }

    public void guardarDatosDelRegistro(String[] datosDelRegistro, Integer idinar) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        AdhRein registro = new AdhRein();
        registro.setIdempl(Integer.parseInt(datosDelRegistro[0]));
        try {

            String formatoHora = datosDelRegistro[1].substring(datosDelRegistro[1].length() - 4, datosDelRegistro[1].length());
            if (formatoHora.trim().toUpperCase().equals("P.M.")) {
                registro.setFechar(sumarHorasAFecha(formatoFecha.parse(datosDelRegistro[1]), 12));
            } else {
                registro.setFechar(formatoFecha.parse(datosDelRegistro[1]));
            }
            if (datosDelRegistro[2].trim().toUpperCase().equals("M/ENT")) {
                registro.setTipoin('E');
            } else {
                if (datosDelRegistro[2].trim().toUpperCase().equals("M/SAL")) {
                    registro.setTipoin('S');
                }
            }
            registro.setEstado('R');
            registro.setIdinar(idinar);
            this.adminHorasDaoImpl.insertarRegistroDeIngreso(registro);
        } catch (ParseException ex) {
            System.out.println("Error en formato de fecha");
        }

    }

    private Date sumarHorasAFecha(Date fecha, int horas) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.HOUR, horas);
        return calendar.getTime();
    }

    private static final String cabeceraUsuario = "NO.";
    private static final String cabeceraFecha = "FECHA/HORA";
    private static final String cabeceraEstado = "ESTADO";

    private int[] obtenerCabeceras(String[] datosCabecera) {
        int[] posiciones = new int[3];
        posiciones[0] = -1;
        posiciones[1] = -1;
        posiciones[2] = -1;
        for (int i = 0; i < datosCabecera.length; i++) {
            if (datosCabecera[i].trim().toUpperCase().equals(cabeceraUsuario)) {
                posiciones[0] = i;
            }
            if (datosCabecera[i].trim().toUpperCase().equals(cabeceraFecha)) {
                posiciones[1] = i;
            }
            if (datosCabecera[i].trim().toUpperCase().equals(cabeceraEstado)) {
                posiciones[2] = i;
            }
        }
        return posiciones;
    }

    private boolean existenTodasLasCabeceras(int[] posicionesCabeceras) {
        for (int i = 0; i < posicionesCabeceras.length; i++) {
            if (posicionesCabeceras[i] == -1) {
                return false;
            }
        }
        return true;
    }

    private File obtenerArchivo(MultipartFile file) {
        String nombreNuevo = guardarArchivo(file, "", "", file.getOriginalFilename());
        File archivoAImportar = null;
        try {
            archivoAImportar = convertirMultipartAFile(file, file.getOriginalFilename());
//            FileReader fileR = new FileReader(archivoAImportar);
//            file2 = new BufferedReader(fileR);

        } catch (IllegalStateException ex) {
            System.out.println("Error Obteniendo archivo a importar. Descripción: " + ex.getMessage());
            return null;

        } catch (IOException ex) {
            System.out.println("Error Obteniendo archivo a importar. Descripción: " + ex.getMessage());
            return null;
        }
        return archivoAImportar;
    }

    private File convertirMultipartAFile(MultipartFile multipart, String nombreArchivo) throws IllegalStateException, IOException {
        File convFile = new File(nombreArchivo);
        multipart.transferTo(convFile);
        return convFile;
    }

    private String guardarArchivo(MultipartFile file, String rutaRaiz, String rutaEspecifica, String nombreOriginalArchivo) {
        this.rootLocation = Paths.get(rutaRaiz);
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));

        } catch (Exception e) {
            System.out.println("Error subiendo el archivo al Servidor");
        }
        return file.getOriginalFilename();
    }

}
