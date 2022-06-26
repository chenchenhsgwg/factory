package com.bosssoft.trainee.factory2.system.controller;

import com.bosssoft.trainee.factory2.common.ShiroProperties;
import com.bosssoft.trainee.factory2.config.JWTToken;
import com.bosssoft.trainee.factory2.config.JWTUtils;
import com.bosssoft.trainee.factory2.system.entity.User;
import com.bosssoft.trainee.factory2.utils.AesEncryptUtil;
import com.bosssoft.trainee.factory2.utils.DateUtil;
import com.bosssoft.trainee.factory2.utils.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerTest {

    public Subject subject;
    private MockMvc mockMvc;
    private User user = new User(7, "admin",
            "fJMAPUO9faymPq3mKErq7A==", "狗管理",
            "12335345345", 5, "设备中心",
            "可出租", 3, "超级管理员");
    protected MockHttpSession mockHttpSession;
    protected MockHttpServletRequest mockHttpServletRequest;
    protected MockHttpServletResponse mockHttpServletResponse;

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UserController userController;

    @Autowired
    private ShiroProperties properties;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new DelegatingFilterProxy("shiroFilter", wac), "/")
                        .build();  //初始化MockMvc对象
//        mockHttpServletResponse = new MockHttpServletResponse();
//        mockHttpServletRequest = new MockHttpServletRequest(wac.getServletContext());
//        mockHttpSession = new MockHttpSession(wac.getServletContext());
//        mockHttpServletRequest.setSession(mockHttpSession);
//        SecurityUtils.setSecurityManager(securityManager);
//        subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse).buildSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
//        token.setHost("127.0.0.1");
//        subject.login(token);
    }

    @BeforeEach
    void testBeforeEach() {
        System.out.println("用户测试就要开始。。。");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("用户测试就要结束。。。");
    }

    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有用户测试就要开始。。。");
    }

    @AfterAll
    static void testAfterAll() {
        System.out.println("所有用户测试已经结束。。。");
    }

    @Test
    @DisplayName("合法用户检查测试")
    public void checkLegalUserName() {
        Assertions.assertFalse(userController.checkUserName("admin"), "admin用户查找失败");
    }

    @Test
    @DisplayName("非法用户检查测试")
    public void checkIllegalUserName() {
        Assertions.assertTrue(userController.checkUserName("admin2"), "不存在的用户被查找到");
    }

    @Test
    @DisplayName("合法用户名获取用户完整信息测试")
    public void getLegalDetail() {
        Assertions.assertNotNull(userController.detail("admin"), "无法获取admin用户对象");
    }

    @Test
    @DisplayName("非法用户名获取用户完整信息测试")
    public void getIllegalDetail() {
        Assertions.assertNotNull(userController.detail("admin2"), "错误地获取不存在的用户对象");
    }

    @Test
    @DisplayName("获取所有用户信息")
    public void userList() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new DelegatingFilterProxy("shiroFilter", wac), "/")
                .build();  //初始化MockMvc对象
//        mockHttpServletResponse = new MockHttpServletResponse();
//        mockHttpServletRequest = new MockHttpServletRequest(wac.getServletContext());
//        mockHttpSession = new MockHttpSession(wac.getServletContext());
//        mockHttpServletRequest.setSession(mockHttpSession);
//        SecurityUtils.setSecurityManager(securityManager);
//        subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse).buildSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
//        token.setHost("127.0.0.1");
//        subject.login(token);
//        String token = WebUtil.encryptToken(JWTUtils.sign("admin", AesEncryptUtil.encrypt("admin")));
//        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getJwtTimeOut());
//        String expireTimeStr = DateUtil.formatFullTime(expireTime);
//        JWTToken jwtToken = new JWTToken(token, expireTimeStr);

//        String userId = this.userController.saveTokenToRedis(user, jwtToken, request);
//        String responseString = mockMvc.perform(
//                get("/user")  //请求的url,请求的方法是get
//                //数据的格式
//        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())//打印出请求和相应的内容
//                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
//        System.out.println("返回的json1= " + responseString);
//        this.userController.userList(mockMvc.perform(
//                MockMvcRequestBuilders.get("/user/view")
//        ).andExpect(status().isOk())    //返回的状态是200
//                .andDo(print())//打印出请求和相应的内容
//                .andReturn().getRequest(), user);
    }

    @Test
    public void addUser() {
    }

    @Test
    public void deleteUser() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("asaaa")) // 期望返回的json数据中username字段的值为"张三"
                .andReturn().getResponse().getContentAsString();
        this.userController.deleteUser("1");
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    public void updateUser() {
    }
}