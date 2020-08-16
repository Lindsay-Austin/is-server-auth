package com.servicea.dao;

import com.servicea.entity.Account;
import org.springframework.stereotype.Component;

/**
 * 账户数据库操作类
 * MongoDB操作接口
 */
@Component
public interface AccountRepository {

    /**
     * 根据用户名查找账户信息
     * @param username 用户名
     * @return 账户信息
     */
    Account findByUserName(String username);
}