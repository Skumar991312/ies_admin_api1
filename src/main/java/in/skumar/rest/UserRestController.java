package in.skumar.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.skumar.bindings.LoginForm;
import in.skumar.services.UserServices;

@RestController
public class UserRestController {
	
	@Autowired
	private UserServices userService;
	
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		
		   String status=userService.login(loginForm);
		
		   if(status.equals("Success")) {
			   
		  return "redirect:/dashbord";
		  
		   }else {
			   return status;
		   }
	}
}
		
	