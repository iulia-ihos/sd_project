package armyBase.sd.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import armyBase.sd.model.Role;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.UserDetailed;



@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	
	
	private Long idUser;
	
	private String email;
	
	private String pass;
	
	private Soldier soldier;
	
	private Role rol;
	
	public UserDTO(){
		
	}

	public UserDTO(UserDetailed user){
		this.idUser = user.getIdUser();
	}

	

	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public Soldier getSoldier() {
		return soldier;
	}


	public void setSoldier(Soldier soldier) {
		this.soldier = soldier;
	}


	public Role getRol() {
		return rol;
	}


	public void setRol(Role rol) {
		this.rol = rol;
	}

	
}
