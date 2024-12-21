package org.example.basics;

public class SequentialExecution {
    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Demo1: " +i);
        }
    }

    private static void demo2() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Demo2: " +i);
        }
    }
}
