package DAO;

import connection.ConnectionFactory;
import models.Fornecedor;
import models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProdutoDAO {

    private FornecedorDAO fornDAO = new FornecedorDAO();
    private CategoriaDAO catDAO = new CategoriaDAO();

    public void add(Produto p) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO produtos (nome, preco, qntd, tipoUn, estoqueMin, idFornecedor, idCategoria) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");
            stmt.setString(1, p.getNome());
          
            stmt.setString(4, p.getQtdUn());
            stmt.setDouble(5, p.getEstoqueMin());
           
           
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void up(Produto p) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("UPDATE produtos SET nome = ?, preco = ?, qntd = ?, " +
                    "tipoUn = ?, estoqueMin = ?, idFornecedor = ?, idCategoria = ? WHERE id = ?;");
            stmt.setString(1, p.getNome());
       
            stmt.setString(4, p.getQtdUn());
            stmt.setDouble(5, p.getEstoqueMin());
       

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Produto> listAll() throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE deleted_at is NULL;");
            rs = stmt.executeQuery();

            while (rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setId(rs.getDouble("qntd"));
                prod.setTipoUn(rs.getString("tipoUn"));
                prod.setEstoqueMin(rs.getDouble("estoqueMin"));
                prod.setForn(fornDAO.read(rs.getInt("idFornecedor")));
                prod.setCat(catDAO.read(rs.getInt("idCategoria")));
                lista.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return lista;

    }

    public void del(Produto p) throws ClassNotFoundException {   // ou pelo id, public void del(int id)
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        //Formatando a data
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(System.currentTimeMillis());
        String data = dateFormat.format(date);


        try {

            stmt = con.prepareStatement("UPDATE produtos SET deleted_at = ? WHERE id = ?;");
            stmt.setString(1, data);
           

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Produto read(Produto p) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto prod = new Produto();

        try {
            stmt = con.prepareStatement("SELECT id, nome, preco, qntd, tipoUn," +
                    " estoqueMin, idFornecedor, idCategoria" +
                    " FROM produtos WHERE id = ? and deleted_at is NULL;");
          
            rs = stmt.executeQuery();

            if (rs.next()) {
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setId(rs.getDouble("qntd"));
                prod.setTipoUn(rs.getString("tipoUn"));
                prod.setEstoqueMin(rs.getDouble("estoqueMin"));
                prod.setForn(fornDAO.read(rs.getInt("idFornecedor")));
                prod.setCat(catDAO.read(rs.getInt("idCategoria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prod;
    }

    public Produto read(int id) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto prod = new Produto();

        try {
            stmt = con.prepareStatement("SELECT id, nome, preco, qntd, tipoUn," +
                    " estoqueMin, idFornecedor, idCategoria" +
                    " FROM produtos WHERE id = ? and deleted_at is NULL;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

                if (rs.next()) {
                    prod.setId(rs.getInt("id"));
                    prod.setNome(rs.getString("nome"));
                    prod.setPreco(rs.getDouble("preco"));
                    prod.setId(rs.getDouble("qntd"));
                    prod.setTipoUn(rs.getString("tipoUn"));
                    prod.setEstoqueMin(rs.getDouble("estoqueMin"));
                    prod.setForn(fornDAO.read(rs.getInt("idFornecedor")));
                    prod.setCat(catDAO.read(rs.getInt("idCategoria")));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prod;
    }


    public Produto readAll(int id) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto prod = new Produto();

        try {
            stmt = con.prepareStatement("SELECT id, nome, preco, qntd, tipoUn," +
                    " estoqueMin, idFornecedor, idCategoria" +
                    " FROM produtos WHERE id = ? ;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setId(rs.getDouble("qntd"));
                prod.setTipoUn(rs.getString("tipoUn"));
                prod.setEstoqueMin(rs.getDouble("estoqueMin"));
                prod.setForn(fornDAO.read(rs.getInt("idFornecedor")));
                prod.setCat(catDAO.read(rs.getInt("idCategoria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prod;
    }

    public List<Produto> listAllByForn(Fornecedor f) throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE idFornecedor = ? AND deleted_at is NULL;");
          
            rs = stmt.executeQuery();

            while (rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setId(rs.getDouble("qntd"));
                prod.setTipoUn(rs.getString("tipoUn"));
                prod.setEstoqueMin(rs.getDouble("estoqueMin"));
                prod.setForn(fornDAO.read(rs.getInt("idFornecedor")));
                prod.setCat(catDAO.read(rs.getInt("idCategoria")));
                lista.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }

    public List<Produto> listAllById(String id) throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE id LIKE ? AND deleted_at is NULL;");
            stmt.setString(1, id + "%");
            rs = stmt.executeQuery();

            while (rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setId(rs.getDouble("qntd"));
                prod.setTipoUn(rs.getString("tipoUn"));
                prod.setEstoqueMin(rs.getDouble("estoqueMin"));
                prod.setForn(fornDAO.read(rs.getInt("idFornecedor")));
                prod.setCat(catDAO.read(rs.getInt("idCategoria")));
                lista.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }

    public List<Produto> listAllByName(String nome) throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE nome LIKE ? AND deleted_at is NULL;");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setId(rs.getDouble("qntd"));
                prod.setTipoUn(rs.getString("tipoUn"));
                prod.setEstoqueMin(rs.getDouble("estoqueMin"));
                prod.setForn(fornDAO.read(rs.getInt("idFornecedor")));
                prod.setCat(catDAO.read(rs.getInt("idCategoria")));
                lista.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return lista;
    }

    public List<Produto> listFill(String nome) throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE nome LIKE ? OR id LIKE ? AND deleted_at is NULL;");
            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setId(rs.getDouble("qntd"));
                prod.setTipoUn(rs.getString("tipoUn"));
                prod.setEstoqueMin(rs.getDouble("estoqueMin"));
                prod.setForn(fornDAO.read(rs.getInt("idFornecedor")));
                prod.setCat(catDAO.read(rs.getInt("idCategoria")));
                lista.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return lista;
    }


    public List<Produto> listAllByCategoria(int id) throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE idCategoria = ? AND deleted_at is NULL;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setId(rs.getDouble("qntd"));
                prod.setTipoUn(rs.getString("tipoUn"));
                prod.setEstoqueMin(rs.getDouble("estoqueMin"));
                prod.setForn(fornDAO.read(rs.getInt("idFornecedor")));
                prod.setCat(catDAO.read(rs.getInt("idCategoria")));
                lista.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return lista;
    }

    public int idAutoIncrement() throws ClassNotFoundException {
        int id = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'produtos' AND table_schema = 'controlx'");
            rs = stmt.executeQuery();

            if (rs.next()){
                id = rs.getInt("AUTO_INCREMENT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return id;
        }

    }
}