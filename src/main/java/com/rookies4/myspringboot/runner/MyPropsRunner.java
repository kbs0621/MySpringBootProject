package com.rookies4.myspringboot.runner;

import com.rookies4.myspringboot.config.vo.CustomVO;
import com.rookies4.myspringboot.property.MyBootProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MyPropsRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    @Autowired
    private Environment environment;

    @Autowired
    private MyBootProperties properties;

    @Autowired
    private CustomVO custom;

    //Logger 객체 생성
    private Logger logger = LoggerFactory.getLogger(MyPropsRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("Logger 구현객체명 = {}", logger.getClass().getName());

        logger.info("현재 활성화된 CustomerVO Bean = {}", custom);

        logger.info("MyBootProperties.getName() =  {}", properties.getName());
        logger.info("MyBootProperties.getAge() = {}", properties.getAge());
        logger.info("MyBootProperties.getFullName() {}= ", properties.getFullName());

        logger.info("Properties myboot.name =  {}", name);
        logger.info("Properties myboot.age = {}", age);
        logger.info("Properties myboot.fullName =  {}", environment.getProperty("myboot.fullName"));

        logger.debug("VM Arguments = {}", args.containsOption("foo")); //false
        logger.debug("Program Arguments =  {}", args.containsOption("bar")); //true

        //Program Argument의 모든 이름을 출력하기
        for(String argName: args.getOptionNames()){
            logger.debug("아규먼트 이름 =  {}", argName);
        }
        //Program Argument의 모든 이름을 출력하기
//        for (String argName : args.getOptionNames()){
//            System.out.println("아규먼트 이름 = " + argName);
//        }

        //args.getOptionNames()의 리턴 타입 Set<String>
        //Iterable의 foreach(Consumer) 메서드 호출하기
        //Consumer의 추상메서드 void accpet(T t)
        //1. 익명의 Inner class ( Anonymous Inner Class)
        System.out.println("== 1. 익명의 Inner class로 구현 ==");
        args.getOptionNames().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Inner class 아규먼트 이름 = " + s);
            }
        });


        //2. lambda 함수로 Consumer 구현(함수형 인터페이스)
        System.out.println("== 2. lambda 함수로 Consumer 구현 ==");
        // 추상 메서드는 1개여야만 한다(어떤 메서드를 오버라이딩 할지 모르기 때문, 1개인 경우 무조건 그 추상 메서드만 받아옴)
        args.getOptionNames().forEach(name -> System.out.println("아규먼트 이름 = " + name));

        //3. Method Reference 구현(람다함수를 사용하는 데 argument를 생략 가능함)
        System.out.println("== 3. Method Reference 구현 ==");
        args.getOptionNames().forEach(System.out::println);


    }
}
