package com.yl.common.test;

import com.yl.common.controller.IndexController;
import com.yl.springboot.config.BeanCreateConfig;
import com.yl.springboot.config.ConponentScanConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @author Alex
 * @since 2018/8/28 16:26
 */
public class ConfigDemo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConponentScanConfig.class);

        IndexController bean = context.getBean(IndexController.class);

        System.err.println(bean.index());

        System.out.println(bean.index());
    }

    @Test
    public void demo_1(){
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanCreateConfig.class);

        System.err.println("ioc容器初始化完成");

        Map<String, Object> beans = context.getBeansOfType(Object.class);

        beans.forEach((k,v)->{
            System.err.println("beanName:" + k +",bean:" + v);
        });
    }
}
