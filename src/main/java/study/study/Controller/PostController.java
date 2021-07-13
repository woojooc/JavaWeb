package study.study.Controller;

import study.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //class 주소가 겹치는것은 문제가 되지 않는다.
public class PostController {

    // HTML <Form>
    // ajax 검색
    // 검색 파라미터가 많다
    // http post body -> data
    //  json, xml, multipart-form / text-plain

    //@RequestMapping(method = RequestMethod.POST, path = "/postMethod")
    @PostMapping(value = "/postMethod")//, produces = {"application-json"}) 어떤 방식으로 받을지 지정할 수 있다.
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {

        return searchParam;
    }

    @PutMapping("/putMethod")
    public void put() {

    }

    @PatchMapping("/patchMethod")
    public void patch() {

    }

}
