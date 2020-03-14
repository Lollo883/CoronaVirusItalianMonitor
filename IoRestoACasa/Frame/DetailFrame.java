import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class DetailFrame {

	private JFrame frmDayByDay;
	private JTable table;
	private String region = "" ;
	private DataFinder dataFinder = new DataFinder();
	private ArrayList<DailyData> data;
	private String title="";
	

	/**
	 * Launch the application.
	 */
	
	public void setRegion(String region)
	{
		this.region=region;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailFrame window = new DetailFrame();
					window.frmDayByDay.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DetailFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		
		region=dataFinder.getRegion();
		if (region.equals("") || region.equals("TotaleItalia"))
		{
			data = dataFinder.getData();
		}
		else
		{
			data = dataFinder.getRegionData(region);
		}
		frmDayByDay = new JFrame();
		frmDayByDay.setTitle("Day by Day Table");
		frmDayByDay.setIconImage(Toolkit.getDefaultToolkit().getImage(DetailFrame.class.getResource("/img/virus (1).png")));
		frmDayByDay.setResizable(false);
		frmDayByDay.setBounds(100, 100, 900, 300);
		frmDayByDay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDayByDay.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 874, 177);
		frmDayByDay.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data", "Contagiati", "Ricoverati", "Terapia Intensiva", "Isolamento", "Guariti", "Deceduti", "Nuovi Casi","Tamponi"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class,Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 874, 72);
		frmDayByDay.getContentPane().add(panel);
		if (region.equals("") || region.equals("TotaleItalia"))
			title="Dati accorpati della Nazione";
		else
			title="Dati della regione "+region;
			
		JLabel lblDatiDellaRegione = new JLabel(title);
		lblDatiDellaRegione.setFont(new Font("Agency FB", Font.BOLD, 55));
		panel.add(lblDatiDellaRegione);
		
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i=0;i<data.size();i++)
        {
            model.addRow(new Object[]{data.get(i).getData(), data.get(i).getTotalCases(), data.get(i).getHospitalized(),data.get(i).getIntensiveCare(),data.get(i).getHomeIsolated(),data.get(i).healed,data.get(i).getDead(),data.get(i).getNewPositive(),data.get(i).getSwabs()});
        }

		
	}

	public void setVisible(boolean b) {

		frmDayByDay.setVisible(b);
	}
}
