package vn.minhnna.livestream.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.minhnna.livestream.service.LiveStreamInfoService;
import vn.minhnna.livestream.web.rest.errors.BadRequestAlertException;
import vn.minhnna.livestream.web.rest.util.HeaderUtil;
import vn.minhnna.livestream.web.rest.util.PaginationUtil;
import vn.minhnna.livestream.service.dto.LiveStreamInfoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LiveStreamInfo.
 */
@RestController
@RequestMapping("/api")
public class LiveStreamInfoResource {

    private final Logger log = LoggerFactory.getLogger(LiveStreamInfoResource.class);

    private static final String ENTITY_NAME = "liveStreamInfo";

    private final LiveStreamInfoService liveStreamInfoService;

    public LiveStreamInfoResource(LiveStreamInfoService liveStreamInfoService) {
        this.liveStreamInfoService = liveStreamInfoService;
    }

    /**
     * POST  /live-stream-infos : Create a new liveStreamInfo.
     *
     * @param liveStreamInfoDTO the liveStreamInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new liveStreamInfoDTO, or with status 400 (Bad Request) if the liveStreamInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/live-stream-infos")
    @Timed
    public ResponseEntity<LiveStreamInfoDTO> createLiveStreamInfo(@RequestBody LiveStreamInfoDTO liveStreamInfoDTO) throws URISyntaxException {
        log.debug("REST request to save LiveStreamInfo : {}", liveStreamInfoDTO);
        if (liveStreamInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new liveStreamInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LiveStreamInfoDTO result = liveStreamInfoService.save(liveStreamInfoDTO);
        return ResponseEntity.created(new URI("/api/live-stream-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /live-stream-infos : Updates an existing liveStreamInfo.
     *
     * @param liveStreamInfoDTO the liveStreamInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated liveStreamInfoDTO,
     * or with status 400 (Bad Request) if the liveStreamInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the liveStreamInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/live-stream-infos")
    @Timed
    public ResponseEntity<LiveStreamInfoDTO> updateLiveStreamInfo(@RequestBody LiveStreamInfoDTO liveStreamInfoDTO) throws URISyntaxException {
        log.debug("REST request to update LiveStreamInfo : {}", liveStreamInfoDTO);
        if (liveStreamInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LiveStreamInfoDTO result = liveStreamInfoService.save(liveStreamInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, liveStreamInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /live-stream-infos : get all the liveStreamInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of liveStreamInfos in body
     */
    @GetMapping("/live-stream-infos")
    @Timed
    public ResponseEntity<List<LiveStreamInfoDTO>> getAllLiveStreamInfos(Pageable pageable) {
        log.debug("REST request to get a page of LiveStreamInfos");
        Page<LiveStreamInfoDTO> page = liveStreamInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/live-stream-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /live-stream-infos/:id : get the "id" liveStreamInfo.
     *
     * @param id the id of the liveStreamInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the liveStreamInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/live-stream-infos/{id}")
    @Timed
    public ResponseEntity<LiveStreamInfoDTO> getLiveStreamInfo(@PathVariable String id) {
        log.debug("REST request to get LiveStreamInfo : {}", id);
        Optional<LiveStreamInfoDTO> liveStreamInfoDTO = liveStreamInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(liveStreamInfoDTO);
    }

    /**
     * DELETE  /live-stream-infos/:id : delete the "id" liveStreamInfo.
     *
     * @param id the id of the liveStreamInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/live-stream-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteLiveStreamInfo(@PathVariable String id) {
        log.debug("REST request to delete LiveStreamInfo : {}", id);
        liveStreamInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
