package com.bosssoft.trainee.factory2.system.controller;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class FactoryControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private FactoryController factoryController;

    @Autowired
    protected WebApplicationContext wac;
    private ThreadState _threadState;
    protected Subject _mockSubject;

    @BeforeEach
    public void before() {
        _mockSubject = Mockito.mock(Subject.class);
        _threadState = new SubjectThreadState(_mockSubject);
        _threadState.bind();
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("合法工厂检查测试")
    public void checkLegalFactoryName() {
        Assertions.assertFalse(factoryController.checkUserName("123"), "123工厂查找失败");
    }

    @Test
    @DisplayName("非法工厂检查测试")
    public void checkIllegalFactoryName() {
        Assertions.assertTrue(factoryController.checkUserName("1234"), "不存在的工厂错误地被查找到");
    }

    @Test
    @DisplayName("获取所有工厂信息")
    void factoryList() throws Exception {
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/factory")  //请求的url,请求的方法是get
                //数据的格式
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())//打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("返回的工厂数据= " + responseString);
    }

    @Test
    @DisplayName("新增工厂")
    void addFactory() throws Exception {
        String content = "{\"id\":7, \"name\":\"231as\",\"description\":\"231a2\","+
                "\"userId\":9, \"enabled\":1}";
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/factory")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("删除指定工厂")
    void deleteFactory() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/factory/6")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("更新工厂信息")
    void updateFactory() throws Exception {
        String content = "{\"id\":6, \"name\":\"231as\",\"description\":\"231a2\","+
                "\"userId\":9, \"enabled\":1}";
        System.out.println(content);
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/factory")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("开关工厂")
    void switchFactory() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/factory/switch/6")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }
}