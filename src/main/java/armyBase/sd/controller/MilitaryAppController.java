package armyBase.sd.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@RequestMapping("/login")
	public String login() {
		return "login.html";
	}

	@RequestMapping("/register")
	public String register() {
		return "register.html";
	}
	
	@RequestMapping("/base")
	public String getBase(ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName(); 
		modelMap.addAttribute("name",name );
		modelMap.put("menuList", nav.addPage(PageName.BASE).build());
		Set<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		modelMap.put("roles",roles);
		return "militaryBase.html";
	}
	
	@RequestMapping("/base/{id}")
	public String getBaseDetails(ModelMap modelMap, @PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName(); 
		modelMap.addAttribute("name",name );
		modelMap.put("menuList", nav.addPage(PageName.BASE).build());
		Set<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		modelMap.put("roles",roles);
		return "militaryBaseDetails.html";
	}
	
	@RequestMapping("/soldier")
	public String getSoldier(ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName(); 
		modelMap.addAttribute("name",name );
		modelMap.put("menuList", nav.addPage(PageName.SOLDIER).build());
		Set<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		modelMap.put("roles",roles);
		return "soldier.html";
	}
	
	@RequestMapping("/soldier/{id}")
	public String getSoldierDetails(ModelMap modelMap, @PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		boolean isAdmin = false;
		Set<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		modelMap.put("roles",roles);
		for(String role:roles) {
			if(role.equals("ROLE_ADMIN"))
				isAdmin = true;
		}
		modelMap.addAttribute("isAdmin",isAdmin );
		modelMap.addAttribute("name",name );
		modelMap.put("menuList", nav.addPage(PageName.SOLDIER).build());
		return "soldierDetails.html";
	}
	
	@RequestMapping("/operation")
	public String getOperation(ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName(); 
		modelMap.addAttribute("name",name );
		modelMap.put("menuList", nav.addPage(PageName.OPERATION).build());
		Set<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		modelMap.put("roles",roles);
		return "operation.html";
	}
	
	@RequestMapping("/operation/{id}")
	public String getOperationDetails(ModelMap modelMap, @PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName(); 
		modelMap.addAttribute("name",name );
		boolean isCommander = false;
		Set<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		for(String role:roles) {
			if(role.equals("ROLE_COMMANDER"))
				isCommander = true;
		}
		modelMap.put("role", roles);
		modelMap.addAttribute("isCommander",isCommander );
		modelMap.put("menuList", nav.addPage(PageName.OPERATION).build());
		return "operationDetails.html";
	}
	
	@RequestMapping("/training")
	public String geTraining(ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName(); 
		modelMap.addAttribute("name",name );
		modelMap.put("menuList", nav.addPage(PageName.TRAINING).build());
		Set<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		modelMap.put("roles",roles);
		return "training.html";
	}
	
	@RequestMapping("/training/{id}")
	public String getTrainingDetails(ModelMap modelMap, @PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName(); 
		modelMap.addAttribute("name",name );
		modelMap.put("menuList", nav.addPage(PageName.TRAINING).build());
		Set<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		modelMap.put("roles",roles);
		return "trainingDetails.html";
	}
}
