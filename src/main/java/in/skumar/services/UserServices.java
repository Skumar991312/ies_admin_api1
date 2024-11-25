package in.skumar.services;

import in.skumar.bindings.DashbordCard;
import in.skumar.bindings.LoginForm;

public interface UserServices {
	
   //Login User Data Form
  public boolean login(LoginForm loginForm);
  
  //User Data Forget & Recover by Email
  public String recoverPWD(String email);
  
  public  DashbordCard fetchDashbordInfo();
  
  
}
