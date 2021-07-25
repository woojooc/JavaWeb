package study.study.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.study.StudyApplicationTests;
import study.study.model.entity.Settlement;
import study.study.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SettlementRepositoryTest extends StudyApplicationTests {

    @Autowired
    private SettlementRepository settlementRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {

        User user = userRepository.getById(1L);

        Settlement settlement = Settlement.builder()
                .user(user)
                .price(BigDecimal.valueOf(1000))
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(LocalDateTime.now())
                .build();

        Settlement newSettlement = settlementRepository.save(settlement);

        Assert.assertNotNull(newSettlement);
    }
}
