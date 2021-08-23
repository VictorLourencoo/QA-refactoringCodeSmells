package controls;

import DAO.CategoriaDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Categoria;
import models.Compra;
import models.Fornecedor;
import models.Produto;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public abstract class newClass implements Initializable {

    @FXML
    private TextField txNome;
    @FXML
    private TextField txId;
    @FXML
    private TextField txPreco;
    @FXML
    private TextField txQtd;
    @FXML
    private ComboBox cbUn;
    @FXML
    private TextField txEstoqueMin;
    @FXML
    private ComboBox cbForn;
    @FXML
    private ComboBox cbCat;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btCancelar;

    ProdutoDAO pdao = new ProdutoDAO();
    FornecedorDAO fdao = new FornecedorDAO();
    CategoriaDAO cdao = new CategoriaDAO();

    private boolean edit = false;
    private boolean view = false;
    private int idProd;


    public newClass(){

    }
   public newClass(boolean view, boolean edit, int idProd) throws ClassNotFoundException {
       this.view = view;
       this.edit = edit;
       this.idProd = idProd;
   }


    

    public void btCancel_Click(MouseEvent mouseEvent) throws IOException {
        new Estoque().show();
    }
 

        Node btDetalhesVenda;
		if(!tbVendas.getSelectionModel().isEmpty()){
            btDetalhesVenda.setDisable(false);
        } else {
            btDetalhesVenda.setDisable(true);
        }
   
    }
    

    public void verificaData(){
        Date data = new Date(System.currentTimeMillis());
        TableView<Compra> tbCPendentes;
		tbCPendentes.setRowFactory(tv -> {
            return new TableRow<Compra>() {
                @Override
                public void updateItem(Compra c, boolean empty) {
                    super.updateItem(c, empty) ;
                    if (c == null) {
                        setStyle("");
                    } else if (c.getDataEntrega().before(data)) {
                        setStyle("-fx-text-background-color: #AAA;");
                        //setStyle("-fx-background-color: #0080FF;");
                    } else {
                        //setStyle("-fx-background-color: #0080FF;");
                    }
                
                }}}
    }

		
    


