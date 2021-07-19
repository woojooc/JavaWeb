package study.study.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.study.ifs.CrudInterface;
import study.study.model.network.Header;

public abstract class CrudController<Req,Res> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    protected CrudInterface<Req, Res> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {

        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {

        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {

        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {

        return baseService.delete(id);
    }
}
