package armyBase.sd.dto;

import java.io.Serializable;
import java.util.Set;

import armyBase.sd.model.Soldier;

@SuppressWarnings("serial")
public class RankDTO implements Serializable{
	
	private Long idRank;
	
	private Set<Soldier> soldiersToRank;
	
	private String rankName;
	
	public RankDTO(){
		
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

	@Override
	public String toString(){
		return "ID : " + getIdRank() + " = " + getRankName();
	}
}
