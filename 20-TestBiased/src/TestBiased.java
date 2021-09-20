import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

public class TestBiased {
    // Check Mark Word!
    // import a lib: jol-core:0.16
    public static void main(String[] args) throws InterruptedException {
        // without -XX:BiasedLockingStartupDelay=0
        System.out.println(ClassLayout.parseInstance(new Test()).toPrintable());
        Thread.sleep(4000);
        System.out.println(ClassLayout.parseInstance(new Test()).toPrintable());
    }
}


class Test {

}