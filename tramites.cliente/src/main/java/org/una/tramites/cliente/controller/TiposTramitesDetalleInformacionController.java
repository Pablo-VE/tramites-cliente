/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.DepartamentoDTO;
import org.una.tramites.cliente.dto.TramiteTipoDTO;
import org.una.tramites.cliente.service.DepartamentoService;
import org.una.tramites.cliente.service.TramiteTipoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class TiposTramitesDetalleInformacionController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private ComboBox cmbEstado;
    @FXML
    private ComboBox<String> cmbDepartamento;
    @FXML
    private Label lblFechaCreacion;
    @FXML
    private Label lblFechaModificacion;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    
    private String modalidad;

    /**
     * Initializes the controller class.
     */
    TramiteTipoService tramService = new TramiteTipoService();
    TramiteTipoDTO tramiteTipoEditar = new TramiteTipoDTO();
    DepartamentoService depService = new DepartamentoService();
    DepartamentoDTO departamento = new DepartamentoDTO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modalidad = (String) AppContext.getInstance().get("ModalidadTramitesTipos");
        
        txtId.setDisable(true);
        llenarComboBox();
        if(!modalidad.equals("Ver")){
            btnEliminar.setVisible(false);
        }
        if(modalidad.equals("Agregar")){
            txtId.setVisible(false);
        }else{
            if(modalidad.equals("Ver")){
                txtId.setVisible(true);
                txtId.setDisable(true);
                txtDescripcion.setDisable(true);
                cmbEstado.setDisable(true);
                cmbDepartamento.setDisable(true);
                btnGuardar.setVisible(false);
            }
        }
        
        ArrayList<String> estados = new ArrayList<String>();
        estados.add("Activo");
        estados.add("Inactivo");
        cmbEstado.getItems().clear();
        cmbEstado.getItems().addAll(estados);
        
        
        if(modalidad.equals("Editar") || modalidad.equals("Ver")){
            if(modalidad.equals("Editar")){
                tramiteTipoEditar = (TramiteTipoDTO) AppContext.getInstance().get("TramiteTipoEditar");
            }else{
                if(modalidad.equals("Ver")){
                    tramiteTipoEditar = (TramiteTipoDTO) AppContext.getInstance().get("TramiteTipoVer");
                }
            }
            
            txtId.setText(String.valueOf(tramiteTipoEditar.getId()));
            txtDescripcion.setText(tramiteTipoEditar.getDescripcion());
            
            if(tramiteTipoEditar.isEstado()){
                cmbEstado.setValue("Activo");
            }else{
                cmbEstado.setValue("Inactivo");
            }
            cmbDepartamento.setValue(tramiteTipoEditar.getDepartamento().getNombre());
            lblFechaCreacion.setText(String.valueOf(tramiteTipoEditar.getFechaRegistro()));
            lblFechaModificacion.setText(String.valueOf(tramiteTipoEditar.getFechaModificacion()));
            
        }
        // TODO
    }    

    private boolean estado;
    @FXML
    private void actEstado(ActionEvent event) {
        if(cmbEstado.getValue().equals("Activo")){
            estado=true;
        }else if(cmbEstado.getValue().equals("Inactivo")){
            estado=false;
        }
        
    }
    @FXML
    private void actDepartamento(ActionEvent event) {
        
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("ATRÁS");
        alert.setContentText("¿Está seguro de que desea regresar a la vista anterior?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            irTiposTramites();
        }
    }

    @FXML
    private void actGuardar(ActionEvent event) {
        if(validar()){
            if(modalidad.equals("Agregar")){
                
                TramiteTipoDTO nuevoTramiteTipo = new TramiteTipoDTO();
                nuevoTramiteTipo.setDescripcion(txtDescripcion.getText());
                nuevoTramiteTipo.setEstado(estado);
                nuevoTramiteTipo.setDepartamento(obtenerDepartamento());
                
                
                
                Respuesta res = tramService.guardar(nuevoTramiteTipo);
                if(res.getEstado()){
                    Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Registro de Tipo de Tramite", "Se agrego el tipo de tramite correctamente");
                    irTiposTramites();
                }else{
                     Mensaje.showAndWait(Alert.AlertType.ERROR, "Registro de tipo de tramite", res.getMensaje());
                }
            }else{
                if(modalidad.equals("Editar")){
                    tramiteTipoEditar.setDescripcion(txtDescripcion.getText());
                    tramiteTipoEditar.setEstado(estado);
                    tramiteTipoEditar.setDepartamento(obtenerDepartamento());
                    
                    Date date= new Date();
                    tramiteTipoEditar.setFechaModificacion(date);
                    Respuesta res = tramService.modificar(tramiteTipoEditar.getId(), tramiteTipoEditar);
                    if(res.getEstado()){
                        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Edicion de tipo de tramite", "Se edito el tipo de tramite correctamente");
                        irTiposTramites();
                    }else{
                         Mensaje.showAndWait(Alert.AlertType.ERROR, "Edicion de tipo de tramite", res.getMensaje());
                    }
                }
                
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de tipo de tramite", "Faltan datos por ingresar");
        }
    }
    
    public void irTiposTramites() {
        try{
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
            Parent root = FXMLLoader.load(App.class.getResource("tiposTramites" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }catch(Exception ex){
            
        }
    }
    
    public boolean validar(){
        if(txtDescripcion.getText().isEmpty()){
            return false;
        }
        if(cmbEstado.getValue()==null){
            return false;
        }
        if(cmbDepartamento.getValue()==null){
            return false;
        }
        return true;
    }
    
    public DepartamentoDTO obtenerDepartamento(){
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getDepartamentoByNombre(cmbDepartamento.getValue().toString());
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        
        departamento = departamento(departamentos.get(0).getId());
        return departamento;
    }
    public DepartamentoDTO departamento(Long id){
        Respuesta res =depService.getById(id);
        return (DepartamentoDTO) res.getResultado("Departamento");
    }
    public void llenarComboBox(){
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getAll();
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        ArrayList<String> deps = new ArrayList<String>();
        departamentos.forEach((d)->
            deps.add(d.getNombre())
        );
        cmbDepartamento.getItems().addAll(deps);
    }

    @FXML
    private void actEliminar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Está seguro que desea eliminar este elemento?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try{
                tramService.delete(tramiteTipoEditar.getId());
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Eliminar Trámite", "Se ha eliminado el trámite exitosamente");
                irTiposTramites();
            }catch(Exception e){
            }
        }
        
    }
    
}
