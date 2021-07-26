package tw.com.demo.model.vo;

import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/26
 */
@Value
@Builder(setterPrefix = "set", toBuilder = true)
public class ClientInfoVO {
    private OffsetDateTime triggeredDateTime;
    private String hostname;
    private String ipAddress;
    private String userAgent;
}
