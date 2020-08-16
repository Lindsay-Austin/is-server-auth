package com.servicea.config;


import com.servicea.dao.AccountRepository;
import com.servicea.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // 查询用户使用
//    @Autowired
//    private AccountRepository accountRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        // .withUser("user").password("password").roles("USER")
        // .and()
        // .withUser("app_client").password("nopass").roles("USER")
        // .and()
        // .withUser("admin").password("password").roles("ADMIN");
        //配置用户来源于数据库
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated().and()
                .httpBasic().and().csrf().disable();
    }*/

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
                // 通过用户名获取用户信息
                //Account account = accountRepository.findByUserName(name);
                Account account = new Account();
                if (account != null) {
                    // 创建spring security安全用户
                    *//*User user = new User(account.getUserName(),
                            account.getPassWord(),
                            AuthorityUtils.createAuthorityList(account.getRoles()));*//*

                    return User.withUsername(name)
                            .password("123456")
                            .authorities("ROLE_ADMIN")
                            .build();
                } else {
                    throw new UsernameNotFoundException("用户[" + name + "]不存在");
                }
            }
        };

    }*/
}