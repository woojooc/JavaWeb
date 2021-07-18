package study.study.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {

    private Long id;    //DB INDEX

    private String account;

    private String  password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
}
