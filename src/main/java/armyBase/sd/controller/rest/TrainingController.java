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
		        try {
		            return service.getAll();
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		}
	    
	
		@PutMapping("add")
		public Training add(@RequestBody TrainingDTO training)
		{
			 try {
				 return service.create(training);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
			
		}
		
	
		@PostMapping("update")
		public Training update(@RequestBody TrainingDTO training) {
		        try {
		            return service.update(training);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }

		@GetMapping("getById")
		public Training getById(@RequestParam Long id) {
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
