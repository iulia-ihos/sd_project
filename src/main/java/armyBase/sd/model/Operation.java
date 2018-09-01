package armyBase.sd.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "operation")

public class Operation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOperation;
	
	@JsonIgnore
	@OneToMany(mappedBy= "operationToSoldier",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<SoldierOperation> operationSoldiers;
	
	
	@ManyToOne
    @JoinColumn(name = "idSoldier",nullable=false)
    private Soldier commander;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "startDate")
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	
	public Operation(){
		
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
