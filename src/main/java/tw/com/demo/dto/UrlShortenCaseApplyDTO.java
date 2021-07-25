package tw.com.demo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/13
 */
@Data
@Setter(value = AccessLevel.NONE)
@Builder(setterPrefix = "set", toBuilder = true)
public class UrlShortenCaseApplyDTO {
    @NotBlank(message = "案名不可為空")
    private String caseName;

    @Pattern(regexp = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
        "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:]])?$",
        message = "必須符合http/https格式")
    @NotBlank(message = "必須指定原始URL")
    private String urlToShorten;

    @NotEmpty(message = "縮網址後綴如果設定就不可以是空白")
    private String shortenedUrlKey;

    private String remark;

    @NotNull(message = "必須指定是否啟用WebRTC連線")
    private Boolean webRtcEnabled;

    @NotEmpty(message = "必須指定收件人EMail")
    private List<@NotBlank String> recipients;
}
