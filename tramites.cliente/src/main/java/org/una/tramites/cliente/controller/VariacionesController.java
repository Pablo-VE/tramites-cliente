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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import org.una.tramites.cliente.dto.VariacionDTO;
import org.una.tramites.cliente.util.AppContext;

/**
 * FXML Controller class
 *
 * @author Pablo-VE
 */
public class VariacionesController implements Initializable {

    @FXML
    private AnchorPane apVariaciones;
    @FXML
    private TableView<VariacionDTO> tvVariaciones;
    @FXML
    private Button btnAgregarVariacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tvVariaciones.setRowFactory(tv -> {
            TableRow<VariacionDTO> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount()==1){
                    VariacionDTO var = row.getItem();
		    AppContext.getInstance().set("VariacionSeleccionada", var);
                }
            });
            return row;
        });

    }    
    
}
