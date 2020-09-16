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
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.una.tramites.cliente.util.AppContext;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class RequisitosAgregarController implements Initializable {

    @FXML
    private TextField txtDescripcion;
    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbEstado.getItems().add("Activo");
        cmbEstado.getItems().add("Inactivo");
    }    

    @FXML
    private void actGuardar(ActionEvent event) {
    }

    @FXML
    private void actCancelar(ActionEvent event) {
    }
    
}
