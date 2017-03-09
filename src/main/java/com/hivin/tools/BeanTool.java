package com.hivin.tools;

import com.hivin.service.impl.IBuildServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/6
 */
public class BeanTool {
    static Logger log = LoggerFactory.getLogger(BeanTool.class);

    public static Map<String, String> objToMap(Object obj) {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), (String) field.get(obj));
            }
        } catch (Exception e) {
            log.error("object转换map异常:{}", e);
        }

        return map;
    }

    public static Object mapToObj(Map<String, Object> map, Class<?> clz) {
        Object obj = null;
        try {
            obj = clz.newInstance();
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            log.error("object转换map异常:{}", e);
        }
        return obj;
    }
}
