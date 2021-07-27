package tw.com.demo.model.dto;

import lombok.*;
import tw.com.demo.model.entity.TriggeredClientInfo;

import java.util.List;

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
public class UrlShorteningCaseDTO {
    private String caseId;
    private String caseName;
    private String originalUrl;
    private String shortenedUrlKey;
    private String shortenedUrl;
    private String remark;
    private Boolean webRtcEnabled;
    private List<String> recipients;
    private List<TriggeredClientInfo> clientInfos;

    public void addUrlShortenCaseDetail(TriggeredClientInfo clientInfo) {
        this.clientInfos.add(clientInfo);
    }
}
