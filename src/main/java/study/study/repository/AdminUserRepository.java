package study.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.study.model.entity.AdminUser;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {
}
