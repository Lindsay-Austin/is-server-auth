package com.authservice.config;

import com.authservice.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // 通过用户名获取用户信息
        //Account account = accountRepository.findByUserName(name);
        Account account = new Account();
        if (account != null) {
            // 创建spring security安全用户
                    /*User user = new User(account.getUserName(),
                            account.getPassWord(),
                            AuthorityUtils.createAuthorityList(account.getRoles()));*/

            return User.withUsername(name)
                    .password(passwordEncoder.encode("123456"))
                    .authorities("ROLE_ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("用户[" + name + "]不存在");
        }
    }
}
