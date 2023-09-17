package io.github.xiaoso456.loader;

import cn.hutool.core.io.IoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarSimpleClassLoader extends ClassLoader {

    private String jarPath;

    public JarSimpleClassLoader(String jarPath) {
        this.jarPath = jarPath;
    }


    @Override
    protected Class<?> findClass(String name) {
        String classPath = classnameToPath(name);
        try (JarFile jarFile = new JarFile(jarPath, false)) {
            JarEntry jarEntry = jarFile.getJarEntry(classPath);
            InputStream classInputStream = jarFile.getInputStream(jarEntry);
            byte[] classBytes = IoUtil.readBytes(classInputStream);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 把 io.github.xiaoso456.MyPlugin 这种类名转化为jar包格式class文件路径 io/github/xiaoso456/MyPlugin.class
     *
     * @param classname 类全限定名
     * @return class 文件路径
     */
    private String classnameToPath(String classname) {
        return classname.replaceAll("\\.", "/") + ".class";
    }

}
