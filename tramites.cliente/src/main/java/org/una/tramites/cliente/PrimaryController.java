package org.una.tramites.cliente;

import java.io.IOException;
import javafx.fxml.FXML;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.util.Respuesta;
import org.una.tramites.cliente.service.UsuarioService;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        UsuarioService usuService = new UsuarioService();
        Respuesta respuesta = usuService.getUserById(Long.valueOf(1));
        if(!respuesta.getEstado()){
            System.out.println("Error");
        }else{
            UsuarioDTO usu = (UsuarioDTO) respuesta.getResultado("Usuario");
            System.out.println(usu.getNombreCompleto());
        }
        App.setRoot("secondary");
    }
}
