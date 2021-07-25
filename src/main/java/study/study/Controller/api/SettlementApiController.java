package study.study.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.study.Controller.CrudController;
import study.study.model.entity.Settlement;
import study.study.model.network.Header;
import study.study.model.network.request.SettlementApiRequest;
import study.study.model.network.response.SettlementApiResponse;
import study.study.model.network.response.SettlementPriceInfoApiResponse;
import study.study.service.SettlementApiLogicService;

@RestController
@RequestMapping("api/settlement")
public class SettlementApiController extends CrudController<SettlementApiRequest, SettlementApiResponse, Settlement> {

    @Autowired
    SettlementApiLogicService settlementApiLogicService;

    @GetMapping("{id}/priceInfo")
    public Header<SettlementPriceInfoApiResponse> priceInfo(@PathVariable Long id) {

        return settlementApiLogicService.priceInfo(id);
    }

}
