package com.bosssoft.trainee.factory2.system.controller;

import com.bosssoft.trainee.factory2.common.ShiroProperties;
import com.bosssoft.trainee.factory2.config.JWTToken;
import com.bosssoft.trainee.factory2.config.JWTUtils;
import com.bosssoft.trainee.factory2.utils.AesEncryptUtil;
import com.bosssoft.trainee.factory2.utils.DateUtil;
import com.bosssoft.trainee.factory2.utils.MD5Util;
import com.bosssoft.trainee.factory2.utils.WebUtil;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.util.ThreadState;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.subject.WebSubject;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import static org.apache.shiro.SecurityUtils.getSecurityManager;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class LoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private LoginController loginController;

    @Autowired
    private ShiroProperties properties;

    @Autowired
    protected WebApplicationContext wac;
    private ThreadState _threadState;
    protected Subject _mockSubject;

//    protected void setSubject(Subject subject) {
//        clearSubject();
//        subjectThreadState = createThreadState(subject);
//        subjectThreadState.bind();
//    }

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
    void userOnline() throws Exception {
        String mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/online/admin"))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    void kickout() throws Exception {
        String mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/kickout/1"))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    void logout() throws Exception {
        String mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/logout/1"))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    void getUserRouters() throws Exception {
        String mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/login/admin"))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }
}