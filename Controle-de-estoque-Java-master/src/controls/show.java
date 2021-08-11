package controls;

import DAO.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Usuario;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class show implements Initializable {

    @FXML
    private TextField txNome;

    @FXML
    private TextField txId;

    @FXML
    private TextField txCpf;

    @FXML
    private RadioButton rdM;

    @FXML
    private RadioButton rdF;

    @FXML
    private ToggleGroup Sexo;

    @FXML
    private DatePicker dtDataNasc;

    @FXML
    private TextField txTel1;

    @FXML
    private TextField txTel2;

    @FXML
    private TextField txCep;

    @FXML
    private TextField txRua;

    @FXML
    private TextField txBairro;

    @FXML
    private TextField txCidade;

    @FXML
    private TextField txNum;

    @FXML
    private TextField txComp;

    @FXML
    private TextField txEstado;

    @FXML
    private TextField txLogin;

    @FXML
    private PasswordField txSenha;

    @FXML
    private Label lbUsuario;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    @FXML
    private ComboBox<String> cbCargo;

    int id;
    boolean edit = false;
    boolean view = false;
    boolean checklogin = false;
    UsuarioDAO udao = new UsuarioDAO();

    void GerenciarUsuario(boolean view, boolean edit, int id){
        this.id = id;
        this.view = view;
        this.edit = edit;
    }

    void GerenciarUsuario(){

    }


    public void show(boolean view, boolean edit, int id) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/GerenciarUsuario.fxml"));
        root.setControllerFactory(c -> {
            return new GerenciarUsuario(view, edit, id);
        });
        primaryStage.setTitle("ControlX - Gerenciar Usuário");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/GerenciarUsuario.fxml"));
        root.setControllerFactory(c -> {
            return new GerenciarUsuario();
        });
        primaryStage.setTitle("ControlX - Adicionar Usuário");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}






 



   
}
