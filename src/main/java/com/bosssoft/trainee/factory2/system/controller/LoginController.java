package com.bosssoft.trainee.factory2.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.bosssoft.trainee.factory2.common.*;
import com.bosssoft.trainee.factory2.common.code.CodeStatus;
import com.bosssoft.trainee.factory2.system.mapper.LoginLogMapper;
import com.bosssoft.trainee.factory2.system.service.IUserService;
import com.bosssoft.trainee.factory2.system.service.impl.LoginService;
import com.bosssoft.trainee.factory2.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bosssoft.trainee.factory2.common.router.VueRouter;
import com.bosssoft.trainee.factory2.common.service.RedisService;
import com.bosssoft.trainee.factory2.config.JWTToken;
import com.bosssoft.trainee.factory2.config.JWTUtils;
import com.bosssoft.trainee.factory2.system.entity.Authority;
import com.bosssoft.trainee.factory2.system.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Validated
@RestController
public class LoginController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ShiroProperties properties;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private LoginLogMapper loginLogMapper;

    @PostMapping("/login")
    public Response login(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password, HttpServletRequest request) throws Exception {
        username = StringUtils.lowerCase(username);
        final String errorMessage = "????????????????????????";
        User user = this.loginService.getUser(username);

        if (user == null || password == null) {
            throw new Exception(errorMessage);
        }

        if (!StringUtils.equals(user.getPassword(), password)) {
            throw new Exception(errorMessage);
        }

        String token = WebUtil.encryptToken(JWTUtils.sign(username, password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);

        String userId = this.saveTokenToRedis(user, jwtToken, request);
        user.setUserId(userId);

        Map<String, Object> userInfo = this.generateUserInfo(jwtToken, user);
        return new Response().addCodeMessage(CodeStatus.C200.getCode(), "????????????", CodeStatus.C200.getDesc(), userInfo);
    }

    @GetMapping("index/{username}")
    public Response index(@NotBlank(message = "{required}") @PathVariable String username) {
        Map<String, Object> data = new HashMap<>();
        // ????????????????????????
        Long totalVisitCount = loginLogMapper.findTotalVisitCount();
        data.put("totalVisitCount", totalVisitCount);
        Long todayVisitCount = loginLogMapper.findTodayVisitCount();
        data.put("todayVisitCount", todayVisitCount);
        Long todayIp = loginLogMapper.findTodayIp();
        data.put("todayIp", todayIp);
        // ??????????????????????????????
        List<Map<String, Object>> lastSevenVisitCount = loginLogMapper.findLastSevenDaysVisitCount(null);
        data.put("lastSevenVisitCount", lastSevenVisitCount);
        User param = new User();
        param.setUsername(username);
        List<Map<String, Object>> lastSevenUserVisitCount = loginLogMapper.findLastSevenDaysVisitCount(param);
        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return new Response().data(data);
    }

    @RequiresPermissions("user:online")
    @GetMapping("online/{username}")
    public Response userOnline(String username) throws Exception {
        String now = DateUtil.formatFullTime(LocalDateTime.now());
        Set<String> userOnlineStringSet = redisService.zrangeByScore(Constant.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
        List<ActiveUser> activeUsers = new ArrayList<>();
        for (String userOnlineString : userOnlineStringSet) {
            ActiveUser activeUser = mapper.readValue(userOnlineString, ActiveUser.class);
            activeUser.setToken(null);
            if (StringUtils.isNotBlank(username)) {
                if (StringUtils.equalsIgnoreCase(username, activeUser.getUsername())) {
                    activeUsers.add(activeUser);
                }
            } else {
                activeUsers.add(activeUser);
            }
        }
        return new Response().data(activeUsers);
    }

    @DeleteMapping("kickout/{id}")
    @RequiresPermissions("user:kickout")
    public void kickout(@NotBlank(message = "{required}") @PathVariable String id) throws Exception {
        String now = DateUtil.formatFullTime(LocalDateTime.now());
        Set<String> userOnlineStringSet = redisService.zrangeByScore(Constant.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
        ActiveUser kickoutUser = null;
        String kickoutUserString = "";
        for (String userOnlineString : userOnlineStringSet) {
            ActiveUser activeUser = mapper.readValue(userOnlineString, ActiveUser.class);
            if (StringUtils.equals(activeUser.getId(), id)) {
                kickoutUser = activeUser;
                kickoutUserString = userOnlineString;
            }
        }
        if (kickoutUser != null && StringUtils.isNotBlank(kickoutUserString)) {
            // ?????? zset????????????
            redisService.zrem(Constant.ACTIVE_USERS_ZSET_PREFIX, kickoutUserString);
            // ??????????????? token??????
            redisService.del(Constant.TOKEN_CACHE_PREFIX + kickoutUser.getToken() + "." + kickoutUser.getIp());
        }
    }

    @GetMapping("logout/{id}")
    public Response logout(@NotBlank(message = "{required}") @PathVariable String id) throws Exception {
        try {
            this.kickout(id);
            return new Response().addCodeMessage(CodeStatus.C200.getCode(), "??????????????????", CodeStatus.C200.getDesc());
        } catch (Exception e) {
            String message = "??????????????????";
            throw new FException(message);
        }

    }

    @GetMapping("login/{username}")
    public List<VueRouter<Authority>> getUserRouters(@NotBlank(message = "{required}") @PathVariable String username) {
        return this.loginService.getUserRouters(username);
    }

    @PostMapping("regist")
    public void regist(@NotBlank(message = "{required}") String username,
                       @NotBlank(message = "{required}") String password,
                       @NotBlank(message = "{required}") String realname,
                       @NotNull(message = "{required}") Integer roleId,
                       String telephone,
                       String factoryName,
                       String factoryDescription
    ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleId(roleId);
        user.setTelephone(telephone);
        user.setRealname(realname);
        if (roleId == 2) {
            user.setFactoryDescription(factoryDescription);
            user.setFactoryName(factoryName);
        }
        this.userService.regist(user);
    }

    public String saveTokenToRedis(User user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);

        // ??????????????????
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getUsername());
        activeUser.setIp(ip);
        activeUser.setToken(token.getToken());

        // zset ?????????????????????score ??????????????????
        this.redisService.zadd(Constant.ACTIVE_USERS_ZSET_PREFIX, Double.valueOf(token.getExipreAt()), mapper.writeValueAsString(activeUser));
        // redis ????????????????????? token???key = ?????? + ?????? token + .ip
        this.redisService.set(Constant.TOKEN_CACHE_PREFIX + token.getToken() + StringPool.DOT + ip, token.getToken(), properties.getJwtTimeOut() * 1000);

        return activeUser.getId();
    }

    /**
     * ?????????????????????????????????????????????
     * 1. token
     * 2. Vue Router
     * 3. ????????????
     * 4. ????????????
     * 5. ?????????????????????????????????
     *
     * @param token token
     * @param user  ????????????
     * @return UserInfo
     */
    private Map<String, Object> generateUserInfo(JWTToken token, User user) {
        String username = user.getUsername();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());
        userInfo.put("expireTime", token.getExipreAt());

        Set<String> roles = this.loginService.getUserRoles(username);
        userInfo.put("roles", roles);

        Set<String> permissions = this.loginService.getUserPermissions(username);
        userInfo.put("permissions", permissions);

        user.setPassword("it's a secret");
        userInfo.put("user", user);
        return userInfo;
    }
}
