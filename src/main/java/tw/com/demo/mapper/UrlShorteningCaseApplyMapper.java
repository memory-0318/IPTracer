package tw.com.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tw.com.demo.model.dto.UrlShorteningCaseApplyDTO;
import tw.com.demo.model.vo.UrlShortenCaseApplyVO;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
@Mapper
public interface UrlShorteningCaseApplyMapper {
    UrlShorteningCaseApplyMapper INSTANCE = Mappers.getMapper(UrlShorteningCaseApplyMapper.class);

    UrlShortenCaseApplyVO toVO(UrlShorteningCaseApplyDTO dto);
}
