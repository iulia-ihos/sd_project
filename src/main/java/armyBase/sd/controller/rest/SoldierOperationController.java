package armyBase.sd.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import armyBase.sd.dto.SoldierOperationDTO;
import armyBase.sd.model.SoldierOperation;
import armyBase.sd.service.ISoldierOperationService;



@RestController
@RequestMapping("soldierOp")
public class SoldierOperationController {
		private final ISoldierOperationService service;

	    @Autowired
	    public SoldierOperationController(ISoldierOperationService service) {
	        this.service = service;
	    }

	    @GetMapping("getAll")
		public List<SoldierOperation> getAll() {
		        try {
		            return service.getAll();
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		}
	    
	  
		@PutMapping("add")
		public SoldierOperation add(@RequestBody SoldierOperationDTO soldierOp)
		{
			 try {
				 return service.create(soldierOp);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
			
		}
		
	   // @PreAuthorize("hasAnyRole('COMMANDER','USER')")
		@PostMapping("update")
		public SoldierOperation update(@RequestBody SoldierOperationDTO soldierOp) {
		        try {
		            return service.update(soldierOp);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }

		@GetMapping("getById")
		public SoldierOperation getById(@RequestParam Long id) {
		        try {
		            return service.getById(id);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
		

		@DeleteMapping("deleteById")
		public String deleteById(Long id) {
		        try {
		           service.deleteById(id);
		            return "Deleted!";
		        } catch (Exception e) {
		            return e.getMessage();
		        }
		    }

}