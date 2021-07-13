package study.study.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.study.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
}
