package tw.com.demo.model.entity;

import lombok.Builder;
import lombok.Data;
import tw.com.demo.model.vo.IpInfo;

import java.time.OffsetDateTime;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
@Data
@Builder(setterPrefix = "set", toBuilder = true)
public class TriggeredClientInfo {
    private OffsetDateTime triggeredDateTime;
    private String hostname;
    private String ipAddress;
    private String userAgent;
}
