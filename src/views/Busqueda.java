package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jdbc.controller.HuespedController;
import jdbc.controller.ReservaController;
import jdbc.modelo.Huesped;
import jdbc.modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.Optional;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private JLabel lblAtras;
	private JLabel lblExit;
	private JLabel body;
    private JTabbedPane tabsPanel;
	int xMouse, yMouse;
	String reserva;
	private ReservaController reservaController;
	private HuespedController huespedController;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Busqueda frame = new Busqueda();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/*
	 * Create the frame
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		
		// CONTENT PANE & BODY
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setBounds(100, 100, 910, 571);
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		
		// HEADER
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.setBackground(Color.WHITE);
		header.setLayout(null);
		contentPane.add(header);
		
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);	     
			}
		});
		
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		
		// < ATRÁS
		JPanel btnAtras = new JPanel();
		btnAtras.setBounds(0, 0, 53, 36);
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		header.add(btnAtras);
		
		lblAtras = new JLabel("<");
		lblAtras.setBounds(0, 0, 53, 36);
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(new Color(12, 138, 199));
		lblAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		btnAtras.add(lblAtras);
		setResizable(false);
		
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				lblAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     lblAtras.setForeground(Color.black);
			}
		});
		
		//EXIT
		JPanel btnexit = new JPanel();
		btnexit.setBounds(857, 0, 53, 36);
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		header.add(btnexit);
		
		lblExit = new JLabel("X");
		lblExit.setBounds(0, 0, 53, 36);
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnexit.add(lblExit);
		setResizable(false);
		
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) { // BOTÓN CAMBIA DE COLOR AL PASAR EL MOUSE POR ARRIBA
				btnexit.setBackground(Color.red);
				lblExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { // BOTÓN VUELVE A COLOR ORIGINAL AL MOVER EL MOUSE HACIA OTRA PARTE DE LA PANTALLA
				 btnexit.setBackground(Color.white);
			     lblExit.setForeground(Color.black);
			}
		});

		// LOGO HOTEL
		JLabel lblLogoHotel = new JLabel("");
		lblLogoHotel.setBounds(56, 51, 104, 107);
		lblLogoHotel.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		contentPane.add(lblLogoHotel);


		// SISTEMA DE BÚSQUEDA
		JLabel lblSistemaDeBusqueda = new JLabel("SISTEMA DE BÚSQUEDA");
		lblSistemaDeBusqueda.setBounds(331, 62, 280, 42);
		lblSistemaDeBusqueda.setForeground(new Color(12, 138, 199));
		lblSistemaDeBusqueda.setFont(new Font("Dialog", Font.BOLD, 22));
		contentPane.add(lblSistemaDeBusqueda);
		
		// PANEL DE RESULTADOS
		JTabbedPane tabsPanel = new JTabbedPane(JTabbedPane.TOP);
		tabsPanel.setBounds(20, 169, 865, 328);
		tabsPanel.setBackground(new Color(12, 138, 199));
		tabsPanel.setFont(new Font("Roboto", Font.PLAIN, 16));
		contentPane.add(tabsPanel);

        tablaReservas(); // ACTUALIZA LISTADO RESERVAS
        tablaHuespedes();// ACTUALIZA LISTADO HUÉSPEDES	
		
        JScrollPane scroll_tableReservas = new JScrollPane(tbReservas);
        tabsPanel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_tableReservas, null);
        scroll_tableReservas.setVisible(true);		
        
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		tabsPanel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		// BUSCAR		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBuscar.setColumns(10);
		contentPane.add(txtBuscar);

		JSeparator separator = new JSeparator();
		separator.setBounds(539, 159, 193, 2);
		separator.setForeground(new Color(12, 138, 199));
		separator.setBackground(new Color(12, 138, 199));
		contentPane.add(separator);	
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);  
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnbuscar.add(lblBuscar);
		setResizable(false);
		
		btnbuscar.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (tabsPanel.getSelectedIndex() == 0) {    // SELECCIONA TAB RESERVAS
							limpiarTablaReservas();
							limpiarTablaHuespedes();
							cargarTablaReservas();
						} else if (tabsPanel.getSelectedIndex() == 1) {   // SELECCIONA TAB HUÉSPEDES
							limpiarTablaHuespedes();
							limpiarTablaReservas();
							cargarTablaHuespedes();
                    	}
					}
		});				
        
		//EDITAR
		JPanel btnEditar = new JPanel();
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setBounds(0, 0, 122, 35);
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditar.add(lblEditar);
		setResizable(false);
		
		btnEditar.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (tabsPanel.getSelectedIndex() == 0) {    // SELECCIONA TAB RESERVAS
							modificarReserva();				
							limpiarTablaReservas();
							cargarTablaReservas();
						} else if (tabsPanel.getSelectedIndex() == 1) {   // SELECCIONA TAB HUÉSPEDES
							modificarHuesped();
							limpiarTablaHuespedes();
							cargarTablaHuespedes();
                    	}
					}});		
		
		//ELIMINAR		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setBounds(0, 0, 122, 35);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (tabsPanel.getSelectedIndex() == 0) {
                    eliminarReserva();
                    limpiarTablaReservas();
                    cargarTablaReservas();
                } else if (tabsPanel.getSelectedIndex() == 1) {
                    eliminarHuesped();
                    limpiarTablaHuespedes();
                    cargarTablaHuespedes();
                }
            }
			});
	}

	// TABLA RESERVAS
    private void tablaReservas() {
        tbReservas = new JTable();
    	tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    	tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modeloReserva = (DefaultTableModel) tbReservas.getModel();
        modeloReserva.addColumn("Número de Reserva");
        modeloReserva.addColumn("Fecha Check In");
        modeloReserva.addColumn("Fecha Check Out");
        modeloReserva.addColumn("Valor");
        modeloReserva.addColumn("Forma de Pago"); 
    }    
    
    // CARGAR TABLA RESERVAS
    private void cargarTablaReservas() {
    	List<Reserva> reserva = buscarReservas();
    	try {
    		for (Reserva reservas : reserva) {
    			modeloReserva.addRow(
    					new Object[] {
    							reservas.getId(), 
    							reservas.getFechaE(),
    							reservas.getFechaS(), 
    							reservas.getValor(), 
    							reservas.getFormaPago() });
    		}
    	} catch (Exception e) {
    		throw e;
    	}
    }
    
    // CARGAR TABLA RESERVAS POR ID *** FALTA DESARROLLO ***
    private void cargarTablaReservasPorId(String id) {
    	List<Reserva> reserva = buscarReservasId(id);
    	try {
    		for (Reserva reservas : reserva) {
    			modeloReserva.addRow(
    					new Object[] {
    							reservas.getId(), 
    							reservas.getFechaE(),
    							reservas.getFechaS(), 
    							reservas.getValor(), 
    							reservas.getFormaPago() });
    		}
    	} catch (Exception e) {
    		throw e;
    	}
    }

    // BUSCAR RESERVAS    
    private List<Reserva> buscarReservas() {
    	return this.reservaController.buscarReserva();
    }
    
    // BUSCAR RESERVAS POR ID
	private List<Reserva> buscarReservasId(String id) {
		return this.reservaController.buscarReservaId(txtBuscar.getText());
	}
        
    // ELECCIÓN RESERVA DE LA TABLA
   private Boolean elegirFilaTablaReservas(){
       return tbReservas.getSelectionModel().isSelectionEmpty();
       }
   
   // ELIMINAR RESERVA   
   private void eliminarReserva(){
       if(elegirFilaTablaReservas()){
           JOptionPane.showMessageDialog(this,"Por favor, elije una reserva a eliminar");
           return; 
       }
       Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(),tbReservas.getSelectedColumn()))
               .ifPresentOrElse(
            		   fila ->{
            			   Integer id= Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),0).toString());
            		        int n = JOptionPane.showConfirmDialog(this, "ATENCIÓN: \n ¿Realmente quieres eliminar la reserva? \n Esta acción no se puede deshacer!",
            		                "OK_CANCEL_OPTION", JOptionPane.OK_CANCEL_OPTION,
            		                JOptionPane.INFORMATION_MESSAGE);
            		        if ( n == 0) {
            		        	this.reservaController.eliminarReserva(id);
                 			   	modeloReserva.removeRow(tbReservas.getSelectedRow());                
                 			   	JOptionPane.showMessageDialog(this, " ¡Reserva eliminada con exito!");
            		        } else {
            		        }
            		   },
            		   () -> JOptionPane.showMessageDialog(this,"Por favor, elije una reserva a eliminar"));
   }
   
   // MODIFICAR RESERVA
    private void modificarReserva(){
       if(elegirFilaTablaReservas()){
           JOptionPane.showMessageDialog(this,"Por favor, elije una reserva a modificar,\nrealiza la modificación en la tabla y\nhaz click en editar para guardar la nueva información");
           return;
       } else {
           Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
           .ifPresentOrElse(
           		fila ->{
           			Integer id= Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),0).toString());
           			Date fechaE = Date.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),1).toString());
           			Date fechaS = Date.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),2).toString());
           			String valor= String.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(),3).toString());
           			String formaPago= (String)modeloReserva.getValueAt(tbReservas.getSelectedRow(),4);
           			
           			// DESARROLLAR ACTUALIZACIÓN DE VALOR EN CASO DE MODIFICACIÓN DE FECHAS
           			
           			int n = JOptionPane.showConfirmDialog(this, "ATENCIÓN: \n ¿Realmente quieres aplicar los cambios a la reserva?",
       		                "OK_CANCEL_OPTION", JOptionPane.OK_CANCEL_OPTION,
       		                JOptionPane.INFORMATION_MESSAGE);
       		        if ( n == 0) {
               			this.reservaController.actualizarReserva(fechaE, fechaS, valor, formaPago, id);
               			JOptionPane.showMessageDialog(this,String.format("¡Reserva modificada con exito!"));
       		        	limpiarTablaReservas();
               			cargarTablaReservas();
       		        } else {
       		        }
           		},
           		() -> JOptionPane.showMessageDialog(this, "Por favor, elije una reserva a modificar,\nrealiza la modificación en la tabla y\nhaz click en editar para guardar la nueva información")
           );
       }
    }
    
	// LIMPIAR TABLA RESERVAS
    private void limpiarTablaReservas(){
        modeloReserva.getDataVector().clear();
    }
    public void limpiarTablaR(){
        limpiarTablaReservas();
    }
    
    // TABLA HUÉSPEDES
    private void tablaHuespedes() {
        tbHuespedes = new JTable();        
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");   
    }
    
    // CARGAR TABLA HUÉSPEDES
    private void cargarTablaHuespedes() {
    	List<Huesped> huesped = buscarHuespedes();
    	try {
    		for (Huesped huespedes : huesped) {
    			modeloHuesped.addRow(
    					new Object[] {
    							huespedes.getId(), 
    							huespedes.getNombre(),
    							huespedes.getApellido(),
    							huespedes.getFechaDeNacimiento(),
    							huespedes.getNacionalidad(),
    							huespedes.getTelefono(),
    							huespedes.getIdReserva() });
    		}
    	} catch (Exception e) {
    		throw e;
    	}
    }

    // CARGAR TABLA HUÉSPEDES BUSCADOS POR ID  *** FALTA DESARROLLAR ***
    private void cargarTablaHuespedesPorId(String id) {
        List<Huesped> huesped = buscarHuespedId(id);
        try {
        	for (Huesped huespedes : huesped) {
        		modeloReserva.addRow(
        				new Object[] {
        						huespedes.getId(), 
        						huespedes.getNombre(),
        						huespedes.getApellido(),
        						huespedes.getFechaDeNacimiento(),
        						huespedes.getNacionalidad(),
        						huespedes.getTelefono(),
        						huespedes.getIdReserva() });
        	}
    	} catch (Exception e) {
    		throw e;
    	}
    }
    
    // BUSCAR HUÉSPED
    private List<Huesped> buscarHuespedes() {
    	return this.huespedController.buscarHuesped();
    }
    
	// BUSCAR HUÉSPED POR ID
	private List<Huesped> buscarHuespedId(String id) {
		return this.huespedController.buscarHuespedId(txtBuscar.getText());
	}

    // ELECCIÓN HUÉSPED DE LA TABLA
    private Boolean elegirFilaTablaHuespedes() {
    	return tbHuespedes.getSelectionModel().isSelectionEmpty();
    } 
    
    // MODIFICAR HUÉSPED
    private void modificarHuesped() {
        if(elegirFilaTablaHuespedes()) {
        	JOptionPane.showMessageDialog(this,"Por favor, elige unl registro a modificar,\nrealiza la modificación en la tabla y\nhaz click en editar para guardar la nueva información");
        	return;
        } Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(
                		fila -> {
                			Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                			String nombre = (String) String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1));
                			String apellido = (String) String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2));
                			Date fechaDeNacimiento = Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
                			String nacionalidad = (String) String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4));
                			String telefono = (String) String.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5));
                			Integer idReserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

                			this.huespedController.actualizarHuesped(nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, idReserva, id);
                    
                			JOptionPane.showMessageDialog(this, String.format("¡Registro modificado con exito!"));
                		}, 
                		() -> JOptionPane.showMessageDialog(this,"Por favor, elige unl registro a modificar,\nrealiza la modificación en la tabla y \nhaz click en editar para guardar la nueva información")
                );
    }
    
    // ELIMINAR REGISTRO HUÉSPED
    private void eliminarHuesped() {
        if (elegirFilaTablaHuespedes()) {
            JOptionPane.showMessageDialog(this,"Por favor, elige un registro a eliminar");
            return;
        }
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila ->{
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),0).toString());
    		        int n = JOptionPane.showConfirmDialog(this, "ATENCIÓN: \n ¿Realmente quieres eliminar el registro? \n Esta acción no se puede deshacer!",
    		                "OK_CANCEL_OPTION", JOptionPane.OK_CANCEL_OPTION,
    		                JOptionPane.INFORMATION_MESSAGE);
    		        if ( n == 0) {
    		        	this.huespedController.eliminarHuesped(id);
         			   	modeloHuesped.removeRow(tbHuespedes.getSelectedRow());                
         			   	JOptionPane.showMessageDialog(this, " ¡Registro eliminado con exito!");
    		        } else {
    		        }
                },() ->JOptionPane.showMessageDialog(this,"Por favor, elige un registro a eliminar"));
    }

	// LIMPIAR TABLA HUÉSPEDES
    private void limpiarTablaHuespedes(){
        modeloHuesped.getDataVector().clear();
    }
    public void limpiarTablaH() {
        limpiarTablaHuespedes();
    }
    
	public void tablaR() {
		cargarTablaReservas();
	}
    
    public void tablaH(){
        cargarTablaHuespedes();
    }
    
	// MOVER VENTANA POR PANTALLA SEGÚN VALORES "X" "Y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	}
	
}
