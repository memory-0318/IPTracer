package tw.com.demo.model.dto;

import lombok.*;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/27
 */
@Value
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder(setterPrefix = "set", toBuilder = true)
public class PageRequestDTO {
    private Integer offset;
    private Integer limit;
    private String sortField;
}
