package io.github.xiaoso456.loader;

import cn.hutool.core.io.FileUtil;

public class SimpleClassLoader extends ClassLoader {

    private String classPath;

    public SimpleClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = FileUtil.readBytes(classPath);
        return defineClass(name, bytes, 0, bytes.length);
    }

}
