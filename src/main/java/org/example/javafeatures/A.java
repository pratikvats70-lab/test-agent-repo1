package org.example.javafeatures;

interface B {
        int DB_USERNAME = 10;
        void m1();
}
public class A implements B {
    public static void main(String[] args) {
        B bImpl = new A();
        bImpl.m1();
    }
    @Override
    public void m1() {
        System.out.println("Method B implementation");
    }
}
