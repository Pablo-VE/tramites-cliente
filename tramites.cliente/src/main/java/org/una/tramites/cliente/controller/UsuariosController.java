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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.util.AppContext;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class UsuariosController implements Initializable {

    @FXML
    private Button btnInformacion;
    @FXML
    private Button btnAutorizaciones;
    @FXML
    private Button btnContrasena;
    @FXML
    private StackPane contenedor;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblCedula;

    /**
     * Initializes the controller class.
     */
    private String modalidad;
    UsuarioDTO usuario = new UsuarioDTO();
    @FXML
    private Button btnVolver;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modalidad = (String) AppContext.getInstance().get("ModalidadUsuarios");
        
        if(modalidad.equals("Ver")||modalidad.equals("Modificar")){
            usuario = (UsuarioDTO) AppContext.getInstance().get("UsuarioEnCuestion");
            lblUsuario.setText(usuario.getNombreCompleto());
            lblCedula.setText(usuario.getCedula());
        }
    }    


    @FXML
    private void actInformacion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("usuariosInformacion" + ".fxml"));
        contenedor.getChildren().clear();
        contenedor.getChildren().add(root);
    }

    @FXML
    private void actAutorizaciones(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("usuariosAutorizaciones" + ".fxml"));
        contenedor.getChildren().clear();
        contenedor.getChildren().add(root);
    }

    @FXML
    private void actContrasena(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("usuariosContrasena" + ".fxml"));
        contenedor.getChildren().clear();
        contenedor.getChildren().add(root);
    }

    @FXML
    private void actVolver(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        Parent root = FXMLLoader.load(App.class.getResource("usuariosPrincipal" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
    
}
