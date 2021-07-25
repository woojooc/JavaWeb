package study.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.study.model.entity.OrderGroup;
import study.study.model.entity.Settlement;
import study.study.model.entity.User;
import study.study.model.network.Header;
import study.study.model.network.request.SettlementApiRequest;
import study.study.model.network.response.*;
import study.study.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SettlementApiLogicService extends BaseService<SettlementApiRequest, SettlementApiResponse, Settlement>{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserApiLogicService userApiLogicService;
    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;
    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    public Header<SettlementApiResponse> create(Header<SettlementApiRequest> request) {

      SettlementApiRequest body = request.getData();

      User user = userRepository.getOne(body.getUserId());

      List<OrderGroup> orderGroupList = user.getOrderGroupList();
      BigDecimal totalprice = BigDecimal.ZERO;
        for(int i = 0; i <orderGroupList.size(); i++)
        {
            totalprice.add(orderGroupList.get(i).getTotalPrice());
        }

      Settlement settlement = Settlement.builder()
              .user(user)
              .price(totalprice)
              .registeredAt(LocalDateTime.now())
              .build();

      Settlement newSettle = baseRepository.save(settlement);

        return Header.OK(response(newSettle));
    }

    @Override
    public Header<SettlementApiResponse> read(Long id) {

        Optional<Settlement> optional = baseRepository.findById(id);

        return optional
                .map(settlement -> response(settlement))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<SettlementApiResponse> update(Header<SettlementApiRequest> request) {
        SettlementApiRequest settlementApiRequest = request.getData();

        //2 id -> user 데이터 찾기
        Optional<Settlement> optional = baseRepository.findById(settlementApiRequest.getId());

        return optional.map(settlement -> {
            // 3  data  ->  update
            settlement.setUser(userRepository.getOne(settlementApiRequest.getUserId()))
                    .setPrice(settlementApiRequest.getPrice())
                    .setRegisteredAt(settlementApiRequest.getRegisteredAt())
                    .setUnregisteredAt(settlementApiRequest.getUnregisteredAt());
            return settlement;        // DB 반영 x
        })
                .map(settlement -> baseRepository.save(settlement)) // update 해당 id 에대해서 -> newUser 반환
                .map(updateSettlement -> response(updateSettlement))            // 4 userApiResponse 만들어준다.
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(settlement -> {
                    baseRepository.delete(settlement);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private SettlementApiResponse response(Settlement settlement) {

        SettlementApiResponse settlementApiResponse = SettlementApiResponse.builder()
                .id(settlement.getId())
                .price(settlement.getPrice())
                .userId(settlement.getUser().getId())
                .build();

        return settlementApiResponse;
    }

    public Header<SettlementPriceInfoApiResponse> priceInfo(Long id) {

        User user = userRepository.getOne(id);
        UserApiResponse userApiResponse = userApiLogicService.response(user);

        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = user.getOrderGroupList().stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.resposne(orderGroup).getData();

                    //item api response
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                }).collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        Settlement settlement = Settlement.builder()
                .user(user)
                .price(BigDecimal.ZERO)
                .build();

        for(int i = 0; i <orderGroupList.size(); i++)
        {
            settlement.setPrice(settlement.getPrice().add(orderGroupList.get(i).getTotalPrice()));
        }

        SettlementApiResponse settlementApiResponse = response(settlement);
        settlementApiResponse.setUserApiResponse(userApiResponse);

        SettlementPriceInfoApiResponse settlementPriceInfoApiResponse = SettlementPriceInfoApiResponse.builder()
                .settlementApiResponse(settlementApiResponse)
                .build();

        return Header.OK(settlementPriceInfoApiResponse);
    }
}
