package study.study.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.study.ifs.CrudInterface;
import study.study.model.network.Header;
import study.study.model.network.request.ItemApiRequest;
import study.study.model.network.response.ItemApiResponse;
import study.study.service.ItemApiLogicService;

@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("")        // /api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {

        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")     // /api/item/id
    public Header<ItemApiResponse> read(@PathVariable Long id) {

        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("")         // /api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {

        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")      //  /api/item/id
    public Header delete(@PathVariable Long id) {

        return itemApiLogicService.delete(id);
    }
}
