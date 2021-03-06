package controls;

import DAO.FornecedorDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Fornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GerenciarFornecedor implements Initializable {

    @FXML
    private TextField txNome;

    @FXML
    private TextField txCnpj;

    @FXML
    private TextField txTel1;

    @FXML
    private TextField txId;

    @FXML
    private TextField txTel2;

    @FXML
    private TextField txCep;

    @FXML
    private TextField txNum;

    @FXML
    private TextField txRua;

    @FXML
    private TextField txComp;

    @FXML
    private TextField txBairro;

    @FXML
    private TextField txCidade;

    @FXML
    private TextField txEstado;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    private int id;
    private boolean view = false;
    private boolean edit = false;
    private FornecedorDAO fdao = new FornecedorDAO();

    GerenciarFornecedor(){

    }

    GerenciarFornecedor(boolean view, boolean edit, int id){
        this.view = view;
        this.edit = edit;
        this.id = id;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
            preencher();
            visualizar();
            try {
				botaoSalvar();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    private void preencher() {
		// TODO Auto-generated method stub
		
	}

	public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/GerenciarFornecedor.fxml"));
        root.setControllerFactory(c -> {
            return new GerenciarFornecedor();
        });
        primaryStage.setTitle("ControlX - Adicionar Fornecedor");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(true);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void show(boolean view, boolean edit, int id) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/GerenciarFornecedor.fxml"));
        root.setControllerFactory(c -> {
            return new GerenciarFornecedor(view, edit, id);
        });
        primaryStage.setTitle("ControlX - Gerenciar Fornecedor");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(true);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

  


    public void visualizar(){
        if(view) {
            txNome.setEditable(false);
            txCnpj.setEditable(false);
            txTel1.setEditable(false);
            txTel2.setEditable(false);
            txCep.setEditable(false);
            txNum.setEditable(false);
            txRua.setEditable(false);
            txComp.setEditable(false);
            txBairro.setEditable(false);
            txCidade.setEditable(false);
            txEstado.setEditable(false);
            btSalvar.setVisible(false);
            btCancelar.setText("Voltar");
        }
    }

    public void botaoCancelar() throws IOException {
        new Fornecedores().show();
    }

    public void botaoSalvar() throws ClassNotFoundException, IOException {
        Fornecedor f = new Fornecedor();
        f.setId(Integer.parseInt(txId.getText()));
        f.setNome(txNome.getText());
        f.setCnpj(txCnpj.getText());
        f.setTelefone1(txTel1.getText());
        f.setTelefone2(txTel2.getText());
        f.setCep(txCep.getText());
        f.setNum(Integer.parseInt(txNum.getText()));
        f.setRua(txRua.getText());
        f.setComp(txComp.getText());
        f.setBairro(txBairro.getText());
        f.setCidade(txCidade.getText());
        f.setEstado(txEstado.getText());

        if (edit){
            fdao.up(f);
        } else if (!edit && !view){
            fdao.add(f);
        }

        new Fornecedores().show();
    }

  
}
