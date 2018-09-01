package armyBase.sd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "training")

public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTraining;
	
	@Column(name = "startTime", nullable = false)
	private Date startTime;
	
	@Column(name = "endTime", nullable = false)
	private Date endTime;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
    @JoinColumn(name = "idMilitaryBase",nullable=false)
    private MilitaryBase trainingBase;
	
	@ManyToOne
    @JoinColumn(name = "idSoldier",nullable=false)
    private Soldier instructor;
	
	public Training(){
		
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
