/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.TramiteTipoDTO;
import org.una.tramites.cliente.dto.VariacionDTO;
import org.una.tramites.cliente.service.TramiteTipoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class DisenoTramitesController implements Initializable {

    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnVariaciones;
    @FXML
    private Button btnRequisitos;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private ComboBox<TramiteTipoDTO> cbxTramite;
    @FXML
    private StackPane spContenedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppContext.getInstance().set("ContenedorDisenoTramite", spContenedor);
        cargarTramitesTiposTodos();
        // TODO
    }    

    TramiteTipoService tramService = new TramiteTipoService();
    public void cargarTramitesTiposTodos(){
        ArrayList<TramiteTipoDTO> tramitesTipos = new ArrayList<TramiteTipoDTO>();
        Respuesta res = tramService.getAll();
        tramitesTipos=(ArrayList<TramiteTipoDTO>) res.getResultado("TramitesTipos");
        llenarCBX(tramitesTipos);

    }
    
    TramiteTipoDTO tramiteTipo = new TramiteTipoDTO();
    
    public void llenarCBX(ArrayList<TramiteTipoDTO> tramites){
        cbxTramite.getItems().clear();
        cbxTramite.getItems().addAll(tramites);
        
    }
    
    @FXML
    private void actBorrar(ActionEvent event) {
    }

    @FXML
    private void actVerVariaciones(ActionEvent event) throws IOException {
        if(selecciono){
            System.out.println(tramiteTipo.getId());
            Parent root = FXMLLoader.load(App.class.getResource("variaciones" + ".fxml"));
            spContenedor.getChildren().clear();
            spContenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Variaciones", "Por favor seleccione el tramite");
        }
    }

    @FXML
    private void actVerRequisitos(ActionEvent event) throws IOException {

       
       VariacionDTO va = null;
       va = (VariacionDTO) AppContext.getInstance().get("VariacionSeleccionada");
       if(va!=null){
           System.out.println(va);
        if(selecciono){
            System.out.println(tramiteTipo.getId());
            Parent root = FXMLLoader.load(App.class.getResource("requisitosVer" + ".fxml"));
            spContenedor.getChildren().clear();
            spContenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Requisitos", "Por favor seleccione una variacion");
        }
    
    }
    }

    @FXML
    private void actCancelar(ActionEvent event) {
    }

    @FXML
    private void actGuardar(ActionEvent event) {
    }

    
    boolean selecciono=false;
    @FXML
    private void actTramite(ActionEvent event) {
        selecciono=true;
        AppContext.getInstance().set("tramiteTipo", cbxTramite.getValue());
        tramiteTipo=(TramiteTipoDTO) AppContext.getInstance().get("tramiteTipo");
    }

    
}
