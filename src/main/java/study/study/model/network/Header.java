package study.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // api 통신 시간
    //@JsonProperty("transaction_time")   //json 형태시 지정
    private LocalDateTime transactionTime; //프론트랑 통신시 스트링 사용
    // ISO, YYYY-MM-DD HH:mm:ss

    // api 응답 토드
    private String resultCode;

    // api 부가 설명
    private String description;

    // 계속해서 바뀌는 데이터, 제네릭 사용.
    private T data;

    private Pagination pagination;

    public static <T> Header<T> BadRequest(String errorCode) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(errorCode)
                .description("No")
                .build();
    }


    // OK
    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // DATA OK
    public static <T> Header<T> OK(T data) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // DATA OK
    public static <T> Header<T> OK(T data,Pagination pagination) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }

    //ERROR
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }

}
