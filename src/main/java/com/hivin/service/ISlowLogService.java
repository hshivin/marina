package com.hivin.service;

import com.hivin.vo.QueryParam;
import com.hivin.vo.SlowLogVo;

import java.util.List;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/9
 */
public interface ISlowLogService {
    public List<SlowLogVo> getSlow(QueryParam queryParam);
}
