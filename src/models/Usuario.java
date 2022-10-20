package models;

import interfaces.IUsuario;
import interfaces.IUsuarioRepository;
import javafx.collections.ObservableList;
import repositories.UsuarioRepository;

public class Usuario implements IUsuario {

    // Atributos
    private int id;
    private String nome;
    private String email;
    private String nomeUsuario;
    private String biografia;
    private String senha;

    private String profissao;
    private String telefone;
    private int idade;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Obtendo o Repositório
    private IUsuarioRepository _repository = new UsuarioRepository();

    // Implementando o IUsuario
    @Override
    public boolean cadastrarUsuario(Usuario usuario) {
        if (_repository.inserirUsuario(usuario))
            return true;
        return false;
    }

    @Override
    public boolean atualizarUsuario(Usuario usuario) {
        if (_repository.atualizarUsuario(usuario))
            return true;
        return false;
    }

    @Override
    public boolean removerUsuario(int id) {
        if (_repository.removerUsuario(id))
            return true;
        return false;
    }

    @Override
    public ObservableList<Usuario> listarUsuarios() {
        return _repository.listarUsuarios();
    }

    @Override
    public Usuario listarPorId(int id) {
        return _repository.listarUsuarioPorId(id);
    }

    @Override
    public String toString() {
        return "ID: " + this.id
                + ", Nome: " + this.nome
                + ", Usuário: " + this.nomeUsuario
                + ", Email: " + this.email
                + ", Bio: " + this.biografia
                + ", Profissão: " + this.profissao
                + ", Idade: " + this.idade
                + ", Telefoene: " + this.telefone;
    }

}
