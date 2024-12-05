package in.skumar.services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import in.skumar.bindings.UnlockAccForm;
import in.skumar.bindings.UserAccForm;
import in.skumar.entity.UserEntity;
import in.skumar.repo.UserRepo;
import in.skumar.utils.EmailUtils;

@Service
public class AccountServicesImpl implements AccountServices {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean createUserAccount(UserAccForm accForm) {
      
		UserEntity entity=new UserEntity();
		
		   BeanUtils.copyProperties(accForm, entity);
		   
		   //set Random PWd
		   entity.setUserPwd(generatePwd());
		   
		   //set Account status
		   
		   entity.setAccountStatus("LOCKED");
		   entity.setActiveSw("Y");
		   userRepo.save(entity);
		   
		   //Send Email
		   String subject="User Registration";
		   String body=readEmailBody("REG_EMAIL_BODY.txt",entity);	   
		 return emailUtils.senEmail(subject, body, accForm.getEmail());	
	}
	
	private String readEmailBody(String filename, UserEntity user) {
		
		StringBuilder sb=new StringBuilder();
		
		try(Stream<String> lines=Files.lines(Paths.get(filename))){
			lines.forEach(line-> {
				
				line=line.replace("${FNAME}",user.getFullName());
				line=line.replace("${TEMP_PWD}",user.getUserPwd());
				line=line.replace("${EMAIL}",user.getUserEmail());
				
				sb.append(line);
				});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
     return sb.toString();		
		
	}


	@Override
	public List<UserAccForm> fetchUserAccount() {
		
	List<UserEntity> userEntities=userRepo.findAll();
	
     List<UserAccForm> users=new ArrayList<>();
      
       for(UserEntity userEntity: userEntities) {
    	   
    	   UserAccForm user=new UserAccForm();
    	   BeanUtils.copyProperties(userEntity, user);
    	   users.add(user);
    	  }
	  return users;
	}

	@Override
	public UserAccForm getUserAccById(Integer accId) {
		
		Optional<UserEntity> optional=userRepo.findById(accId);	
		  
		if(optional.isPresent()) {
			
		 UserEntity userEntity=optional.get();
		 UserAccForm user=new UserAccForm();
		 BeanUtils.copyProperties(userEntity, user);
		 return user;
		}
		  
		return null;
	}

	@Override
	public String changeAccStatus(Integer userId, String status) {
		
		int cnt=userRepo.updateUserAccStatus(userId, status);
		
		   if(cnt>0) {
			
			return "Status Changed";
		}
	
			return "Failed Changed";
	}

	@Override
	public String unlockUserAcc(UnlockAccForm unlockAccForm) {
		
		UserEntity entity=userRepo.findByEmail(unlockAccForm.getEmail());
		
		entity.setUserPwd(unlockAccForm.getNewPwd());
		entity.setAccountStatus("UNLOCKED");
		userRepo.save(entity);
		
		return "Account Unlocked";
	}
	
	private String generatePwd() {

	    // create a string of uppercase and lowercase characters and numbers
	    String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";

	    // combine all strings
	    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

	    // create random string builder
	    StringBuilder sb = new StringBuilder();

	    // create an object of Random class
	    Random random = new Random();

	    // specify length of random string
	    int length = 6;

	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = random.nextInt(alphaNumeric.length());

	      // get character specified by index
	      // from the string
	      char randomChar = alphaNumeric.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	    }

	    return sb.toString();
	}

}