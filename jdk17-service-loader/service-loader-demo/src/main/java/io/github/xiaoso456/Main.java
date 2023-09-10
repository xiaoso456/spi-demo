package io.github.xiaoso456;

import io.github.xiaoso456.api.Print;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<Print> printServiceLoader = ServiceLoader.load(Print.class);
        Iterator<Print> printIterator = printServiceLoader.iterator();
        while(printIterator.hasNext()){
            Print printService = printIterator.next();
            printService.println("打印");
        }

    }
}