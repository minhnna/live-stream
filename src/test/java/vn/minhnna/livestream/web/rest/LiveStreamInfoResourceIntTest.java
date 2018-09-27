package vn.minhnna.livestream.web.rest;

import vn.minhnna.livestream.LivestreamApp;

import vn.minhnna.livestream.domain.LiveStreamInfo;
import vn.minhnna.livestream.repository.LiveStreamInfoRepository;
import vn.minhnna.livestream.service.LiveStreamInfoService;
import vn.minhnna.livestream.service.dto.LiveStreamInfoDTO;
import vn.minhnna.livestream.service.mapper.LiveStreamInfoMapper;
import vn.minhnna.livestream.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static vn.minhnna.livestream.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LiveStreamInfoResource REST controller.
 *
 * @see LiveStreamInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LivestreamApp.class)
public class LiveStreamInfoResourceIntTest {

    private static final String DEFAULT_VIDEO_ID = "AAAAAAAAAA";
    private static final String UPDATED_VIDEO_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AI_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_AI_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_RESULT = "BBBBBBBBBB";

    @Autowired
    private LiveStreamInfoRepository liveStreamInfoRepository;

    @Autowired
    private LiveStreamInfoMapper liveStreamInfoMapper;
    
    @Autowired
    private LiveStreamInfoService liveStreamInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restLiveStreamInfoMockMvc;

    private LiveStreamInfo liveStreamInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LiveStreamInfoResource liveStreamInfoResource = new LiveStreamInfoResource(liveStreamInfoService);
        this.restLiveStreamInfoMockMvc = MockMvcBuilders.standaloneSetup(liveStreamInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LiveStreamInfo createEntity() {
        LiveStreamInfo liveStreamInfo = new LiveStreamInfo()
            .videoId(DEFAULT_VIDEO_ID)
            .aiResult(DEFAULT_AI_RESULT)
            .result(DEFAULT_RESULT);
        return liveStreamInfo;
    }

    @Before
    public void initTest() {
        liveStreamInfoRepository.deleteAll();
        liveStreamInfo = createEntity();
    }

    @Test
    public void createLiveStreamInfo() throws Exception {
        int databaseSizeBeforeCreate = liveStreamInfoRepository.findAll().size();

        // Create the LiveStreamInfo
        LiveStreamInfoDTO liveStreamInfoDTO = liveStreamInfoMapper.toDto(liveStreamInfo);
        restLiveStreamInfoMockMvc.perform(post("/api/live-stream-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(liveStreamInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the LiveStreamInfo in the database
        List<LiveStreamInfo> liveStreamInfoList = liveStreamInfoRepository.findAll();
        assertThat(liveStreamInfoList).hasSize(databaseSizeBeforeCreate + 1);
        LiveStreamInfo testLiveStreamInfo = liveStreamInfoList.get(liveStreamInfoList.size() - 1);
        assertThat(testLiveStreamInfo.getVideoId()).isEqualTo(DEFAULT_VIDEO_ID);
        assertThat(testLiveStreamInfo.getAiResult()).isEqualTo(DEFAULT_AI_RESULT);
        assertThat(testLiveStreamInfo.getResult()).isEqualTo(DEFAULT_RESULT);
    }

    @Test
    public void createLiveStreamInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = liveStreamInfoRepository.findAll().size();

        // Create the LiveStreamInfo with an existing ID
        liveStreamInfo.setId("existing_id");
        LiveStreamInfoDTO liveStreamInfoDTO = liveStreamInfoMapper.toDto(liveStreamInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLiveStreamInfoMockMvc.perform(post("/api/live-stream-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(liveStreamInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LiveStreamInfo in the database
        List<LiveStreamInfo> liveStreamInfoList = liveStreamInfoRepository.findAll();
        assertThat(liveStreamInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllLiveStreamInfos() throws Exception {
        // Initialize the database
        liveStreamInfoRepository.save(liveStreamInfo);

        // Get all the liveStreamInfoList
        restLiveStreamInfoMockMvc.perform(get("/api/live-stream-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(liveStreamInfo.getId())))
            .andExpect(jsonPath("$.[*].videoId").value(hasItem(DEFAULT_VIDEO_ID.toString())))
            .andExpect(jsonPath("$.[*].aiResult").value(hasItem(DEFAULT_AI_RESULT.toString())))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT.toString())));
    }
    
    @Test
    public void getLiveStreamInfo() throws Exception {
        // Initialize the database
        liveStreamInfoRepository.save(liveStreamInfo);

        // Get the liveStreamInfo
        restLiveStreamInfoMockMvc.perform(get("/api/live-stream-infos/{id}", liveStreamInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(liveStreamInfo.getId()))
            .andExpect(jsonPath("$.videoId").value(DEFAULT_VIDEO_ID.toString()))
            .andExpect(jsonPath("$.aiResult").value(DEFAULT_AI_RESULT.toString()))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT.toString()));
    }

    @Test
    public void getNonExistingLiveStreamInfo() throws Exception {
        // Get the liveStreamInfo
        restLiveStreamInfoMockMvc.perform(get("/api/live-stream-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLiveStreamInfo() throws Exception {
        // Initialize the database
        liveStreamInfoRepository.save(liveStreamInfo);

        int databaseSizeBeforeUpdate = liveStreamInfoRepository.findAll().size();

        // Update the liveStreamInfo
        LiveStreamInfo updatedLiveStreamInfo = liveStreamInfoRepository.findById(liveStreamInfo.getId()).get();
        updatedLiveStreamInfo
            .videoId(UPDATED_VIDEO_ID)
            .aiResult(UPDATED_AI_RESULT)
            .result(UPDATED_RESULT);
        LiveStreamInfoDTO liveStreamInfoDTO = liveStreamInfoMapper.toDto(updatedLiveStreamInfo);

        restLiveStreamInfoMockMvc.perform(put("/api/live-stream-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(liveStreamInfoDTO)))
            .andExpect(status().isOk());

        // Validate the LiveStreamInfo in the database
        List<LiveStreamInfo> liveStreamInfoList = liveStreamInfoRepository.findAll();
        assertThat(liveStreamInfoList).hasSize(databaseSizeBeforeUpdate);
        LiveStreamInfo testLiveStreamInfo = liveStreamInfoList.get(liveStreamInfoList.size() - 1);
        assertThat(testLiveStreamInfo.getVideoId()).isEqualTo(UPDATED_VIDEO_ID);
        assertThat(testLiveStreamInfo.getAiResult()).isEqualTo(UPDATED_AI_RESULT);
        assertThat(testLiveStreamInfo.getResult()).isEqualTo(UPDATED_RESULT);
    }

    @Test
    public void updateNonExistingLiveStreamInfo() throws Exception {
        int databaseSizeBeforeUpdate = liveStreamInfoRepository.findAll().size();

        // Create the LiveStreamInfo
        LiveStreamInfoDTO liveStreamInfoDTO = liveStreamInfoMapper.toDto(liveStreamInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLiveStreamInfoMockMvc.perform(put("/api/live-stream-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(liveStreamInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LiveStreamInfo in the database
        List<LiveStreamInfo> liveStreamInfoList = liveStreamInfoRepository.findAll();
        assertThat(liveStreamInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteLiveStreamInfo() throws Exception {
        // Initialize the database
        liveStreamInfoRepository.save(liveStreamInfo);

        int databaseSizeBeforeDelete = liveStreamInfoRepository.findAll().size();

        // Get the liveStreamInfo
        restLiveStreamInfoMockMvc.perform(delete("/api/live-stream-infos/{id}", liveStreamInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LiveStreamInfo> liveStreamInfoList = liveStreamInfoRepository.findAll();
        assertThat(liveStreamInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LiveStreamInfo.class);
        LiveStreamInfo liveStreamInfo1 = new LiveStreamInfo();
        liveStreamInfo1.setId("id1");
        LiveStreamInfo liveStreamInfo2 = new LiveStreamInfo();
        liveStreamInfo2.setId(liveStreamInfo1.getId());
        assertThat(liveStreamInfo1).isEqualTo(liveStreamInfo2);
        liveStreamInfo2.setId("id2");
        assertThat(liveStreamInfo1).isNotEqualTo(liveStreamInfo2);
        liveStreamInfo1.setId(null);
        assertThat(liveStreamInfo1).isNotEqualTo(liveStreamInfo2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LiveStreamInfoDTO.class);
        LiveStreamInfoDTO liveStreamInfoDTO1 = new LiveStreamInfoDTO();
        liveStreamInfoDTO1.setId("id1");
        LiveStreamInfoDTO liveStreamInfoDTO2 = new LiveStreamInfoDTO();
        assertThat(liveStreamInfoDTO1).isNotEqualTo(liveStreamInfoDTO2);
        liveStreamInfoDTO2.setId(liveStreamInfoDTO1.getId());
        assertThat(liveStreamInfoDTO1).isEqualTo(liveStreamInfoDTO2);
        liveStreamInfoDTO2.setId("id2");
        assertThat(liveStreamInfoDTO1).isNotEqualTo(liveStreamInfoDTO2);
        liveStreamInfoDTO1.setId(null);
        assertThat(liveStreamInfoDTO1).isNotEqualTo(liveStreamInfoDTO2);
    }
}
