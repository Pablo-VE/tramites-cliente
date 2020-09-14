/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class PermisosDetalleInformacionController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private Label lblFechaCreacion;
    @FXML
    private Label lblFechaModificacion;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Button btnAtras;
    @FXML
    private TextField txtDescripcion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actEstado(ActionEvent event) {
    }

    @FXML
    private void actAtras(ActionEvent event) {
    }

    @FXML
    private void actGuardar(ActionEvent event) {
    }
    
}
