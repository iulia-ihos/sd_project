package armyBase.sd.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import armyBase.sd.dto.MilitaryBaseDTO;
import armyBase.sd.dto.RankDTO;
import armyBase.sd.dto.SoldierDTO;
import armyBase.sd.dto.UserDTO;
import armyBase.sd.model.MilitaryBase;
import armyBase.sd.model.Rank;
import armyBase.sd.model.Role;
import armyBase.sd.model.UserDetailed;
import armyBase.sd.request.Encryption;
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
public class SoldierRegisterView extends JFrame {

	private JPanel contentPane;
	private JTextField year;
	private JTextField day;
	private JTextField month;
	private JTextField rankId;
	private JTextField name;
	private JTextField alias;
	private JTextField baseId;
	private JTextField email;
	private JTextField pass;

	private JList baseList;
	private JList rankList;
	
	public SoldierRegisterView() {
		super("Register ");
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
		alias.setBounds(142, 284, 146, 26);
		alias.setText("");
		getContentPane().add(alias);
		alias.setColumns(10);
		
		baseId = new JTextField();
		baseId.setBounds(142, 320, 146, 26);
		getContentPane().add(baseId);
		baseId.setColumns(10);
		
		baseList = new JList();
		baseList.setBounds(393, 14, 328, 152);
		baseList.setBackground(Color.DARK_GRAY);
		getContentPane().add(baseList);
		updateBaseList();
		
		rankList = new JList();
		rankList.setBounds(393, 253, 328, 136);
		rankList.setBackground(Color.GRAY);
		getContentPane().add(rankList);
		updateRankList();
		
		JLabel label = new JLabel("");
		label.setBounds(92, 479, 69, 20);
		label.setBackground(Color.WHITE);
		getContentPane().add(label);
		
		JLabel lblNewLabel_2 = new JLabel("email");
		lblNewLabel_2.setBounds(34, 479, 69, 20);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(34, 537, 69, 20);
		lblPassword.setBackground(Color.WHITE);
		getContentPane().add(lblPassword);
		
		email = new JTextField();
		email.setBounds(142, 479, 146, 26);
		email.setBackground(Color.WHITE);
		getContentPane().add(email);
		email.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(142, 534, 146, 26);
		pass.setBackground(Color.WHITE);
		getContentPane().add(pass);
		pass.setColumns(10);
		
		JButton add = new JButton("add");
		add.setBounds(381, 450, 121, 49);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  //JOptionPane.showMessageDialog(null,"operation not allowed");  
				 MilitaryBaseRequest baseRequest = new MilitaryBaseRequest();
				 UserRequest userRequest = new UserRequest();
				 RoleRequest req = new RoleRequest();
				 
				 UserDTO u = new UserDTO();
				 u.setIdUser(new Long(0));
				 u.setEmail(email.getText());
				 u.setPass(Encryption.encryptPassword(pass.getText()));
				 
				 u.setRol(new Role(req.getById(new Long (1))));
				 try {
					 userRequest.register(u);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 
				 UserDTO user = userRequest.getByEmail(email.getText());
				 
				 RankRequest rankRequest = new RankRequest();
			
				  SoldierDTO obj = new SoldierDTO();
				  obj.setUser(new UserDetailed(user));
				  obj.setAlias(alias.getText());
				  obj.setBase(new MilitaryBase(baseRequest.getById(new Long(Integer.parseInt(baseId.getText())))));
				  obj.setDob(new GregorianCalendar(Integer.parseInt(year.getText()), 
						  Integer.parseInt(month.getText()),
						  Integer.parseInt(month.getText())).getTime());
				  obj.setFullName(name.getText());
				  obj.setIdSoldier(new Long(0));
				  obj.setRank(new Rank(rankRequest.getById(new Long(Integer.parseInt(rankId.getText())))));
				  
						
				  SoldierRequest request = new SoldierRequest();
				  
				  try {
					request.save(obj);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"could not update database");
				}
			}
		});
		contentPane.add(add);
		getContentPane().add(add);
	
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
}
