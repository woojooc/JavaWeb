package study.study.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.study.StudyApplicationTests;
import study.study.model.entity.User;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

//객체를 통한 데이터 관리
public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)   직접 객체를 안만들고 스프링에서 관리. 의존성을 주입
    // 싱글톤!!
    @Autowired
    private UserRepository userRepository;// = new UserRepository();



    @Test   //테스트 코드
    public void create() {

        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);

        /*
        // String aql = insert into user (%s. %s. %d) value ( account, email, age);
        // JPA 는 오브젝트를 가지고 데이터 관리

        User user = new User();
        //user.setId();         ai 자동으로 넣어줌
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser2");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " +newUser);
        */
    }

    @Test
    @Transactional
    public void read() {//@RequestParam Long id) {

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        Assert.assertNotNull(user);

        /*
        // select * from user where id = ?
        //Optional<User> user = userRepository.findById(1L);
        Optional<User> user = userRepository.findByAccount("TestUser03");

        user.ifPresent(selectUser -> {
            //
            System.out.println("user : "+selectUser);
            System.out.println("email : "+selectUser.getEmail());
            //

            selectUser.getOrderDetailList().stream().forEach(detail-> {
                //System.out.println(detail.getItemId());

                Item item = detail.getItem();
                System.out.println(item);
            });
        });

        //return user.get();
        */
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            //selectUser.setId(3L);  //3번 아이디의 유저의 데이터가 바뀌게 된다.
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
                //해당아이디가 있는지 확인하고 존재하면 바꾸게 된다.
        });
    }

    @Test
    @Transactional      //실제적으로 데이터베이스에서 삭제가 되지 않게 함. 마지막에 다시 롤백
    //@RequestParam("/api/user")  //URL에 삭제할 대상에대한 정보가 들어온다
    public void delete() {  //@RequestParam Long id) {
        Optional<User> user = userRepository.findById(3L);

        //반드시 2L의 값이 존재해야한다.
        Assert.assertTrue(user.isPresent());    //true

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        //지워 졌는지 다시 확인
        Optional<User> deleteUser = userRepository.findById(3L);

        Assert.assertFalse(deleteUser.isPresent());     //false

        /*
        if(deleteUser.isPresent()) {
            System.out.println("데이터 존재 + "+deleteUser.get());
        }else{
            System.out.println("데이터 없음");
        }
        */
    }

}
