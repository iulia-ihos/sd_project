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
		     return service.getAll();
		}
	    
	  
		@PostMapping("add")
		public SoldierOperation add(@RequestBody SoldierOperationDTO soldierOp)
		{
			return service.create(soldierOp);
		}
		
	   // @PreAuthorize("hasAnyRole('COMMANDER','USER')")
		@PutMapping("update")
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
		

		@DeleteMapping("deleteById/{id}")
		public void deleteById(@PathVariable Long id) {
		      service.deleteById(id);
		    }
		
		@DeleteMapping("delete")
		public String delete(@RequestBody SoldierOperationDTO soldOp) {
		      service.delete(soldOp); 
		      return "DELETED";
		    }

}