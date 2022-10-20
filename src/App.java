import models.Usuario;

public class App {
    public static void main(String[] args) throws Exception {
        var usuario = new Usuario();

        // Listar usuários: ok
        // for (Usuario u : usuario.listarUsuarios()) {
        // System.out.println(u.toString());
        // }

        // Cadastrar: ok
        // usuario.setNome("Milena");
        // usuario.setEmail("milena.anselmo@gmail.com");
        // usuario.setSenha("1234567");
        // usuario.setBiografia("dev");
        // usuario.setNomeUsuario("ladans.io");
        // usuario.setProfissao("Desenvolvedor");
        // usuario.setTelefone("860459152");
        // usuario.setIdade(27);

        // if (usuario.cadastrarUsuario(usuario)) {
        // System.out.println("Registrado com sucesso o/");

        // for (Usuario p : usuario.listarUsuarios()) {
        // System.out.println(p.toString());
        // }
        // }

        // Atualizar: ok
        // usuario = usuario.listarPorId(3);

        // usuario.setIdade(1);
        // usuario.setNome("Ladans");

        // if (usuario.atualizarUsuario(usuario)) {
        // System.out.println("Atualizado com sucesso o/");

        // for (Usuario p : usuario.listarUsuarios()) {
        // System.out.println(p.toString());
        // }
        // }

        // Delete: ok
        // if (usuario.removerUsuario(2)) {
        // System.out.println("Apagado com sucesso o/");

        // for (Usuario p : usuario.listarUsuarios()) {
        // System.out.println(p.toString());
        // }
        // }

        // Listar por Id: ok
        // System.out.println(usuario.listarPorId(3));
    }
}
