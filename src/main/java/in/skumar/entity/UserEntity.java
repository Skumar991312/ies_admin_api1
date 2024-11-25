package in.skumar.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="IES_USERS")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String fullName;
	private String userGender;
	private String userEmail;
	private String userPzzd;
	private Long userPhno;
	private LocalDate userDob;
	private Integer userSsn;
	
	private String accountStatus="LOCKED";
	private String activeSw="ACTIVE";
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdDate;
	
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	private Integer createdBy;
	private Integer updatedBy;
	
	private Integer userRole;
	
	@OneToMany(mappedBy ="useruId",cascade =CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PlanEntity> planEntities;
   
	@OneToMany(mappedBy = "usercId")
	@JsonBackReference
    private List<CitizenAppEntity> citizens;
   
	
	
	
	
	

}
