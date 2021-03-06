package com.gopher.system.service.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.gopher.system.constant.CodeAndMsg;
import com.gopher.system.exception.BusinessRuntimeException;
import com.gopher.system.model.Customer;
import com.gopher.system.model.CustomerUser;
import com.gopher.system.model.User;
import com.gopher.system.model.vo.request.CustomerRequst;
import com.gopher.system.model.vo.request.LoginRequst;
import com.gopher.system.model.vo.request.LogoutRequst;
import com.gopher.system.model.vo.request.RegisterRequst;
import com.gopher.system.model.vo.response.LoginResponse;
import com.gopher.system.model.vo.response.WechatAuthResponse;
import com.gopher.system.service.AuthService;
import com.gopher.system.service.CacheService;
import com.gopher.system.service.CustomerService;
import com.gopher.system.service.CustomerUserService;
import com.gopher.system.service.UserService;
import com.gopher.system.service.WechatService;
import com.gopher.system.util.CookieUtils;
import com.gopher.system.util.MD5Utils;

@Service
public class AuthServiceImpl implements AuthService{
	private static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerUserService customerUserService;
	@Autowired
	private WechatService wechatService;
	@Autowired
	private CacheService cacheService;
    /**
     * 此注册为商户注册,需要添加一条用户信息(标注为商户,只允许登录APP)
     * 添加一条商户信息,如果存在则不添加
     */
	@Transactional
	@Override
	public void register(RegisterRequst registerRequst) {
		if(null == registerRequst){
			throw new BusinessRuntimeException(CodeAndMsg.PARAM_NOT_NULL);
		}
		final String account  = registerRequst.getAccount();
		final String password = registerRequst.getPassword();
		final String company  = registerRequst.getCompany();
		final String phone    = registerRequst.getPhone();
		final String code     = registerRequst.getCode();
		final String nickName = registerRequst.getNickName();
	
		if(!StringUtils.hasText(account)){
			throw new BusinessRuntimeException("账户不能为空");
		}
		if(!StringUtils.hasText(password)){
			throw new BusinessRuntimeException("密码不能为空");
		}
		if(!StringUtils.hasText(company)){
			throw new BusinessRuntimeException("企业名称不能为空");
		}
		if(!StringUtils.hasText(phone)){
			throw new BusinessRuntimeException("电话不能为空");
		}
		if(!StringUtils.hasText(code)){
			throw new BusinessRuntimeException("微信鉴权码不能为空");
		}
		User userDB = userService.getUserByAccount(account);
		if(null != userDB){
			throw new BusinessRuntimeException("账号已经存在,请重新输入账号");
		}
		User user = new User();
		user.setAccount(account);
		user.setPassword(MD5Utils.MD5(password));
		user.setPhone(phone);
		//TODO 直接回去用户微信信息
		WechatAuthResponse rsp = wechatService.getSession(code);
		if(null != rsp){
			LOG.info("根据微信鉴权码获取微信用户OPENID:{}",JSON.toJSONString(rsp));
			user.setName(nickName);
			user.setWechat(rsp.getOpen_id());
		}
		Customer customerDB  = customerService.findByName(company);
		if(null == customerDB){
			customerDB= new Customer();
			customerDB.setName(company);
			customerDB.setMobilePhone(phone);
			CustomerRequst customerRequst = new CustomerRequst();
			BeanUtils.copyProperties(customerDB, customerRequst);
		    customerService.add(customerRequst);
		}
		final Integer customerId = customerDB.getId();
		user.setUserType(User.CUSTOMER);
		userService.insert(user);
		final Integer userId =user.getId();
		CustomerUser customerUser = new CustomerUser();
		customerUser.setCustomerId(customerId);
		customerUser.setUserId(userId);
		customerUserService.insert(customerUser);
	}

	@Override
	public LoginResponse login(LoginRequst loginRequest) {
		if(null == loginRequest){
			throw new BusinessRuntimeException(CodeAndMsg.PARAM_NOT_NULL);
		}
		final String account = loginRequest.getAccount();
		final String password = loginRequest.getPassword();
		final String APPLICATION = loginRequest.getApplication();
		if(!StringUtils.hasText(account)){
			throw new BusinessRuntimeException("账号不能为空");
		}
		if(!StringUtils.hasText(password)){
			throw new BusinessRuntimeException("密码不能为空");
		}
		User userDB = userService.getUserByAccount(account);
		
		if(null == userDB){
			throw new BusinessRuntimeException("无效的账号,请检查账号后重新登录");
		}
		if(userDB.getUserType() == 1 && Objects.equals(APPLICATION, "web")) {
			throw new BusinessRuntimeException("客户账号，禁止访问");
		}
		final String passwordDB = userDB.getPassword();
		if(!Objects.equals(password, passwordDB)){
			throw new BusinessRuntimeException("账号或密码错误,请检查账号密码后重新登录");
		}
		LoginResponse response = new LoginResponse();
		final String TOKEN = MD5Utils.MD5(account+System.currentTimeMillis());
		response.setToken(TOKEN);
		cacheService.set(TOKEN,userDB.getId(),CookieUtils.DEFAULT_TOKEN_ALIVE);
		return response;
	}

	@Override
	public void logout(LogoutRequst logoutRequst) {
		final String token = logoutRequst.getToken();
		if(StringUtils.hasText(token)) {
			cacheService.delete(token);
		}
	}

}
