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

import armyBase.sd.dto.MilitaryBaseDTO;
import armyBase.sd.exceptions.InvalidDataException;
import armyBase.sd.model.MilitaryBase;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.Training;
import armyBase.sd.service.IMilitaryBaseService;




@RestController
@RequestMapping("base")
public class MilitaryBaseController {
		private final IMilitaryBaseService baseService;

	    @Autowired
	    public MilitaryBaseController(IMilitaryBaseService baseService) {
	        this.baseService = baseService;
	    }

	    @GetMapping("getAll")
		public List<MilitaryBase> getAll() {
		        try {
		            return baseService.getAll();
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		}
	    
	    @GetMapping("getSoldiers/{id}")
		public List<Soldier> getSoldiers(@PathVariable Long id) {
		       return baseService.getSoldiers(id);
		}
	    
	    @GetMapping("getTrainings/{id}")
		public List<Training> gettrainings(@PathVariable Long id) {
		       return baseService.getTrainings(id);
		}
	
		@PostMapping("add")
		public MilitaryBase add(@RequestBody MilitaryBaseDTO base) throws InvalidDataException
		{
			return baseService.create(base);
		}
		

		@PutMapping("update")
		public MilitaryBase update(@RequestBody MilitaryBaseDTO base) {
		        return baseService.update(base);
		    }

		@GetMapping("getById/{id}")
		public MilitaryBase getById(@PathVariable Long id) {
		        return baseService.getById(id);
		    }
		
	
		@DeleteMapping("deleteById/{id}")
		public String deleteById(@PathVariable Long id) {
			baseService.deleteById(id);
		    return "Deleted!";
		    }

}
