package controls;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Categoria;
import models.Fornecedor;

public class iniCombo {
	private Object cbForn;

	public void iniComboBox() throws ClassNotFoundException {
        //Categoria
        ObservableList<Categoria> categorias = FXCollections.observableArrayList();
        Object cdao;
	
        Object cbCat;
	

     


    }
	 public void initialize(URL location, ResourceBundle resources) {
	        try {
	            if(Login.getUser().getCargo() == 3){
	                Object btAdd;
					
	            }
	         
	            iniComboBox();
	         
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

}
