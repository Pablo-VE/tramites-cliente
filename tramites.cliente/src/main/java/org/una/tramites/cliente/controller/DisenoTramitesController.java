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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class DisenoTramitesController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView tbTramite;
    @FXML
    private Button btnVariaciones;
    @FXML
    private Button btnRequisitos;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actBuscar(ActionEvent event) {
    }

    @FXML
    private void actBorrar(ActionEvent event) {
    }

    @FXML
    private void actVerVariaciones(ActionEvent event) {
    }

    @FXML
    private void actVerRequisitos(ActionEvent event) {
    }

    @FXML
    private void actCancelar(ActionEvent event) {
    }

    @FXML
    private void actGuardar(ActionEvent event) {
    }
    
}
