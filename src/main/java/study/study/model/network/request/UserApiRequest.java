package study.study.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
