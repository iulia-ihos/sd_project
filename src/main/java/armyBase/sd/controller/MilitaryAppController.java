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
	public String base(ModelMap modelMap) {
		modelMap.put("menuList", nav.addPage(PageName.BASE).build());
		return "militaryBase.html";
	}
	
	@RequestMapping("/base/{id}")
	public String baseDetails(ModelMap modelMap) {
		modelMap.put("menuList", nav.addPage(PageName.BASE).build());
		return "militaryBaseDetails.html";
	}
	
	@RequestMapping("/soldier")
	public String soldier(ModelMap modelMap) {
		modelMap.put("menuList", nav.addPage(PageName.SOLDIER).build());
		return "soldier.html";
	}
	
	@RequestMapping("/soldier/{id}")
	public String soldier(ModelMap modelMap, @PathVariable Long id) {
		modelMap.put("menuList", nav.addPage(PageName.SOLDIER).build());
		return "soldierDetails.html";
	}
	
	@RequestMapping("/operation")
	public String operations(ModelMap modelMap) {
		modelMap.put("menuList", nav.addPage(PageName.OPERATION).build());
		return "operations.html";
	}
	
	@RequestMapping("/training")
	public String training(ModelMap modelMap) {
		modelMap.put("menuList", nav.addPage(PageName.TRAINING).build());
		return "training.html";
	}
}
