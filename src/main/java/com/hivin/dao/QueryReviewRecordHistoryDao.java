package com.hivin.dao;

import com.hivin.model.QueryReviewRecordHistory;
import org.apache.ibatis.annotations.Param;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/10
 */
public interface QueryReviewRecordHistoryDao {
    public int insertOne(QueryReviewRecordHistory queryReviewRecord);
    public int checkByChecksum(@Param("checksum") String  checksum);
}
