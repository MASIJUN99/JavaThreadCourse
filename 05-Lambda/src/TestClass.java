// this is a usual way to do
public class TestClass {

    // method 2: create a static class which implement the interface
    static class TestImplement2 implements TestInterface {
        @Override
        public void lambda(int var) {
            System.out.println("Hello From Method 2!");
        }
    }

    public static void main(String[] args) {
        TestInterface test = new TestImplement1();
        test.lambda(1);

        test = new TestImplement2();
        test.lambda(1);

        // method 3: create a method inner class which implement the interface
        class TestImplement3 implements TestInterface {
            @Override
            public void lambda(int var) {
                System.out.println("Hello From Method 3!");
            }
        }

        test = new TestImplement3();
        test.lambda(1);

        // method 4: create an anonymous class
        test = new TestInterface() {
            @Override
            public void lambda(int var) {
                System.out.println("Hello From Method 4!");
            }
        };
        test.lambda(1);

        // method 5: using lambda
        test = (var) -> {
            System.out.println("Hello From Method 5!");
        };
        test.lambda(1);

        // method 6: using lambda without init
        TestInterface test2 = (var) -> {
            System.out.println("Hello From Method 6!");
        };
        test2.lambda(1);
    }

}

// function interface: only have single method in this interface!
interface TestInterface {
    void lambda(int var);
}

// method 1: to create a class which implement this interface
class TestImplement1 implements TestInterface {
    @Override
    public void lambda(int var) {
        System.out.println("Hello From Method 1!");
    }
}