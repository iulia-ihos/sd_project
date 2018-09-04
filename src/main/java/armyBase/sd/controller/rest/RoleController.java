  package armyBase.sd.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import armyBase.sd.model.Role;
import armyBase.sd.service.IRoleService;





@RestController
@RequestMapping("role")
public class RoleController {
		private final IRoleService roleService;

	    @Autowired
	    public RoleController(IRoleService roleService) {
	        this.roleService = roleService;
	    }

	    @GetMapping("getAll")
		public List<Role> getAll() {
		            return roleService.getAll();

		}
	    
	    @GetMapping("getById/{id}")
		public Role getById(@PathVariable Long id) {
		            return roleService.getById(id);
		    }
}