package armyBase.sd.dto;

import java.io.Serializable;
import java.util.Set;

import armyBase.sd.model.Soldier;
import armyBase.sd.model.Training;

@SuppressWarnings("serial")
public class MilitaryBaseDTO implements Serializable{
	
	
	private Long idMilitaryBase;
	
	private Set<Soldier> soldiers;
	
	private Set<Training> training;
	
	private Double latitude;
	
	private Double longitude;
	
	private String description;
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MilitaryBaseDTO(){
		
	}

	public Long getIdMilitaryBase() {
		return idMilitaryBase;
	}

	public void setIdMilitaryBase(Long idMilitaryBase) {
		this.idMilitaryBase = idMilitaryBase;
	}

	public Set<Soldier> getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(Set<Soldier> soldiers) {
		this.soldiers = soldiers;
	}

	public Set<Training> getTraining() {
		return training;
	}

	public void setTraining(Set<Training> training) {
		this.training = training;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return "ID : " + getIdMilitaryBase() + " Name " + description;
		
	}
	
	
}
