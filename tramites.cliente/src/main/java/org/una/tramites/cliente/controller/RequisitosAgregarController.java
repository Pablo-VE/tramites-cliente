/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
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
import org.una.tramites.cliente.dto.RequisitoDTO;
import org.una.tramites.cliente.service.RequisitoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class RequisitosAgregarController implements Initializable {

    @FXML
    private TextField txtDescripcion;
    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblFecha2;
    @FXML
    private Button btnEliminar;
    @FXML
    private Label lblId;
    @FXML
    private Label lblId2;
    private String modalidad;
    RequisitoDTO requisitoEditar = new RequisitoDTO();
    RequisitoService reqService = new RequisitoService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbEstado.getItems().add("Activo");
        cmbEstado.getItems().add("Inactivo");
        modalidad = (String) AppContext.getInstance().get("ModalidadRequisitos");
        if(!modalidad.equals("Ver")){
            btnEliminar.setVisible(false);
            lblId.setVisible(false);
            lblId2.setVisible(false);
            lblFecha.setVisible(false);
            lblFecha2.setVisible(false);
        }
        if(modalidad.equals("Modificar")){
            requisitoEditar = (RequisitoDTO) AppContext.getInstance().get("RequisitoEditar");
            txtDescripcion.setText(requisitoEditar.getDescripcion());
            if(requisitoEditar.isEstado()){
                cmbEstado.setValue("Activo");
            }else{
                cmbEstado.setValue("Inactivo");
            }
            lblFecha2.setText(String.valueOf(requisitoEditar.getFechaRegistro()));
            
        }else{
            if(modalidad.equals("Agregar")){
                
            }else{
                if(modalidad.equals("Ver")){
                    requisitoEditar = (RequisitoDTO) AppContext.getInstance().get("RequisitoVer");
                    lblId2.setText(String.valueOf(requisitoEditar.getId()));
                    txtDescripcion.setText(requisitoEditar.getDescripcion());
                    
                    if(requisitoEditar.isEstado()){
                        cmbEstado.setValue("Activo");
                    }else{
                        cmbEstado.setValue("Inactivo");
                    }
                    cmbEstado.setDisable(true);
                    lblFecha2.setText(String.valueOf(requisitoEditar.getFechaRegistro())); 
                    btnGuardar.setVisible(false);
                }
            }
        }
    }    

    @FXML
    private void actGuardar(ActionEvent event) throws IOException {
        if(validar()){
            if(modalidad.equals("Agregar")){
                RequisitoDTO nuevoRequisito = new RequisitoDTO();
                nuevoRequisito.setDescripcion(txtDescripcion.getText());
                nuevoRequisito.setEstado(estado);
                
                Respuesta res = reqService.guardar(nuevoRequisito);
                if(res.getEstado()){
                    Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Registro de requisito", "Se agrego el requisito correctamente");
                    irRequisitos();
                }else{
                     Mensaje.showAndWait(Alert.AlertType.ERROR, "Registro de departamento", res.getMensaje());
                }
            }else{
                if(modalidad.equals("Editar")){
                    requisitoEditar.setDescripcion(txtDescripcion.getText());
                    requisitoEditar.setEstado(estado);
                    Date date= new Date();
                    Respuesta res = reqService.modificar(requisitoEditar.getId(), requisitoEditar);
                    if(res.getEstado()){
                        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Edicion de requisito", "Se edito el requisito correctamente");
                        irRequisitos();
                    }else{
                         Mensaje.showAndWait(Alert.AlertType.ERROR, "Edicion de requisito", res.getMensaje());
                    }
                } 
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de requisito", "Faltan datos por ingresar");
        }
    }

    @FXML
    private void actCancelar(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("ATRAS");
        alert.setContentText("¿Esta seguro de que desea regresar a la vista anterior?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            irRequisitos();
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
                reqService.delete(requisitoEditar.getId());
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Eliminar Requisito", "Se ha eliminado el requisito exitosamente");
                irRequisitos();
            }catch(Exception e){
            }
        }
    }
    
    private void irRequisitos() throws IOException{
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("ContenedorDisenoTramite");
            Parent root = FXMLLoader.load(App.class.getResource("requisitosVer" + ".fxml")); //Revisar si la direccion esta bien
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
    }
    public boolean validar(){
        if(txtDescripcion.getText().isEmpty()){
            return false;
        }
        if(cmbEstado.getValue()==null){
            return false;
        }
        return true;
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
    
}
