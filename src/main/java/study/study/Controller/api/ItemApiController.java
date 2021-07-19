package study.study.Controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.study.Controller.CrudController;
import study.study.model.entity.Item;
import study.study.model.network.request.ItemApiRequest;
import study.study.model.network.response.ItemApiResponse;

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {
// implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    /*
    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @PostConstruct
    public void init() {                        //static 메서드가 실행되는것과 유사하게 작동.
        this.baseService = itemApiLogicService;
    }
     */

    /*
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
    */
}
