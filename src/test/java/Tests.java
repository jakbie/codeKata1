import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void test1_1() {
        Assertions.assertEquals(Task.add(""), 0);
    }

    @Test
    public void test1_2() {
        Assertions.assertEquals(Task.add("1"), 1);
    }

    @Test
    public void test1_3() {
        Assertions.assertEquals(Task.add("1,2"), 3);
    }

    @Test
    public void test1_4() {
        Assertions.assertEquals(Task.add("1,2,3"), 6);
    }


    @Test
    public void test2() {
        Assertions.assertEquals(Task.add("1,2,3,4,5,6,7,8,9"), 45);
    }


    @Test
    public void test3() {
        Assertions.assertEquals(Task.add("1\n2,3\n4,5"), 15);
    }


    @Test
    public void test4_1() {
        Assertions.assertEquals(Task.add("//;\n1;2;"), 3);
    }

    @Test
    public void test4_2() {
        Assertions.assertEquals(Task.add("//&\n1&2&3&14"), 20);
    }


    @Test
    public void test5_1() {
        Exception exception = Assertions.assertThrows(NegativesNotAllowedException.class, () -> {
            Task.add("1,2,-3,5,-4");
        });

        String expectedMessage = "Negatives not allowed: -3, -4";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void test5_2() {
        Exception exception = Assertions.assertThrows(NegativesNotAllowedException.class, () -> {
            Task.add("//@\n1@2@-3@5@-1");
        });

        String expectedMessage = "Negatives not allowed: -3, -1";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void test5_3() {
        Exception exception = Assertions.assertThrows(NegativesNotAllowedException.class, () -> {
            Task.add("//[@@][$]\n1@@-2$999$1001");
        });

        String expectedMessage = "Negatives not allowed: -2";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void test6_1() {
        Assertions.assertEquals(Task.add("1,2,1000,1005"), 1003);
    }

    @Test
    public void test6_2() {
        Assertions.assertEquals(Task.add("//@\n1@2@1000@1005"), 1003);
    }

    @Test
    public void test6_3() {
        Assertions.assertEquals(Task.add("//[@@][$]\n1@@2$999$1001"), 1002);
    }


    @Test
    public void test7_1() {
        Assertions.assertEquals(Task.add("//[***]\n1***2***3"), 6);
    }

    @Test
    public void test7_2() {
        Assertions.assertEquals(Task.add("//[***#]\n1***#2***#3"), 6);
    }


    @Test
    public void test8_1() {
        Assertions.assertEquals(Task.add("//[*][%]\n1*2%3"), 6);
    }

    @Test
    public void test8_2() {
        Assertions.assertEquals(Task.add("//[*@][%]\n1*@2%3"), 6);
    }

}