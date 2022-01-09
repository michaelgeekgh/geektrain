package com.geektrain.jvm.module1;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 Hello 方法，
 * 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 */
public class Classloader extends ClassLoader{
    public static void main(String[] args) throws URISyntaxException, IOException {

        URLClassLoader urlClassLoader=(URLClassLoader) Classloader.class.getClassLoader();
        System.out.println(urlClassLoader);
    }
    public static void printClassLoader(String name,ClassLoader classLoader) {
        if(null!=classLoader) {
            System.out.println(name + "classLoader ->" +classLoader);
            printURLForClassloader(classLoader);
        } else {
            System.out.println(name+"classLoader is null");
        }
    }

    private static void printURLForClassloader(ClassLoader classLoader) {
    }

    public Class<?> findClass() {
        System.out.println("findClass entry");
        String classPath="file:///D:/workspace/src/Hello.class";
//        String cp1="file:///D:/workspace/jvm/target/classes/com/geektrain/jvm/module1/Classloader.class";
        byte[] classBytes=null;
        Path path=null;
        try {
            path= Paths.get(new URI(classPath));
            classBytes= Files.readAllBytes(path);
//            System.out.println("classBytes"+classBytes);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class clazz=defineClass(null,classBytes,0,classBytes.length);
//        System.out.println("clazz=="+clazz);
        return clazz;

    }
    public Class<?> findXClass() {
        System.out.println("findClass entry");
        String classPath="file:///D:/workspace/src/Hello.xlass";
        byte[] classBytes=null;
        Path path=null;
        try {
            path= Paths.get(new URI(classPath));
            classBytes= Files.readAllBytes(path);
//            System.out.println("classBytes"+classBytes);
            /**
             * 字节取反
             */
            byte temp;
            for (int i = 0; i < classBytes.length; i++) {
                temp=classBytes[i];
                classBytes[i]=(byte)(~temp);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class clazz=defineClass(null,classBytes,0,classBytes.length);
//        System.out.println("clazz=="+clazz);
        return clazz;
    }

}
