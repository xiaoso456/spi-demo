package io.github.xiaoso456.api;

public class PrintFileImpl implements Print {
    @Override
    public void println(String message) {
        String template =
        """
        "%s"已经写入到文件
        """;

        System.out.println(String.format(template,message));
    }
}
