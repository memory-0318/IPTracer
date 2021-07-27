package tw.com.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tw.com.demo.model.dto.TriggeredClientInfoDTO;
import tw.com.demo.model.entity.TriggeredClientInfo;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/27
 */
@Mapper
public interface TriggeredClientInfoMapper {
    TriggeredClientInfoMapper INSTANCE = Mappers.getMapper(TriggeredClientInfoMapper.class);

    TriggeredClientInfoDTO toDTO(TriggeredClientInfo triggeredClientInfo);
}
