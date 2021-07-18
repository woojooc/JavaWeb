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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderDetailList","partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;
    private String content;
    private BigDecimal price;
    private String brandName;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    //Item N : 1 Partner
    @ManyToOne
    private Partner partner;

    // Item 1 : N Detail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;


    /*
    // 1:N
    // LAZY = 지연 로딩, EAGER = 즉시 로딩

    //from order_detail orderdetai0_ left outer join item item1_ on orderdetai0_.item_id=item1_.id where orderdetai0_.user_id=?
    // LAZY = SELECT * FROM item where id = ?
    // 선택한 아이디에 대해서만 찾아온다.
    // 연관관계에 있어서는 여러개 있으면 이거 추천

    // EAGER = 1 : 1
    // item_id = order_deetail.item_id
    // user_id = order_detail.user_id
    // where item.id = ?
    // JOIN
    // 연관관계가 형성된 모든 테이블에 대해서 join이 일어난다. 성능저하. 가지고 오지 못할 가능성도
    @OneToMany(fetch = FetchType.LAZY, mappedBy="item")
    private List<OrderDetail> orderDetailList;
     */
}
