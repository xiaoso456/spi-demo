package io.github.xiaoso456;

import io.github.xiaoso456.api.Print;
import io.github.xiaoso456.utils.MiniServiceLoader;

import java.util.Iterator;


public class Main {

    public static void main(String[] args) {
        MiniServiceLoader<Print> printServiceLoader = MiniServiceLoader.load(Print.class);
        Iterator<Print> printIterator = printServiceLoader.iterator();
        while(printIterator.hasNext()){
            Print printService = printIterator.next();
            printService.println("打印");
        }


    }
}