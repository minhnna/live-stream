package vn.minhnna.livestream.repository;

import vn.minhnna.livestream.domain.LiveStreamInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the LiveStreamInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LiveStreamInfoRepository extends MongoRepository<LiveStreamInfo, String> {

}
