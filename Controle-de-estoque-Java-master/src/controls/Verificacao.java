package controls;

import DAO.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Compra;
import models.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Verificacao implements Initializable {

    @FXML
    private TableView<Compra> tbCPendentes;
    @FXML
    private Button btVisualizar;
    @FXML
    private Button btVoltar;

    CompraDAO cdao = new CompraDAO();
    ProdutoDAO pdao = new ProdutoDAO();
	private Node btDetalhesCompra;
	private Node btDetalhesVenda;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewPendentes(cdao.listAll());
		
		verificaData();
    }



    private void verificaData() {
		// TODO Auto-generated method stub
		
	}



	private void listViewPendentes(List<Compra> listAll) {
		// TODO Auto-generated method stub
		
	}


  
    }

 

   
   

}
   
	   
	            
	    


		

