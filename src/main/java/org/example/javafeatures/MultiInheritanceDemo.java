package org.example.javafeatures;

public class MultiInheritanceDemo implements Left, Right {
    @Override
    public void m1() {
        System.out.println("Method m1 implementation in MultiInheritanceDemo");
    }

    @Override
    public void m2() {
        System.out.println("Overridden method m2 in MultiInheritanceDemo to resolve conflict");
//        Right.super.m2();
    }

    @Override
    public void m4() {
        System.out.println("Method m4 implementation in MultiInheritanceDemo");
    }

    public static void main(String[] args) {
        MultiInheritanceDemo demo = new MultiInheritanceDemo();
        demo.m1(); // Calls the implemented method
        demo.m2(); // Calls the overridden default method
        demo.m4();
        Left.m3(); // Calls the static method from Left interface
        Right.m3(); // Calls the static method from Right interface
        demo.m2();
    }
}
