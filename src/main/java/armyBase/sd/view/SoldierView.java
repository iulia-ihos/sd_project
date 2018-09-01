package armyBase.sd.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.http.client.ClientProtocolException;

import armyBase.sd.dto.SoldierDTO;
import armyBase.sd.dto.UserDTO;
import armyBase.sd.model.Soldier;
import armyBase.sd.request.MilitaryBaseRequest;
import armyBase.sd.request.RankRequest;
import armyBase.sd.request.RoleRequest;
import armyBase.sd.request.SoldierRequest;
import armyBase.sd.request.UserRequest;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

@SuppressWarnings("serial")
public class SoldierView extends JFrame {

	private JPanel contentPane;
    private JList list;
    private JButton btnDeleteById;
    private JButton btnUpdate;
    private String email;
    
 
	public SoldierView(String email) {
		super("soldier");
	    this.email = email;
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
				
				updateJList();	
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
				Long id = Long.parseLong(JOptionPane.showInputDialog("ID of the soldier to be deleted"));
				SoldierRequest request = new SoldierRequest();
				RoleRequest roleReq = new RoleRequest();
				UserRequest userRequest = new UserRequest();
				UserDTO user = userRequest.getByEmail(email);
				String role = user.getRol().getRoleName();
				
			  if(role.equals("ADMIN"))
				try {
					request.deleteById(id);
				} catch (Exception e){
					JOptionPane.showMessageDialog(null,"could not complete operation");
				}
			  else
					JOptionPane.showMessageDialog(null,"not authorized");
			   }
			
		});
		
		
		//btnDeleteById.setBackground(Color.RED);
		btnDeleteById.setBounds(144, 514, 152, 47);
		contentPane.add(btnDeleteById);
		
		btnUpdate = new JButton("Update");
		//btnUpdate.setForeground(Color.RED);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SoldierUpdateView s = new SoldierUpdateView(email);
				s.setVisible(true);
				
			}
		});
		btnUpdate.setBounds(409, 514, 131, 47);
		contentPane.add(btnUpdate);
		
		
		
		
	}

	
	
	private void updateJList(){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    MilitaryBaseRequest baseRequest = new MilitaryBaseRequest();
		RankRequest rankRequest = new RankRequest();
	    SoldierRequest soldierRequest = new SoldierRequest();
	    List<SoldierDTO> soldiers  = soldierRequest.getAll();
	    for(SoldierDTO p : soldiers){
	        model.addElement("ID : " + p.getIdSoldier() + ". " + p.getFullName()
	        	+ "\"" + p.getAlias() + "\" - " + p.getRank().getRankName());  
	
	    }
	    list.setModel(model);
	    list.setSelectedIndex(0);
	}
}
