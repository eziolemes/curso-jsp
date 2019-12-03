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
			String sql = "insert into usuario(login, senha, nome) values (?,?,?)";

			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
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
		String sql = "select * from usuario";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(rs.getLong("id"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setNome(rs.getString("nome"));

			lista.add(usuario);
		}

		return lista;
	}

	public void delete(Integer id) {

		try {
			String sql = "delete from usuario where id = ?";
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
		String sql = "select * from usuario where id = '" + id + "'";

		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(rs.getLong("id"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setNome(rs.getString("nome"));

			return usuario;
		}
		return null;
	}

	public void atualizar(BeanCursoJsp usuario) {
		try {
			String sql = "update usuario set login=?, senha=?, nome=? where id=?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getNome());
			ps.setLong(4, usuario.getId());

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
}