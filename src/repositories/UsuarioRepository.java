package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connection.ConnectionFactory;
import interfaces.IUsuarioRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Usuario;

public class UsuarioRepository implements IUsuarioRepository {

    // Conexão
    public static Connection conexao = new ConnectionFactory().getConnection();

    @Override
    public boolean inserirUsuario(Usuario usuario) {
        try {

            // Prepara a query
            PreparedStatement stmt = conexao.prepareStatement(SqlQueries.inserirUsuario);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getNomeUsuario());
            stmt.setString(4, usuario.getBiografia());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getProfissao());
            stmt.setString(7, usuario.getTelefone());
            stmt.setInt(8, usuario.getIdade());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean atualizarUsuario(Usuario usuario) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(SqlQueries.atualizarUsuario);

            // Atualizando usuario
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getNomeUsuario());
            stmt.setString(4, usuario.getBiografia());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getProfissao());
            stmt.setString(7, usuario.getTelefone());
            stmt.setInt(8, usuario.getIdade());
            stmt.setInt(9, usuario.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Usuario> listarUsuarios() {
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = conexao.prepareStatement(SqlQueries.listarUsuarios);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                var usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setBiografia(rs.getString("biografia"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setProfissao(rs.getString("profissao"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setIdade(rs.getInt("idade"));

                usuarios.add(usuario);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return usuarios;
    }

    @Override
    public boolean removerUsuario(int id) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(SqlQueries.removerUsuario);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario listarUsuarioPorId(int id) {
        var usuario = new Usuario();

        try {
            PreparedStatement stmt = conexao.prepareStatement(SqlQueries.listarUsuarioPorId);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("nome"));
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setBiografia(rs.getString("biografia"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setProfissao(rs.getString("profissao"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setIdade(rs.getInt("idade"));
            }

            rs.close();

            return usuario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
