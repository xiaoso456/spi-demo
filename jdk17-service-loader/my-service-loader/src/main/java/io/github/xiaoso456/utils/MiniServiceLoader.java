package io.github.xiaoso456.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class MiniServiceLoader<T> implements Iterable<T>{

    private final Class<T> serviceType;

    private final List<T> serviceList = new ArrayList<>();

    private MiniServiceLoader(Class<T> serviceType) {
        this.serviceType = serviceType;
    }

    private void init() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String fileName = "META-INF/services/" + serviceType.getName();
        // 获取所有 jar 包类路径下指定服务文件 url
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(fileName);

        // 服务实现类的全限定名list
        List<String> serviceFullNames = new ArrayList<>();
        while (resources.hasMoreElements()) {
            // 读取文件
            URL url = resources.nextElement();
            URLConnection urlConnection = url.openConnection();
            urlConnection.setUseCaches(false);

            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            // 逐行读取文件，每一行是服务一个实现类
            while (true) {
                String serviceFullName = bufferedReader.readLine();
                if (serviceFullName == null) {
                    // 如果读取下一行为null，说明文件读取完毕
                    break;
                }
                if ("".equals(serviceFullName.trim())) {
                    // 跳过空行
                    continue;
                }
                serviceFullNames.add(serviceFullName);
            }
        }

        // 实例化所有服务
        for (String serviceFullName : serviceFullNames) {
            Class<?> clazz = Class.forName(serviceFullName);
            // 获取构造函数
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            // 设置构造函数可访问
            constructor.setAccessible(true);
            // 实例化类
            @SuppressWarnings("unchecked")
            T instance = (T) constructor.newInstance();
            serviceList.add(instance);
        }

    }

    public static <T> MiniServiceLoader<T> load(Class<T> serviceType) {
        MiniServiceLoader<T> miniServiceLoader = new MiniServiceLoader<>(serviceType);
        // 直接加载服务，不使用懒加载
        try {
            miniServiceLoader.init();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("mini service loader 初始化服务 " + serviceType.getName() + " 失败", e);
        }
        return miniServiceLoader;
    }


    @Override
    public Iterator<T> iterator() {
        return serviceList.iterator();
    }
}
