package com.bosssoft.trainee.factory2.system.controller;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.junit.Before;
import org.junit.Test;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Autowired
    protected WebApplicationContext wac;
    private ThreadState _threadState;
    protected Subject _mockSubject;

    @Before
    public void before() {
        _mockSubject = Mockito.mock(Subject.class);
        _threadState = new SubjectThreadState(_mockSubject);
        _threadState.bind();
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                        .build();  //初始化MockMvc对象
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
        Assertions.assertNull(userController.detail("admin2"), "错误地获取不存在的用户对象");
    }

    @Test
    @DisplayName("获取所有用户信息")
    public void userList() throws Exception {
        String responseString = mockMvc.perform(
                get("/user")  //请求的url,请求的方法是get
                //数据的格式
        ).andExpect(status().isOk())    //返回的状态是200
                .andDo(print())//打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("返回的json数据= " + responseString);
    }

    @Test
    @DisplayName("新增用户")
    public void addUser() throws Exception {
        String content = "{\"id\":20, \"username\":\"231as\",\"realname\":\"231a2\","+
                "\"password\":\"231a\", \"telephone\":\"13940131059\"," +
                "\"roleId\":1, \"roleName\":\"经销商\"}";
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("删除指定用户")
    public void deleteUser() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }

    @Test
    @DisplayName("更新用户信息")
    public void updateUser() throws Exception {
        String content = "{\"id\":1, \"username\":\"asaaa\",\"realname\":\"大人物\","+
                "\"password\":\"q4nKCJApl0Ef4Rf%2B4Kve9A==\", \"telephone\":\"13940131059\"}";
        System.out.println(content);
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(MockMvcResultHandlers.print()) // 打印信息
                .andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的状态码为200
                .andReturn().getResponse().getContentAsString(Charset.defaultCharset());
        System.out.println("mvcResult: " + mvcResult);
    }
}