package study.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.study.model.entity.OrderGroup;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup,Long> {
}
