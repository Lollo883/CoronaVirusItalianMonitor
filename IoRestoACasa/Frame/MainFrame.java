import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import java.awt.Component;

public class MainFrame {

	private JFrame frmRestiamoUniti;
	private JLabel lblPlaceholder_1;
	private JLabel lblPlaceholder_1_1;
	private JLabel lblGuariti;
	private JLabel lblPlaceholder;
	private JLabel lblNewLabel;
	private DataFinder dataFinder;
	private DailyData todayData;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmRestiamoUniti.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		dataFinder = new DataFinder();
		todayData = dataFinder.getLastDayInfo();
		frmRestiamoUniti = new JFrame();
		frmRestiamoUniti.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/img/virus (1).png")));
		frmRestiamoUniti.setTitle("Coronavirus - Italian Free Monitor");
		frmRestiamoUniti.setForeground(new Color(0, 0, 0));
		frmRestiamoUniti.setResizable(false);
		frmRestiamoUniti.setBounds(100, 100, 600, 330);
		frmRestiamoUniti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRestiamoUniti.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 93, 180, 161);
		frmRestiamoUniti.getContentPane().add(panel);
		
		lblGuariti = new JLabel("Guariti");
		lblGuariti.setFont(new Font("Agency FB", Font.BOLD, 50));
		panel.add(lblGuariti);
		
		lblPlaceholder = new JLabel(String.valueOf(todayData.getHealed()));
		lblPlaceholder.setFont(new Font("Agency FB", Font.PLAIN, 50));
		lblPlaceholder.setForeground(new Color(0, 100, 0));
		panel.add(lblPlaceholder);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(200, 93, 194, 161);
		frmRestiamoUniti.getContentPane().add(panel_1);
		
		JLabel lblGuariti_1 = new JLabel("  Totali  ");
		lblGuariti_1.setFont(new Font("Agency FB", Font.BOLD, 50));
		panel_1.add(lblGuariti_1);
		
		lblPlaceholder_1 = new JLabel(String.valueOf(todayData.getTotalCases()));
		lblPlaceholder_1.setForeground(new Color(0, 0, 139));
		lblPlaceholder_1.setFont(new Font("Agency FB", Font.PLAIN, 50));
		panel_1.add(lblPlaceholder_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(404, 93, 180, 161);
		frmRestiamoUniti.getContentPane().add(panel_2);
		
		JLabel lblGuariti_2 = new JLabel("Deceduti");
		lblGuariti_2.setFont(new Font("Agency FB", Font.BOLD, 50));
		panel_2.add(lblGuariti_2);
		
		lblPlaceholder_1_1 = new JLabel(String.valueOf(todayData.getDead()));
		lblPlaceholder_1_1.setForeground(new Color(139, 0, 0));
		lblPlaceholder_1_1.setFont(new Font("Agency FB", Font.PLAIN, 50));
		panel_2.add(lblPlaceholder_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(404, 28, 180, 48);
		frmRestiamoUniti.getContentPane().add(panel_3);
		
		JLabel lblWe = new JLabel("Aggiornamento del:");
		lblWe.setFont(new Font("Agency FB", Font.BOLD, 15));
		panel_3.add(lblWe);
		
		lblNewLabel = new JLabel(todayData.getData());
		lblNewLabel.setFont(new Font("Agency FB", Font.BOLD, 15));
		panel_3.add(lblNewLabel);
		
		JButton btnAggiorna = new JButton("Aggiorna");
		btnAggiorna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dataFinder = new DataFinder();
				todayData = dataFinder.getLastDayInfo();
				
				lblPlaceholder.setText(String.valueOf(todayData.getHealed()));

				lblPlaceholder_1.setText(String.valueOf(todayData.getTotalCases()));
				
				lblPlaceholder_1_1.setText(String.valueOf(todayData.getDead()));

				lblNewLabel.setText(todayData.getData());

				
			}
		});
		btnAggiorna.setBounds(143, 265, 89, 23);
		frmRestiamoUniti.getContentPane().add(btnAggiorna);
		
		JButton btnVaiAlDettaglio = new JButton("Vai al Dettaglio");
		btnVaiAlDettaglio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataFinder.setRegion("");
				DetailFrame detailFrame = new DetailFrame();
				detailFrame.setRegion("TotaleItalia");
				detailFrame.setVisible(true);
			}
		});
		btnVaiAlDettaglio.setBounds(10, 265, 123, 23);
		frmRestiamoUniti.getContentPane().add(btnVaiAlDettaglio);
		
		JButton btnCrediti = new JButton("Crediti");
		btnCrediti.setBounds(242, 265, 89, 23);
		frmRestiamoUniti.getContentPane().add(btnCrediti);
		
		comboBox = new JComboBox();
		comboBox.setRequestFocusEnabled(false);
		comboBox.setLightWeightPopupEnabled(false);
		comboBox.setFocusable(false);
		comboBox.setAutoscrolls(true);
		comboBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setToolTipText("Seleziona una regione");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Abruzzo", "Basilicata", "Calabria", "Campania", "Emilia Romagna", "Friuli Venezia Giulia", "Lazio", "Liguria", "Lombardia", "Marche", "Molise", "P.A. Bolzano", "P.A. Trento", "Piemonte", "Puglia", "Sardegna", "Sicilia", "Toscana", "Umbria", "Valle d'Aosta", "Veneto"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(10, 28, 123, 20);
		frmRestiamoUniti.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Vai alla Regione");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String region=comboBox.getSelectedItem().toString();
				DataFinder.setRegion(region);
				DetailFrame detailFrame = new DetailFrame();
				detailFrame.setRegion(region);
				detailFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 53, 125, 23);
		frmRestiamoUniti.getContentPane().add(btnNewButton);
	}
}
