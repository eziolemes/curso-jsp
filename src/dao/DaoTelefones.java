package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.Telefones;
import connection.SingleConnection;

public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Telefones telefone) {

		try {

			String sql = "insert into telefone(numero, tipo, usuario) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<Telefones> listar(Long user) throws Exception {
		List<Telefones> listar = new ArrayList<Telefones>();

		String sql = "select * from telefone where usuario=" + user;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {

			Telefones telefone = new Telefones();
			
			telefone.setId(rs.getLong("id"));
			telefone.setNumero(rs.getString("numero"));
			telefone.setTipo(rs.getString("tipo"));
			telefone.setUsuario(rs.getLong("usuario"));
			
			listar.add(telefone);
		}

		return listar;
	}

	public void delete(String id) {

		try {
			String sql = "delete from telefone where id = '" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

}