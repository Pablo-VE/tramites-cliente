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
import org.una.tramites.cliente.dto.PermisoDTO;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class UsuariosAutorizacionesController implements Initializable {

    @FXML
    private TableView<PermisoDTO> tbAutorizaaciones;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnLimpiar;
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
    private void actCancelar(ActionEvent event) {
    }

    @FXML
    private void actLimpiar(ActionEvent event) {
    }

    @FXML
    private void actGuardar(ActionEvent event) {
    }
    
}
