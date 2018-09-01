package armyBase.sd.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import armyBase.sd.model.Soldier;

@Transactional
@Repository
public interface SoldierDAO extends JpaRepository<Soldier, Long>{
	Soldier findByIdSoldier(Long id);
	Soldier findByUserIdUser(Long id);
	Soldier findByTagNumber(String id);

	@Modifying
	@Query("delete from Soldier s where s.idSoldier = ?1")
	void deleteByIdSoldier(Long id);

}
