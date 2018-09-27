package vn.minhnna.livestream.service;

import vn.minhnna.livestream.service.dto.LiveStreamInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LiveStreamInfo.
 */
public interface LiveStreamInfoService {

    /**
     * Save a liveStreamInfo.
     *
     * @param liveStreamInfoDTO the entity to save
     * @return the persisted entity
     */
    LiveStreamInfoDTO save(LiveStreamInfoDTO liveStreamInfoDTO);

    /**
     * Get all the liveStreamInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LiveStreamInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" liveStreamInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LiveStreamInfoDTO> findOne(String id);

    /**
     * Delete the "id" liveStreamInfo.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
