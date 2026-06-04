package org.example.javafeatures;

public class InheritanceDemo implements Left {
    @Override
    public void m1() {
        System.out.println("Method m1 implementation in InheritanceDemo");
    }

    @Override
    public void m4() {
        System.out.println("Method m4 implementation in InheritanceDemo");
    }

    public static void m3() {
        System.out.println("Method m3 implementation in InheritanceDemo");
    }

    public static void main(String[] args) {
        Left demo = new InheritanceDemo();
        demo.m1(); // Calls the implemented method
        demo.m2(); // Calls the default method ypu can also overwrite it
        demo.m4();
        Left.m3(); // Calls the static method from Left interface
        InheritanceDemo.m3();
    }
}
