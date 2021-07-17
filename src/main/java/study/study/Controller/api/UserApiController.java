package study.study.Controller.api;

import org.springframework.web.bind.annotation.*;
import study.study.ifs.CrudInterface;
import study.study.model.network.Header;
import study.study.model.network.request.UserApiRequest;
import study.study.model.network.response.UserApiResponse;

@RestController
@RequestMapping("/api/user")    //이 주소를 가지는 컨트롤러
public class UserApiController implements CrudInterface<UserApiRequest,UserApiResponse> {



    @Override
    @PostMapping("")    // /api/user
    public Header<UserApiResponse> create(@RequestBody UserApiRequest userApiRequest) {
        return null;
    }

    @Override
    @GetMapping("{id}")     //  /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name = "id")Long id) {
        return null;
    }

    //@GetMapping({"ids"})
    //public Header read(@PathVariable(name="ids") Long id)

    @Override
    @PutMapping("")     //  /api/user
    public Header<UserApiResponse> update(@RequestBody UserApiRequest userApiRequest) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")      //  /api/user/{id}
    public Header delete(@PathVariable(name = "id") Long id) {
        return null;
    }
}
