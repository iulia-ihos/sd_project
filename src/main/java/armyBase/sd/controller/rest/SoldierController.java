package armyBase.sd.controller.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import armyBase.sd.dto.SoldierDTO;
import armyBase.sd.dto.UserDTO;
import armyBase.sd.exceptions.EntityNotFoundException;
import armyBase.sd.exceptions.InvalidDataException;
import armyBase.sd.model.Operation;
import armyBase.sd.model.Role;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.Training;
import armyBase.sd.model.UserDetailed;
import armyBase.sd.service.IOperationService;
import armyBase.sd.service.IRoleService;
import armyBase.sd.service.ISoldierOperationService;
import armyBase.sd.service.ISoldierService;
import armyBase.sd.service.ITrainingService;
import armyBase.sd.service.IUserService;

@RestController
@RequestMapping("rest/soldier")
public class SoldierController {
		private final ISoldierService soldierService;

	    @Autowired
	    public SoldierController(ISoldierService soldierService) {
	        this.soldierService = soldierService;
	    }
	    
	    @Autowired 
	    private IUserService userService;
	    
	    @Autowired 
	    private IRoleService roleService;
	    
	    @Autowired 
	    private ISoldierOperationService soldierOpService;
	    
	    @Autowired 
	    private IOperationService opService;
	    
	    @Autowired 
	    private ITrainingService trainingService;


	    @GetMapping("getAll")
		public List<Soldier> getAll() {
		        try {
		            return soldierService.getAll();
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		}
	    
	    @GetMapping("getByTagNumber/{tag}")
		public Soldier getByTagNumber(@PathVariable String tag) throws EntityNotFoundException {
	    	Soldier soldier = soldierService.getByTagNumber(tag);
	    	if(soldier == null)
	    		throw new EntityNotFoundException("The entity does not exist");
	    	return soldier;
		 
		}
	    
	 
		@PostMapping("add")
		public Soldier add(@RequestBody SoldierDTO soldier) throws InvalidDataException
		{
			return soldierService.create(soldier);	
		}
		
	    
	    @PutMapping("updateRole")
		public Soldier updateRole(@RequestParam Long idSoldier, Long idRole) {
		        try {
		            Soldier s = soldierService.getById(idSoldier);
		            
		            UserDetailed user = s.getUser();
		            
		            Role role = roleService.getById(idRole);
		            user.setRol(role);
		            
		            userService.updateRole(new UserDTO(userService.getById(user.getIdUser())));
		            
		            return s;
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
	    
		@PutMapping("update")
		public Soldier update(@RequestBody SoldierDTO soldier) {
		       return soldierService.update(soldier);
		    }
        
	    
		@GetMapping("{id}")
		public Soldier getById(@PathVariable Long id) {
		        try {
		            return soldierService.getById(id);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
		
		@GetMapping("getByIdUser")
		public Soldier getByIdUser(@RequestParam Long id) {
		        try {
		            return soldierService.getByIdUser(id);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
		
		
		@DeleteMapping("deleteById/{id}")
		public String deleteById(@PathVariable Long id) throws InvalidDataException {
			
		           soldierService.deleteById(id);
		            return "Deleted!";
		    }
		
		 @GetMapping("getOperations/{id}")
			public List<Operation> getOperations(@PathVariable Long id) {		 
			 return soldierOpService.getAllOperationsBySoldier(id);
			}
		 
		 @GetMapping("getOperationsInCharge/{id}")
			public List<Operation> getOperationsInCharge(@PathVariable Long id) {
			 return opService.getBySoldierInCharge(id);
			}
		 
		 @GetMapping("getTrainingsInCharge/{id}")
			public List<Training> getTrainingsInCharge(@PathVariable Long id) {
			 return trainingService.getBySoldierInCharge(id);
			}

}
