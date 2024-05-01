package cn.ljserver.tool.weboffice.v3.model;

import cn.ljserver.tool.weboffice.v3.exception.ConvertErrorCodes;
import cn.ljserver.tool.weboffice.v3.exception.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 程序响应实体
 *
 * @param <T>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProviderResponseEntity<T> {
    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public static <T> ProviderResponseEntity<T> ok(T data) {
        return new ProviderResponseEntity<>(0, "ok", data);
    }

    public static ProviderResponseEntity<?> ok() {
        return new ProviderResponseEntity<>(0, "ok", "");
    }

    public static <T> ProviderResponseEntity<T> err() {
        return new ProviderResponseEntity<>(ErrorCodes.InternalError.getCode(),
                ErrorCodes.InternalError.name(), null);
    }

    public static <T> ProviderResponseEntity<T> err(ConvertErrorCodes e) {
        return new ProviderResponseEntity<>(e.getCode(), e.name(), null);
    }

    public static <T> ProviderResponseEntity<T> err(ErrorCodes e) {
        return new ProviderResponseEntity<>(e.getCode(), e.name(), null);
    }
}
