package study.study.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import study.study.ifs.CrudInterface;


@Component
public abstract class BaseService<Req,Res,Entity> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    protected JpaRepository<Entity,Long> baseRepository;

    //Entity findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

    //CRUD 상속받은 클래스에서 작성성
}
