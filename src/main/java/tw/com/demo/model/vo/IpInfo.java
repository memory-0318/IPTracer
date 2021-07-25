package tw.com.demo.model.vo;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
@Value
@Builder(setterPrefix = "set", toBuilder = true)
public class IpInfo {
    @NotNull(message = "IP must not be null")
    private String ip;

    @NotNull(message = "Port must not be null")
    @Size(min = 0, max = 65535, message = "The port must be between 0 and 65535")
    private Integer port;
}
