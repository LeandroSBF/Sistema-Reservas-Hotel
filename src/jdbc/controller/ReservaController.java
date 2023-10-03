package jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jdbc.dao.ReservaDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		Connection conexion= new ConnectionFactory().recuperarConexion();
		this.reservaDAO= new ReservaDAO(conexion);
	}
	
	public void guardarReserva(Reserva reserva) {
		this.reservaDAO.guardarReserva(reserva);
	}
	
	public List<Reserva> buscarReserva(){
		return this.reservaDAO.buscarReserva();
	}
	
	public List<Reserva> buscarReservaId(String id){
		return this.reservaDAO.buscarReservaId(id);
	}
	
	public void actualizarReserva(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
		this.reservaDAO.actualizarReserva(fechaE, fechaS, valor, formaPago, id);
	}
	
	public void eliminarReserva(Integer id) {
		this.reservaDAO.eliminarReserva(id);
	}
	
}
