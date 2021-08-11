package controls;

import DAO.VendaDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import models.Produto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.text.html.ListView;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import models.Produto;
import models.Usuario;
import models.Venda;

public class Vendas implements Initializable{
    @FXML
    TextField txQtdVenda;
    @FXML
    TextField txQtdEstoque;
    @FXML
    TextField txPrecoVenda;
    @FXML
    TextField txVendedor;
    @FXML
    TextField txPrecoUn;
    @FXML
    TextField txPesquisar;
    @FXML
    TextField txNome;
    @FXML
    TextField txId;
    @FXML
    TextField txPrecoTotal;
    @FXML
    TableView<Produto> tbProdutos;
    @FXML
    Button btVoltar;
    @FXML
    Button btRemover;
    @FXML
    Button btLimparVenda;
    @FXML
    Button btLimparText;
    @FXML
    Button btFinalizar;
    @FXML
    Button btAdicionar;
    @FXML
    javafx.scene.control.ListView lvProdutos;

    VendaDAO vDAO = new VendaDAO();
    ProdutoDAO pDAO = new ProdutoDAO();

    static List<Produto> produtos = new ArrayList<>();
    static double precoTotal = 0 ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            atvBotaoAdd();
           
            produtos.clear();
            txPrecoTotal.clear();
            precoTotal = 0;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vendas(){

    }

    

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/Vendas.fxml"));
        root.setControllerFactory(c -> {
            return new Vendas();
        });
        primaryStage.setTitle("ControlX - Vendas");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    
     

    public void autoComplete() throws ClassNotFoundException {
        String pesquisa = txPesquisar.getText();
        ObservableList<Produto> prods = FXCollections.observableArrayList();
        for (Produto p : pDAO.listAllByName(pesquisa)) {
            prods.add(p);
        }
        lvProdutos.setItems(prods);
    }
    public void fillFields(){
        Produto p = (Produto) lvProdutos.getSelectionModel().getSelectedItem();
        txId.setText(String.valueOf(p.getCat()));
        txNome.setText(p.getNome());
        txQtdEstoque.setText(String.valueOf(p.getQtdUn()) + " " + p.getQtdUn());
        txPrecoUn.setText(String.valueOf("R$ " + p.getPreco()));
        txPrecoVenda.setText(String.valueOf("R$ " + p.getPreco()));
    }
    public void clearFields(){
        txNome.clear();
        txId.clear();
        txPesquisar.clear();
        txPrecoUn.clear();
        txPrecoVenda.clear();
        txQtdEstoque.clear();
        txQtdVenda.clear();
        atvBotaoAdd();
        lvProdutos.setItems(null);
    }


    
  

    public void refreshTable(){
        tbProdutos.getItems().clear();
        tbProdutos.getColumns().clear();
        lvProdutos.setItems(null);

        ObservableList<Produto> prod = FXCollections.observableArrayList();

        for (Produto p : produtos) { //Para cada produto presente na lista estática
           
        }

        TableColumn<Produto, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(30);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Produto, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setMinWidth(150);
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Produto, String> precoColumn = new TableColumn<>("Preço (R$)");
        precoColumn.setMinWidth(50);
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        TableColumn<Produto, String> qtdColumn = new TableColumn<>("Qtd de Venda");
        qtdColumn.setMinWidth(50);
        qtdColumn.setCellValueFactory(new PropertyValueFactory<>("qtd"));


        tbProdutos.setItems(prod);

        tbProdutos.getColumns().addAll(idColumn, nomeColumn, precoColumn, qtdColumn);
        clearFields();
        DecimalFormat df = new DecimalFormat("#0.00");
        precoTotal = 0;
        
        txPrecoTotal.setText(String.valueOf(df.format(precoTotal)));
    }


    public void clearTable(){
        produtos.clear();
        precoTotal = 0;
        txPrecoTotal.setText(String.valueOf(precoTotal));
        refreshTable();
        atvBotaoAdd();
    }

    private void atvBotaoAdd() {
		// TODO Auto-generated method stub
		
	}

	public void removeItem() throws ClassNotFoundException {
        Produto p = pDAO.read(tbProdutos.getSelectionModel().getSelectedItem());
        for(Produto prods: produtos){
            if (prods.getCat() == p.getCat())
                produtos.remove(prods);
        }
        refreshTable();
    }

    
    public void finalizarVenda() throws ClassNotFoundException, IOException {
        Venda venda = new Venda();
        venda.setProdutos(produtos);
        Date data = new Date(System.currentTimeMillis());
        venda.setData(data);
        //DecimalFormat df = new DecimalFormat("#0.00");
        //txPrecoTotal.setText(String.valueOf(df.format(precoTotal)));
        venda.setValor(precoTotal);
        venda.setUsuario(Login.getUser());
        boolean sucess = vDAO.vender(venda);
       
        
        if(sucess){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ControlX - Venda Concluída");
            alert.setHeaderText("Produtos vendidos com sucesso");
            alert.setContentText("A venda foi finalizada com sucesso! \nCheque o histórico para mais detalhes.");
            alert.showAndWait();
            new Vendas().show();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ControlX - Venda Malsucedida");
            alert.setHeaderText("Algo deu errado");
            alert.setContentText("Um erro inesperado aconteceu! A Venda não foi finalizada.");
            alert.showAndWait();
        }
    }
}
