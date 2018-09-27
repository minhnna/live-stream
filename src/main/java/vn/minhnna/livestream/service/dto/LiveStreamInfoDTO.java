package vn.minhnna.livestream.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LiveStreamInfo entity.
 */
public class LiveStreamInfoDTO implements Serializable {

    private String id;

    private String videoId;

    private String aiResult;

    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getAiResult() {
        return aiResult;
    }

    public void setAiResult(String aiResult) {
        this.aiResult = aiResult;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LiveStreamInfoDTO liveStreamInfoDTO = (LiveStreamInfoDTO) o;
        if (liveStreamInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), liveStreamInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LiveStreamInfoDTO{" +
            "id=" + getId() +
            ", videoId='" + getVideoId() + "'" +
            ", aiResult='" + getAiResult() + "'" +
            ", result='" + getResult() + "'" +
            "}";
    }
}
