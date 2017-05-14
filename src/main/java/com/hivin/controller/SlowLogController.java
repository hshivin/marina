package com.hivin.controller;

import com.hivin.Params.ReponseCode;
import com.hivin.service.ISlowLogService;
import com.hivin.vo.QueryParam;
import com.hivin.vo.ReponseVo;
import com.hivin.vo.SlowLogVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/15
 */
@RestController
@RequestMapping(value = "/slowlog")
public class SlowLogController {
    @Resource(name = "slowLogService")
    ISlowLogService iSlowLogService;

    @RequestMapping(value = "/getSlowReport", method = RequestMethod.POST)
    public ReponseVo getSlowReport(@RequestBody QueryParam queryParam) {
        ReponseVo reponseVo = new ReponseVo();
        List<SlowLogVo> slowLogVos = iSlowLogService.getSlow(queryParam);
        reponseVo.setCode(ReponseCode.SUCCESS.getCode());
        reponseVo.setMsg(ReponseCode.SUCCESS.getMsg());
        reponseVo.setResult(slowLogVos);
        return reponseVo;
    }


}
