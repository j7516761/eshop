package com.example.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.interceptor.ProductInterceptor;
import com.example.pojo.entity.User;
import com.example.service.UserService;

/**
 * UserService 的實作類別，處理與使用者相關的業務邏輯。 這個類別實作了 UserService 介面，並使用 UserDao 來進行資料庫操作。
 * 註解：@Service 標註此類別為 Spring 的服務層 Bean。
 */
@Service
public class UserServiceImpl implements UserService {

	// 自動注入 UserDao，負責執行實際的資料庫操作
	@Autowired
	private UserDao userDao;

	private static final Logger logger = LoggerFactory.getLogger(ProductInterceptor.class);

	// BCryptPasswordEncoder 用於雜湊密碼
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * 根據使用者的登入帳號與密碼，查詢使用者是否存在。
	 * 
	 * @param user 包含登入帳號與密碼的 User 物件
	 * @return 如果使用者存在，返回對應的 User 物件，否則返回 null
	 */
	@Override
	public User getLoginUser(User user) {
		User foundUser = userDao.findById(user.getLoginId());
		if (foundUser != null) {
			logger.debug("DB Password : " + foundUser.getPassword());
			logger.debug("Hash Password : " + passwordEncoder.encode(user.getPassword()));
			if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
				// 密碼正確，返回找到的使用者
				return foundUser;
			}
		}
		return null;
	}

	/**
	 * 新增一個新的使用者到資料庫中。
	 * 
	 * @param user 要新增的 User 物件
	 */
	@Override
	public void addUser(User user) {
		// 對密碼進行雜湊
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);

		// 呼叫 UserDao 來新增使用者
		userDao.save(user);
	}
}
