package io.github.xiaoso456;

import java.util.Objects;

public class MyPlugin {

    private String name;
    private String version;

    static {
        System.out.println("加载插件成功");
    }

    public MyPlugin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyPlugin myPlugin)) return false;

        if (!Objects.equals(name, myPlugin.name)) return false;
        return Objects.equals(version, myPlugin.version);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyPlugin{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
