package study.study.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.study.StudyApplicationTests;
import study.study.model.entity.OrderDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("WATTING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
        orderDetail.setOrderGroupId(1L);
        orderDetail.setItemId(1L);
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);

        /*
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrderAt(LocalDateTime.now());

        //orderDetail.setUserId(1L);
        //orderDetail.setItemId(1L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        Assert.assertNotNull(newOrderDetail);

        */
    }
}
