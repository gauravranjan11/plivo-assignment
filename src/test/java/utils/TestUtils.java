package utils;

public class TestUtils {

    public static class TestObject {
        String  testVar1;
        Integer testVar2;

        public TestObject(String testVar1, Integer testVar2) {
            this.testVar1 = testVar1;
            this.testVar2 = testVar2;
        }

        @Override
        public boolean equals(Object obj) {
            TestObject testObject = (TestObject) obj;
            if (this.testVar1.equals(testObject.testVar1) && (this.testVar2.equals(testObject.testVar2))) {
                return true;
            }
            return false;
        }
    }
}
