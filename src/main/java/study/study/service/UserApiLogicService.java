package study.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import study.study.model.entity.OrderGroup;
import study.study.model.entity.User;
import study.study.model.enumclass.UserStatus;
import study.study.model.network.Header;
import study.study.model.network.Pagination;
import study.study.model.network.request.UserApiRequest;
import study.study.model.network.response.ItemApiResponse;
import study.study.model.network.response.OrderGroupApiResponse;
import study.study.model.network.response.UserApiResponse;
import study.study.model.network.response.UserOrderInfoApiResponse;
import study.study.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//409 중복 충돌 status code
@ResponseStatus(code = HttpStatus.CONFLICT)
class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse,User> {
        //implements CrudInterface<UserApiRequest, UserApiResponse> {

    //@Autowired
    //private UserRepository userRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    //1. request DATA 가져오기
    //2. USer 생성
    //3. 생성된 데이터 기준으로 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request Data 가져오기
        UserApiRequest userApiRequest = request.getData();

        //User existingUser = baseRepository.findByEmail(userApiRequest.getEmail());

        //if(existingUser != null)
        //{
        //   return responseError("400");
        //}

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
        //return response(newUser);
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getONe, getById
        Optional<User> optional = baseRepository.findById(id);

        // user -> userApiReponse return
        return optional
                .map(user -> response(user))
                //.map(userApiResponse -> Header.OK(userApiResponse))
                .map(Header::OK)
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
                .map(Header::OK)
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

    //private Header<UserApiResponse> response(User user) {
    public UserApiResponse response(User user) {
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
        //return Header.OK(userApiResponse);
        return userApiResponse;
    }

    //내가만든거
    private Header<UserApiResponse> responseError(String errorCode) {

        return Header.BadRequest(errorCode);
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {

        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());
        // List<UserApiReponse>
        // Header <List<UserApiReponse>>
        ///*
        //이러한 객체를 통해서 필요한 정보만 보내도록 한다.  비밀번호 같은거 보호
        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();
        //*/

        return Header.OK(userApiResponseList, pagination);
        //return Header.OK(userApiResponseList);
    }

    public Header<UserApiResponse> checkEmailCreate(Header<UserApiRequest> request) {
        //DB에 있는 User 데이터 가져오고
        UserApiRequest userApiRequest = request.getData();

        //위의 userApiRequest에서 Email부분만 가져와서 있는지 여부를 확인하고
        Optional<User> checkEmail = userRepository.findByEmail(userApiRequest.getEmail());

        //map을 쓰려고 했는데 에러가 발생해서 if문으로 대체
        if (checkEmail.isPresent()){
            throw new AlreadyExistsException("중복된 이메일 입니다.");
        }
        else {
            User user = User.builder()
                    .account(userApiRequest.getAccount())
                    .password(userApiRequest.getPassword())
                    .status(UserStatus.REGISTERED)
                    .phoneNumber(userApiRequest.getPhoneNumber())
                    .email(userApiRequest.getEmail())
                    .registeredAt(LocalDateTime.now())
                    .build();

            User newUser= baseRepository.save(user);

            //3.여러번 사용하므로 맨 아래에 메서드 작성했음

            return Header.OK(response(newUser));

        }
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {

        //User
        User user = userRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        //OrderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = user.getOrderGroupList().stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.resposne(orderGroup).getData();

                    //item api response
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();
        return Header.OK(userOrderInfoApiResponse);
    }
}
