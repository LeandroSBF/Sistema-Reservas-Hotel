package jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jdbc.dao.HuespedDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Huesped;

public class HuespedController {

	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		Connection conexion= new ConnectionFactory().recuperarConexion();
		this.huespedDAO= new HuespedDAO(conexion);
	}
	
	public void guardarHuesped(Huesped huesped) {
		this.huespedDAO.guardarHuesped(huesped);
	}
	
	public List<Huesped> buscarHuesped(){
		return this.huespedDAO.buscarHuesped();
	}
	
	public List<Huesped> buscarHuespedId(String id){
		return this.huespedDAO.buscarHuespedId(id);
	}
	
	public void actualizarHuesped(String nombre, String apellido, Date fechaDeNacimiento, 
			String Nacionalidad, String telefono, Integer idReserva, Integer id) {
		this.huespedDAO.actualizarHuesped(nombre, apellido, fechaDeNacimiento, 
				Nacionalidad, telefono, idReserva, id);
	}
	
	public void eliminarHuesped(Integer id) {
		this.huespedDAO.eliminarHuesped(id);
	}
	 
	
}
