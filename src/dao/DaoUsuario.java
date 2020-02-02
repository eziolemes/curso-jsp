package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) {
		try {
			String sql = "insert into usuario(login, senha, nome, fone, cep, rua, bairro, cidade, estado, ibge, fotobase64, contenttype, curriculoBase64, contentTypeCurriculo, fotoBase64Miniatura) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getFone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getEstado());
			insert.setString(10, usuario.getIbge());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContentTypeCurriculo());
			insert.setString(15, usuario.getFotoBase64Miniatura());
			
			insert.execute();
			connection.commit();
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanCursoJsp> listar() throws Exception {
		List<BeanCursoJsp> lista = new ArrayList<BeanCursoJsp>();
		String sql = "select * from usuario where login <> 'admin'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(rs.getLong("id"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setNome(rs.getString("nome"));
			usuario.setFone(rs.getString("fone"));
			usuario.setCep(rs.getString("cep"));
			usuario.setRua(rs.getString("rua"));
			usuario.setBairro(rs.getString("bairro"));
			usuario.setCidade(rs.getString("cidade"));
			usuario.setEstado(rs.getString("estado"));
			usuario.setIbge(rs.getString("ibge"));
//			usuario.setFotoBase64(rs.getString("fotobase64"));
			usuario.setFotoBase64Miniatura(rs.getString("fotobase64miniatura"));
			usuario.setContentType(rs.getString("contenttype"));
			usuario.setCurriculoBase64(rs.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(rs.getString("contenttypecurriculo"));

			lista.add(usuario);
		}

		return lista;
	}

	public void delete(Integer id) {

		try {
			String sql = "delete from usuario where id = ? and login <> 'admin'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();

			connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultar(String id) throws SQLException{
		String sql = "select * from usuario where id = '" + id + "' and login <> 'admin'";

		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(rs.getLong("id"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setNome(rs.getString("nome"));
			usuario.setFone(rs.getString("fone"));
			usuario.setCep(rs.getString("cep"));
			usuario.setRua(rs.getString("rua"));
			usuario.setBairro(rs.getString("bairro"));
			usuario.setCidade(rs.getString("cidade"));
			usuario.setEstado(rs.getString("estado"));
			usuario.setIbge(rs.getString("ibge"));
			usuario.setFotoBase64(rs.getString("fotobase64"));
			usuario.setFotoBase64Miniatura(rs.getString("fotobase64miniatura"));
			usuario.setContentType(rs.getString("contenttype"));
			usuario.setCurriculoBase64(rs.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(rs.getString("contenttypecurriculo"));

			return usuario;
		}
		return null;
	}
	
	public boolean validarLogin(String login) throws SQLException{
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";

		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			return rs.getInt("qtd") <= 0;
		}
		return false;
	}
	
	public boolean validarLoginUpdate(String login, String id) throws SQLException{
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> " + id;

		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			return rs.getInt("qtd") <= 0;
		}
		return false;
	}

	public void atualizar(BeanCursoJsp usuario) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("update usuario set login=?, senha=?, nome=?, fone=?,cep=?,rua=?,bairro=?,cidade=?,estado=?,ibge=?,");

			if(usuario.isAtualizarImagem()) {
				sql.append("fotobase64=?,contenttype=?,");
			}
			
			sql.append("curriculobase64=?,contenttypecurriculo=?,fotobase64miniatura=?  where id=?");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getNome());
			ps.setString(4, usuario.getFone());
			ps.setString(5, usuario.getCep());
			ps.setString(6, usuario.getRua());
			ps.setString(7, usuario.getBairro());
			ps.setString(8, usuario.getCidade());
			ps.setString(9, usuario.getEstado());
			ps.setString(10, usuario.getIbge());
			ps.setString(11, usuario.getFotoBase64());
			ps.setString(12, usuario.getContentType());
			ps.setString(13, usuario.getCurriculoBase64());
			ps.setString(14, usuario.getContentTypeCurriculo());
			ps.setString(15, usuario.getFotoBase64Miniatura());
			ps.setLong(16, usuario.getId());

			ps.executeUpdate();
			connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha='" + senha + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			
			return resultSet.getInt("qtd") <= 0;/*Return true*/
		}
		return false;
	}
}