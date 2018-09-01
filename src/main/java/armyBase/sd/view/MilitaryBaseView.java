package armyBase.sd.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import armyBase.sd.dto.MilitaryBaseDTO;
import armyBase.sd.model.Soldier;
import armyBase.sd.request.MilitaryBaseRequest;



@SuppressWarnings("serial")
public class MilitaryBaseView extends JFrame {

	private JPanel contentPane;
    private JList list;
    private JButton btnDeleteById;
    private JButton btnUpdate;
    private JButton btnNewButton;
   // private String email;
    
 
	public MilitaryBaseView() {
		super("Military Bases");
	  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1018, 633);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnList = new JButton("List all");
		btnList.setBounds(30, 39, 169, 39);
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateBaseList();	
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnList);
		
		list = new JList();
		list.setBounds(40, 94, 609, 373);
		contentPane.add(list);
		

		
		btnDeleteById = new JButton("Delete");
		btnDeleteById.setBounds(144, 514, 152, 47);
		btnDeleteById.setForeground(Color.RED);
		btnDeleteById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Long id = Long.parseLong(JOptionPane.showInputDialog("ID of the base to be deleted"));
				MilitaryBaseRequest request = new MilitaryBaseRequest();
				try {
					request.deleteById(id);
				} catch (Exception e){
					JOptionPane.showMessageDialog(null,"could not complete operation");
				}
			}
		});
		
		
		btnDeleteById.setBackground(Color.RED);
		contentPane.add(btnDeleteById);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(409, 514, 131, 47);
		//btnDeleteById.setForeground(Color.RED);
		btnDeleteById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}
		});
		contentPane.add(btnUpdate);
		
		btnNewButton = new JButton("List All Soldiers For Military Base");
		btnNewButton.setBounds(339, 16, 346, 57);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Long id = Long.parseLong(JOptionPane.showInputDialog("ID of the base"));
				updateSoldierList(id);
			}
			
		});
		contentPane.add(btnNewButton);
		
		
		
		
	}

	
	
	private void updateBaseList(){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    MilitaryBaseRequest baseRequest = new MilitaryBaseRequest();
		
		
	    List<MilitaryBaseDTO> bases  = baseRequest.getAll();
	    for(MilitaryBaseDTO p : bases){
	        model.addElement("ID : " + p.getIdMilitaryBase() + ". " + p.getDescription());  
	
	    }
	    list.setModel(model);
	    list.setSelectedIndex(0);
	}
	
	private void updateSoldierList(Long id){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    MilitaryBaseRequest baseRequest = new MilitaryBaseRequest();

	    Set<Soldier> soldiers  = baseRequest.getById(id).getSoldiers();
	    for(Soldier p : soldiers){
	        model.addElement("ID : " + p.getIdSoldier() + ". " + p.getFullName()
	        	+ "\"" + p.getAlias() + "\" - " + p.getRank().getRankName());  
	
	    }
	    list.setModel(model);
	    list.setSelectedIndex(0);
	}
}
