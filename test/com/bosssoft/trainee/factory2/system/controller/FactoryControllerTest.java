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
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //?????????MockMvc??????
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("????????????????????????")
    public void checkLegalFactoryName() {
        Assertions.assertFalse(factoryController.checkUserName("123"), "123??????????????????");
    }

    @Test
    @DisplayName("????????????????????????")
    public void checkIllegalFactoryName() {
        Assertions.assertTrue(factoryController.checkUserName("1234"), "???????????????????????????????????????");
    }

    @Test
    @DisplayName("????????????????????????")
    void factoryList() throws Exception {
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/factory")  //?????????url,??????????????????get
                //???????????????
        ).andExpect(status().isOk())    //??????????????????200
                .andDo(print())//?????????????????????????????????
                .andReturn().getResponse().getContentAsString();   //????????????????????????????????????
        System.out.println("?????????????????????= " + responseString);
    }

    @Test
    @DisplayName("????????????")
    void addFactory() throws Exception {
        String content = "{\"id\":7, \"name\":\"231as\",\"description\":\"231a2\","+
                "\"userId\":9, \"enabled\":1}";
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/factory")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(MockMvcResultHandlers.print()) // ????????????
                .andExpect(MockMvcResultMatchers.status().isOk()) // ???????????????????????????200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("??????????????????")
    void deleteFactory() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/factory/6")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print()) // ????????????
                .andExpect(MockMvcResultMatchers.status().isOk()) // ???????????????????????????200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("??????????????????")
    void updateFactory() throws Exception {
        String content = "{\"id\":6, \"name\":\"231as\",\"description\":\"231a2\","+
                "\"userId\":9, \"enabled\":1}";
        System.out.println(content);
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/factory")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(MockMvcResultHandlers.print()) // ????????????
                .andExpect(MockMvcResultMatchers.status().isOk()) // ???????????????????????????200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("????????????")
    void switchFactory() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/factory/switch/6")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()) // ????????????
                .andExpect(MockMvcResultMatchers.status().isOk()) // ???????????????????????????200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }
}