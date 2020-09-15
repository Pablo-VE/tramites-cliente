/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.PermisoOtorgadoDTO;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.service.PermisoOtorgadoService;
import org.una.tramites.cliente.service.UsuarioService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Respuesta;
import org.una.tramites.cliente.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class UsuariosController implements Initializable {

    @FXML
    private Button btnInformacion;
    @FXML
    private Button btnAutorizaciones;
    @FXML
    private Button btnContrasena;
    @FXML
    private StackPane contenedor;
    @FXML
    public Label lblUsuario;
    @FXML
    public Label lblCedula;

    /**
     * Initializes the controller class.
     */
    private String modalidad;
    UsuarioDTO usuario = new UsuarioDTO();
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnGuardar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblUsuario.setText("");
        lblCedula.setText("");
        modalidad = (String) AppContext.getInstance().get("ModalidadUsuarios");
        btnGuardar.setVisible(false);
        btnGuardar.setDisable(true);
        if(modalidad.equals("Ver")||modalidad.equals("Modificar")){
            usuario = (UsuarioDTO) AppContext.getInstance().get("UsuarioEnCuestion");
            lblUsuario.setText(usuario.getNombreCompleto());
            lblCedula.setText(usuario.getCedula());
        }else{
            if(modalidad.equals("Agregar")){
                usuario=new UsuarioDTO();
                lblUsuario.setText("usuario nuevo");
                lblCedula.setText("usuario nuevo");
                AppContext.getInstance().set("UsuarioNuevo", usuario);
                btnGuardar.setVisible(true);
                btnGuardar.setDisable(false);
                AppContext.getInstance().set("ConUsuarios", this);
            }
            
        }
    }    


    @FXML
    private void actInformacion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("usuariosInformacion" + ".fxml"));
        contenedor.getChildren().clear();
        contenedor.getChildren().add(root);
    }

    @FXML
    private void actAutorizaciones(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("usuariosAutorizaciones" + ".fxml"));
        contenedor.getChildren().clear();
        contenedor.getChildren().add(root);
    }

    @FXML
    private void actContrasena(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("usuariosContrasena" + ".fxml"));
        contenedor.getChildren().clear();
        contenedor.getChildren().add(root);
    }

    @FXML
    private void actVolver(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        Parent root = FXMLLoader.load(App.class.getResource("usuariosPrincipal" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

    PermisoOtorgadoService perOtorService = new PermisoOtorgadoService();
    private UsuarioService usuarioService= new UsuarioService();
    @FXML
    private void actGuardar(ActionEvent event) {
        usuario = (UsuarioDTO) AppContext.getInstance().get("UsuarioNuevo");
        if(usuario.getCedula()!=null && usuario.getNombreCompleto()!=null &&usuario.getPasswordEncriptado()!=null){
            List<PermisoOtorgadoDTO> permisosOtorgar = new ArrayList<>();
            if(usuario.getPermisosOtorgados()!= null){
                permisosOtorgar = usuario.getPermisosOtorgados();
                usuario.setPermisosOtorgados(new ArrayList<>());
            }
            Respuesta res = usuarioService.guardarUsuario(usuario);
            UsuarioDTO usu = (UsuarioDTO) res.getResultado("Usuario");
            if(!permisosOtorgar.isEmpty()){
                for(int i=0; i<permisosOtorgar.size(); i++){
                    permisosOtorgar.get(i).setUsuario(usu);
                    perOtorService.guardar(permisosOtorgar.get(i));
                }
            }
            
            if(res.getEstado()){
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Creacion de usuario", "Se ha creado con exito un nuevo usuario");
            }else{
                Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de usuario", "Ha surgido un error por favor intentar mas tarde");
            } 
        }
        
    }
    
}
