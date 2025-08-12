package com.rookies4.myspringboot.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyPropsRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("VM Arguments = " + args.containsOption("foo"));
        System.out.println("Program Arguments = " + args.containsOption("bar"));

        for (String argName : args.getOptionNames()){
            System.out.println("아규먼트 이름 = " + argName);
        }

    }
}
