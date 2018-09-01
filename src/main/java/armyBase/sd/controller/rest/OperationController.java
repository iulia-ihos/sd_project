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

import armyBase.sd.dto.OperationDTO;
import armyBase.sd.model.Operation;
import armyBase.sd.model.Soldier;
import armyBase.sd.service.IOperationService;
import armyBase.sd.service.ISoldierOperationService;

@RestController
@RequestMapping("operation")
public class OperationController {
		private final IOperationService opService;

	    @Autowired
	    public OperationController(IOperationService opService) {
	        this.opService = opService;
	    }
	    
	    @Autowired ISoldierOperationService soldOpService;

	    @GetMapping("getAll")
		public List<Operation> getAll() {
		        try {
		            return opService.getAll();
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		}
	    
	
	   
		@PutMapping("add")
		public Operation add(@RequestBody OperationDTO op)
		{
			 try {
				 return opService.create(op);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
			
		}
		

		@PostMapping("update")
		public Operation update(@RequestBody OperationDTO op) {
		        try {
		            return opService.update(op);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }

		@GetMapping("getById")
		public Operation getById(@RequestParam Long id) {
		        try {
		            return opService.getById(id);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
		
		@GetMapping("getSoldiers/{id}")
		public List<Soldier> getSoldiers(@PathVariable Long id) {
		      return soldOpService.getAllSoldiersByOperation(id);
		    }
		
		

		@DeleteMapping("deleteById")
		public String deleteById(@RequestParam Long id) {
		        try {
		           opService.deleteById(id);
		            return "Deleted!";
		        } catch (Exception e) {
		            return e.getMessage();
		        }
		    }

}
