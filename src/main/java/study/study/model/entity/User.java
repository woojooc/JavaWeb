package study.study.model.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity                      // == table
//@Table(name = "user")      //클래스와 이름이 같으면 생략 가능
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder                        //생성
@Accessors(chain = true)        //체이닝된 형태로 객체를 수정하거나 생성가능. 값 수정
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        //@Column(name = "account")     //변수명과 동일시 자동매치
        private String account;

        private String password;
        private String status;

        private String email;
        private String phoneNumber;


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

        // User 1 : N OrderGroup
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        private List<OrderGroup> orderGroupList;

        /*
        // user 1 : M OrderDetail
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")   // OrderDetail 의 User user와 이름 동일해야한다
        private List<OrderDetail> orderDetailList;
        */
}

