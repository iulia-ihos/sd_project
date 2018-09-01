package armyBase.sd.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import armyBase.sd.dto.RoleDTO;

@Entity
@Table(name="roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;
	
	
	@Column(name="roleName")
	private String roleName;
	
	
	@OneToMany(mappedBy= "rol",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<UserDetailed> usersToRole;
	
	public Role() {
		
	}
	
	public Role(RoleDTO byId) {
		this.idRole = byId.getIdRole();
	}

	@Override
	public String toString(){
		return roleName.toUpperCase();
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UserDetailed> getUsersToRole() {
		return usersToRole;
	}

	public void setUsersToRole(Set<UserDetailed> usersToRole) {
		this.usersToRole = usersToRole;
	}
}
