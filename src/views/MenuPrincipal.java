package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblExit;
	int xMouse, yMouse;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 910, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		// MAIN PANEL
		Panel panel = new Panel();
		panel.setBackground(SystemColor.window);
		panel.setBounds(0, 0, 910, 537);
		panel.setLayout(null);
		contentPane.add(panel);
		
		// HEADER BAR
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.setBackground(Color.WHITE);
		header.setLayout(null);
		panel.add(header);
		
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
		
		// BOTON SALIR X
		JPanel btnexit = new JPanel();
		btnexit.setBounds(857, 0, 53, 36);
		btnexit.setBackground(Color.WHITE);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setLayout(null);
		header.add(btnexit);
		
		lblExit = new JLabel("X");
		lblExit.setBounds(0, 0, 53, 36);
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnexit.add(lblExit);
		
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				lblExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     lblExit.setForeground(Color.black);
			}
		});	
		
		// IMAGEN PRINCIPAL
		JLabel imagenPrincipal = new JLabel("");
		imagenPrincipal.setBounds(-50, 0, 732, 501);
		imagenPrincipal.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/menu-img.png")));
		panel.add(imagenPrincipal);
		
		// LOGO
		JLabel logo = new JLabel("");
		logo.setBounds(722, 80, 150, 156);
		logo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/aH-150px.png")));
		panel.add(logo);
		
		// BOTÓN LOGIN
		JPanel btnLogin = new JPanel(); 
		btnLogin.setBounds(754, 300, 83, 70);
		btnLogin.setBackground(SystemColor.window);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLogin.setLayout(null);
		panel.add(btnLogin);
	
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(754, 265, 83, 24);
		lblLogin.setBackground(SystemColor.window);
		panel.add(lblLogin);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(SystemColor.textHighlight);
		lblLogin.setFont(new Font("Roboto Light", Font.PLAIN, 20));
	
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
			
		JLabel imagenLogin = new JLabel("");
		imagenLogin.setBounds(0, 0, 80, 70);
		imagenLogin.setHorizontalAlignment(SwingConstants.CENTER);
		imagenLogin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/login.png")));
		btnLogin.add(imagenLogin);
		
		// FOOT PANEL
		JPanel footPanel = new JPanel();
		footPanel.setBounds(0, 500, 910, 37);
		footPanel.setBackground(new Color(12, 138, 199));
		footPanel.setLayout(null);
		panel.add(footPanel);
		
		// FOOT
		JLabel lblCopyR = new JLabel("Desarrollado por LeandroSBF © 2023");
		lblCopyR.setBounds(315, 11, 284, 19);
		lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblCopyR.setForeground(new Color(240, 248, 255));
		footPanel.add(lblCopyR);		
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