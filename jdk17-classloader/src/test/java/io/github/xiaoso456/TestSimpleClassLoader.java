package io.github.xiaoso456;

import cn.hutool.core.io.FileUtil;
import io.github.xiaoso456.loader.SimpleClassLoader;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.jar.JarFile;

public class TestSimpleClassLoader  {

    @Test
    void testTestSimpleClassLoader() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader("D:\\code\\project\\idea_project\\spi-demo\\my-plugin\\target\\classes\\io\\github\\xiaoso456\\MyPlugin.class");
        Class<?> myPluginClass = simpleClassLoader.loadClass("io.github.xiaoso456.MyPlugin");

        Object instance = myPluginClass.getDeclaredConstructor().newInstance();

        System.out.println(instance);
    }


}
