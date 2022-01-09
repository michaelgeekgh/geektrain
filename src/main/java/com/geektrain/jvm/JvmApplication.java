package com.geektrain.jvm;

import com.geektrain.jvm.module1.Classloader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
public class JvmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvmApplication.class, args);
        Classloader cl= new Classloader();
        Class<?> clazz=cl.findXClass();
        try {
            Object obj=clazz.newInstance();
            Method method=clazz.getMethod("hello",null);
            method.invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println("hello ClassLoader!");
    }

}