package com.hivin.service.impl;

import com.hivin.service.IHelloWorldService;
import org.springframework.stereotype.Service;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/2/21
 */
@Service("helloWorldService")
public class IHelloWorldServiceImpl implements IHelloWorldService {
    @Override
    public String getHelloMessage() {
        return "你好！";
    }
}
