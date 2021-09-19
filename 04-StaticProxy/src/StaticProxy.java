import java.awt.geom.AffineTransform;
import java.lang.annotation.Target;

public class StaticProxy {

    public static void main(String[] args) {

        new ProxyClass(new RealClass()).marry();
        // compare with Thread
        new Thread(() -> System.out.println("print something")).start();
    }

}


interface InterfaceClass {
    void marry();
}

// Real role
class RealClass implements InterfaceClass {

    @Override
    public void marry() {
        System.out.println("invoke method");
    }
}

// Proxy role
class ProxyClass implements InterfaceClass {

    private InterfaceClass target;

    public ProxyClass(InterfaceClass target) {
        this.target = target;
    }

    @Override
    public void marry() {
        before();
        this.target.marry();
        after();
    }

    private void before() {
        System.out.println("before proxy");
    }

    private void after() {
        System.out.println("after proxy");
    }
}