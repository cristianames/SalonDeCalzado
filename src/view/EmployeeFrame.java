package view;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logic.Controller;

import java.awt.Dimension;
 
public class EmployeeFrame extends JDialog {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 4499793964212264362L;
	
	private EmployeeFrame frame;
	private Object[][] content;

	public EmployeeFrame(AdvancedFrame parent) {
        super();
        frame = this;
        content = Controller.getInstance().obtainEmployeeDetails();
        this.setVisible(true);
        		
 
        String[] columnNames = {"Empleado",
                                "% Tiempo ocioso",
                                "min Tiempo ocioso"};
 
        final JTable table = new JTable(content, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);

		//frame.setLocationRelativeTo(parent);
        frame.setLocation(20, 20);
        this.pack();
    }
}