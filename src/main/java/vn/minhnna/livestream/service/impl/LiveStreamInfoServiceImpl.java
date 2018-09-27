package vn.minhnna.livestream.service.impl;

import vn.minhnna.livestream.service.LiveStreamInfoService;
import vn.minhnna.livestream.domain.LiveStreamInfo;
import vn.minhnna.livestream.repository.LiveStreamInfoRepository;
import vn.minhnna.livestream.service.dto.LiveStreamInfoDTO;
import vn.minhnna.livestream.service.mapper.LiveStreamInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing LiveStreamInfo.
 */
@Service
public class LiveStreamInfoServiceImpl implements LiveStreamInfoService {

    private final Logger log = LoggerFactory.getLogger(LiveStreamInfoServiceImpl.class);

    private final LiveStreamInfoRepository liveStreamInfoRepository;

    private final LiveStreamInfoMapper liveStreamInfoMapper;

    public LiveStreamInfoServiceImpl(LiveStreamInfoRepository liveStreamInfoRepository, LiveStreamInfoMapper liveStreamInfoMapper) {
        this.liveStreamInfoRepository = liveStreamInfoRepository;
        this.liveStreamInfoMapper = liveStreamInfoMapper;
    }

    /**
     * Save a liveStreamInfo.
     *
     * @param liveStreamInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LiveStreamInfoDTO save(LiveStreamInfoDTO liveStreamInfoDTO) {
        log.debug("Request to save LiveStreamInfo : {}", liveStreamInfoDTO);
        LiveStreamInfo liveStreamInfo = liveStreamInfoMapper.toEntity(liveStreamInfoDTO);
        liveStreamInfo = liveStreamInfoRepository.save(liveStreamInfo);
        return liveStreamInfoMapper.toDto(liveStreamInfo);
    }

    /**
     * Get all the liveStreamInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<LiveStreamInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LiveStreamInfos");
        return liveStreamInfoRepository.findAll(pageable)
            .map(liveStreamInfoMapper::toDto);
    }


    /**
     * Get one liveStreamInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<LiveStreamInfoDTO> findOne(String id) {
        log.debug("Request to get LiveStreamInfo : {}", id);
        return liveStreamInfoRepository.findById(id)
            .map(liveStreamInfoMapper::toDto);
    }

    /**
     * Delete the liveStreamInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete LiveStreamInfo : {}", id);
        liveStreamInfoRepository.deleteById(id);
    }
}
