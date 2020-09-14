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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class UsuariosPrincipalController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView tbUsuarios;
    @FXML
    private ComboBox<String> cmbTipoBusqueda;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnVer;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;

   

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
    private void actVolver(ActionEvent event) {
    }

    @FXML
    private void actVer(ActionEvent event) {
    }

    @FXML
    private void actAgregar(ActionEvent event) {
    }

    @FXML
    private void actModificar(ActionEvent event) {
    }

    
    
}
