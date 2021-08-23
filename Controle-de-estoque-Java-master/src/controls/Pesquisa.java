package controls;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DAO.CategoriaDAO;
import DAO.ProdutoDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Categoria;
import models.Produto;

public class Pesquisa implements Initializable {
    @FXML
    private TableView<Produto> tbView;
    @FXML
    private RadioButton rdId;
    @FXML
    private RadioButton rdNome;
    
    private TextField txPesquisar;
 
    @FXML
    private ComboBox cbCat;
    @FXML
    private Button btRemove;
    @FXML
    private Button btEdit;
    @FXML
    private Button btView;
    @FXML
    private Button btCategorias;
    @FXML
    private ProdutoDAO pdao = new ProdutoDAO();
    private CategoriaDAO cdao = new CategoriaDAO();
	
	public void pesquisarProduto() throws ClassNotFoundException {
/*     if (rdId.isSelected()) {
	            listView(pdao.listAllById(txPesquisar.getText()));
	        } else if (rdNome.isSelected()) {
	            listView(pdao.listAllByName(txPesquisar.getText()));
	        }

	        if (txPesquisar.getText().equals("")) {
	            listView(pdao.listAll());
	        }
 */   	    
}
	public void pesquisarUsuario() throws ClassNotFoundException {
		/* 
		if (rdId.isSelected()) {
            listView(pdao.listAllById(txPesquisar.getText()));
        } else if (rdNome.isSelected()) {
            listView(pdao.listAllByName(txPesquisar.getText()));
        }

        if (txPesquisar.getText().equals("")) {
            listView(pdao.listAll());
        }
        */
}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	   public void pesquisarCategoria() throws ClassNotFoundException {
	        if (rdId.isSelected()) {
	            listView(cdao.listAllById(txPesquisar.getText()));
	        } else if (rdNome.isSelected()) {
	            listView(cdao.listAllByName(txPesquisar.getText()));
	        }

	        if (txPesquisar.getText().equals("")) {
	            listView(cdao.listAll());
	        }
	    }
	private void listView(List<Categoria> listAllById) {
		// TODO Auto-generated method stub
		
	}
}
