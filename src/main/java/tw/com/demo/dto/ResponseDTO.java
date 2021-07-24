package tw.com.demo.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/13
 */
@Data
@Setter(value = AccessLevel.NONE)
@Builder(setterPrefix = "set")
public class ResponseDTO <T> {
    private Boolean success;
    private OffsetDateTime dateTime;
    private T data;
    private String errorMsg;

    public static ResponseDTO<Object> ok() {
        return ResponseDTO.ok(null);
    }

    public static <T> ResponseDTO<T> ok(T data) {
        return ResponseDTO.<T>builder()
            .setSuccess(true)
            .setDateTime(OffsetDateTime.now())
            .setData(data)
            .setErrorMsg(null)
            .build();
    }

    public static ResponseDTO<Object> fail() {
        return ResponseDTO.fail("Unexpected error");
    }

    public static ResponseDTO<Object> fail(String errorMsg) {
        return ResponseDTO.builder()
            .setSuccess(false)
            .setDateTime(OffsetDateTime.now())
            .setData(null)
            .setErrorMsg(errorMsg)
            .build();
    }
}
