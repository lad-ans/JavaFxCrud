package interfaces;

import javafx.collections.ObservableList;
import models.Usuario;

public interface IUsuarioRepository {
    public boolean inserirUsuario(Usuario usuario);

    public boolean atualizarUsuario(Usuario usuario);

    public boolean removerUsuario(int id);

    public ObservableList<Usuario> listarUsuarios();

    public Usuario listarUsuarioPorId(int id);
}
