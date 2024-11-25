package in.skumar.services;

import java.util.List;

import org.springframework.stereotype.Service;

import in.skumar.bindings.UnlockAccForm;
import in.skumar.bindings.UserAccForm;

@Service
public class AccountServicesImpl implements AccountServices {

	@Override
	public boolean createUserAccount(UserAccForm accForm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserAccForm> fetchUserAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccForm getUserAccById(Integer accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeAccStatus(Integer accId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unlockUserAcc(UnlockAccForm unlockAccForm) {
		// TODO Auto-generated method stub
		return null;
	}

}
