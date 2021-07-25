package study.study.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettlementApiRequest {

    private Long id;
    private Long userId;
    private BigDecimal price;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

}
