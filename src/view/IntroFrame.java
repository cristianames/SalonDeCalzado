package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import logic.Controller;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;



public class IntroFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1947126327001296656L;
	private JComboBox cbxEmpleados;
	private JSlider slider;
	private JPanel contentPane;
	private JTextField txtPCR;
	private JTextField txtGN;
	private JTextField txtTxtstmax;
	private JTextField ftxtTsim;
	private JProgressBar progressBar;
	private Timer timer;
	private JRadioButton rdbtnMonth;
	JButton btnAdvanced;
	
	private IntroFrame frame;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public IntroFrame() {
		setResizable(false);
		setTitle("TheGRID - SALON DE CALZADO");
		frame = this;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "¿Realmente desea cerrar?", "Finalizar", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		
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
		
		cbxEmpleados = new JComboBox<Object>();
		cbxEmpleados.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		cbxEmpleados.setBounds(29, 47, 38, 20);
		pnlConfig.add(cbxEmpleados);
		
		JLabel lblEmpleados = new JLabel("Cantidad de empleados a contratar.");
		lblEmpleados.setBounds(90, 50, 206, 17);
		pnlConfig.add(lblEmpleados);
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(50);
		slider.setMajorTickSpacing(100);
		slider.setMinimum(0);
		slider.setMaximum(250);
		slider.setValue(80);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				txtTxtstmax.setText(String.valueOf(slider.getValue()));
			}
		});
		slider.setBackground(UIManager.getColor("ToggleButton.highlight"));
		slider.setForeground(UIManager.getColor("ToggleButton.darkShadow"));
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(29, 78, 38, 102);
		slider.setValue(80);
		pnlConfig.add(slider);
		
		JLabel lblStmax = new JLabel("St de repos: ");
		lblStmax.setBounds(90, 119, 88, 20);
		pnlConfig.add(lblStmax);
		
		txtTxtstmax = new JTextField();
		txtTxtstmax.setText(String.valueOf(slider.getValue()));
		txtTxtstmax.setEditable(false);
		txtTxtstmax.setBounds(178, 119, 38, 20);
		pnlConfig.add(txtTxtstmax);
		txtTxtstmax.setColumns(10);
		
		ftxtTsim = new JTextField();
		ftxtTsim.setBounds(29, 201, 70, 20);
		pnlConfig.add(ftxtTsim);		
		
		
		rdbtnMonth = new JRadioButton("Simular meses");
		buttonGroup_1.add(rdbtnMonth);
		rdbtnMonth.setBackground(Color.WHITE);
		rdbtnMonth.setBounds(130, 178, 135, 23);
		pnlConfig.add(rdbtnMonth);
		
		JRadioButton rdbtnMinutes = new JRadioButton("Simular minutos");
		rdbtnMinutes.setSelected(true);
		buttonGroup_1.add(rdbtnMinutes);
		rdbtnMinutes.setBackground(Color.WHITE);
		rdbtnMinutes.setBounds(130, 220, 135, 23);
		pnlConfig.add(rdbtnMinutes);
		
		btnAdvanced = new JButton("Ver Detalles");
		btnAdvanced.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AdvancedFrame(frame);
				progressBar.setValue(0);
				progressBar.setForeground(new Color(0, 139, 139));	//Light blue
			}
		});
		btnAdvanced.setEnabled(false);
		btnAdvanced.setBounds(262, 360, 129, 23);
		contentPane.add(btnAdvanced);
		
		JPanel pnlResults = new JPanel();
		pnlResults.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlResults.setBounds(29, 306, 215, 92);
		contentPane.add(pnlResults);
		pnlResults.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 139, 139));	//Light blue		
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
		
		JLabel lblLblpcr = new JLabel("% Clien. Arrep.");
		lblLblpcr.setBounds(10, 43, 98, 14);
		pnlResults.add(lblLblpcr);
		
		JLabel lblLblgn = new JLabel("Ganancia Neta");
		lblLblgn.setBounds(119, 43, 86, 14);
		pnlResults.add(lblLblgn);
		
		JButton btnRunSimu = new JButton("Simular");
		btnRunSimu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setForeground(new Color(0, 139, 139));	//Light blue
				try {
					Integer.parseInt((String) ftxtTsim.getText());
				} catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Ingrese números en el campo de tiempo!","Datos inválidos", JOptionPane.ERROR_MESSAGE);
					return;
				}				
				if(Integer.parseInt((String) ftxtTsim.getText())<=0) JOptionPane.showMessageDialog(null,"Tiene que haber como mínimo una iteración!","Cantidad incorrecta",JOptionPane.INFORMATION_MESSAGE);
				else{
					timer = new Timer(50,new TimerListener());
					timer.start();
				}
			}
		});
		btnRunSimu.setBounds(254, 311, 137, 23);
		contentPane.add(btnRunSimu);
		

		frame.setLocationRelativeTo(null);
		
		  
	    }
	private void runSimulation(){
	    int emp = Integer.parseInt((String) cbxEmpleados.getSelectedItem());
		int stm = slider.getValue();
		int iter = Integer.parseInt((String) ftxtTsim.getText()); 
		if (rdbtnMonth.isSelected()) iter *= 10560;
		Controller logic = Controller.getInstance();
		logic.runSimulation(iter, emp, stm);
		btnAdvanced.setEnabled(true);
		float[] results = logic.getResultVars();
		txtPCR.setText(String.valueOf(results[3]));
		txtGN.setText(String.valueOf(results[4]));
		progressBar.setForeground(new Color(50, 205, 50));	//Lime green
	}
	
	class TimerListener implements ActionListener {
    	int val = 0;
    	Random ran = new Random();
        public void actionPerformed(ActionEvent evt) {
        	progressBar.setValue(val+=ran.nextInt(15));
        	if(val >= 100){
        		timer.stop();
        		runSimulation();
        	}	
        }	    
	}
	
}
