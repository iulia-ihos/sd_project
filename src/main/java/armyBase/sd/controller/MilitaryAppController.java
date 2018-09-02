package armyBase.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import armyBase.sd.navBar.NavFactory.PageName;
import armyBase.sd.navBar.NavFactory.TopNavFactory;

@Controller
public class MilitaryAppController {

	@Autowired
	private TopNavFactory nav;
	
	@RequestMapping("/")
	public String start() {
		return "start.html";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register.html";
	}
	
	@RequestMapping("/base")
	public String getBase(ModelMap modelMap) {
		modelMap.put("menuList", nav.addPage(PageName.BASE).build());
		return "militaryBase.html";
	}
	
	@RequestMapping("/base/{id}")
	public String getBaseDetails(ModelMap modelMap, @PathVariable Long id) {
		modelMap.put("menuList", nav.addPage(PageName.BASE).build());
		return "militaryBaseDetails.html";
	}
	
	@RequestMapping("/soldier")
	public String getSoldier(ModelMap modelMap) {
		modelMap.put("menuList", nav.addPage(PageName.SOLDIER).build());
		return "soldier.html";
	}
	
	@RequestMapping("/soldier/{id}")
	public String getSoldierDetails(ModelMap modelMap, @PathVariable Long id) {
		modelMap.put("menuList", nav.addPage(PageName.SOLDIER).build());
		return "soldierDetails.html";
	}
	
	@RequestMapping("/operation")
	public String getOperation(ModelMap modelMap) {
		modelMap.put("menuList", nav.addPage(PageName.OPERATION).build());
		return "operation.html";
	}
	
	@RequestMapping("/operation/{id}")
	public String getOperationDetails(ModelMap modelMap, @PathVariable Long id) {
		modelMap.put("menuList", nav.addPage(PageName.OPERATION).build());
		return "operationDetails.html";
	}
	
	@RequestMapping("/training")
	public String geTraining(ModelMap modelMap) {
		modelMap.put("menuList", nav.addPage(PageName.TRAINING).build());
		return "training.html";
	}
	
	@RequestMapping("/training/{id}")
	public String getTrainingDetails(ModelMap modelMap, @PathVariable Long id) {
		modelMap.put("menuList", nav.addPage(PageName.TRAINING).build());
		return "trainingDetails.html";
	}
}
