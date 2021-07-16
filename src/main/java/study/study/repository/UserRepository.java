package study.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.study.model.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    // 쿼리 메서드. 쿼리문을 메서드처럼 사용
    // select * from user where account =? << test02 등 아이디로 검색
    Optional<User> findByAccount(String account);   //변수명은 중용하지 않으나 코딩할때 컬럼명과 맞춰주는 것이 좋다.

    Optional<User> findByEmail(String email);

    // select * from user where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account,String email);

}
