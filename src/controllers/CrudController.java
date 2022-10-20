package controllers;

import custom.NumberField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import models.Usuario;
import utils.MeuAlert;

public class CrudController {

    public ObservableList<Usuario> obsUsuarios = FXCollections.observableArrayList();

    @FXML
    private Button btnApagar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnRefresh;

    @FXML
    private ChoiceBox<String> choiceBuscar;

    @FXML
    private TableColumn<Usuario, String> colBio;

    @FXML
    private TableColumn<Usuario, String> colEmail;

    @FXML
    private TableColumn<Usuario, Integer> colId;

    @FXML
    private TableColumn<Usuario, Integer> colIdade;

    @FXML
    private TableColumn<Usuario, String> colNome;

    @FXML
    private TableColumn<Usuario, String> colProfissao;

    @FXML
    private TableColumn<Usuario, String> colTelefone;

    @FXML
    private TableColumn<Usuario, String> colUsuario;

    @FXML
    private Label resultados;

    @FXML
    private Tab tabEntrada;

    @FXML
    private Tab tabSaida;

    @FXML
    private TextField tfBio;

    @FXML
    private TextField tfBuscar;

    @FXML
    private TextField tfEmail;

    @FXML
    private NumberField tfIdade;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfProfissao;

    @FXML
    private TextField tfSenha;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfUsuario;

    @FXML
    private TableView<Usuario> tvUsuarios;

    @FXML
    private TabPane tabPanelUsuario;

    @FXML
    void onApagar(ActionEvent event) {
        new Usuario().removerUsuario(1); // TODO
        MeuAlert.mostrar("Aviso", "Usuário removido com sucesso", AlertType.INFORMATION);

        _limparCampos();
        _desabilitarCampos();

        btnNovo.setDisable(false);
        btnGuardar.setDisable(true);
        btnApagar.setDisable(true);
        btnCancelar.setDisable(true);

        _refreshTableView();
    }

    @FXML
    void onBuscar(ActionEvent event) {
        obsUsuarios.clear();

        var tipoBusca = choiceBuscar.getValue();

        if (tipoBusca.equals("Id")) {
            var id = 0;
            try {
                id = Integer.parseInt(tfBuscar.getText());
            } catch (Exception e) {
                MeuAlert.mostrar("Erro", "Informe um Id válido", AlertType.WARNING);
                return;
            }

            var usuario = new Usuario().listarPorId(id);

            if (usuario != null) {
                obsUsuarios.add(usuario);

                resultados.setText("Resultados: 1");
            } else {
                resultados.setText("Resultados: 0");
            }

            return;
        }
    }

    @FXML
    void onCancelar(ActionEvent event) {
        _limparCampos();
        _habilitarCampos();

        btnNovo.setDisable(false);
        btnGuardar.setDisable(true);
        btnApagar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    @FXML
    void onContextMenuRequested(ContextMenuEvent event) {
        ContextMenu menu = new ContextMenu();
        MenuItem modificarItem = new MenuItem("Acções");
        modificarItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Usuario usuario = tvUsuarios.getSelectionModel().getSelectedItem();

                if (usuario == null) {
                    MeuAlert.mostrar("Aviso", "Selecione um usuário para atualizar", AlertType.WARNING);
                    return;
                }

                tfNome.setText(usuario.getNome());
                tfBio.setText(usuario.getBiografia());
                tfEmail.setText(usuario.getEmail());
                tfSenha.setText(usuario.getSenha());
                tfIdade.setText(String.valueOf(usuario.getIdade()));
                tfProfissao.setText(usuario.getProfissao());
                tfUsuario.setText(usuario.getNomeUsuario());
                tfTelefone.setText(usuario.getTelefone());

                _habilitarCampos();

                btnNovo.setDisable(true);
                btnGuardar.setDisable(false);
                btnApagar.setDisable(false);
                btnCancelar.setDisable(false);

                // Mudar as telas
                SingleSelectionModel<Tab> selectionModel = tabPanelUsuario.getSelectionModel();
                selectionModel.select(0);

            }
        });

        menu.getItems().add(modificarItem);
        tvUsuarios.setContextMenu(menu);
    }

    @FXML
    void onGuardar(ActionEvent event) {
        Usuario usuario = new Usuario();

        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String nomeUsuario = tfUsuario.getText();
        String biografia = tfBio.getText();
        String senha = tfSenha.getText();
        String profissao = tfProfissao.getText();
        String telefone = tfTelefone.getText();
        int idade;

        try {
            idade = Integer.parseInt(tfIdade.getText());
        } catch (Exception e) {
            MeuAlert.mostrar("Erro de Validaç±ao", "Preencha apenas números", AlertType.WARNING);
            return;
        }

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setNomeUsuario(nomeUsuario);
        usuario.setBiografia(biografia);
        usuario.setSenha(senha);
        usuario.setProfissao(profissao);
        usuario.setTelefone(telefone);
        usuario.setIdade(idade);

        if (usuario.cadastrarUsuario(usuario)) {
            _limparCampos();
            _desabilitarCampos();
            btnNovo.setDisable(false);
            btnGuardar.setDisable(true);
            btnApagar.setDisable(true);
            btnCancelar.setDisable(true);

            MeuAlert.mostrar("Aviso", "Usuário cadastrado com sucesso", AlertType.INFORMATION);

            _refreshTableView();
        }
    }

    @FXML
    void onNovo(ActionEvent event) {
        _limparCampos();
        _habilitarCampos();

        btnNovo.setDisable(true);
        btnGuardar.setDisable(false);
        btnApagar.setDisable(true);
        btnCancelar.setDisable(false);
    }

    @FXML
    void onRefresh(ActionEvent event) {
        _refreshTableView();
    }

    @FXML
    void initialize() {
        configurarTableView();
        _refreshTableView();
        _limparCampos();
        _desabilitarCampos();

        _colocarImagensBotoes();
        btnNovo.setDisable(false);
        btnGuardar.setDisable(true);
        btnApagar.setDisable(true);
        btnCancelar.setDisable(true);

        choiceBuscar.getItems().addAll("Id");

        // Default
        choiceBuscar.setValue("Id");
    }

    public void configurarTableView() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colBio.setCellValueFactory(new PropertyValueFactory<>("biografia"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
        colProfissao.setCellValueFactory(new PropertyValueFactory<>("profissao"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        tvUsuarios.setItems(obsUsuarios);
    }

    public void _refreshTableView() {
        obsUsuarios.clear();
        Usuario usuario = new Usuario();

        ObservableList<Usuario> usuarios = usuario.listarUsuarios();
        obsUsuarios.setAll(usuarios);

        int length = usuarios.size();
        resultados.setText("Resultados: " + length);
    }

    private void _limparCampos() {
        tfNome.setText("");
        tfBio.setText("");
        tfNome.setText("");
        tfEmail.setText("");
        tfIdade.setText("0");
        tfProfissao.setText("");
        tfUsuario.setText("");
        tfSenha.setText("");
        tfTelefone.setText("");
    }

    private void _desabilitarCampos() {
        tfNome.setDisable(true);
        tfBio.setDisable(true);
        tfNome.setDisable(true);
        tfEmail.setDisable(true);
        tfIdade.setDisable(true);
        tfProfissao.setDisable(true);
        tfUsuario.setDisable(true);
        tfSenha.setDisable(true);
        tfTelefone.setDisable(true);
    }

    private void _habilitarCampos() {
        tfNome.setDisable(false);
        tfBio.setDisable(false);
        tfNome.setDisable(false);
        tfEmail.setDisable(false);
        tfIdade.setDisable(false);
        tfProfissao.setDisable(false);
        tfUsuario.setDisable(false);
        tfSenha.setDisable(false);
        tfTelefone.setDisable(false);
    }

    private void _colocarImagensBotoes() {
        var linkNovo = getClass().getResource("/img/nuevo.png");
        var linkGuardar = getClass().getResource("/img/guardar.png");
        var linkApagar = getClass().getResource("/img/eliminar.png");
        var linkCancelar = getClass().getResource("/img/cancelar.png");
        var linkBuscar = getClass().getResource("/img/buscar.png");
        var linkRefresh = getClass().getResource("/img/refrescar.png");

        Image novaImg = new Image(linkNovo.toString(), 24, 24, false, true);
        Image guardarImg = new Image(linkGuardar.toString(), 24, 24, false, true);
        Image apagarImg = new Image(linkApagar.toString(), 24, 24, false, true);
        Image cancelarImg = new Image(linkCancelar.toString(), 24, 24, false, true);
        Image buscarImg = new Image(linkBuscar.toString(), 16, 16, false, true);
        Image refreshImg = new Image(linkRefresh.toString(), 16, 16, false, true);

        btnNovo.setGraphic(new ImageView(novaImg));
        btnGuardar.setGraphic(new ImageView(guardarImg));
        btnApagar.setGraphic(new ImageView(apagarImg));
        btnCancelar.setGraphic(new ImageView(cancelarImg));
        btnBuscar.setGraphic(new ImageView(buscarImg));
        btnRefresh.setGraphic(new ImageView(refreshImg));
    }
}
