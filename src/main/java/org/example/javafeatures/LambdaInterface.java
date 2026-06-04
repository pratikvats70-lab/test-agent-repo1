package org.example.javafeatures;

@FunctionalInterface
public interface LambdaInterface {
    void m1(String name);
     default void m3() {
         System.out.println("Default method in LambdaInterface");
     }
    default void m4() {
        System.out.println("Default method in LambdaInterface");
    }
     static void m5() {
         System.out.println("Static method in LambdaInterface");
     }
    static void m6() {
        System.out.println("Static method in LambdaInterface");
    }

}
