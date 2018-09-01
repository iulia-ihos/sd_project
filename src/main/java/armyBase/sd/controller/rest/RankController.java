package armyBase.sd.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import armyBase.sd.model.Rank;

import armyBase.sd.service.IRankService;



@RestController
@RequestMapping("rank")
public class RankController {
		private final IRankService rankService;

	    @Autowired
	    public RankController(IRankService rankService) {
	        this.rankService = rankService;
	    }

	    @GetMapping("getAll")
		public List<Rank> getAll() {
		        try {
		            return rankService.getAll();
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		}
	    
	    @GetMapping("getById")
		public Rank getById(@RequestParam Long id) {
		        try {
		            return rankService.getById(id);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
}