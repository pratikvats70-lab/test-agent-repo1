package org.example.javafeatures;

public interface Right {
    void m1();
    default void m2() {
        System.out.println("Default method in Right interface");
    }
    static void m3() {
        System.out.println("Static method in Right interface");
    }
}
