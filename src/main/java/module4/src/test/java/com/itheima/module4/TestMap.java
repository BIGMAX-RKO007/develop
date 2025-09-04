package com.itheima.module4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 核心类库学习 - Map
 *  1. key(键) -> value(值)  键值对 entry， key 要唯一
 * 映射关系
 *  2. 两个泛型，分别用来限制 key 和 value 的类型
 *  3. Map.of 创建的是不可变的 Map， new HashMap() 创建的是可变的 Map集合
 *  4. Map 的增、删、改、查操作
 *  5. Map 的遍历
 */
public class TestMap {

    // find3("aaa")
    public static String find3(String key) {
        /*Map<String, String> map = Map.of(
                "bright", "小明",
                "white", "小白",
                "black", "小黑"
        );*/

        Map<String, String> map = new HashMap<>();
        map.put("bright", "小明");
        map.put("white", "小白");
        map.put("black", "小黑");  // 可以新增(如果key在map中还不存在)

        map.put("black", "小二黑"); // 也可以修改(如果key在map中存在)

        map.remove("black"); // 根据 key 删除

        map.entrySet(); // 获取所有的键值对集合, 可以用增强for循环遍历
        /*
            for(临时变量的类型的 Map.Entry         : 要遍历的集合)
            for(Map.Entry<String,String> e : map.entrySet()) {
            }
         */
        for (Map.Entry<String, String> e : map.entrySet()) {
            System.out.println("key " + e.getKey() + " value " + e.getValue());
        }

        return map.get(key); // 根据 key 查询 value
    }

    public static void main(String[] args) {
        System.out.println(find3("black"));
    }


    // find1(2)
    public static String find1(int key) { // key是唯一的代号
        String[] array = new String[]{"小明", "小白", "小黑"};
        if(key < 0 || key >= array.length) {
            return null;
        }
        return array[key];
    }

    // find1("小黑")
    /*public static String find1(String value) {
        String[] array = new String[]{"小明", "小白", "小黑"};
        for (String s : array) { // s 代表是数组某个元素
            if ( s.equals(value) ) { // 如果两个 String 内容相同， equals 返回 true
                return s;
            }
        }
        return null;
    }*/

    public static String find2(String value) {
        List<String> list = List.of("小明", "小白", "小黑");
        for (String s : list) {
            if (s.equals(value)) {
                return s;
            }
        }
        return null;
    }
}
