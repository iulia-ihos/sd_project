package armyBase.sd.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class View extends JFrame {

	private JPanel contentPane;
    private String email;
    

	public View(String email) {
		super("View");
	    
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSoldiers = new JButton("Soldiers");
		btnSoldiers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoldierView sv = new SoldierView(email);
				sv.setVisible(true);
			}
		});
		btnSoldiers.setBounds(27, 54, 115, 29);
		contentPane.add(btnSoldiers);
		
		JButton btnBases = new JButton("MilitaryBases");
		btnBases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnBases.setBounds(236, 54, 148, 29);
		contentPane.add(btnBases);
		
		JButton btnOperation = new JButton("Operations");
		btnOperation.setBounds(15, 109, 148, 29);
		btnOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperationView opView = new OperationView();
				opView.setVisible(true);
				
			}
		});
		contentPane.add(btnOperation);
		
		JButton btnTraining = new JButton("Training");
		btnTraining.setBounds(256, 109, 115, 29);
		btnTraining.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainingView tView = new TrainingView();
				tView.setVisible(true);
				
			}
		});
		contentPane.add(btnTraining);
		
		JLabel lblId = new JLabel(email);
		lblId.setBounds(344, 16, 69, 20);
		contentPane.add(lblId);
	}
	
}
