package tw.com.demo.model.dto;

import lombok.Builder;
import lombok.Data;
import tw.com.demo.model.vo.IpInfo;

import java.time.OffsetDateTime;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/26
 */
@Data
@Builder(setterPrefix = "set", toBuilder = true)
public class ClientIpInfoDTO {
    private String hostname;
    private String ipAddress;
}
