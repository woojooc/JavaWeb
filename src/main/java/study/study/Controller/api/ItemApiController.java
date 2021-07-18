package study.study.Controller.api;

import org.springframework.web.bind.annotation.*;
import study.study.ifs.CrudInterface;
import study.study.model.network.Header;
import study.study.model.network.request.ItemApiRequest;
import study.study.model.network.response.ItemApiResponse;

@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {


    @Override
    @PostMapping("")        // /api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    @GetMapping("{id}")     // /api/item/id
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping("")         // /api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")      //  /api/item/id
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
