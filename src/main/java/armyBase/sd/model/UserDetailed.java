package armyBase.sd.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import armyBase.sd.dto.UserDTO;



@SuppressWarnings("serial")
@Entity
@Table(name="users")

public class UserDetailed implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@Column(name="email", unique = true)
	private String email;
	
	@Column(name="pass")
	private String pass;
	
	@ManyToOne
	@JoinColumn(name = "idRole", nullable = false)
	private Role rol;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user")
	private Soldier soldier;
	
	public UserDetailed(){
		
	}

	public UserDetailed(UserDetailed user) {
		this.idUser = user.getIdUser();
		this.email = user.getEmail();
		this.pass = user.getPass();
		this.rol = user.getRol();
		this.soldier = user.getSoldier();
		System.err.println( getEmail()+getPass());
	}
	
	
	public UserDetailed(UserDTO user) {
		this.idUser = user.getIdUser();
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

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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

	public void setRol(Role role) {
		this.rol = role;
	}

	
}
