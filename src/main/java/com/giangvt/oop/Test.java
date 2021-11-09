package com.giangvt.oop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface Hello {
    public String sayHello(String name);
}

public class Test {
    public static void main(String[] args) {
//        Hello s = name -> "Hello, " + name;
//        System.out.println(s.sayHello("Giang"));
        List<Integer> number = Arrays.asList(2, 3, 4, 5);
        number.stream().map(x->x*x).collect(Collectors.toList()).forEach(y->System.out.println(y));

    }
}
