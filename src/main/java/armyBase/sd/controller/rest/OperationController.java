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
		     return opService.getAll();
		}

		@PostMapping("add")
		public Operation add(@RequestBody OperationDTO op)
		{
			return opService.create(op);

		}
		
		@PutMapping("update")
		public Operation update(@RequestBody OperationDTO op) {
		       return opService.update(op);
		    }

		@GetMapping("getById/{id}")
		public Operation getById(@PathVariable Long id) {
		       return opService.getById(id);
		    }
		
		@GetMapping("getSoldiers/{id}")
		public List<Soldier> getSoldiers(@PathVariable Long id) {
		      return soldOpService.getAllSoldiersByOperation(id);
		    }

		@DeleteMapping("deleteById/{id}")
		public String deleteById(@PathVariable Long id) {
		       opService.deleteById(id);
		            return "Deleted!";
		    }

}
