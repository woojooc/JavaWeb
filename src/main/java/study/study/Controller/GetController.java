package study.study.Controller;

import org.springframework.web.bind.annotation.*;
import study.study.model.SearchParam;
import study.study.model.network.Header;

@RestController
@RequestMapping("/api") //localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") //Localhost:8080/api/getMethod
    public String getRequest() {

        return "He getMethod";
    }

    @GetMapping("/getParameter") //Localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        //public String getParameter(@RequestParam String id, @RequestParam String password) {
        //String password = "bbb";  //로컬 변수도 사용은 가능

        System.out.println("id : " + id);
        System.out.println("pwd : " + pwd);

        return id + pwd;
    }

    //Localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    //변수가 계속해서 늘어나는 상황
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // { "account" : "", "email" : "", "page" : 0 }  자동으로 json으로 변형 시켜준다.
        return searchParam;
    }

    // 헤더가 잘 내려가는지 확인
    @GetMapping("/header")
    public Header getHeader() {

        // {"resultCode: "OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }

}
