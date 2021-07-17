package study.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = {"user","item"} )   // lombok 상호참조 풀림
@ToString(exclude = {"orderGroup","item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    //private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    //Detail N : 1 Item
    @ManyToOne
    private Item item;

    // Detail N: 1 Group
    @ManyToOne
    private OrderGroup orderGroup;  // mappedBY 의 변수명과 일치해야 한다.

    /*
    // N OrderDetail : 1 user
    @ManyToOne
    private User user;  // 알아서 user_id 와 연결됨
    //private Long userId;

    // N : 1
    @ManyToOne
    private Item item;
    //private Long itemId;
    */

}
