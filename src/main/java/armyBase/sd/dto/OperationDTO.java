package armyBase.sd.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import armyBase.sd.model.Soldier;
import armyBase.sd.model.SoldierOperation;

@SuppressWarnings("serial")
public class OperationDTO implements Serializable {
	
	private Long idOperation;
	
	
	private Set<SoldierOperation> operationSoldiers;
	
    private Soldier commander;
	
	private String status;
	
	private String description;
	
	private Date startDate;
	
	private Date endDate;
	
	
	public OperationDTO(){
		
	}


	public Long getIdOperation() {
		return idOperation;
	}


	public void setIdOperation(Long idOperation) {
		this.idOperation = idOperation;
	}


	public Set<SoldierOperation> getOperationSoldiers() {
		return operationSoldiers;
	}


	public void setOperationSoldiers(Set<SoldierOperation> operationSoldiers) {
		this.operationSoldiers = operationSoldiers;
	}


	public Soldier getCommander() {
		return commander;
	}


	public void setCommander(Soldier commander) {
		this.commander = commander;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
