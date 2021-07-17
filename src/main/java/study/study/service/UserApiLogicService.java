package study.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.study.ifs.CrudInterface;
import study.study.model.network.Header;
import study.study.model.network.request.UserApiRequest;
import study.study.model.network.response.UserApiResponse;
import study.study.repository.UserRepository;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    //1. request DATA 가져오기
    //2. USer 생성
    //3. 생성된 데이터 기준으로 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(UserApiRequest request) {
        return null;
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<UserApiResponse> update(UserApiRequest request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
