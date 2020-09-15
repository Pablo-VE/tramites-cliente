/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.service.UsuarioService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Respuesta;
import org.una.tramites.cliente.util.Mensaje;

/**
 *
 * @author Jeffry
 */
public class UsuariosContrasenaController implements Initializable {

    @FXML
    private Button btnVerActual;
    @FXML
    private Button btnVerNueva;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblContrasena1;
    @FXML
    private Label lblContrasena2;
    @FXML
    private PasswordField txtContrasena1;
    @FXML
    private PasswordField txtContrasena2;

    
    UsuarioService usuService = new UsuarioService();
    @FXML
    private void actVerActual(MouseEvent event) {
        
    }

    @FXML
    private void actVerNueva(MouseEvent event) {
    }

    @FXML
    private void actVerActual(ActionEvent event) {
    }

    @FXML
    private void actVerNueva(ActionEvent event) {
    }

    @FXML
    private void actGuardar(ActionEvent event) {
        if(validar()){
            if(modalidad.equals("Agregar")){
                usuario.setPasswordEncriptado(txtContrasena2.getText());
                AppContext.getInstance().set("UsuarioNuevo", usuario);
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Contraseña de usuario nuevo", "Se le ha establecido una contraseña al usuario nuevo");
            }else{
                if(modalidad.equals("Modificar")){
                    usuario.setPasswordEncriptado(txtContrasena2.getText());
                    Date date = new Date();
                    usuario.setFechaModificacion(date);
                    Respuesta res = usuService.cambiarContrasena(usuario.getId(), usuario);
                    if(res.getEstado()){
                        AppContext.getInstance().set("UsuarioEnCuestion", usuario);
                        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Informacion de usuario", "Se ha guardado la nueva contrasena con exito");
                    }else{
                        Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de usuario", "Ha surgido un error por favor intentar mas tarde");
                    }
                }
            }
        }
    }

    @FXML
    private void actCancelar(ActionEvent event) {
    }

    
    private String modalidad;
    UsuarioDTO usuario=new UsuarioDTO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modalidad = (String) AppContext.getInstance().get("ModalidadUsuarios");
        if(modalidad.equals("Agregar")){
            lblContrasena1.setText("Contraseña:");
            lblContrasena2.setText("Confirmar contraseña:");
            usuario = new UsuarioDTO();
            usuario= (UsuarioDTO) AppContext.getInstance().get("UsuarioNuevo");
        }else{
            usuario = (UsuarioDTO) AppContext.getInstance().get("UsuarioEnCuestion");
            lblContrasena1.setText("Contraseña actual:");
            lblContrasena2.setText("Contraseña nueva:");
          //  txtContrasena1.setText(usuario.getPasswordEncriptado());
        }
           
    }
    
    
    
    public boolean validar(){
        if(txtContrasena1.getText().isEmpty() || txtContrasena2.getText().isEmpty()){
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Registro de Contraseña", "Faltan datos por ingresar");
            return false;
        }else{
            if(modalidad.equals("Agregar")){
                if(!txtContrasena1.getText().equals(txtContrasena2.getText())){
                    Mensaje.showAndWait(Alert.AlertType.ERROR, "Registro de Contraseña", "Contraseñas no coinciden");
                    return false;
                }
            }if(modalidad.equals("Modificar")){
                Respuesta res = usuService.cambioContrasena(usuario.getCedula(), txtContrasena1.getText());
                if(!res.getEstado()){
                    Mensaje.showAndWait(Alert.AlertType.ERROR, "Registro de Contraseña", "Contraseña actual no coincide");
                    return false;
                }
            }
        }
        return true;
    }
    
}
