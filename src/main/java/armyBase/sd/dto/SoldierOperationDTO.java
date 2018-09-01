package armyBase.sd.dto;

import java.io.Serializable;

import armyBase.sd.model.Operation;
import armyBase.sd.model.Soldier;

@SuppressWarnings("serial")
public class SoldierOperationDTO implements Serializable {
	
	
	private Long idSoldierOperation;
	
    private Soldier soldierToOperation;

    private Operation operationToSoldier;

    public SoldierOperationDTO(){
    	
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
