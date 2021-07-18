package study.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.study.ifs.CrudInterface;
import study.study.model.entity.User;
import study.study.model.network.Header;
import study.study.model.network.request.UserApiRequest;
import study.study.model.network.response.UserApiResponse;
import study.study.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    //1. request DATA 가져오기
    //2. USer 생성
    //3. 생성된 데이터 기준으로 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request Data 가져오기
        UserApiRequest userApiRequest = request.getData();

        //2. USer 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        //3. 생성된 데이터 기준으로 -> UserApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getONe, getById
        Optional<User> optional = userRepository.findById(id);

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
        return null;
    }

    @Override
    public Header delete(Long id) {


        return null;
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
}
