package in.skumar.services;

import org.springframework.stereotype.Service;

import in.skumar.bindings.DashbordCard;
import in.skumar.bindings.LoginForm;

@Service
public class UserServiceImpl implements UserServices{

	@Override
	public boolean login(LoginForm loginForm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String recoverPWD(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DashbordCard fetchDashbordInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
