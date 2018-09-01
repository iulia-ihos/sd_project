package armyBase.sd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import armyBase.sd.model.Rank;



@Repository
@Transactional

public interface  RankDAO extends JpaRepository<Rank, Long> {
	Rank findByIdRank(Long id);
}
