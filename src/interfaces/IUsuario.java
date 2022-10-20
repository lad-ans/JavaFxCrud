package interfaces;

import javafx.collections.ObservableList;
import models.Usuario;

//Contrato da regra de negócio
public interface IUsuario {
    public boolean cadastrarUsuario(Usuario produto);

    public boolean atualizarUsuario(Usuario produto);

    public boolean removerUsuario(int id);

    public ObservableList<Usuario> listarUsuarios();

    public Usuario listarPorId(int id);
}
