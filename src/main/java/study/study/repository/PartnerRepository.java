package study.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.study.model.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {

}
