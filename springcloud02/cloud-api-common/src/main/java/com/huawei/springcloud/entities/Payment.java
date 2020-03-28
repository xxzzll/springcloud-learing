package com.huawei.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

/**
 * @author xixi
 * @Description：
 * @create 2020/3/9
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;

    public static void main(String[] args) {
        Payment.testList();
    }

    public static void testList(){
//        List list = new ArrayList();
//        list.add("a");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");
//        list.add("b");

//        List list = new LinkedList();
//        list.add("a");
//        list.add("b");
//        System.out.println(list);
//
//        System.out.println("a".hashCode());
//        System.out.println("a".hashCode() >>> 16);
//        System.out.println("a".hashCode() ^ ("a".hashCode() >>> 16));
//
//
//        System.out.println((16 - 1) & ("a".hashCode() ^ ("a".hashCode() >>> 16))); // 结果也是0-15,跟"a".hashCode()%16结果一样
//        System.out.println("a".hashCode()%16);
//
//        int binCount = 0;; ;
//        System.out.println(++binCount);

//        Map map = new HashMap();
//        map.put("a", 1);
//        map.put("b", 2);
//        map.put("a", 2);

        Payment[] payment = new Payment[16];
        Payment[] payment2 = payment;

        payment2[0] = new Payment(1L, "aaa");
        System.out.println(payment[0]);
        System.out.println(payment2[0]);
    }
}
