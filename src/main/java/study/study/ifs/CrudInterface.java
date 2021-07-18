package study.study.ifs;

import study.study.model.network.Header;

public interface CrudInterface<Req,Res> {

    Header<Res> create(Header<Req> request);    //todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);

}
