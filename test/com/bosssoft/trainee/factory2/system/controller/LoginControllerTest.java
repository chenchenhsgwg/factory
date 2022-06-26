package com.bosssoft.trainee.factory2.system.controller;

import com.bosssoft.trainee.factory2.utils.AesEncryptUtil;
import com.bosssoft.trainee.factory2.utils.MD5Util;
import com.sun.deploy.net.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.subject.WebSubject;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

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
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void login() throws Exception {
        String username = "admin";
        String password = "admin";
        password = AesEncryptUtil.encrypt(password);
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);
        Cookie usernameCookie = new Cookie("username", username);
        Cookie passwordCookie = new Cookie("password", password);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        MvcResult mvcResult = mockMvc.perform(
//                        MockMvcRequestBuilders.post("/login").
//                                contentType(MediaType.APPLICATION_JSON_VALUE).
//                                content(String.valueOf(json))).
//                        andExpect(status().isOk()).
//                        andDo(print()).
//                        andReturn();
//        mvcResult.getResponse().getContentAsString();
//        mvcResult.getResponse().getCookie("username");
        HttpServletResponse httpServletResponse = (HttpServletResponse) this.loginController.login(username, password,
                mockMvc.perform(
                        MockMvcRequestBuilders.post("/login").
                                contentType(MediaType.APPLICATION_JSON_VALUE).
                                content(String.valueOf(json)).cookie(usernameCookie, passwordCookie)).
                        andExpect(status().isOk()).
                        andDo(print()).
                        andReturn().getRequest());
        httpServletResponse.getStatus();
        httpServletResponse.getHeaderNames();
//        String responseString = mockMvc.perform(
//                MockMvcRequestBuilders.post("/login").
//                contentType(MediaType.APPLICATION_JSON_VALUE).
//                        content(String.valueOf(json))).
//                andExpect(status().isOk()).
//                andDo(print()).
//                andReturn().getResponse().getContentAsString();
        //将相应的数据转换为字符串
//        System.out.println("返回的json1= " + responseString);
    }

    @Test
    void index() {
    }

    @Test
    void userOnline() {
    }

    @Test
    void kickout() {
    }

    @Test
    void logout() {
    }

    @Test
    void getUserRouters() {
    }

    @Test
    void regist() {
    }
}