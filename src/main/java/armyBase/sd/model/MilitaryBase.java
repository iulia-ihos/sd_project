package armyBase.sd.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import armyBase.sd.dto.MilitaryBaseDTO;

@Entity
@Table(name = "militaryBase")

public class MilitaryBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMilitaryBase;
	
	@JsonIgnore
	@OneToMany(mappedBy= "base",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Soldier> soldiers;
	
	@JsonIgnore
	@OneToMany(mappedBy= "trainingBase",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Training> training;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "name")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MilitaryBase(){
		
	}
	
    public MilitaryBase(MilitaryBaseDTO base){
    	this.idMilitaryBase = base.getIdMilitaryBase();
		
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
	
	
}
