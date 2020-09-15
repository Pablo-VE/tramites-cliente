/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.service.UsuarioService;
import org.una.tramites.cliente.util.Respuesta;
import org.una.tramites.cliente.util.Mensaje;

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
    private Button btnIngresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    txtUsuario.setText("lujepa2");
    txtContrasena.setText("Una2020");

// TODO
    }    

    @FXML
    private void actIngresar(ActionEvent event) throws IOException {
        if(validarCampos()){
            Respuesta res = usuService.LogIn(txtUsuario.getText(), txtContrasena.getText());
            if(res.getEstado()){
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Ingreso de usuario", "Has ingresado correctamente");
                Parent root = FXMLLoader.load(App.class.getResource("dashboard" + ".fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                
                Stage stage2 = (Stage) btnIngresar.getScene().getWindow();
                stage2.close();
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
