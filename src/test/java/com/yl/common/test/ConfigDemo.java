package com.yl.common.test;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;
import com.yl.common.controller.IndexController;
import com.yl.common.demo.*;
import com.yl.common.service.UserService;
import com.yl.job.task.DemoTask;
import com.yl.job.task.TaskComponet;
import com.yl.job.task.TaskProxy;
import com.yl.springboot.config.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;

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

    @Test
    public void demo_2(){

        DiamondManager diamondManager = new DefaultDiamondManager("market", "service",
            new ManagerListener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String s) {

                }
            });

        String info = diamondManager.getAvailableConfigureInfomation(100);
        System.err.println(info);

    }

    @Test
    public void demo_3(){
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);

        System.err.println("IOC容器创建完成");

        Car car = context.getBean("car", Car.class);

        System.err.println(car);

        ((AnnotationConfigApplicationContext) context).close();
    }


    @Test
    public void demo_4(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ValueProfileConfig.class);

        System.err.println("IOC容器创建完成");

        Person person = context.getBean(Person.class);

        System.err.println(person);

        Environment environment = context.getEnvironment();

        System.err.println(environment.getProperty("person.name"));

        ((AnnotationConfigApplicationContext) context).close();
    }


    @Test
    public void demo_5(){
        ApplicationContext context = new AnnotationConfigApplicationContext(DIConfig.class);

        System.err.println("IOC容器创建完成");

        UserService userService = context.getBean(UserService.class);

        System.err.println(userService);

        Map<String, ? extends UserDao> userDaos = context.getBeansOfType(UserDao.class);

        userDaos.forEach((k,v)->{
            System.err.println("beanName:" + k + " Bean:" + v);
        });
        
        Optional<String> os = Optional.of("miss");

        System.err.println(os.get());

        ((AnnotationConfigApplicationContext) context).close();
    }


    @Test
    public void demo_6(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        MathCalculator cal = context.getBean(MathCalculator.class);

        int ret = cal.div(1, 0);

        ((AnnotationConfigApplicationContext) context).close();

    }

    @Test
    public void demo_7(){
        ApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);


//        context.publishEvent(new ApplicationEvent("自定义事件") {
//        });

        ApplicationEventMulticaster eventMulticaster = context.getBean(ApplicationEventMulticaster.class);

        System.err.println(eventMulticaster);

        UserEvent userEvent = new UserEvent(new User(1, "miss"));

        EventPublisher.publishEvent(userEvent);

        ((AnnotationConfigApplicationContext) context).close();
    }


    @Test
    public void demo_8(){
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfig.class);
        DemoTask task = context.getBean(DemoTask.class);
        TaskComponet annotation = task.getClass().getAnnotation(TaskComponet.class);
//        TaskProxy.proxy(annotation.value(), context).run();
          TaskProxy.getProxy(annotation.value(), context).run();
    }


}
