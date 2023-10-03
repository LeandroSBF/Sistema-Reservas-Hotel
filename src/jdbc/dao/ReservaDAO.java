package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Reserva;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardarReserva(Reserva reserva) {
		String sql = "INSERT INTO reserva (fecha_entrada, fecha_salida, valor, forma_pago) VALUES (?, ?, ?, ?)";
		try {
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){				
				pstm.setDate(1, reserva.getFechaE());
				pstm.setDate(2, reserva.getFechaS());
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPago());
							
				pstm.executeUpdate();
							
				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
					reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarReserva() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reserva";
		try {
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarReservaId(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reserva WHERE id = ?";		
		try {
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();
				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void actualizarReserva(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
		String sql = "UPDATE reserva SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE id = ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setDate(1, fechaE);
			pstm.setDate(2, fechaS);
			pstm.setString(3, valor);
			pstm.setString(4, formaPago);
			pstm.setInt(5, id);
			
			pstm.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminarReserva(Integer id) {
		String sql = "DELETE FROM reserva WHERE id = ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try(ResultSet rsts = pstm.getResultSet()){
			while(rsts.next()) {
				Reserva datos = new Reserva(
						rsts.getInt(1),  
						rsts.getDate(2), 
						rsts.getDate(3), 	
						rsts.getString(4), 
						rsts.getString(5));
				reservas.add(datos);
			}
		}
	}
	
}
