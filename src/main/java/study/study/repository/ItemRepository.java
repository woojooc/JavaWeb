package study.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.study.model.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
