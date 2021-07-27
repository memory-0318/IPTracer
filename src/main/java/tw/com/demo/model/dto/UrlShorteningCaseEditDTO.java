package tw.com.demo.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/26
 */
@Value
@ToString
@EqualsAndHashCode
@Builder(setterPrefix = "set", toBuilder = true)
public class UrlShorteningCaseEditDTO {
    @NotBlank(message = "案名不可為空")
    private String caseName;
    private String remark;
    @NotEmpty(message = "必須指定收件人EMail")
    private List<@NotBlank String> recipients;
}
