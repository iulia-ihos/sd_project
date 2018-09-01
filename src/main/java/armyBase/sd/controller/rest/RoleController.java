  package armyBase.sd.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		        try {
		            return roleService.getAll();
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		}
	    
	    @GetMapping("getById")
		public Role getById(@RequestParam Long id) {
		        try {
		            return roleService.getById(id);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
}