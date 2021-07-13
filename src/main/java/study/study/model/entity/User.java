package study.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
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
}
