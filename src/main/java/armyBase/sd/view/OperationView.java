package armyBase.sd.view;


import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import armyBase.sd.dto.OperationDTO;
import armyBase.sd.dto.SoldierDTO;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.SoldierOperation;
import armyBase.sd.request.OperationRequest;
import armyBase.sd.request.SoldierRequest;

@SuppressWarnings("serial")
public class OperationView extends JFrame {

	private JPanel contentPane;
    private JList list;
    private JButton btnDeleteById;
    private JButton btnUpdate;
    private JButton btnNewButton;
   // private String email;
    
 
	public OperationView() {
		super("Operations");
	  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1018, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnList = new JButton("List all");
		btnList.setBounds(30, 39, 169, 39);
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateBaseList();	
			}
		});
		contentPane.add(btnList);
		
		list = new JList();
		list.setBounds(40, 94, 609, 373);
		contentPane.add(list);
		

		
		btnDeleteById = new JButton("Delete");
		//btnDeleteById.setForeground(Color.RED);
		btnDeleteById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Long id = Long.parseLong(JOptionPane.showInputDialog("ID of the base to be deleted"));
				OperationRequest request = new OperationRequest();
				try {
					request.deleteById(id);
				} catch (Exception e){
					JOptionPane.showMessageDialog(null,"could not complete operation");
				}
			}
		});
		
        btnDeleteById.setBounds(409, 514, 131, 47);
		contentPane.add(btnDeleteById);	
	}

	
	
	private void updateBaseList(){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    OperationRequest opRequest = new OperationRequest();
		
		
	    List<OperationDTO> ops  = opRequest.getAll();
	    for(OperationDTO p : ops){
	        model.addElement("ID : " + p.getIdOperation() + ". " + p.getDescription()+" Status : " + p.getStatus());  
	
	    }
	    list.setModel(model);
	    list.setSelectedIndex(0);
	}
	
	private void updateSoldierList(Long id){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    SoldierRequest soldierRequest = new SoldierRequest();
	    OperationRequest request = new OperationRequest();

	    Set<SoldierOperation> os = request.getById(id).getOperationSoldiers();
	    List<SoldierDTO> soldiers = new ArrayList<SoldierDTO>();
	    for (SoldierOperation o:os){
	    	soldiers.add(soldierRequest.getById(o.getSoldierToOperation().getIdSoldier()));
	    }
	    
	    for(SoldierDTO p : soldiers){
	        model.addElement("ID : " + p.getIdSoldier() + ". " + p.getFullName()
	        	+ "\"" + p.getAlias() + "\" - " + p.getRank().getRankName());  
	
	    }
	    list.setModel(model);
	    list.setSelectedIndex(0);
	}
}
