package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/8/18 0018 11:53
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    UserArgumentResolver userArgumentResolver;

    // 自动将 “获取当前用户+续期” 的逻辑 放入 userArgumentResolver 中，
    // 然后 再 springmvc 层接口入参中直接取用户对象即可获取解析好的当前用户
    // 从业务中剥离到配置文件中，代码更加优雅简洁
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }
}
