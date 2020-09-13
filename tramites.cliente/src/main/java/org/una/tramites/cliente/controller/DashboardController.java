/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.una.tramites.cliente.App;
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
    @FXML
    private StackPane Contenedor;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AppContext.getInstance().set("Contenedor", this.Contenedor);
        
        
        atResponse = (AuthenticationResponse) AppContext.getInstance().get("UsuarioAutenticado");
        if(atResponse!=null){
            usuarioLoggeado = atResponse.getUsuario();
            
            TituloMenuUsuarios.setText("Usuario: "+usuarioLoggeado.getNombreCompleto());
        }
    }    

    @FXML
    private void actPermisos(ActionEvent event) {
    }

    @FXML
    private void actUsuarios(ActionEvent event) {
    }

    @FXML
    private void actParametros(ActionEvent event) {
    }

    @FXML
    private void actDepartamentos(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(App.class.getResource("departamentos" + ".fxml"));
         Contenedor.getChildren().clear();
         Contenedor.getChildren().add(root);
    }

    @FXML
    private void actTiposTramites(ActionEvent event) {
    }
    
}
