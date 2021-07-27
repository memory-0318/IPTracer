package tw.com.demo.model.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/26
 */
@Value
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder(setterPrefix = "set", toBuilder = true)
public class UrlShorteningCaseEditVO {
    private String caseName;
    private String remark;
    private List<@NotBlank String> recipients;
}
