package study.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity                      // == table
//@Table(name = "user")      //클래스와 이름이 같으면 생략 가능
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        //@Column(name = "account")     //변수명과 동일시 자동매치
        private String account;
        private String email;
        private String phoneNumber;
        private LocalDateTime createdAt;
        private String createdBy;
        private LocalDateTime updatedAt;
        private String updatedBy;

        // user 1 : M OrderDetail
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")   // OrderDetail 의 User user와 이름 동일해야한다
        private List<OrderDetail> orderDetailList;
}
