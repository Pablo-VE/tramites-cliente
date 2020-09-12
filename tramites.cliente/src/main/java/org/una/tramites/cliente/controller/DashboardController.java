/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.una.tramites.cliente.dto.AuthenticationResponse;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.util.AppContext;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class DashboardController implements Initializable {

    @FXML
    private MenuItem menuDepartamentos;
    @FXML
    private MenuItem menuTiposTramites;
    @FXML
    private MenuItem menuDisenoTramites;
    @FXML
    private MenuItem menuPermisos;
    @FXML
    private MenuItem menuUsuarios;
    @FXML
    private MenuItem menuParametros;
    @FXML
    private MenuItem menuCambiarUsuario;
    @FXML
    private MenuItem menuCerrarSesion;

    /**
     * Initializes the controller class.
     */
    
    UsuarioDTO usuarioLoggeado = new UsuarioDTO();
    AuthenticationResponse atResponse = new AuthenticationResponse();
    @FXML
    private Menu TituloMenuUsuarios;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atResponse = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
        if(atResponse!=null){
            usuarioLoggeado = atResponse.getUsuario();
            
            TituloMenuUsuarios.setText("Usuario: "+usuarioLoggeado.getNombreCompleto());
        }
    }    
    
}
