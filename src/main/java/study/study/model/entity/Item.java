package study.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;
    private String content;
    private Integer price;
    private String brandName;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;
    private String updatedBy;

    private Long partnerId;





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
