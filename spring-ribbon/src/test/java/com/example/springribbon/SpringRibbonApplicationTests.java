package com.example.springribbon;

import com.example.springribbon.reflect.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRibbonApplicationTests {

    @Test
    public void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "123");
        Object object = SpringContextUtil.methodInvoke("reflectTest.test", null);
        System.out.println(object);
    }

}
