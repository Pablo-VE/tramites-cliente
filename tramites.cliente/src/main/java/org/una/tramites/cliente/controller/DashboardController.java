/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.AuthenticationResponse;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.util.AppContext;
import proyectotitan.util.Mensaje;

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
    private void actPermisos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("permisos" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

    @FXML
    private void actUsuarios(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("usuarios" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
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
    private void actTiposTramites(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("tiposTramites" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

    @FXML
    private void actDisenoTramites(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("disenoTramites" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

    @FXML
    private void actCerrarSesion(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Está seguro que desea salir?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(App.class.getResource("login" + ".fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage stage2 = (Stage) Contenedor.getScene().getWindow();
            stage2.close();
        }
    }
    
}
