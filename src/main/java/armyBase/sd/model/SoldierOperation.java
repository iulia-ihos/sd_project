package armyBase.sd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "soldierOperation")

public class SoldierOperation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSoldierOperation;
	
	//@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "idSoldier",nullable=false)
    private Soldier soldierToOperation;

	//@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idOperation",nullable=false)
    private Operation operationToSoldier;

    public SoldierOperation(){
    	
    }

	public Long getIdSoldierOperation() {
		return idSoldierOperation;
	}

	public void setIdSoldierOperation(Long idSoldierOperation) {
		this.idSoldierOperation = idSoldierOperation;
	}

	public Soldier getSoldierToOperation() {
		return soldierToOperation;
	}

	public void setSoldierToOperation(Soldier soldierToOperation) {
		this.soldierToOperation = soldierToOperation;
	}

	public Operation getOperationToSoldier() {
		return operationToSoldier;
	}

	public void setOperationToSoldier(Operation operationToSoldier) {
		this.operationToSoldier = operationToSoldier;
	}
}
