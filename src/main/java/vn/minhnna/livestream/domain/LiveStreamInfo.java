package vn.minhnna.livestream.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LiveStreamInfo.
 */
@Document(collection = "live_stream_info")
public class LiveStreamInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("video_id")
    private String videoId;

    @Field("ai_result")
    private String aiResult;

    @Field("result")
    private String result;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public LiveStreamInfo videoId(String videoId) {
        this.videoId = videoId;
        return this;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getAiResult() {
        return aiResult;
    }

    public LiveStreamInfo aiResult(String aiResult) {
        this.aiResult = aiResult;
        return this;
    }

    public void setAiResult(String aiResult) {
        this.aiResult = aiResult;
    }

    public String getResult() {
        return result;
    }

    public LiveStreamInfo result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LiveStreamInfo liveStreamInfo = (LiveStreamInfo) o;
        if (liveStreamInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), liveStreamInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LiveStreamInfo{" +
            "id=" + getId() +
            ", videoId='" + getVideoId() + "'" +
            ", aiResult='" + getAiResult() + "'" +
            ", result='" + getResult() + "'" +
            "}";
    }
}
