package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.MeuAlert;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField tfConfirmarSenha;

    @FXML
    private PasswordField tfSenha;

    @FXML
    private TextField tfUsuario;

    @FXML
    void onEntrar(ActionEvent event) throws IOException {
        var usuario = tfUsuario.getText();
        var senha = tfSenha.getText();
        var senhaConfirmacao = tfConfirmarSenha.getText();

        if (senha.equals(senhaConfirmacao)) {

            if (usuario.equals("admin") && senha.equals("123")) {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/views/Crud.fxml"));
                Scene scene = new Scene(root);

                stage.setResizable(false);
                stage.setTitle("Gestão Usuários");

                // Ao fechar a tela de usuários
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent e) {
                        Platform.exit();
                        System.exit(0);
                    }
                });

                stage.setScene(scene);
                stage.show();

                // Ocultar a tela de login
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                MeuAlert.mostrar("Erro de Login", "Usuário ou Senha incorretos", AlertType.ERROR);
            }

        } else {
            MeuAlert.mostrar("Senhas não coicidem", "As senhas introduzidas não coincidem", AlertType.ERROR);
        }
    }

    @FXML
    void onExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert tfConfirmarSenha != null
                : "fx:id=\"tfConfirmarSenha\" was not injected: check your FXML file 'Login.fxml'.";
        assert tfSenha != null : "fx:id=\"tfSenha\" was not injected: check your FXML file 'Login.fxml'.";
        assert tfUsuario != null : "fx:id=\"tfUsuario\" was not injected: check your FXML file 'Login.fxml'.";

    }

}
