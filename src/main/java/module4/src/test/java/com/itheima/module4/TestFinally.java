package com.itheima.module4;

import java.io.Closeable;
import java.io.IOException;

/*
    finally - 最终的

 */
public class TestFinally {
    public static void main(String[] args) {
        /*try {
            int i = 1 / 0;
            System.out.println(1);
        } catch (Exception e) {
            System.out.println(2);
        } finally {
            System.out.println(3);
        }*/

        /*Resource r = new Resource();
        try {
            System.out.println("使用资源");
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            r.close();
        }*/

        // try - with - resource
        try (Resource r = new Resource()) {
            System.out.println("使用资源");
//            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// 外部资源： 文件、数据库    close
class Resource implements Closeable {
    @Override
    public void close() {
        System.out.println("释放资源");
    }
}


