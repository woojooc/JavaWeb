package study.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.study.model.entity.Settlement;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement,Long> {

}

