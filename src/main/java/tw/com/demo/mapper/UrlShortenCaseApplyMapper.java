package tw.com.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tw.com.demo.dto.UrlShortenCaseApplyDTO;
import tw.com.demo.model.vo.UrlShortenCaseApplyVO;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
@Mapper
public interface UrlShortenCaseApplyMapper {
    UrlShortenCaseApplyMapper INSTANCE = Mappers.getMapper(UrlShortenCaseApplyMapper.class);

    UrlShortenCaseApplyVO toVO(UrlShortenCaseApplyDTO dto);
}
