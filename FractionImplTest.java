package fraction;

import org.junit.Test;

import static org.junit.Assert.*;

public class FractionImplTest {
    private String add = "add";
    private String subtract = "subtract";
    private String multiply = "multiply";
    private String divide = "divide";
    private String abs = "abs";
    private String compareto = "compareto";
    private String inverse = "inverse";
    private String negate = "negate";

    private String create_two_fractions_return_operation(String f1s, String f2s, String operation){
        Fraction f1 = new FractionImpl(f1s);
        Fraction f2 = new FractionImpl(f2s);
        switch (operation){
            case "add":
                return f1.add(f2).toString();
            case "subtract":
                return f1.subtract(f2).toString();
            case "multiply":
                return f1.multiply(f2).toString();
            case "divide":
                return f1.divide(f2).toString();
            case "equals":
                return Boolean.toString(f1.equals(f2));
            case "compareto":
                return Integer.toString(f1.compareTo(f2));
            default:
                System.out.println("INVALID OPERATION GIVEN TO create_two_fractions_return_operation");
                return "";
        }
    }
    private String create_fraction_test_operation(String f1s, String operation){
        Fraction f1 = new FractionImpl(f1s);
        switch (operation){
            case "abs":
                return f1.abs().toString();
            case "negate":
                return f1.negate().toString();
            case "inverse":
                return f1.inverse().toString();
            default:
                return "";
        }
    }

    @org.junit.Test
    public void constructions() throws Exception {
        assertEquals(new FractionImpl("-8/12 ").toString(), "-2/3");
        assertEquals(new FractionImpl(" 8 / -12").toString(), "-2/3");
        assertEquals(new FractionImpl("-8/-12 ").toString(), "2/3");
        assertEquals(new FractionImpl(" 8 / -12 ").toString(), "-2/3");
        assertEquals(new FractionImpl(" 8/ -12 ").toString(), "-2/3");
        assertEquals(new FractionImpl("8 /-12").toString(), "-2/3");
        assertEquals(new FractionImpl("8/-12 ").toString(), "-2/3");
        assertEquals(new FractionImpl("8 / -12").toString(), "-2/3");
        assertEquals(new FractionImpl("8 / -12").toString(), "-2/3");
        assertEquals(new FractionImpl("8/-12").toString(), "-2/3");
        assertEquals(new FractionImpl("-5 /6 ").toString(), "-5/6");
        assertEquals(new FractionImpl("8/-1 ").toString(), "-8");
        assertEquals(new FractionImpl("-6").toString(), "-6");
        assertEquals(new FractionImpl("6").toString(), "6");
        assertEquals(new FractionImpl(8, -1).toString(), "-8");
        assertEquals(new FractionImpl(8, -12).toString(), "-2/3");
        assertEquals(new FractionImpl("0").toString(), "0");
    }
    @Test(expected =  ArithmeticException.class)
    public void testConstructionsArithmeticExceptionThrown(){
        assertEquals(new FractionImpl("1/0").toString(), "0");
    }


    @org.junit.Test
    public void add() throws Exception {
        assertEquals(create_two_fractions_return_operation("1/3", "1/3", add), "2/3");
        assertEquals(create_two_fractions_return_operation("1/2", "1/2", add), "1");
        assertEquals(create_two_fractions_return_operation("-1/2", "1/2", add), "0");
        assertEquals(create_two_fractions_return_operation("-1/2", "-1/2", add), "-1");
        assertEquals(create_two_fractions_return_operation("-4/3", "1/3", add), "-1");
        assertEquals(create_two_fractions_return_operation("4/3", "-1/3", add), "1");
        assertEquals(create_two_fractions_return_operation("3", "-3", add), "0");
        assertEquals(create_two_fractions_return_operation("6", "-1/3", add), "17/3");
        assertEquals(create_two_fractions_return_operation("3/4", "1/16", add), "13/16");
        assertEquals(create_two_fractions_return_operation("6", "-1/3", add), "17/3");
        assertEquals(create_two_fractions_return_operation("-12","-4/1", add), "-16");
        assertEquals(create_two_fractions_return_operation("3/4","1/9", add), "31/36");
        assertEquals(create_two_fractions_return_operation("3/4","1/16", add), "13/16");
        assertEquals(create_two_fractions_return_operation("3/4","0", add), "3/4");
        assertEquals(create_two_fractions_return_operation("0","0", add), "0");
    }
    @Test(expected =  ArithmeticException.class)
    public void testAddArithmeticExceptionThrown(){
        assertEquals(create_two_fractions_return_operation("1/0","0", add), "0");;
    }


    @org.junit.Test
    public void subtract() throws Exception {
        assertEquals(create_two_fractions_return_operation("1/3", "1/3", subtract), "0");
        assertEquals(create_two_fractions_return_operation("1/2", "1/2", subtract), "0");
        assertEquals(create_two_fractions_return_operation("-1/2", "1/2", subtract), "-1");
        assertEquals(create_two_fractions_return_operation("-1/2", "-1/2", subtract), "0");
        assertEquals(create_two_fractions_return_operation("-4/3", "1/3", subtract), "-5/3");
        assertEquals(create_two_fractions_return_operation("4/3", "-1/3", subtract), "5/3");
        assertEquals(create_two_fractions_return_operation("3", "-3", subtract), "6");
        assertEquals(create_two_fractions_return_operation("6", "-1/3", subtract), "19/3");
        assertEquals(create_two_fractions_return_operation("3/4", "1/16", subtract), "11/16");
        assertEquals(create_two_fractions_return_operation("6", "-1/3", subtract), "19/3");
        assertEquals(create_two_fractions_return_operation("-12", "-4/1", subtract), "-8");
        assertEquals(create_two_fractions_return_operation("3/4", "1/9", subtract), "23/36");
        assertEquals(create_two_fractions_return_operation("3/4", "1/16", subtract), "11/16");
    }

    @org.junit.Test
    public void multiply() throws Exception {
        assertEquals(create_two_fractions_return_operation("1/3", "1/3", multiply), "1/9");
        assertEquals(create_two_fractions_return_operation("1/2", "1/2", multiply), "1/4");
        assertEquals(create_two_fractions_return_operation("-1/2", "1/2", multiply), "-1/4");
        assertEquals(create_two_fractions_return_operation("-1/2", "-1/2", multiply), "1/4");
        assertEquals(create_two_fractions_return_operation("-4/3", "1/3", multiply), "-4/9");
        assertEquals(create_two_fractions_return_operation("4/3", "-1/3", multiply), "-4/9");
        assertEquals(create_two_fractions_return_operation("3", "-3", multiply), "-9");
        assertEquals(create_two_fractions_return_operation("6", "-1/3", multiply), "-2");
        assertEquals(create_two_fractions_return_operation("3/4", "1/16", multiply), "3/64");
        assertEquals(create_two_fractions_return_operation("6", "-1/3", multiply), "-2");
        assertEquals(create_two_fractions_return_operation("-12", "-4/1", multiply), "48");
        assertEquals(create_two_fractions_return_operation("3/4", "1/9", multiply), "1/12");
        assertEquals(create_two_fractions_return_operation("3/4", "1/16", multiply), "3/64");
        assertEquals(create_two_fractions_return_operation("3/4", "0", multiply), "0");
        assertEquals(create_two_fractions_return_operation("3/4", "-0", multiply), "0");
    }

    @org.junit.Test
    public void divide() throws Exception {
        assertEquals(create_two_fractions_return_operation("1/3", "1/3", divide), "1");
        assertEquals(create_two_fractions_return_operation("1/2", "1/2", divide), "1");
        assertEquals(create_two_fractions_return_operation("-1/2", "1/2", divide), "-1");
        assertEquals(create_two_fractions_return_operation("-1/2", "-1/2", divide), "1");
        assertEquals(create_two_fractions_return_operation("-4/3", "1/3", divide), "-4");
        assertEquals(create_two_fractions_return_operation("4/3", "-1/3", divide), "-4");
        assertEquals(create_two_fractions_return_operation("3", "-3", divide), "-1");
        assertEquals(create_two_fractions_return_operation("6", "-1/3", divide), "-18");
        assertEquals(create_two_fractions_return_operation("3/4", "1/16", divide), "12");
        assertEquals(create_two_fractions_return_operation("6", "-1/3", divide), "-18");
        assertEquals(create_two_fractions_return_operation("-12", "-4/1", divide), "3");
        assertEquals(create_two_fractions_return_operation("3/4", "1/9", divide), "27/4");
        assertEquals(create_two_fractions_return_operation("3/4", "1/16", divide), "12");
        assertEquals(create_two_fractions_return_operation("3/4", "-3", divide), "-1/4");
    }
    @Test(expected =  ArithmeticException.class)
    public void testDivideArithmeticExceptionThrown(){
        assertEquals(create_two_fractions_return_operation("3/4", "-0", divide), "12");
    }

    @org.junit.Test
    public void abs() throws Exception {
        assertEquals(create_fraction_test_operation("-3/4", abs), "3/4");
        assertEquals(create_fraction_test_operation("3/-4", abs), "3/4");
        assertEquals(create_fraction_test_operation("-4", abs), "4");
        assertEquals(create_fraction_test_operation("16", abs), "16");
        assertEquals(create_fraction_test_operation("0", abs), "0");
        assertEquals(create_fraction_test_operation("-0", abs), "0");
        assertEquals(create_fraction_test_operation("-150", abs), "150");
        assertEquals(create_fraction_test_operation("-150/1", abs), "150");
        assertEquals(create_fraction_test_operation("-150/2", abs), "75");
    }

    @org.junit.Test
    public void negate() throws Exception {
        assertEquals(create_fraction_test_operation("12/6", negate), "-2");
        assertEquals(create_fraction_test_operation("-6/18", negate), "1/3");
        assertEquals(create_fraction_test_operation("-0", negate), "0");
        assertEquals(create_fraction_test_operation("-52", negate), "52");
        assertEquals(create_fraction_test_operation("1/6", negate), "-1/6");
        assertEquals(create_fraction_test_operation("-6/2", negate), "3");
//        assertEquals(create_fraction_test_operation("0", negate), "-0"); // inconsequential
    }

    @org.junit.Test
    public void equals() throws Exception {

    }

    @org.junit.Test
    public void equals1() throws Exception {

    }

//    @org.junit.Test
//    public void clone() throws Exception {
//
//    }

    @org.junit.Test
    public void inverse() throws Exception {

    }

    @org.junit.Test
    public void compareTo() throws Exception {

    }

    @org.junit.Test
    public void compareTo1() throws Exception {

    }

    @org.junit.Test
    public void compareTo2() throws Exception {

    }

//    @org.junit.Test
//    public void toString() throws Exception {
//
//    }

}