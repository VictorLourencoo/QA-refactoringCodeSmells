package controls;

import DAO.CompraDAO;
import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Compra;
import models.Produto;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

public class NovaCompra implements Initializable {

    ProdutoDAO pDAO = new ProdutoDAO();

    @FXML
    private TextField txNome;

    @FXML
    private TextField txPesquisar;

    @FXML
    private Label lbNome;

    @FXML
    private TextField txId;

    @FXML
    private TextField txQtdEstoque;

    @FXML
    private TextField txPrecoUn;

    @FXML
    private TextField txQtdCompra;

    @FXML
    private TextField txPrecoCompra;

    @FXML
    private Button btAdicionar;

    @FXML
    private Button btLimparText;

    @FXML
    private TextField txPrecoTotal;

    @FXML
    private Label lbPesquisar;

    @FXML
    private TableView<Produto> tbProdutos;

    @FXML
    private Label lbCarrinho;

    @FXML
    private Label lbVendedor;

    @FXML
    private TextField txVendedor;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btFinalizar;

    @FXML
    private Button btLimparVenda;

    @FXML
    private Button btRemover;

    @FXML
    private DatePicker dtEntrega;

    @FXML
    javafx.scene.control.ListView lvProdutos;

    CompraDAO cdao = new CompraDAO();
    static List<Produto> produtos = new ArrayList<>();
    static double precoTotal = 0 ;

    boolean view = false;
    Compra compra = new Compra();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            atvBotaoAdd();
            txPrecoTotal.clear();
            precoTotal = 0;
            produtos.clear();
            if(view){
                visualizarCompra();
            }
            else{
                getUser();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    NovaCompra(){

    }

    NovaCompra(boolean view, Compra c){
        this.view = view;
        this.compra = c;
    }

    public void getUser(){
        txVendedor.setText(Login.getUser().getNum());
    }

    public void show() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/NovaCompra.fxml"));
        root.setControllerFactory(c -> {
            return new NovaCompra();
        });
        primaryStage.setTitle("ControlX - Nova Compra");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void show(boolean view, Compra compra) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("/views/NovaCompra.fxml"));
        root.setControllerFactory(c -> {
            return new NovaCompra(view, compra);
        });
        primaryStage.setTitle("ControlX - Visualizar Compra");
        Main.stage.hide();
        Main.stage = primaryStage;
        primaryStage.setScene(new Scene(root.load(), primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);
        Main.stage.getIcons().add(new Image("images/controlx.png"));
        primaryStage.show();
    }

    public void visualizarCompra() throws ClassNotFoundException {
        btRemover.setDisable(true);
        btRemover.setDisable(true);
        btAdicionar.setDisable(true);
        btLimparText.setDisable(true);
        btLimparVenda.setDisable(true);
        txPesquisar.setDisable(true);
        txPrecoCompra.setDisable(true);
        dtEntrega.setDisable(false);
        txPrecoTotal.setDisable(false);
        txVendedor.setEditable(false);
        txQtdCompra.setDisable(true);

        listView(compra.getProdutos());

        dtEntrega.setValue(LocalDate.parse(compra.getDataEntrega().toString()));
        txPrecoTotal.clear();
        txPrecoTotal.setText(Double.toString(compra.getValor()));
        txVendedor.clear();
        txVendedor.setText(compra.getUsuario().getNum());
    }


    public void listView(List <Produto> prods){
        tbProdutos.getItems().clear();
        tbProdutos.getColumns().clear();
        produtos.clear();
        for (Produto p : prods){
            produtos.add(p);
        }
        refreshTable();
    }

    public void botaoVoltar() throws IOException {
        new Compras().show();
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
        txId.setText(String.valueOf(p.getId()));
        txNome.setText(p.getNum());
        txQtdEstoque.setText(String.valueOf(p.getId()) + " " + p.getQtdUn());
        txPrecoUn.setText(String.valueOf("R$ " + p.getPreco1()));
        txPrecoCompra.setText(String.valueOf(p.getPreco1()));
    }
    public void clearFields(){
        txNome.clear();
        txId.clear();
        txPesquisar.clear();
        txPrecoUn.clear();
        txPrecoCompra.clear();
        txQtdEstoque.clear();
        txQtdCompra.clear();
        atvBotaoAdd();
        lvProdutos.setItems(null);
    }

    public void addProds()throws ClassNotFoundException {
        //Dando cast do objeto Produto selecionado na ListView
        Produto pro = (Produto) lvProdutos.getSelectionModel().getSelectedItem();
        for (Produto p : produtos) {
            if (p.getId() == pro.getId()) { //Se o produto ja tiver adicionado na lista, cancelar
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ControlX - Produto duplicado");
                alert.setHeaderText("Produto já adicionado na venda");
                alert.setContentText("Esse produto já está adicionado ao carrinho de venda, operação cancelada!");

                alert.showAndWait();
                return;
            }
        }
        if (Double.parseDouble(txPrecoCompra.getText()) > pro.getPreco1()) {//Se Preço compra for maior que preço venda
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ControlX - Aviso");
            alert.setResizable(false);
            alert.setHeaderText("Perda de lucro detectada");
            alert.setContentText("Você está comprando por um preço maior do que o de venda, deseja continuar?");
            alert.getButtonTypes();

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent())
                return;
            else if(result.get() == ButtonType.OK) {
                //Lista estática produtos terá como qtd a quantidade de produto que foi vendida
                pro.setQtd(Double.parseDouble(txQtdCompra.getText()));
                pro.setPreco(Double.parseDouble(txPrecoCompra.getText()));
                //Adicionando o produto selecionado a lista
                produtos.add(pro);
                refreshTable();
                return;
            }
            else if(result.get() == ButtonType.CANCEL)
                return;

        }
        //Lista estática produtos terá como qtd a quantidade de produto que foi vendida
        pro.setQtd(Double.parseDouble(txQtdCompra.getText()));
        pro.setPreco(Double.parseDouble(txPrecoCompra.getText()));
        //Adicionando o produto selecionado a lista
        produtos.add(pro);
        refreshTable();
    }

    public void refreshTable(){
        tbProdutos.getItems().clear();
        tbProdutos.getColumns().clear();
        lvProdutos.setItems(null);

        ObservableList<Produto> prod = FXCollections.observableArrayList();

        for (Produto p : produtos) { //Para cada produto presente na lista estática
            //Adicionamos na observable list
            prod.add(new Produto(p.getId(), p.getNum(), p.getPreco1(), p.getId(), p.getQtdUn(), p.getCat()));
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

        TableColumn<Produto, String> qtdColumn = new TableColumn<>("Qtd de Compra");
        qtdColumn.setMinWidth(50);
        qtdColumn.setCellValueFactory(new PropertyValueFactory<>("qtd"));


        tbProdutos.setItems(prod);

        tbProdutos.getColumns().addAll(idColumn, nomeColumn, precoColumn, qtdColumn);
        clearFields();
        DecimalFormat df = new DecimalFormat("#0.00");
        precoTotal = 0;
        for(Produto p: produtos){
           
        }
        txPrecoTotal.setText(String.valueOf(df.format(precoTotal)));
    }



    public void atvBotaoAdd(){
    if(!view) {
        if (txNome.getText().isEmpty() || txPrecoCompra.getText().isEmpty() || txPrecoUn.getText().isEmpty() ||
                txQtdEstoque.getText().isEmpty() || txQtdCompra.getText().isEmpty() || txId.getText().isEmpty()) {
            btAdicionar.setDisable(true);
        } else {
            btAdicionar.setDisable(false);
        }
        if (produtos.isEmpty() || dtEntrega.getValue() == null) {
            btFinalizar.setDisable(true);
        } else {
            btFinalizar.setDisable(false);
        }
        if (produtos.isEmpty())
            btRemover.setDisable(true);
        else
            btRemover.setDisable(false);
    }else{
        ///
    }
    }

    public void clearTable(){
        produtos.clear();
       
        txPrecoTotal.setText(String.valueOf(precoTotal));
        refreshTable();
        atvBotaoAdd();
    }

    public void removeItem() throws ClassNotFoundException {
        Produto p = pDAO.read(tbProdutos.getSelectionModel().getSelectedItem());
        for(Produto prods: produtos){
            if (prods.getId() == p.getId())
                produtos.remove(prods);
        }
        refreshTable();
    }


}
