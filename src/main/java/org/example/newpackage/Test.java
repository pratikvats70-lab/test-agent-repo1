package org.example.newpackage;

import org.example.javafeatures.LambdaInterface;

/*public class Test implements B {
    @Override
    public void m1() {

    }
    // since B is protected it cant be accessed outside the package, so we get an error when we try to implement it in this class
}*/
public class Test {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        LambdaInterface obj = (name)-> System.out.println("hi " + name);
        obj.m1("Pratik");
    }
}
