package com.yl.springboot.config;

import com.yl.common.service.UserService;
import com.yl.common.service.impl.UserServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex
 * @since 2018/8/28 14:06
 * @des 主配置类
 */
@Configuration
@ComponentScans(value = {
        @ComponentScan(value = "com.yl",useDefaultFilters = false,includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {RestController.class})
        })
})
public class ConponentScanConfig {

        @Bean
        public UserService userService(){
                return new UserServiceImpl();
        }

}
