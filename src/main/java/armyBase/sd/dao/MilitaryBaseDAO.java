package armyBase.sd.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import armyBase.sd.model.MilitaryBase;

@Transactional
@Repository

public interface MilitaryBaseDAO extends JpaRepository<MilitaryBase,Long> {
	MilitaryBase findByIdMilitaryBase(Long id);
	
	
}
