package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    private MockMvc mockMvc;

    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //加了@Transactional 不会插入数据库中产生脏数据
    @Test
    @Transactional
    public void test() throws Exception {


        //模拟一个get请求
        // mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}", "mrbird"));

        //模拟一个post请求
        // ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));
        User user = new User();
        user.setId(this.userService.getSequence("seq_user"));
        user.setUsername("JUnit");
        user.setPasswd("123456");
        user.setStatus("1");
        user.setCreateTime(new Date());
        this.userService.save(user);
    }

    @Test
    public void testController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{userName}", "test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("test"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void testSaveController() throws Exception {
        User user = new User();
        user.setUsername("Dopa11");
        user.setPasswd("ac3af72d9f95161a502fd326865c2f15");
        user.setStatus("1");
        String userJson = mapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testService() {
        User user = this.userService.findByName("mrbird");
        System.out.println(user);
    }

}
