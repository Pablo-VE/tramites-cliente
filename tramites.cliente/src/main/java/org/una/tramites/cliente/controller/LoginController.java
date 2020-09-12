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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.una.tramites.cliente.service.UsuarioService;
import org.una.tramites.cliente.util.Respuesta;
import proyectotitan.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author Pablo-VE
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    
    UsuarioService usuService = new UsuarioService();
    @FXML
    private Button btningresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actIngresar(ActionEvent event) {
        if(validarCampos()){
            Respuesta res = usuService.LogIn(txtUsuario.getText(), txtContrasena.getText());
            if(res.getEstado()){
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Ingreso de usuario", "Has ingresado correctamente");
            }else{
                Mensaje.showAndWait(Alert.AlertType.ERROR, "Ingreso de usuario", "Ha surgido un error, intenta más tarde");
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Ingreso de usuario", "Faltan datos por ingresar");
        }
    }
    
    
    public boolean validarCampos(){
        if(txtContrasena.getText().isEmpty() || txtUsuario.getText().isEmpty()){
            return false;
        }
        return true;
    }
}
