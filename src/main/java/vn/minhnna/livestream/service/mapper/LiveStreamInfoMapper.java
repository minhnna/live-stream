package vn.minhnna.livestream.service.mapper;

import vn.minhnna.livestream.domain.*;
import vn.minhnna.livestream.service.dto.LiveStreamInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LiveStreamInfo and its DTO LiveStreamInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LiveStreamInfoMapper extends EntityMapper<LiveStreamInfoDTO, LiveStreamInfo> {


}
