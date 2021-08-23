package DAO;

import connection.ConnectionFactory;
import models.Usuario;
import controls.getsAtribb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioDAO {

  


    public void add(Usuario u) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        //Formatando a data
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        try {

            stmt = con.prepareStatement("INSERT INTO usuario (nome, cpf, sexo, dataNasc," +
                    " tel1, tel2, cep, num, rua, comp, bairro, cidade, estado, cargo, login, senha) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
         
            stmt.setString(2, u.getCpf());
            stmt.setString(3, u.getSexo());
            String data = dateFormat.format(u.getDataNasc());
            stmt.setString(4, data);
            stmt.setString(5, u.getTelefone1());
            stmt.setString(6, u.getTelefone2());
            stmt.setString(7, u.getCep());
            stmt.setInt(8, u.getNum());
            stmt.setString(9, u.getRua());
            stmt.setString(10, u.getComp());
            stmt.setString(11, u.getBairro());
            stmt.setString(12, u.getCidade());
            stmt.setString(13, u.getEstado());
         
            stmt.setString(15, u.getLogin());
            stmt.setString(16, u.getSenha());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void up(Usuario u) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        //Formatando a data
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        try {
            stmt = con.prepareStatement("UPDATE usuario SET nome = ?, cpf = ?, sexo = ?, dataNasc = ?," +
                    " tel1 = ?, tel2 = ?, cep = ?, num = ?, rua = ?, comp = ?," +
                    " bairro = ?, cidade = ?, estado = ?, cargo = ?, login = ?, senha = ? WHERE id = ?;");
            Usuario user = new Usuario(0, null, null, null, null, null, null, null, 0, null, null, null, null, null, 0, null, null);
            
            stmt.setString(2, u.getCpf());
            stmt.setString(3, u.getSexo());
            String data = dateFormat.format(u.getDataNasc());
            stmt.setString(4, data);
            stmt.setString(5, u.getTelefone1());
            stmt.setString(6, u.getTelefone2());
            stmt.setString(7, u.getCep());
        
           
            stmt.setString(10, u.getComp());
            stmt.setString(11, u.getBairro());
            stmt.setString(12, u.getCidade());
            stmt.setString(13, u.getEstado());
       

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        

    }
    public List<Usuario> listAll() throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE deleted_at is NULL;");
            rs = stmt.executeQuery();

            while (rs.next()){
                Usuario user = new Usuario(0, null, null, null, null, null, null, null, 0, null, null, null, null, null, 0, null, null);
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setSexo(rs.getString("sexo"));
                user.setDataNasc(rs.getDate("dataNasc"));
                user.setTelefone1(rs.getString("tel1"));
                user.setTelefone2(rs.getString("tel2"));
                user.setCep(rs.getString("cep"));
                user.setNum(rs.getInt("num"));
                user.setRua(rs.getString("rua"));
                user.setComp(rs.getString("comp"));
                user.setBairro(rs.getString("bairro"));
                user.setCidade(rs.getString("cidade"));
                user.setEstado(rs.getString("estado"));
                user.setCargo(rs.getInt("cargo"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));

                lista.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return lista;
    }

    public List<Usuario> listAllById(String id) throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE id LIKE ? AND deleted_at is NULL;");
            stmt.setString(1, "%" + id + "%");
            rs = stmt.executeQuery();

            while (rs.next()){
                Usuario user = new Usuario(0, id, id, id, null, id, id, id, 0, id, id, id, id, id, 0, id, id);
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setSexo(rs.getString("sexo"));
                user.setDataNasc(rs.getDate("dataNasc"));
                user.setTelefone1(rs.getString("tel1"));
                user.setTelefone2(rs.getString("tel2"));
                user.setCep(rs.getString("cep"));
                user.setNum(rs.getInt("num"));
                user.setRua(rs.getString("rua"));
                user.setComp(rs.getString("comp"));
                user.setBairro(rs.getString("bairro"));
                user.setCidade(rs.getString("cidade"));
                user.setEstado(rs.getString("estado"));
                user.setCargo(rs.getInt("cargo"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));

                lista.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return lista;
    }

    public List<Usuario> listAllByName(String nome) throws ClassNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE nome LIKE ? AND deleted_at is NULL;");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()){
                Usuario user = new Usuario(0, nome, nome, nome, null, nome, nome, nome, 0, nome, nome, nome, nome, nome, 0, nome, nome);
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setSexo(rs.getString("sexo"));
                user.setDataNasc(rs.getDate("dataNasc"));
                user.setTelefone1(rs.getString("tel1"));
                user.setTelefone2(rs.getString("tel2"));
                user.setCep(rs.getString("cep"));
                user.setNum(rs.getInt("num"));
                user.setRua(rs.getString("rua"));
                user.setComp(rs.getString("comp"));
                user.setBairro(rs.getString("bairro"));
                user.setCidade(rs.getString("cidade"));
                user.setEstado(rs.getString("estado"));
                user.setCargo(rs.getInt("cargo"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));

                lista.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return lista;
    }

    public void del(Usuario u) throws ClassNotFoundException {   // ou pelo id, public void del(int id)
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        //Formatando a data
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(System.currentTimeMillis());
        String data = dateFormat.format(date);


        try {

            stmt = con.prepareStatement("UPDATE usuario SET deleted_at = ? WHERE id = ?;");
            stmt.setString(1, data);
       

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public Usuario read(int id) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = new Usuario(id, null, null, null, null, null, null, null, id, null, null, null, null, null, id, null, null);

        try {
            stmt = con.prepareStatement("SELECT * "+
                    " FROM usuario WHERE id = ? and deleted_at is NULL;");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setSexo(rs.getString("sexo"));
                user.setDataNasc(rs.getDate("dataNasc"));
                user.setTelefone1(rs.getString("tel1"));
                user.setTelefone2(rs.getString("tel2"));
                user.setCep(rs.getString("cep"));
                user.setNum(rs.getInt("num"));
                user.setRua(rs.getString("rua"));
                user.setComp(rs.getString("comp"));
                user.setBairro(rs.getString("bairro"));
                user.setCidade(rs.getString("cidade"));
                user.setEstado(rs.getString("estado"));
                user.setCargo(rs.getInt("cargo"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return user;
    }

    public Usuario read(String login) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = new Usuario(0, login, login, login, null, login, login, login, 0, login, login, login, login, login, 0, login, login);

        try {
            stmt = con.prepareStatement("SELECT * "+
                    " FROM usuario WHERE login = ? AND deleted_at is NULL;");
            stmt.setString(1, login);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setSexo(rs.getString("sexo"));
                user.setDataNasc(rs.getDate("dataNasc"));
                user.setTelefone1(rs.getString("tel1"));
                user.setTelefone2(rs.getString("tel2"));
                user.setCep(rs.getString("cep"));
                user.setNum(rs.getInt("num"));
                user.setRua(rs.getString("rua"));
                user.setComp(rs.getString("comp"));
                user.setBairro(rs.getString("bairro"));
                user.setCidade(rs.getString("cidade"));
                user.setEstado(rs.getString("estado"));
                user.setCargo(rs.getInt("cargo"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return user;
    }

    public Usuario read(Usuario u) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = new Usuario(0, null, null, null, null, null, null, null, 0, null, null, null, null, null, 0, null, null);

        try {
            stmt = con.prepareStatement("SELECT * "+
                    " FROM usuario WHERE id = ? and deleted_at is NULL;");
          
            rs = stmt.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setSexo(rs.getString("sexo"));
                user.setDataNasc(rs.getDate("dataNasc"));
                user.setTelefone1(rs.getString("tel1"));
                user.setTelefone2(rs.getString("tel2"));
                user.setCep(rs.getString("cep"));
                user.setNum(rs.getInt("num"));
                user.setRua(rs.getString("rua"));
                user.setComp(rs.getString("comp"));
                user.setBairro(rs.getString("bairro"));
                user.setCidade(rs.getString("cidade"));
                user.setEstado(rs.getString("estado"));
                user.setCargo(rs.getInt("cargo"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return user;
    }

    public int idAutoIncrement() throws ClassNotFoundException {
        int id = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'usuario' AND table_schema = 'controlx'");
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

    public boolean verificaLogin(String login) throws ClassNotFoundException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = true;

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login = ?;");
            stmt.setString(1, login);
            rs = stmt.executeQuery();

            if (rs.next()) {
               check = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;
    }
}
