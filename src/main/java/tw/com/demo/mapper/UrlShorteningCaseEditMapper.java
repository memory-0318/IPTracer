package tw.com.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tw.com.demo.model.dto.UrlShorteningCaseEditDTO;
import tw.com.demo.model.vo.UrlShorteningCaseEditVO;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/26
 */
@Mapper
public interface UrlShorteningCaseEditMapper {
    UrlShorteningCaseEditMapper INSTANCE = Mappers.getMapper(UrlShorteningCaseEditMapper.class);

    UrlShorteningCaseEditVO toVO(UrlShorteningCaseEditDTO dto);
}
