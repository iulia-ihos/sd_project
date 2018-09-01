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

import armyBase.sd.dto.RankDTO;

@Entity
@Table(name = "rank")

public class Rank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRank;
	
	@JsonIgnore
	@OneToMany(mappedBy= "rank",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Soldier> soldiersToRank;
	
	@Column(name = "rankName", nullable = false)
	private String rankName;
	
	public Rank(){
		
	}
	
	public Rank(RankDTO rank){
		this.idRank = rank.getIdRank();
	}

	public Long getIdRank() {
		return idRank;
	}

	public void setIdRank(Long idRank) {
		this.idRank = idRank;
	}

	public Set<Soldier> getSoldiersToRank() {
		return soldiersToRank;
	}

	public void setSoldiersToRank(Set<Soldier> soldiersToRank) {
		this.soldiersToRank = soldiersToRank;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

}
