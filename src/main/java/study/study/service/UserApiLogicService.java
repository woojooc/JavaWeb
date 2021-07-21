package study.study.service;

import org.springframework.stereotype.Service;
import study.study.model.entity.User;
import study.study.model.enumclass.UserStatus;
import study.study.model.network.Header;
import study.study.model.network.request.UserApiRequest;
import study.study.model.network.response.UserApiResponse;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse,User> {
        //implements CrudInterface<UserApiRequest, UserApiResponse> {

    //@Autowired
    //private UserRepository userRepository;

    //1. request DATA 가져오기
    //2. USer 생성
    //3. 생성된 데이터 기준으로 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request Data 가져오기
        UserApiRequest userApiRequest = request.getData();

         User existingUser = baseRepository.findByEmail(userApiRequest.getEmail());

        if(existingUser != null)
        {
           return responseError("400");
        }

        //2. USer 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        //3. 생성된 데이터 기준으로 -> UserApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getONe, getById
        Optional<User> optional = baseRepository.findById(id);

        // user -> userApiReponse return
        return optional
                .map(user -> response(user))
                .orElseGet(()->Header.ERROR("데이터 없음")); // user가 없다면.

        // 이렇게 한번에 람다로 표현할 수 있다.
        /*
        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(()->Header.ERROR("데이터 없음"));
        */
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        //모든 데이터가 정상적으로 왔다는 가정하에 예외처리들 안함
        // 1 data
        UserApiRequest userApiRequest = request.getData();

        //2 id -> user 데이터 찾기
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            // 3  data  ->  update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;        // DB 반영 x
                })
                .map(user -> baseRepository.save(user)) // update 해당 id 에대해서 -> newUser 반환
                .map(updateUser -> response(updateUser))            // 4 userApiResponse 만들어준다.
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {

        // id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);

        // repository -> delete
        return optional.map(user -> {
            baseRepository.delete(user);

                    // response return
                    return Header.OK();
                 })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user) {
        //user -> userApiResponse 만들어서 리턴

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())   //todo 암호화 길이
                .email(user.getEmail())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        //Header + data return
        return Header.OK(userApiResponse);
    }

    private Header<UserApiResponse> responseError(String errorCode) {

        return Header.BadRequest(errorCode);
    }
}
