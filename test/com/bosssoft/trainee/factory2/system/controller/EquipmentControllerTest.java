package com.bosssoft.trainee.factory2.system.controller;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
class EquipmentControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private EquipmentController equipmentController;

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
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();  //初始化MockMvc对象
    }

    @Test
    @DisplayName("查看指定设备数据")
    void activeEquipmentList() throws Exception {
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/equipment/6")  //请求的url,请求的方法是get
                //数据的格式
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())//打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("返回的设备数据= " + responseString);
    }

    @Test
    @DisplayName("查看非法设备数据")
    void activeIllegalEquipmentList() throws Exception {
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.get("/equipment/60")  //请求的url,请求的方法是get
                //数据的格式
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())//打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("返回的设备数据= " + responseString);
    }

    @Test
    @DisplayName("新增设备")
    void addEquipment() throws Exception {
        String content = "{\"id\":3, \"typeId\":3,\"name\":\"扇子机床\","+
                "\"state\":\"空闲\", \"factoryId\":5," +
                "\"enabled\":1}";
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/equipment")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("删除设备")
    void deleteEquipment() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/equipment/29")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("更新设备信息")
    void updateEquipment() throws Exception {
        String content = "{\"id\":1, \"typeId\":4,\"name\":\"扇子机床\","+
                "\"state\":\"空闲\", \"factoryId\":5, \"enabled\":1}";
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/equipment")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("开关设备")
    void switchEquipment() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/equipment/switch/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }
}