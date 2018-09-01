package armyBase.sd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import armyBase.sd.dao.RoleDAO;
import armyBase.sd.model.Role;
import armyBase.sd.service.IRoleService;



@Component
public class RoleService implements IRoleService {
	private final RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

	@Override
	public List<Role> getAll() {
		return roleDAO.findAll();
	}

	@Override
	public Role getById(Long id) {
		return roleDAO.findByIdRole(id);
	}
}

