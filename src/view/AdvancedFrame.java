package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import logic.Controller;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdvancedFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8132276992723051112L;
	
	IntroFrame source;
	AdvancedFrame frame;
	private JTextField txtPPS;
	private JTextField txtPTE;
	private JTextField txtPPP;
	private JTextField txtPCA;
	private JTextField txtGN;
	private JTextField txtEmp;
	private JTextField txtStm;
	private JLabel lblEmp;
	private JLabel lblStm;

	private String[] columnNames = {
			"Evento",
            "T",
            "St Actual",
            "St vendido",
            "St rechazado",
            "Precio venta",
            "Arrepentido?"};	
	private Object[][] tableData;
	
	private float[] resultVars;
	private float[] controlVars;
	private JTable tblInfo;
	
	/**
	 * Create the dialog.
	 */
	public AdvancedFrame(IntroFrame father) {
		source = father;
		source.setVisible(false);
		frame = this;
		setVisible(true);
		setBounds(100, 100, 689, 419);
		getContentPane().setLayout(null);
		
		tableData = Controller.getInstance().obtainSimuDetails(); 
		resultVars = Controller.getInstance().getResultVars();
		controlVars = Controller.getInstance().getControlVars();
		
		JPanel pnlResult = new JPanel();
		pnlResult.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
		pnlResult.setBounds(10, 95, 151, 274);
		getContentPane().add(pnlResult);
		pnlResult.setLayout(null);
		
		txtPPS = new JTextField();
		txtPPS.setEditable(false);
		txtPPS.setBounds(10, 26, 86, 20);
		pnlResult.add(txtPPS);
		txtPPS.setColumns(10);
		txtPPS.setText(String.valueOf(resultVars[0]));
		
		txtPTE = new JTextField();
		txtPTE.setEditable(false);
		txtPTE.setBounds(10, 72, 86, 20);
		pnlResult.add(txtPTE);
		txtPTE.setColumns(10);
		txtPTE.setText(String.valueOf(resultVars[1]));
		
		txtPPP = new JTextField();
		txtPPP.setEditable(false);
		txtPPP.setBounds(10, 118, 86, 20);
		pnlResult.add(txtPPP);
		txtPPP.setColumns(10);
		txtPPP.setText(String.valueOf(resultVars[2]));
		
		txtPCA = new JTextField();
		txtPCA.setEditable(false);
		txtPCA.setBounds(10, 164, 86, 20);
		pnlResult.add(txtPCA);
		txtPCA.setColumns(10);
		txtPCA.setText(String.valueOf(resultVars[3]));
		
		txtGN = new JTextField();
		txtGN.setEditable(false);
		txtGN.setBounds(10, 211, 86, 20);
		pnlResult.add(txtGN);
		txtGN.setColumns(10);
		txtGN.setText(String.valueOf(resultVars[4]));
		
		JButton btnPTO = new JButton("Emp. Detalles");
		btnPTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EmployeeFrame(frame);
			}
		});
		btnPTO.setBounds(10, 242, 131, 23);
		pnlResult.add(btnPTO);
		
		JLabel lblPPS = new JLabel("Prom. perm. sist.");
		lblPPS.setBounds(10, 11, 131, 14);
		pnlResult.add(lblPPS);
		
		JLabel lblPTE = new JLabel("Prom. tiempo espera");
		lblPTE.setBounds(10, 57, 131, 14);
		pnlResult.add(lblPTE);
		
		JLabel lblPPP = new JLabel("%. pares perdidos");
		lblPPP.setBounds(10, 103, 131, 14);
		pnlResult.add(lblPPP);
		
		JLabel lblPCA = new JLabel("%. clientes arrep.");
		lblPCA.setBounds(10, 149, 131, 14);
		pnlResult.add(lblPCA);
		
		JLabel lblGN = new JLabel("Ganancia neta");
		lblGN.setBounds(10, 195, 131, 14);
		pnlResult.add(lblGN);
		
		JPanel pnlControl = new JPanel();
		pnlControl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
		pnlControl.setBounds(10, 11, 151, 73);
		getContentPane().add(pnlControl);
		pnlControl.setLayout(null);
		
		txtEmp = new JTextField();
		txtEmp.setEditable(false);
		txtEmp.setBounds(10, 42, 60, 20);
		pnlControl.add(txtEmp);
		txtEmp.setColumns(10);
		txtEmp.setText(String.valueOf(controlVars[0]));
		
		txtStm = new JTextField();
		txtStm.setEditable(false);
		txtStm.setColumns(10);
		txtStm.setBounds(81, 42, 60, 20);
		pnlControl.add(txtStm);
		txtStm.setText(String.valueOf(controlVars[1]));
		
		lblEmp = new JLabel("Cant. emp.");
		lblEmp.setBounds(10, 17, 60, 14);
		pnlControl.add(lblEmp);
		
		lblStm = new JLabel("St. max");
		lblStm.setBounds(81, 17, 60, 14);
		pnlControl.add(lblStm);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(171, 11, 492, 358);
		getContentPane().add(scrollPane);
		
		tblInfo = new JTable(tableData, columnNames);
		tblInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
		scrollPane.setViewportView(tblInfo);
		try{
			frame.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	source.setVisible(true);
			    }
			});
		}catch (Exception e){
			
		}
	}
}
