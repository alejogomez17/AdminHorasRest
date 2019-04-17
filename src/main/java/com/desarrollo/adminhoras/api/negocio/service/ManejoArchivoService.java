
package com.desarrollo.adminhoras.api.negocio.service;

import com.desarrollo.adminhoras.api.datos.dominio.GenEmpl;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Alejo Gomez
 */
public interface ManejoArchivoService {
    List<GenEmpl> obtenerEmpleados();
    
    void importarArchivo(MultipartFile file);
}
