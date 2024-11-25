package in.skumar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.skumar.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	
	
}


