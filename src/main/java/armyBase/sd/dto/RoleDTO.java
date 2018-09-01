package armyBase.sd.dto;

import java.util.Set;

import armyBase.sd.model.UserDetailed;

public class RoleDTO {
	

	
	private Long idRole;

	private String roleName;

	private Set<UserDetailed> usersToRole;
	
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