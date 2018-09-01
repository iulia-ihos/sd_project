package armyBase.sd.dto;



import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import armyBase.sd.model.MilitaryBase;
import armyBase.sd.model.Operation;
import armyBase.sd.model.Rank;
import armyBase.sd.model.SoldierOperation;
import armyBase.sd.model.Training;
import armyBase.sd.model.UserDetailed;



@SuppressWarnings("serial")
public class SoldierDTO implements Serializable {

	private Long idSoldier;

	private Rank rank;

	private Date dob;
	
	private String fullName;

	private String tagNumber;

	private String alias;
	
	private MilitaryBase base;
	
	private Set<SoldierOperation> soldierOperations;
	
	private Set<Operation> operationsInCharge;
	
	private Set<Training>trainingsInCharge;
	
	private UserDetailed user;
	
	public SoldierDTO(){
	
	}


	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
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
	
	public String getTagNumber() {
		return tagNumber;
	}


	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
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
