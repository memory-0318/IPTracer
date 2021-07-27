package tw.com.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tw.com.demo.model.dto.UrlShorteningCaseDTO;
import tw.com.demo.model.entity.UrlShorteningCase;
import tw.com.demo.model.vo.UrlShortenCaseApplyVO;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
@Mapper
public interface UrlShortenCaseMapper {
    UrlShortenCaseMapper INSTANCE = Mappers.getMapper(UrlShortenCaseMapper.class);

    UrlShorteningCase toBO(UrlShortenCaseApplyVO vo);

    UrlShorteningCaseDTO toDTO(UrlShorteningCase urlShorteningCase);
}
