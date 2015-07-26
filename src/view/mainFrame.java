package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import logic.mainLogic;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.sql.Time;
import java.util.Random;
import java.awt.event.InputMethodEvent;
import javax.swing.event.ChangeListener;

import javafx.scene.control.ProgressBar;

import javax.swing.event.ChangeEvent;

public class mainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1947126327001296656L;
	private JPanel contentPane;
	private JTextField txtPCR;
	private JTextField txtGN;
	/**
	 * @wbp.nonvisual location=-27,259
	 */
	private final JTree tree = new JTree();
	private JTextField txtTxtstmax;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 446);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbltitle = new JLabel("TP SIMULACION\t-\tSALON DE CALZADO\t-\tUTN FRBA");
		lbltitle.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		lbltitle.setBounds(10, 11, 402, 23);
		contentPane.add(lbltitle);
		
		JPanel pnlConfig = new JPanel();
		pnlConfig.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnlConfig.setBackground(UIManager.getColor("ToggleButton.highlight"));
		pnlConfig.setBounds(52, 45, 306, 250);
		contentPane.add(pnlConfig);
		pnlConfig.setLayout(null);
		
		JComboBox cbxEmpleados = new JComboBox();
		cbxEmpleados.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		cbxEmpleados.setBounds(29, 47, 38, 20);
		pnlConfig.add(cbxEmpleados);
		
		JLabel lblEmpleados = new JLabel("Cantidad de empleados a contratar.");
		lblEmpleados.setBounds(90, 50, 206, 17);
		pnlConfig.add(lblEmpleados);
		
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				txtTxtstmax.setText(String.valueOf(slider.getValue()));
			}
		});
		slider.setBackground(UIManager.getColor("ToggleButton.highlight"));
		slider.setForeground(UIManager.getColor("ToggleButton.darkShadow"));
		slider.setPaintTicks(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(29, 78, 38, 102);
		pnlConfig.add(slider);
		
		JLabel lblStmax = new JLabel("Stock m\u00E1ximo: ");
		lblStmax.setBounds(90, 119, 88, 20);
		pnlConfig.add(lblStmax);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)spinner.getValue()<0)spinner.setValue(0);
			}
		});
		spinner.setBounds(22, 191, 58, 20);
		pnlConfig.add(spinner);
		
		JLabel lblIter = new JLabel("Cantidad de iteraciones");
		lblIter.setBounds(90, 193, 145, 17);
		pnlConfig.add(lblIter);
		
		txtTxtstmax = new JTextField();
		txtTxtstmax.setText(String.valueOf(slider.getValue()));
		txtTxtstmax.setEditable(false);
		txtTxtstmax.setBounds(178, 119, 38, 20);
		pnlConfig.add(txtTxtstmax);
		txtTxtstmax.setColumns(10);
		
		JButton btnAdvanced = new JButton("Ver Detalles");
		btnAdvanced.setEnabled(false);
		btnAdvanced.setBounds(262, 360, 129, 23);
		contentPane.add(btnAdvanced);
		
		JPanel pnlResults = new JPanel();
		pnlResults.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlResults.setBounds(29, 306, 215, 92);
		contentPane.add(pnlResults);
		pnlResults.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 11, 146, 21);
		pnlResults.add(progressBar);
		
		txtPCR = new JTextField();
		txtPCR.setBounds(10, 61, 86, 20);
		pnlResults.add(txtPCR);
		txtPCR.setEditable(false);
		txtPCR.setColumns(10);
		
		txtGN = new JTextField();
		txtGN.setBounds(119, 61, 86, 20);
		pnlResults.add(txtGN);
		txtGN.setEditable(false);
		txtGN.setColumns(10);
		
		JLabel lblLblpcr = new JLabel("Porc. Cli. Retiran");
		lblLblpcr.setBounds(10, 43, 98, 14);
		pnlResults.add(lblLblpcr);
		
		JLabel lblLblgn = new JLabel("Ganancia Neta");
		lblLblgn.setBounds(119, 43, 86, 14);
		pnlResults.add(lblLblgn);
		
		JButton btnRunSimu = new JButton("Simular");
		btnRunSimu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((int)spinner.getValue()<=0) JOptionPane.showMessageDialog(null,"Tiene que haber como mínimo una iteración!");
				else{
					int emp = Integer.parseInt((String) cbxEmpleados.getSelectedItem());
					int stm = slider.getValue();
					int iter = (int)spinner.getValue(); 
					mainLogic logic = mainLogic.getInstance();
					logic.ejecutarSimulacion(emp, stm, iter);
					int progress = 0;
					int acum;
					while (progress < 1000){
						progress += dormir();
						acum = progress / 10;
						progressBar.setValue(acum);
					}
					progressBar.setValue(100);						
					txtPCR.setText(String.valueOf(logic.getPCR()));
					txtGN.setText(String.valueOf(logic.getGN()));
				}
			}
		});
		btnRunSimu.setBounds(254, 311, 137, 23);
		contentPane.add(btnRunSimu);
	}
	
	private int dormir(){
		Random ran = new Random();
		int time = ran.nextInt(400);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
}
