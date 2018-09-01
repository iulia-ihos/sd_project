package armyBase.sd.controller.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import armyBase.sd.dto.MilitaryBaseDTO;
import armyBase.sd.exceptions.InvalidDataException;
import armyBase.sd.model.MilitaryBase;
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
	    
	
		@PostMapping("add")
		public MilitaryBase add(@RequestBody MilitaryBaseDTO base) throws InvalidDataException
		{
			return baseService.create(base);
		}
		

		@PostMapping("update")
		public MilitaryBase update(@RequestBody MilitaryBaseDTO base) {
		        try {
		            return baseService.update(base);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }

		@GetMapping("getById")
		public MilitaryBase getById(@RequestParam Long id) {
		        try {
		            return baseService.getById(id);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
		
	
		@DeleteMapping("deleteById/{id} ")
		public String deleteById(@PathVariable Long id) {
		        try {
		           baseService.deleteById(id);
		            return "Deleted!";
		        } catch (Exception e) {
		            return e.getMessage();
		        }
		    }

}
