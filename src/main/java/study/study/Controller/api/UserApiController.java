package study.study.Controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.study.ifs.CrudInterface;
import study.study.model.network.Header;
import study.study.model.network.request.UserApiRequest;
import study.study.model.network.response.UserApiResponse;
import study.study.service.UserApiLogicService;

@Slf4j  // log 파일
@RestController
@RequestMapping("/api/user")    //이 주소를 가지는 컨트롤러
public class UserApiController implements CrudInterface<UserApiRequest,UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")    // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {

        log.info("{}", request);     //info 레벨로 로그를 남기겠다.   request.toString()
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")     //  /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name = "id")Long id) {

        log.info("reade id : {}", id);
        return userApiLogicService.read(id);
    }

    //@GetMapping({"ids"})
    //public Header read(@PathVariable(name="ids") Long id)

    @Override
    @PutMapping("")     //  /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> userApiRequest) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")      //  /api/user/{id}
    public Header delete(@PathVariable(name = "id") Long id) {
        return null;
    }
}
