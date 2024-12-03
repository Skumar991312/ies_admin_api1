package in.skumar.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.skumar.bindings.DashbordCard;
import in.skumar.bindings.LoginForm;
import in.skumar.bindings.UserAccForm;
import in.skumar.entity.EligEntity;
import in.skumar.entity.UserEntity;
import in.skumar.repo.EligRepo;
import in.skumar.repo.PlanRepo;
import in.skumar.repo.UserRepo;
import in.skumar.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserServices{
	
  @Autowired
  private UserRepo userRepo;
  
  @Autowired
  private EmailUtils emailUtils;
  
  @Autowired
  private EligRepo eligRepo;
  
  @Autowired
  private PlanRepo planRepo;
  
	@Override
	public String login(LoginForm loginForm) {
		
		UserEntity entity=userRepo.findByEmailAndPwd(loginForm.getEmail(),loginForm.getPwd());
		
		if(entity == null) {
			
			return "Invalid Credentials";
		}
		if("Y".equals(entity.getActiveSw()) && "UNLOCKED".equals(entity.getAccountStatus())){
			
		   return "Success"; 
		}
		else {
			return "Account Locked/In-Active";
		}
		
	}

	@Override
	public boolean recoverPWD(String email) {
		
		  UserEntity userEntity=userRepo.findByEmail(email);
		  
		  if(null==userEntity){
			  
			  return false;
			  
	  }else {
					  
		String subject="Recover Pwd";
		String body=readEmailBody("FORGET_EMAIL_BODY.txt",userEntity);
		
		return emailUtils.senEmail(subject, body,email);
		
		
		  }
	}

	private String readEmailBody(String string, UserEntity userEntity) {
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DashbordCard fetchDashbordInfo() {
		
	Long planCont=planRepo.count();
	
	List<EligEntity> eligList=eligRepo.findAll();
	
	Long approvedCount=eligList.stream().filter(ed->ed.getPlanStatus().equals("AP")).count();
	
	
	Long denined=eligList.stream().filter(ed->ed.getPlanStatus().equals("DN")).count();
	
	    Double benefitAmt=eligList.stream().mapToDouble(ed->ed.getBenefitAmt()).summaryStatistics().getSum();
	
	DashbordCard card=new DashbordCard();
	
	card.setPlanCout(planCont);
	card.setApprovedCount(approvedCount);
	card.setBenefitAtmGiven(benefitAmt);
	card.setDeninedCount(denined);
	
	return card;
	}

	@Override
	public UserAccForm getUserByEmail(String email) {
		
		UserEntity userEntity=userRepo.findByEmail(email);
		UserAccForm user=new UserAccForm();
		BeanUtils.copyProperties(userEntity, user);
		
		return user;
	}

}

 