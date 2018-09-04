package armyBase.sd.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import armyBase.sd.dto.UserDTO;
import armyBase.sd.model.UserDetailed;
import armyBase.sd.service.IUserService;



@RestController
@RequestMapping("user")
public class UserController {
		private final IUserService userService;

	    @Autowired
	    public UserController(IUserService userService) {
	        this.userService = userService;
	    }

	    @GetMapping("login")
	   	public String login(@RequestParam String email, String password) {
	   	        try {
	   	            if (userService.login(email,password)) return "LOGIN SUCCESSFUL";
	   	        } catch (Exception e) {
	   	            e.printStackTrace();
	   	            return null;
	   	        }
				return "WRONG PASSWORD";
	   	}
	    
	    @GetMapping("changePassword")
	   	public UserDetailed changePassword(@RequestParam String email, String password) {
	   	        try {
	   	            return userService.changePassword(email, password);
	   	        } catch (Exception e) {
	   	            e.printStackTrace();
	   	            return null;
	   	        }
	   	}

		@GetMapping("getByEmail/{email}")
		public ResponseEntity<UserDetailed> getByEmail(@PathVariable String email) {
			System.out.println( "\n\n\n\ngetByEmail");
		        try {
		            return ResponseEntity.ok(userService.getByEmail(email));
		        } catch (Exception e) {
		            e.printStackTrace();
		            return ResponseEntity.ok(new UserDetailed());
		        }
		    }
		
		@GetMapping("getById/{id}")
		public UserDetailed getById(@PathVariable Long id) {
		        try {
		            return userService.getById(id);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
		
		@PostMapping("register")
		public UserDetailed register(@RequestBody UserDTO user)
		{
			 try {
				 return userService.create(user);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
			
		}   
}
