package study.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = {"user","item"} )   // lombok 상호참조 풀림
@ToString(exclude = {"orderGroup","item"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    //private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
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
