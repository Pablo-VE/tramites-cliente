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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.TramiteTipoDTO;
import org.una.tramites.cliente.dto.VariacionDTO;
import org.una.tramites.cliente.service.VariacionService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Pablo-VE
 */
public class VariacionesDetalleController implements Initializable {

    @FXML
    private AnchorPane apVariacionesDetalle;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<String> cbxEstado;
    
    TramiteTipoDTO tramiteTipo = new TramiteTipoDTO();
    VariacionDTO variacion = new VariacionDTO();
    VariacionService varService = new VariacionService();
    String modalidad; 
    
    @FXML
    private TextField txtDescripcion;
    
    @FXML
    private Button btnEliminar;
    @FXML
    private ComboBox<Integer> cbxGrupo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    tramiteTipo=(TramiteTipoDTO) AppContext.getInstance().get("tramiteTipo");
    
    cbxEstado.getItems().add("Activo");
        cbxEstado.getItems().add("Inactivo");
        modalidad = (String) AppContext.getInstance().get("ModalidadVariacion");
        if(!modalidad.equals("Ver")){
            btnEliminar.setVisible(false);
//            lblId.setVisible(false);
//            lblId2.setVisible(false);
//            lblFecha.setVisible(false);
//            lblFecha2.setVisible(false);
        }
        if(modalidad.equals("Modificar")){
            variacion = (VariacionDTO) AppContext.getInstance().get("VariacionEditar");
            txtDescripcion.setText(variacion.getDescripcion());
            if(variacion.getEstado()){
                cbxEstado.setValue("Activo");
            }else{
                cbxEstado.setValue("Inactivo");
            }
            //lblFecha2.setText(String.valueOf(requisitoEditar.getFechaRegistro()));
            
        }else{
            if(modalidad.equals("Agregar")){
                
            }else{
                if(modalidad.equals("Ver")){
                    variacion = (VariacionDTO) AppContext.getInstance().get("RequisitoVer");
                    //lblId2.setText(String.valueOf(variacion.getId()));
                    txtDescripcion.setText(variacion.getDescripcion());
                    
                    if(variacion.getEstado()){
                        cbxEstado.setValue("Activo");
                    }else{
                        cbxEstado.setValue("Inactivo");
                    }
                    cbxEstado.setDisable(true);
                    //lblFecha2.setText(String.valueOf(requisitoEditar.getFechaRegistro())); 
                    btnGuardar.setVisible(false);
                }
            }
        }
    // TODO
    }    
    private boolean estado;
    @FXML
    private void actEstado(ActionEvent event) {
        
        if(cbxEstado.getValue().equals("Activo")){
            estado=true;
        }else if(cbxEstado.getValue().equals("Inactivo")){
            estado=false;
        }
        
    }
 public boolean validar(){
        if(cbxEstado.getValue()==null){
            return false;
        }
        return true;
    }

    @FXML
    private void actGuardar(ActionEvent event) throws IOException {
         if(validar()){
            if(modalidad.equals("Agregar")){
                VariacionDTO nuevoPermiso = new VariacionDTO();
                
                nuevoPermiso.setDescripcion(txtDescripcion.getText());
                nuevoPermiso.setEstado(estado);
                nuevoPermiso.setGrupo(cbxGrupo.getValue());
                nuevoPermiso.setTramites(tramiteTipo);
                
                Respuesta res = varService.guardar(nuevoPermiso);
                if(res.getEstado()){
                    Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Registro de variaciones", "Se agrego la variacion correctamente");
                    irRequisitos();
                }else{
                     Mensaje.showAndWait(Alert.AlertType.ERROR, "Registro de permiso", res.getMensaje());
                }
            }else{
                if(modalidad.equals("Modificar")){
                    variacion.setGrupo(cbxGrupo.getValue());
                    variacion.setDescripcion(txtDescripcion.getText());
                    variacion.setEstado(estado);
                    
                    Respuesta res = varService.modificar(variacion.getId(), variacion);
                    if(res.getEstado()){
                        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Edicion de variacion", "Se edito la variacion correctamente");
                        irRequisitos();
                    }else{
                         Mensaje.showAndWait(Alert.AlertType.ERROR, "Edicion de variacion", res.getMensaje());
                    }
                } 
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de variacion", "Faltan datos por ingresar");
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
    private void irRequisitos() throws IOException{
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("ContenedorDisenoTramite");
        Parent root = FXMLLoader.load(App.class.getResource("variaciones" + ".fxml")); //Revisar si la direccion esta bien
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
   

    @FXML
    private void actEliminar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Está seguro que desea eliminar este elemento?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try{
                varService.delete(variacion.getId());
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Eliminar Requisito", "Se ha eliminado el requisito exitosamente");
                irRequisitos();
            }catch(Exception e){
            }
        }
    }

    @FXML
    private void actGrupo(ActionEvent event) {
    }
    
}
