package io.github.xiaoso456;

import io.github.xiaoso456.loader.JarSimpleClassLoader;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TestJarSimpleClassLoader {

    @Test
    void testTestSimpleClassLoader() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JarSimpleClassLoader jarSimpleClassLoader = new JarSimpleClassLoader("D:\\code\\project\\idea_project\\spi-demo\\my-plugin\\target\\my-plugin.jar");
        Class<?> myPluginClass = jarSimpleClassLoader.loadClass("io.github.xiaoso456.MyPlugin");

        Object instance = myPluginClass.getDeclaredConstructor().newInstance();

        System.out.println(instance);

    }


}
