package io.github.xiaoso456.api;

public class PrintSystemImpl implements Print{
    @Override
    public void println(String message) {
        System.out.println(message);
    }
}
