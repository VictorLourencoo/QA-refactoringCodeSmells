package controls;

import java.io.IOException;
import java.util.Date;

import javafx.scene.control.Alert;
import models.Compra;
import models.Produto;

public class methodLong {
    private double precoTotal;

	public void comprar() throws ClassNotFoundException, IOException {
    Object produtos;
	if(!view) {
        if (tbProdutos.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ControlX - Aviso");
            alert.setHeaderText("Impossível concluir a compra");
            alert.setContentText("Você deve adicionar ao menos um produto\n na lista de compras.");

            alert.showAndWait();
        } else {

            Compra c = new Compra();
            c.setProdutos(produtos);
            c.setUsuario(Login.getUser());
            c.setValor(precoTotal);

            c.setData(new Date(System.currentTimeMillis()));

            c.setDataEntrega(java.sql.Date.valueOf(dtEntrega.getValue()));

            boolean sucess = cdao.comprar(c);
            if (sucess) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ControlX - Compra Concluída");
                alert.setHeaderText("Compra agendada com sucesso");
                alert.setContentText("A compra foi agendada com sucesso! \nCheque o histórico para mais detalhes.");
                alert.showAndWait();
                new Compras().show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ControlX - Compra Malsucedida");
                alert.setHeaderText("Algo deu errado");
                alert.setContentText("Um erro inesperado aconteceu! A Compra não foi finalizada.");
                alert.showAndWait();
            }
        }
    }else{

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ControlX - Aviso");
        alert.setHeaderText("Pedido Finalizada");
        alert.setContentText("Produtos comprados foram acrescentado e atualizados.");

   

        //Atualizando Produtos
        for(Produto p: produtos){
            Produto pEstoque = pDAO.read(p.getId());
            pEstoque.setQtd(pEstoque.getQtd() + p.getQtd());
            pDAO.up(pEstoque);
        }

        if (sucess) {
            new Compras().show();

        } else {

        }

        alert.showAndWait();

    }

    }

}
