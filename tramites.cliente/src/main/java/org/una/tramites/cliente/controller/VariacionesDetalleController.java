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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pablo-VE
 */
public class VariacionesDetalleController implements Initializable {

    @FXML
    private AnchorPane apVariacionesDetalle;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<?> cbxEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actGuardar(ActionEvent event) {
    }

    @FXML
    private void actCancelar(ActionEvent event) {
    }

    @FXML
    private void actEstado(ActionEvent event) {
    }
    
}
