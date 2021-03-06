package com.desarrollo.adminhoras.api;

import com.desarrollo.adminhoras.api.datos.dominio.GenEmpl;
import com.desarrollo.adminhoras.api.modelo.ConflictoRegistro;
import com.desarrollo.adminhoras.api.modelo.IngresoArchivo;
import com.desarrollo.adminhoras.api.modelo.peticion.PeticionVerifcarRegistros;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorConflictoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.desarrollo.adminhoras.api.negocio.control.ManejoArchivoService;
import org.springframework.web.bind.annotation.RequestBody;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorRegistroService;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorIngresosService;

/**
 *
 * @author Alejo Gomez
 */
@RestController
public class AplicationController {

    @Autowired
    private ManejoArchivoService manejoRegistroServiceImpl;
    @Autowired
    private AdministradorIngresosService administracionIngresosServiceImpl;
    @Autowired
    private AdministradorConflictoService administradorConflictoServiceImpl;

    @CrossOrigin
    @PostMapping("/importarArchivo")
    public String importarArchivo(@RequestParam("archivos") MultipartFile file) {
        try {
            this.manejoRegistroServiceImpl.importarArchivo(file);
        } catch (Exception ex) {

        }
        return "";
    }

    @CrossOrigin
    @RequestMapping(path = "/verificarRegistros", method = RequestMethod.POST, consumes = "application/json")
    public String verificarRegistrosDeIngresoDeArchivo(@RequestBody PeticionVerifcarRegistros peticion) {
        this.administracionIngresosServiceImpl.verificarIngresoArchivo(peticion.getIngresoArchivo());
        return "";
    }

    @CrossOrigin
    @RequestMapping(path = "/obtenerEmpleados", method = RequestMethod.GET)
    public List<GenEmpl> busqueda() {
        return this.manejoRegistroServiceImpl.obtenerEmpleados();
    }

    @CrossOrigin
    @RequestMapping(path = "/obtenerIngresosArchivo", method = RequestMethod.GET)
    public List<IngresoArchivo> obtenerIngresosDeArchivo() {
        return this.administracionIngresosServiceImpl.obtenerIngresosDeArchivos();
    }

    @CrossOrigin
    @RequestMapping(path = "/conflictosRegistros", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<ConflictoRegistro> obtenerConflictosEnRegistrosPorIngreso(@RequestBody PeticionVerifcarRegistros peticion) {

        return this.administradorConflictoServiceImpl.obtenerConflictosDeRegistrosPorIngreso(peticion.getIngresoArchivo());

    }

    @CrossOrigin
    @RequestMapping(path = "/hola", method = RequestMethod.GET)
    public String saludar() {

        return "Siscar OGMIOS Seed - Operaciones";

    }
}
