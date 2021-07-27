package tw.com.demo.model.dto;

import lombok.*;

import java.time.OffsetDateTime;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
@Value
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder(setterPrefix = "set", toBuilder = true)
public class TriggeredClientInfoDTO {
    private OffsetDateTime triggeredDateTime;
    private String hostname;
    private String ipAddress;
    private String userAgent;
}
