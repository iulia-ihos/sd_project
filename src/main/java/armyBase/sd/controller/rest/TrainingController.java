package armyBase.sd.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import armyBase.sd.dto.TrainingDTO;
import armyBase.sd.model.Training;
import armyBase.sd.service.ITrainingService;



@RestController
@RequestMapping("training")
public class TrainingController {
		private final ITrainingService service;

	    @Autowired
	    public TrainingController(ITrainingService service) {
	        this.service = service;
	    }

	    @GetMapping("getAll")
		public List<Training> getAll() {
		            return service.getAll();
		}
	    
	    @PreAuthorize("hasRole('ROLE_ADMIN')")
		@PostMapping("add")
		public Training add(@RequestBody TrainingDTO training)
		{
				 return service.create(training);

		}
		
	    @PreAuthorize("hasRole('ROLE_ADMIN')")
		@PutMapping("update")
		public Training update(@RequestBody TrainingDTO training) {

		            return service.update(training);
		    }

		@GetMapping("getById/{id}")
		public Training getById(@PathVariable Long id) {
		            return service.getById(id);
		    }
		
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		@DeleteMapping("deleteById/{id}")
		public String deleteById(@PathVariable Long id) {
		            return "Deleted!";
		    }

}
