package armyBase.sd.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import armyBase.sd.dto.MilitaryBaseDTO;
import armyBase.sd.dto.RankDTO;
import armyBase.sd.dto.RoleDTO;
import armyBase.sd.dto.SoldierDTO;
import armyBase.sd.model.MilitaryBase;
import armyBase.sd.model.Rank;
import armyBase.sd.request.MilitaryBaseRequest;
import armyBase.sd.request.RankRequest;
import armyBase.sd.request.RoleRequest;
import armyBase.sd.request.SoldierRequest;
import armyBase.sd.request.UserRequest;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class SoldierUpdateView extends JFrame {
	private JPanel contentPane;
	private JTextField year;
	private JTextField day;
	private JTextField month;
	private JTextField rankId;
	private JTextField name;
	private JTextField alias;
	private JTextField baseId;

	private JList baseList;
	private JList rankList;
	private JList roleList;
	private String email;
	public SoldierUpdateView(String email) {
		super("Update");
		this.email = email;
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 989, 747);
	
		
		
		JLabel lblNewLabel = new JLabel("Rank_id");
		lblNewLabel.setBounds(34, 32, 105, 20);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DOB");
		lblNewLabel_1.setBounds(32, 68, 69, 20);
		getContentPane().add(lblNewLabel_1);
		
		year = new JTextField();
		year.setBounds(142, 93, 146, 26);
		getContentPane().add(year);
		year.setColumns(10);
		
		JLabel lblYear = new JLabel("year");
		lblYear.setBounds(58, 93, 69, 20);
		getContentPane().add(lblYear);
		
		JLabel lblMonth = new JLabel("month");
		lblMonth.setBounds(58, 134, 69, 20);
		getContentPane().add(lblMonth);
		
		JLabel lblDay = new JLabel("day");
		lblDay.setBounds(58, 180, 69, 20);
		getContentPane().add(lblDay);
		
		day = new JTextField();
		day.setBounds(142, 177, 146, 26);
		getContentPane().add(day);
		day.setColumns(10);
		
		month = new JTextField();
		month.setBounds(142, 135, 146, 26);
		getContentPane().add(month);
		month.setColumns(10);
		
		rankId = new JTextField();
		rankId.setBounds(142, 29, 146, 26);
		getContentPane().add(rankId);
		rankId.setColumns(10);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(34, 254, 93, 20);
		getContentPane().add(lblFullName);
		
		JLabel lblAlias = new JLabel("Alias");
		lblAlias.setBounds(44, 287, 69, 20);
		getContentPane().add(lblAlias);
		
		JLabel lblBaseId = new JLabel("Base_id");
		lblBaseId.setBounds(32, 323, 69, 20);
		getContentPane().add(lblBaseId);
		
		name = new JTextField();
		name.setBounds(142, 251, 146, 26);
		getContentPane().add(name);
		name.setColumns(10);
		
		alias = new JTextField();
		alias.setText("");
		alias.setBounds(142, 284, 146, 26);
		getContentPane().add(alias);
		alias.setColumns(10);
		
		baseId = new JTextField();
		baseId.setBounds(142, 320, 146, 26);
		getContentPane().add(baseId);
		baseId.setColumns(10);
		
		baseList = new JList();
		baseList.setBackground(Color.DARK_GRAY);
		baseList.setBounds(393, 14, 328, 152);
		getContentPane().add(baseList);
		updateBaseList();
		
		rankList = new JList();
		rankList.setBackground(Color.GRAY);
		rankList.setBounds(393, 253, 328, 136);
		getContentPane().add(rankList);
		updateRankList();
		
		roleList = new JList();
		
		roleList.setBackground(Color.DARK_GRAY);
		
		roleList.setBounds(404, 448, 328, 136);
		getContentPane().add(roleList);
		
		JLabel label = new JLabel("");
		label.setBackground(Color.WHITE);
		label.setBounds(92, 479, 69, 20);
		getContentPane().add(label);
		
		JButton btnUpdate = new JButton("Save Changes");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  //JOptionPane.showMessageDialog(null,"operation not allowed");  
				 MilitaryBaseRequest baseRequest = new MilitaryBaseRequest();
				 RankRequest rankRequest = new RankRequest();
				 SoldierRequest request = new SoldierRequest();
			
				  //Long id = Long.parseLong(JOptionPane.showInputDialog("ID of the soldier to be updated"));
				  UserRequest userRequest = new UserRequest();
				  Long idUser = userRequest.getByEmail(email).getIdUser();
				  request.getByIdUser(idUser);
				  Long id =  request.getByIdUser(idUser).getIdSoldier();
				  
				  SoldierDTO obj = new SoldierDTO();
				  obj.setIdSoldier(id);
				  obj.setAlias(alias.getText());
				  if(!baseId.getText().equals(""));
				  	obj.setBase(new MilitaryBase(baseRequest.getById(Long.parseLong(baseId.getText()))));
				  if((!month.getText().equals(""))&& (!year.getText().equals("")) &&(!day.getText().equals("")))
				  obj.setDob(new GregorianCalendar(Integer.parseInt(year.getText()), 
						  Integer.parseInt(month.getText()),
						  Integer.parseInt(day.getText())).getTime());
				  obj.setFullName(name.getText());
				 
				  obj.setRank(new Rank(rankRequest.getById(new Long(Integer.parseInt(rankId.getText())))));
				  
						
				  
				  
				  try {
					request.update(obj);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"could not update database");
				}
			}
		});
		contentPane.add(btnUpdate);	
	
		btnUpdate.setBounds(70, 422, 171, 55);
		getContentPane().add(btnUpdate);
		
		JButton btnUpdateRole = new JButton("Update Role");
		btnUpdateRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  //JOptionPane.showMessageDialog(null,"operation not allowed"); 
				 updateRoleList();
				 SoldierRequest request = new SoldierRequest();
				 Long idSoldier = Long.parseLong(JOptionPane.showInputDialog("Soldier id?"));
				 Long idRole = Long.parseLong(JOptionPane.showInputDialog("Role id?"));
				  
				  try {
					request.updateRole(idSoldier, idRole);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"could not update database");
				}
			}
		});
		//btnUpdateRole.setForeground(Color.RED);
		//btnUpdateRole.setBackground(Color.RED);
		btnUpdateRole.setBounds(70, 529, 171, 55);
		getContentPane().add(btnUpdateRole);
		
	
	
	}
	
	

	
	private void updateBaseList(){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    MilitaryBaseRequest request = new MilitaryBaseRequest();
	    List<MilitaryBaseDTO> list  = request.getAll();
	    for(MilitaryBaseDTO p : list){
	        model.addElement(p.toString());
	    }
	    baseList.setModel(model);
	    baseList.setSelectedIndex(0);
	}
	
	private void updateRankList(){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    RankRequest s = new RankRequest();
	    List<RankDTO> list  = s.getAll();
	    for(RankDTO p : list){
	        model.addElement(p.toString());
	    }
	    rankList.setModel(model);
	    rankList.setSelectedIndex(0);
	}
	
	private void updateRoleList(){
	    DefaultListModel<String> model = new DefaultListModel<String>();
	    RoleRequest s = new RoleRequest();
	    List<RoleDTO> list  = s.getAll();
	    for(RoleDTO p : list){
	        model.addElement("ID : " + p.getIdRole() + " = " + p.getRoleName());
	    }
	    roleList.setModel(model);
	    roleList.setSelectedIndex(0);
	}
}
