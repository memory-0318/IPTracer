package tw.com.demo.model.vo;

import lombok.*;

import java.util.List;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
@Value
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class UrlShortenCaseApplyVO {
    private String caseName;
    private String originalUrl;
    private String shortenedUrlKey;
    private String remark;
    private Boolean webRtcEnabled;
    private List<String> recipients;
}
