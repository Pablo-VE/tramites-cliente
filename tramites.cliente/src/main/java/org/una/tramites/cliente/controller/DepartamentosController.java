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
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class DepartamentosController implements Initializable {

    @FXML
    private TextField textBuscar;
    @FXML
    private Button botonBuscar;
    @FXML
    private Button botonBorrar;
    @FXML
    private TableView<?> tablaDepartamentos;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
