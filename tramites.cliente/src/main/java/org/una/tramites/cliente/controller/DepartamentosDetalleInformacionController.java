/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.DepartamentoDTO;
import org.una.tramites.cliente.service.DepartamentoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Respuesta;
import org.una.tramites.cliente.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class DepartamentosDetalleInformacionController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private Label lblFechaCreacion;
    @FXML
    private Label lblFechaModificacion;
    @FXML
    private Button btnGuardar;
    
    private String modalidad;
    
    DepartamentoService depService = new DepartamentoService();
    DepartamentoDTO departamento = new DepartamentoDTO();
    @FXML
    private Button btnAtras;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setDisable(true);
        
        modalidad = (String) AppContext.getInstance().get("ModalidadDepartamentos");
       
        if(modalidad.equals("Agregar")){
            txtId.setVisible(false);     
        }else{
            if(modalidad.equals("Ver")){
                txtNombre.setDisable(true);
                cmbEstado.setDisable(true);
            }
        }
        
        ArrayList<String> estados = new ArrayList<String>();
        estados.add("Activo");
        estados.add("Inactivo");
        cmbEstado.getItems().clear();
        cmbEstado.getItems().addAll(estados);
        
        
        if(modalidad.equals("Editar")||modalidad.equals("Ver")){
            if(modalidad.equals("Ver")){
                departamento = (DepartamentoDTO) AppContext.getInstance().get("DepartamentoVer");
            }else{
                if(modalidad.equals("Editar")){
                    departamento = (DepartamentoDTO) AppContext.getInstance().get("DepartamentoEditar");
                }
            }
            txtId.setText(String.valueOf(departamento.getId()));
            txtNombre.setText(departamento.getNombre());
            if(departamento.getEstado()){
                cmbEstado.setValue("Activo");
            }else{
                cmbEstado.setValue("Inactivo");
            }
            lblFechaCreacion.setText(String.valueOf(departamento.getFechaRegistro()));
            lblFechaModificacion.setText(String.valueOf(departamento.getFechaModificacion()));
            
        }
        // TODO
    }    

    public boolean validar(){
        if(txtNombre.getText().isEmpty()){
            return false;
        }
        if(cmbEstado.getValue()==null){
            return false;
        }
        return true;
    }
    
    
    @FXML
    private void actGuardar(ActionEvent event) {
        
        if(validar()){
            if(modalidad.equals("Agregar")){
                DepartamentoDTO nuevoDepartamento = new DepartamentoDTO();
                nuevoDepartamento.setNombre(txtNombre.getText());
                nuevoDepartamento.setEstado(estado);
                
                Respuesta res = depService.guardarDepartamento(nuevoDepartamento);
                if(res.getEstado()){
                    Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Registro de departamento", "Se agrego el departamento correctamente");
                    irDepartamentos();
                }else{
                     Mensaje.showAndWait(Alert.AlertType.ERROR, "Registro de departamento", res.getMensaje());
                }
            }else{
                if(modalidad.equals("Editar")){
                    departamento.setNombre(txtNombre.getText());
                    departamento.setEstado(estado);
                    Date date= new Date();
                    departamento.setFechaModificacion(date);
                    Respuesta res = depService.modificarDepartamento(departamento.getId(), departamento);
                    if(res.getEstado()){
                        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Edicion de departamento", "Se edito el departamento correctamente");
                        irDepartamentos();
                    }else{
                         Mensaje.showAndWait(Alert.AlertType.ERROR, "Edicion de departamento", res.getMensaje());
                    }
                } 
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de departamento", "Faltan datos por ingresar");
        }
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
    
    
    public void irDepartamentos() {
        try{
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
            Parent root = FXMLLoader.load(App.class.getResource("departamentos" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }catch(Exception ex){
            
        }
    }

    @FXML
    private void actAtras(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("ATRAS");
            alert.setContentText("¿Esta seguro de que desea regresar a la vista anterior?");
            Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    irDepartamentos();
                }
    }
}
