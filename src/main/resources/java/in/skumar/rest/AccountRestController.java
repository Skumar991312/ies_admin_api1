package in.skumar.rest;


import java.lang.System.Logger;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.skumar.bindings.UserAccForm;
import in.skumar.services.AccountServices;
import jakarta.websocket.server.PathParam;

@RestController
public class AccountRestController {
	
   private Logger logger=LoggerFactory.getLogger(AccountRestController.class);
	
	@Autowired
	private AccountServices accService;
	
	@PostMapping("/user")
	public ResponseEntity<String>  createAccount(@RequestBody UserAccForm userAccForm){
		 
		logger.debug("This is a debug message");
		
		boolean status=accService.createUserAccount(userAccForm);
		
		if(status) {
			return new ResponseEntity<>("Account Created",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Account Creations Failed",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	@GetMapping("/users")
	public ResponseEntity<List<UserAccForm>> getUsers(){
		
		List<UserAccForm> userList=accService.fetchUserAccount();
		
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}
   
	@GetMapping("/user{userId}")
	public ResponseEntity<UserAccForm> getUser(@PathParam("userId") Integer userId){

         UserAccForm user=accService.getUserAccById(userId);
         
      return new ResponseEntity<>(user,HttpStatus.OK);
}
	
	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccForm>> updatedUserAcc(@PathVariable("userId") Integer userId,@PathVariable("status") String status){
		
		accService.changeAccStatus(userId, status);
		
		List<UserAccForm> userList=accService.fetchUserAccount();
		
		return new ResponseEntity<>(userList,HttpStatus.OK); 
		
		
	}
} 
