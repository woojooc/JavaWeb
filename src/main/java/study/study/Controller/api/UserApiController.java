package study.study.Controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import study.study.Controller.CrudController;
import study.study.model.entity.User;
import study.study.model.network.Header;
import study.study.model.network.request.UserApiRequest;
import study.study.model.network.response.UserApiResponse;
import study.study.service.UserApiLogicService;

import java.util.List;

@Slf4j  // log 파일
@RestController
@RequestMapping("/api/user")    //이 주소를 가지는 컨트롤러
public class UserApiController extends CrudController<UserApiRequest,UserApiResponse, User> {
        //implements CrudInterface<UserApiRequest,UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("{}", pageable);
        return userApiLogicService.search(pageable);
    }

    @Override
    @PostMapping("")    // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {

        log.info("{}", request);     //info 레벨로 로그를 남기겠다.   request.toString()

        return userApiLogicService.checkEmailCreate(request);
    }

    /*
    @Autowired
    private UserApiLogicService userApiLogicService;

    @PostConstruct
    public void init() {
        this.baseService = userApiLogicService;
    }
*/

    /*
    @Override
    @PostMapping("")    // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {

        log.info("{}", request);     //info 레벨로 로그를 남기겠다.   request.toString()

        Header<UserApiResponse> response = userApiLogicService.create(request);

        if(response.getResultCode() == "400")
        {
            error400();
        }

        return response;
    }


    @RequestMapping("/error400")
    public ResponseEntity<UserApiResponse> error400() {

        return new ResponseEntity<UserApiResponse>(HttpStatus.BAD_REQUEST);
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
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {

        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")      //  /api/user/{id}
    public Header delete(@PathVariable(name = "id") Long id) {
        log.info("delete id : {}", id);
        return userApiLogicService.delete(id);
    }

     */
}
