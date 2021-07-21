package study.study.Controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.study.Controller.CrudController;
import study.study.model.entity.Partner;
import study.study.model.network.request.PartnerApiRequest;
import study.study.model.network.response.PartnerApiResponse;

@RestController
@RequestMapping("api/partner")
public class PartnerController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {
}
