package armyBase.sd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import armyBase.sd.model.Role;

@Service
public interface IRoleService{

	public List<Role> getAll();
	public Role getById(Long id);
 
}
