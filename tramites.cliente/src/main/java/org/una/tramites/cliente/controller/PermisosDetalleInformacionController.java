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
import static javassist.CtMethod.ConstParameter.string;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.DepartamentoDTO;
import org.una.tramites.cliente.dto.PermisoDTO;
import org.una.tramites.cliente.service.PermisoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;


/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class PermisosDetalleInformacionController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private Label lblFechaCreacion;
    @FXML
    private Label lblFechaModificacion;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Button btnAtras;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Label LabelId;
    
    PermisoDTO permiso = new PermisoDTO();
    private String modalidad;
    PermisoService perService = new PermisoService();
    PermisoDTO permisoEditar = new PermisoDTO();
    @FXML
    private Label labelModificacion;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setDisable(true);
        Date date = new Date();
        lblFechaCreacion.setText(date.toString());
        
        modalidad = (String) AppContext.getInstance().get("ModalidadPermisos");
        
        ArrayList<String> estados = new ArrayList<String>();
        estados.add("Activo");
        estados.add("Inactivo");
        cmbEstado.getItems().clear();
        cmbEstado.getItems().addAll(estados);
        
        
        if(modalidad.equals("Modificar")){
            permisoEditar = (PermisoDTO) AppContext.getInstance().get("PermisoEnCuestion");
            txtId.setText(String.valueOf(permisoEditar.getId()));
            txtCodigo.setText(permisoEditar.getCodigo());
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
                labelModificacion.setVisible(false);
            }else{
                if(modalidad.equals("Ver")){
                    
                }
            }
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
                PermisoDTO nuevoPermiso = new PermisoDTO();
                
                nuevoPermiso.setCodigo(txtCodigo.getText());
                nuevoPermiso.setDescripcion(txtDescripcion.getText());
                nuevoPermiso.setEstado(estado);
                
                Respuesta res = perService.guardar(nuevoPermiso);
                if(res.getEstado()){
                    Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Registro de permisos", "Se agrego el permiso correctamente");
                    irPermisos();
                }else{
                     Mensaje.showAndWait(Alert.AlertType.ERROR, "Registro de permiso", res.getMensaje());
                }
            }else{
                if(modalidad.equals("Modificar")){
                    permisoEditar.setCodigo(txtCodigo.getText());
                    permisoEditar.setDescripcion(txtDescripcion.getText());
                    permisoEditar.setEstado(estado);
                    Date date= new Date();
                    permisoEditar.setFechaModificacion(date);
                    Respuesta res = perService.modificar(permisoEditar.getId(), permisoEditar);
                    if(res.getEstado()){
                        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Edicion de permisos", "Se edito el permiso correctamente");
                        irPermisos();
                    }else{
                         Mensaje.showAndWait(Alert.AlertType.ERROR, "Edicion de permiso", res.getMensaje());
                    }
                } 
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de permisos", "Faltan datos por ingresar");
        }
    }
    
    public void irPermisos() {
        try{
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
            Parent root = FXMLLoader.load(App.class.getResource("permisos" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }catch(Exception ex){
            
        }
    }
}
