package org.example.javafeatures;

public interface Left {
    void m1();
    default void m2() {
        System.out.println("Default method in Left interface");
    }
    static void m3() {
        System.out.println("Static method in Left interface");
    }
    void m4();
}
