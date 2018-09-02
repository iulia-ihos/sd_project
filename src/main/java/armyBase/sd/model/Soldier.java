package armyBase.sd.model;



import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="soldier")
public class Soldier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSoldier;
	
	@ManyToOne
	@JoinColumn(name = "idRank", nullable = false)
	private Rank rank;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(name="fullName")
	private String fullName;
	
	@Column(name="tagNumber", length = 7, unique = true, nullable = false)
	private String tagNumber;

	@Column(name="alias")
	private String alias;
	
	
	@ManyToOne
	@JoinColumn(name = "idMilitaryBase", nullable = false)
	private MilitaryBase base;
	
	@JsonIgnore
	@OneToMany(mappedBy= "soldierToOperation",fetch = FetchType.EAGER)
	private Set<SoldierOperation> soldierOperations;
	
	@JsonIgnore
	@OneToMany(mappedBy= "commander",fetch = FetchType.EAGER)
	private Set<Operation> operationsInCharge;
	
	@JsonIgnore
	@OneToMany(mappedBy= "instructor",fetch = FetchType.EAGER)
	private Set<Training>trainingsInCharge;
	
	
	@OneToOne
    @JoinColumn(name = "idUser", nullable = true, unique = true)
    private UserDetailed user;
	
	public Soldier(){
	
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public MilitaryBase getBase() {
		return base;
	}

	public void setBase(MilitaryBase base) {
		this.base = base;
	}

	public Set<SoldierOperation> getSoldierOperations() {
		return soldierOperations;
	}

	public void setSoldierOperations(Set<SoldierOperation> soldierOperations) {
		this.soldierOperations = soldierOperations;
	}

	public Set<Operation> getOperationsInCharge() {
		return operationsInCharge;
	}

	public void setOperationsInCharge(Set<Operation> operationsInCharge) {
		this.operationsInCharge = operationsInCharge;
	}

	public Set<Training> getTrainingsInCharge() {
		return trainingsInCharge;
	}

	public void setTrainingsInCharge(Set<Training> trainingsInCharge) {
		this.trainingsInCharge = trainingsInCharge;
	}



	public Long getIdSoldier() {
		return idSoldier;
	}



	public void setIdSoldier(Long idSoldier) {
		this.idSoldier = idSoldier;
	}



	public UserDetailed getUser() {
		return user;
	}



	public void setUser(UserDetailed user) {
		this.user = user;
	}
	
}
