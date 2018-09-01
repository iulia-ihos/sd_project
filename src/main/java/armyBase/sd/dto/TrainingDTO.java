package armyBase.sd.dto;

import java.io.Serializable;
import java.util.Date;

import armyBase.sd.model.MilitaryBase;
import armyBase.sd.model.Soldier;

@SuppressWarnings("serial")
public class TrainingDTO implements Serializable {
	
	private Long idTraining;
	
	private Date startTime;
	
	private Date endTime;
	
	private String description;

    private MilitaryBase trainingBase;

    private Soldier instructor;
	
	public TrainingDTO(){
		
	}

	public Long getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(Long idTraining) {
		this.idTraining = idTraining;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MilitaryBase getTrainingBase() {
		return trainingBase;
	}

	public void setTrainingBase(MilitaryBase trainingBase) {
		this.trainingBase = trainingBase;
	}

	public Soldier getInstructor() {
		return instructor;
	}

	public void setInstructor(Soldier instructor) {
		this.instructor = instructor;
	}

}
