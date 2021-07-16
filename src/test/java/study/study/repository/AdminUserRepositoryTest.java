package study.study.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.study.StudyApplicationTests;
import study.study.model.entity.AdminUser;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);
    }
}