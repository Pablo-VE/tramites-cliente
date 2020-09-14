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
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.una.tramites.cliente.App;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class UsuariosController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnInformacion;
    @FXML
    private Button btnAutorizaciones;
    @FXML
    private Button btnContrasena;
    @FXML
    private StackPane contenedor;
    @FXML
    private ComboBox<String> cmbTipoBusqueda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbTipoBusqueda.getItems().add("Id");
        cmbTipoBusqueda.getItems().add("Nombre");
    }    

    @FXML
    private void actBuscar(ActionEvent event) {
    }

    @FXML
    private void actBorrar(ActionEvent event) {
    }

    @FXML
    private void actGuardar(ActionEvent event) {
    }

    @FXML
    private void actCancelar(ActionEvent event) {
    }

    @FXML
    private void actLimpiar(ActionEvent event) {
    }

    @FXML
    private void actInformacion(ActionEvent event) {
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
    
}
