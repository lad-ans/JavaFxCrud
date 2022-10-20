package repositories;

public class SqlQueries {

        final static String tabelaUsuario = "[usuario]";

        /// Queries em [usuario]

        // listar
        final static String listarUsuarios = "select * from " + tabelaUsuario;

        // listar
        final static String listarUsuarioPorId = "select * from " + tabelaUsuario + " where id=?";

        // inserir
        final static String inserirUsuario = "insert into "
                        + tabelaUsuario
                        + "(nome, email, nome_usuario, biografia, senha, profissao, telefone, idade) values(?, ?, ?, ?, ?, ?, ?, ?)";
        // atualizar
        final static String atualizarUsuario = "update "
                        + tabelaUsuario
                        + " set nome=?, email=?, nome_usuario=?, biografia=?, senha=?, profissao=?, telefone=?, idade=? where id=?";
        // remover
        final static String removerUsuario = "delete from "
                        + tabelaUsuario
                        + " where id=?";
}
