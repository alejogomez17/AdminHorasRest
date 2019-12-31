package com.desarrollo.adminhoras.api.negocio.impl;

import com.desarrollo.adminhoras.api.datos.dao.AdminHorasDao;
import com.desarrollo.adminhoras.api.datos.dominio.GenEmpl;
import com.desarrollo.adminhoras.api.datos.dominio.AdhInar;
import com.desarrollo.adminhoras.api.datos.dominio.AdhRein;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.desarrollo.adminhoras.api.negocio.control.ManejoArchivoService;
import com.desarrollo.adminhoras.api.utilidades.Formato;
import com.desarrollo.adminhoras.api.utilidades.Tool;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Alejo Gomez
 */
public class ManejoArchivoServiceImpl implements ManejoArchivoService {

    
    private Path rootLocation = null;

    //cabeceras que se pueden encontrar en el archivo
    private final String CABECERA_USUARIO = "NO.";
    private final String CABECERA_FECHA = "FECHA/HORA";
    private final String CABECERA_ESTADO = "ESTADO";

    @Autowired
    private AdminHorasDao adminHorasDaoImpl;

    @Override
    public List<GenEmpl> obtenerEmpleados() {
        return this.adminHorasDaoImpl.obtenerListaEmpleados();
    }

    @Override
    public void importarArchivo(MultipartFile file) {
        File archivoExcel = obtenerArchivo(file);
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
            int filaTirada = 0;
            int[] posicionesDeCabeceras = null;
            while (rowIterator.hasNext()) {
                filaTirada++;
                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell;
                // la fila == 1 contiene las cabeceras
                if (filaTirada != 1) {
                    int columnaIterada = 0;
                    String[] datosDelRegistro = new String[3];
                    while (cellIterator.hasNext()) {
                        columnaIterada++;
                        cell = cellIterator.next();
                        //identificar la columna que se va a agregar
                        if (columnaIterada - 1 == posicionesDeCabeceras[0]) {
                            datosDelRegistro[0] = cell.getStringCellValue();
                        }
                        if (columnaIterada - 1 == posicionesDeCabeceras[1]) {
                            datosDelRegistro[1] = cell.getStringCellValue();
                        }
                        if (columnaIterada - 1 == posicionesDeCabeceras[2]) {
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
                        //excepción para cuando el archivo no trae cabeceras necesarias 
                    }
                }

            }
        } catch (Exception ex) {

        }
    }
    
    private File obtenerArchivo(MultipartFile file) {
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
    
    private int[] obtenerCabeceras(String[] datosCabecera) {
        int[] posiciones = new int[3];
        posiciones[0] = -1;
        posiciones[1] = -1;
        posiciones[2] = -1;
        for (int i = 0; i < datosCabecera.length; i++) {
            if (datosCabecera[i].trim().toUpperCase().equals(this.CABECERA_USUARIO)) {
                posiciones[0] = i;
            }
            if (datosCabecera[i].trim().toUpperCase().equals(this.CABECERA_FECHA)) {
                posiciones[1] = i;
            }
            if (datosCabecera[i].trim().toUpperCase().equals(this.CABECERA_ESTADO)) {
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

    private void guardarDatosDelRegistro(String[] datosDelRegistro, Integer idinar) {
        AdhRein registro = new AdhRein();
        registro.setIdempl(Integer.parseInt(datosDelRegistro[0]));
        try {

            String formatoHora = datosDelRegistro[1].substring(datosDelRegistro[1].length() - 4, datosDelRegistro[1].length());
            if (formatoHora.trim().toUpperCase().equals("P.M.")) {
                registro.setFechar(Tool.sumarHorasAFecha(Formato.FORMATO_FECHA_HORA.parse(datosDelRegistro[1]), 12));
            } else {
                registro.setFechar(Formato.FORMATO_FECHA_HORA.parse(datosDelRegistro[1]));
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

}
