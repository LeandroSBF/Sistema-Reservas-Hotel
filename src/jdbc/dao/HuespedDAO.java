package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Huesped;

public class HuespedDAO {

	private Connection connection;
	
	public HuespedDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardarHuesped(Huesped huesped) {
		String sql = "INSERT INTO huesped (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?,?,?)";
		try {			
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, huesped.getNombre());
				pstm.setString(2, huesped.getApellido());
				pstm.setDate(3, huesped.getFechaDeNacimiento());
				pstm.setString(4, huesped.getNacionalidad());
				pstm.setString(5, huesped.getTelefono());
				pstm.setInt(6, huesped.getIdReserva());  // esto da error por ser null

				pstm.execute();

				try (ResultSet rsts = pstm.getGeneratedKeys()) {
					while (rsts.next()) {
						huesped.setId(rsts.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Huesped> buscarHuesped() {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huesped";
		try {
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> buscarHuespedId(String id) {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huesped WHERE id_reserva = ?";
		try {
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void actualizarHuesped(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer id_reserva, Integer id) {
		try (PreparedStatement pstm = connection
				.prepareStatement("UPDATE huesped SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id = ?")) {
			pstm.setString(1, nombre);
			pstm.setString(2, apellido);
			pstm.setDate(3, fechaN);
			pstm.setString(4, nacionalidad);
			pstm.setString(5, telefono);
			pstm.setInt(6, id_reserva);
			pstm.setInt(7, id);
			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminarHuesped(Integer id) {
		try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM huesped WHERE id = ?")) {
			pstm.setInt(1, id);
			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEnHuesped(List<Huesped> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rsts = pstm.getResultSet()) {
			while (rsts.next()) {
				Huesped huesped = new Huesped(rsts.getInt(1), rsts.getString(2), rsts.getString(3), rsts.getDate(4), rsts.getString(5), rsts.getString(6), rsts.getInt(7));
				reservas.add(huesped);
			}
		}				
	}
	
}
