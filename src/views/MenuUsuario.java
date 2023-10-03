package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.SystemColor;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class MenuUsuario extends JFrame {

	private JPanel contentPane;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelRegistro;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuUsuario frame = new MenuUsuario();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MenuUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 609);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		// HEADER BAR
		JPanel header = new JPanel();
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
		
		// EXIT X
		JPanel btnexit = new JPanel();
		btnexit.setBounds(891, 0, 53, 36);
		btnexit.setBackground(Color.WHITE);
		btnexit.setLayout(null);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnexit.add(labelExit);
		
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		
		
		
		// PANEL MENU
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(12, 138, 199));
		panelMenu.setBounds(0, 0, 257, 609);
		panelMenu.setLayout(null);
		contentPane.add(panelMenu);
		
		// LOGO HOTEL
		JLabel lblLogoHotel = new JLabel("");
		lblLogoHotel.setBounds(50, 58, 150, 150);
		panelMenu.add(lblLogoHotel);
		lblLogoHotel.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/aH-150px.png")));
		
		// SEPARATOR
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 219, 201, 2);
		panelMenu.add(separator);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 944, 36);
		header.setLayout(null);
		contentPane.add(header);
		
		// REGISTRO DE RESERVAS
		JPanel btnRegistro = new JPanel();
		btnRegistro.setBackground(new Color(12, 138, 199));
		btnRegistro.setBounds(0, 255, 257, 56);
		btnRegistro.setLayout(null);
		panelMenu.add(btnRegistro);
		
		labelRegistro = new JLabel("Registro de reservas");
		labelRegistro.setBounds(25, 11, 205, 34);
		labelRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelRegistro.setForeground(SystemColor.text);
		labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		labelRegistro.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/reservado.png")));
		btnRegistro.add(labelRegistro);
		
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistro.setBackground(new Color(118, 187, 223));				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistro.setBackground(new Color(12, 138, 199));	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();
			}
		});
		
		// BUSQUEDA
		JPanel btnBusqueda = new JPanel();
		btnBusqueda.setBounds(0, 312, 257, 56);
		btnBusqueda.setBackground(new Color(12, 138, 199));
		btnBusqueda.setLayout(null);
		panelMenu.add(btnBusqueda);
		
		JLabel lblBusquedaDeReservas = new JLabel("Búsqueda");
		lblBusquedaDeReservas.setBounds(27, 11, 200, 34);
		lblBusquedaDeReservas.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblBusquedaDeReservas.setForeground(Color.WHITE);
		lblBusquedaDeReservas.setHorizontalAlignment(SwingConstants.LEFT);
		lblBusquedaDeReservas.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/pessoas.png")));
		btnBusqueda.add(lblBusquedaDeReservas);
		
		btnBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBusqueda.setBackground(new Color(118, 187, 223));				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBusqueda.setBackground(new Color(12, 138, 199));	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Busqueda busqueda = new Busqueda();
				busqueda.setVisible(true);
				dispose();
			}
		});
		
		// PANEL FECHA
	    JPanel panelFecha = new JPanel();
	    panelFecha.setBackground(new Color(118, 187, 223));
	    panelFecha.setBounds(256, 84, 688, 121);
	    panelFecha.setLayout(null);
	    contentPane.add(panelFecha);
	    
	    JLabel lblSistemaDeReservas = new JLabel("Sistema de Reservas Hotel Alura");
	    lblSistemaDeReservas.setBounds(180, 11, 356, 42);
	    lblSistemaDeReservas.setFont(new Font("Roboto", Font.PLAIN, 24));
	    lblSistemaDeReservas.setForeground(Color.WHITE);
	    panelFecha.add(lblSistemaDeReservas);
	    
	    JLabel labelFecha = new JLabel("New label");
	    labelFecha.setBounds(35, 64, 294, 36);
	    labelFecha.setFont(new Font("Roboto", Font.PLAIN, 33));
	    labelFecha.setForeground(Color.WHITE);
	    panelFecha.add(labelFecha);
	    
	    Date fechaActual = new Date(); //fecha y hora actual
	    String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual); //formatear la fecha en una cadena
	    labelFecha.setText("Hoy es " + fecha); //setear la representacion en cadena de la fecha
	    
	    JLabel lblBienvenido = new JLabel("Bienvenido");
	    lblBienvenido.setBounds(302, 234, 147, 46);
	    lblBienvenido.setFont(new Font("Roboto", Font.BOLD, 24));
	    contentPane.add(lblBienvenido);
	    
	    String textoDescripcion = "<html><body>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel   </body></html>";
	    JLabel labelDescripcion = new JLabel(textoDescripcion);
	    labelDescripcion.setBounds(312, 291, 598, 66);
	    labelDescripcion.setFont(new Font("Roboto", Font.PLAIN, 17));
	    contentPane.add(labelDescripcion);
	    
	    String textoDescripcion1 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus reservas y huéspedes, tendrá acceso a heramientas especiales para tareas específicas como lo son:</body></html>";
	    JLabel labelDescripcion_1 = new JLabel(textoDescripcion1);
	    labelDescripcion_1.setBounds(311, 345, 569, 88);
	    labelDescripcion_1.setFont(new Font("Roboto", Font.PLAIN, 17));
	    contentPane.add(labelDescripcion_1);
	    
	    JLabel lblRegistro = new JLabel("- Registro de Reservas y Huéspedes");
	    lblRegistro.setBounds(312, 444, 295, 27);
	    lblRegistro.setFont(new Font("Roboto", Font.PLAIN, 17));
	    contentPane.add(lblRegistro);
	    
	    JLabel lblEdicion = new JLabel("- Edición de Reservas y Huéspedes existentes");
	    lblEdicion.setBounds(312, 482, 355, 27);
	    lblEdicion.setFont(new Font("Roboto", Font.PLAIN, 17));
	    contentPane.add(lblEdicion);
	    
	    JLabel lblEliminacion = new JLabel("- Eliminación de todo tipo de registros");
	    lblEliminacion.setBounds(312, 520, 295, 27);
	    lblEliminacion.setFont(new Font("Roboto", Font.PLAIN, 17));
	    contentPane.add(lblEliminacion);
	}
	
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
