package study.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user","item"} )   // lombok 상호참조 풀림
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;

    // N OrderDetail : 1 user
    @ManyToOne
    private User user;  // 알아서 user_id 와 연결됨
    //private Long userId;

    // N : 1
    @ManyToOne
    private Item item;
    //private Long itemId;


}
