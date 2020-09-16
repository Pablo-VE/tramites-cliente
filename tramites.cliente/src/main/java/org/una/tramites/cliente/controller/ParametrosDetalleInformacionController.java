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
import org.una.tramites.cliente.dto.ParametrosGeneralesDTO;
import org.una.tramites.cliente.service.ParametrosGeneralesService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class ParametrosDetalleInformacionController implements Initializable {

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
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;

    
    ParametrosGeneralesDTO permiso = new ParametrosGeneralesDTO();
    private String modalidad;
    ParametrosGeneralesService perService = new ParametrosGeneralesService();
    ParametrosGeneralesDTO permisoEditar = new ParametrosGeneralesDTO();
    @FXML
    private TextField txtValor;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Label LabelId;
    @FXML
    private Button btnEliminar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtId.setDisable(true);
        Date date = new Date();
        lblFechaCreacion.setText(date.toString());
        
        modalidad = (String) AppContext.getInstance().get("ModalidadParametros");
        
        ArrayList<String> estados = new ArrayList<String>();
        estados.add("Activo");
        estados.add("Inactivo");
        cmbEstado.getItems().clear();
        cmbEstado.getItems().addAll(estados);
        if(!modalidad.equals("Ver")){
           btnEliminar.setVisible(false);
        }

        
        if(modalidad.equals("Modificar")){
            permisoEditar = (ParametrosGeneralesDTO) AppContext.getInstance().get("ParametroEnCuestion");
            txtId.setText(String.valueOf(permisoEditar.getId()));
            txtNombre.setText(permisoEditar.getNombre());
            txtValor.setText(permisoEditar.getValor());
            txtDescripcion.setText(permisoEditar.getDescripcion());
            if(permisoEditar.getEstado()){
                cmbEstado.setValue("Activo");
            }else{
                cmbEstado.setValue("Inactivo");
            }
            lblFechaCreacion.setText(String.valueOf(permisoEditar.getFechaRegistro()));
            lblFechaModificacion.setText(String.valueOf(permisoEditar.getFechaModificacion()));
        }else{
            if(modalidad.equals("Agregar")){
                txtId.setVisible(false);
                LabelId.setVisible(false);
                lblFechaModificacion.setVisible(false);
                //labelModificacion.setVisible(false);
            }else{
                if(modalidad.equals("Ver")){
                    permisoEditar = (ParametrosGeneralesDTO) AppContext.getInstance().get("ParametroEnCuestion");
                    txtId.setText(String.valueOf(permisoEditar.getId()));
                    txtId.setDisable(true);
                    txtNombre.setText(permisoEditar.getNombre());
                    txtNombre.setDisable(true);
                    txtValor.setText(permisoEditar.getValor());
                    txtValor.setDisable(true);
                    txtDescripcion.setText(permisoEditar.getDescripcion());
                    txtDescripcion.setDisable(true);
                    if(permisoEditar.getEstado()){
                        cmbEstado.setValue("Activo");
                    }else{
                        cmbEstado.setValue("Inactivo");
                    }
                    cmbEstado.setDisable(true);
                    lblFechaCreacion.setText(String.valueOf(permisoEditar.getFechaRegistro()));
                    lblFechaModificacion.setText(String.valueOf(permisoEditar.getFechaModificacion())); 
                    btnGuardar.setVisible(false);
                }
            }
        }         
// TODO
    }    

    @FXML
    private void actAtras(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("ATRAS");
        alert.setContentText("¿Esta seguro de que desea regresar a la vista anterior?");
        Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                irPermisos();
            }
    }

    public boolean validar(){
        if(cmbEstado.getValue()==null){
            return false;
        }
        return true;
    }
    
    @FXML
    private void actGuardar(ActionEvent event) {
         if(validar()){
            if(modalidad.equals("Agregar")){
                ParametrosGeneralesDTO nuevoParametro = new ParametrosGeneralesDTO();
                
                nuevoParametro.setNombre(txtNombre.getText());
                nuevoParametro.setValor(txtValor.getText());
                nuevoParametro.setDescripcion(txtDescripcion.getText());
                nuevoParametro.setEstado(estado);
                
                Respuesta res = perService.guardar(nuevoParametro);
                if(res.getEstado()){
                    Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Registro de parametros", "Se agrego el parametro correctamente");
                    irPermisos();
                }else{
                     Mensaje.showAndWait(Alert.AlertType.ERROR, "Registro de parametro", res.getMensaje());
                }
            }else{
                if(modalidad.equals("Modificar")){
                    permisoEditar.setNombre(txtNombre.getText());
                    permisoEditar.setValor(txtValor.getText());
                    permisoEditar.setDescripcion(txtDescripcion.getText());
                    permisoEditar.setEstado(estado);
                    Date date= new Date();
                    permisoEditar.setFechaModificacion(date);
                    Respuesta res = perService.modificar(permisoEditar.getId(), permisoEditar);
                    if(res.getEstado()){
                        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Edicion de parametro", "Se edito el parametro correctamente");
                        irPermisos();
                    }else{
                         Mensaje.showAndWait(Alert.AlertType.ERROR, "Edicion de parametro", res.getMensaje());
                    }
                } 
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de parametro", "Faltan datos por ingresar");
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
    
     public void irPermisos() {
        try{
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
            Parent root = FXMLLoader.load(App.class.getResource("parametros" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }catch(Exception ex){
            
        }
    }

    @FXML
    private void actEliminar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Está seguro que desea eliminar este elemento?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try{
                perService.delete(permisoEditar.getId());
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Eliminar Parámetro", "Se ha eliminado el parámetro exitosamente");
                irPermisos();
            }catch(Exception e){
            }
        }
    }


}
