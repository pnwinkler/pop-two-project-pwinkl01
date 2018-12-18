// todo: all methods should have comments. Follow the instructions at the bottom of the instructions page
// todo: see to it that fractions are immutable after creation
package fraction;

public class FractionImpl implements Fraction {
    private int numerator;
    private int denominator;

    public FractionImpl(int numerator, int denominator) {
        int[] result = simplify_fraction(numerator, denominator);
        this.numerator = result[0];
        this.denominator = result[1];
    }

    public FractionImpl(int wholeNumber) {
        this.numerator = wholeNumber;
        this.denominator = 1;
    }

    public FractionImpl(String fraction) {
        // fraction contains either whole number like "-3" or fraction like "8/-12"
        if (!fraction.contains("/")) {
            // it's a whole number
            this.numerator = Integer.parseInt(fraction);
            this.denominator = 1;
        } else {
            // it's already a fraction. Just simplify
            int[] num_denom = fraction_string_to_ints(fraction);
            num_denom = simplify_fraction(num_denom[0], num_denom[1]);
            this.numerator = num_denom[0];
            this.denominator = num_denom[1];
        }
    }

    private int[] fraction_string_to_ints(String str_fraction) {
        // take a string fraction then return its nom and denom
        // does not simplify
        // assumes it is receiving a fraction, not a wholenumber
        int slash_index = str_fraction.indexOf("/");
        String s_numr = str_fraction.substring(0, slash_index);
        String s_denom = str_fraction.substring(slash_index + 1);
        int numr = Integer.parseInt(s_numr.replace(" ", ""));
        int denom = Integer.parseInt(s_denom.replace(" ", ""));

        int[] numer_denom2 = new int[2];
        numer_denom2[0] = numr;
        numer_denom2[1] = denom;
        return numer_denom2;
    }

    private int[] simplify_fraction(int numerator, int denominator) {
        // interact with local_numer and local_denom so that
        // this function can operate on other instances of class fraction
        int local_numer = numerator;
        int local_denom = denominator;

        if (denominator == 0) {
            throw new ArithmeticException("Arithmetic Exception: denominator equals 0");
        }

        // normalize fraction
        for (int i = Math.max(Math.abs(local_denom), Math.abs(local_numer)); i > 1; --i) {
            if ((local_denom % i == 0) && (local_numer % i == 0)) {
                local_numer /= i;
                local_denom /= i;
            }
        }

        // handling negatives: if num and denom negative, axe both signs
        if (local_numer < 0 && local_denom < 0) {
            local_numer *= -1;
            local_denom *= -1;
        } else if (local_numer < 0 ^ local_denom < 0) {
            // handling negatives: If only one is negative, make numer negative and denom positive
            if (local_numer > 0) local_numer *= -1;
            if (local_denom < 0) local_denom *= -1;
        }
        int[] numer_denom3 = new int[2];
        numer_denom3[0] = local_numer;
        numer_denom3[1] = local_denom;
        return numer_denom3;
    }

    private <T> int[] get_f_values(T obj) {
        // return obj's numer, denom
        // accepts whole number or Fraction object or String

        String fstr = obj.toString();
        int[] f_num_denom = new int[2];
        if (fstr.contains("/")) {
            f_num_denom = fraction_string_to_ints(obj.toString());
        } else {
            f_num_denom[0] = Integer.parseInt(obj.toString());
            f_num_denom[1] = 1;
        }
        return f_num_denom;
    }

    @Override
    public Fraction add(Fraction f) {
        int fc = get_f_values(f)[0];
        int fd = get_f_values(f)[1];
        int top = (this.numerator * fd) + (this.denominator * fc);
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction subtract(Fraction f) {
        int fc = get_f_values(f)[0];
        int fd = get_f_values(f)[1];
        int top = (this.numerator * fd) - (this.denominator * fc);
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction multiply(Fraction f) {
        int top = this.numerator * get_f_values(f)[0];
        int bot = this.denominator * get_f_values(f)[1];
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction divide(Fraction f) {
        int bot = this.denominator * get_f_values(f)[0];
        int top = this.numerator * get_f_values(f)[1];
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction abs() {
        return new FractionImpl(Math.abs(this.numerator), Math.abs(this.denominator));
    }

    @Override
    public Fraction negate() {
        return new FractionImpl((this.numerator * -1), this.denominator);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Fraction) {
            String o_str = o.toString();
            int f_numr, f_denom;

            if (o_str.contains("/")) {
                int slash_index = o_str.indexOf("/");
                String s_numr = o_str.substring(0, slash_index);
                String s_denom = o_str.substring(slash_index + 1, o_str.length());
                f_numr = Integer.parseInt(s_numr.replace(" ", ""));
                f_denom = Integer.parseInt(s_denom.replace(" ", ""));
            } else {
                f_numr = Integer.parseInt(o_str);
                f_denom = 1;
            }

            return (Integer.compare(this.numerator, f_numr) == 0 &&
                    Integer.compare(this.denominator, f_denom) == 0);
        } else {
            return false;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Fraction inverse() {
        // a/b --> b/a
        if (this.numerator == 0 || this.denominator == 0) return new FractionImpl(0); // prevents Arithmetic Exceptions
        else {
            int orignumerator = this.numerator;
            this.numerator = this.denominator;
            this.denominator = orignumerator;
            return new FractionImpl(this.numerator, this.denominator);
        }
    }

    // problem: in original FractionImpl it's public int compareTo(Fraction o)
    // in original Fraction.java it's public int compareTo(Fraction o)
    // in coursework instructions it's public int compareTo(Object o)
    // todo: ask why that is / what to do.
    // for now, I'm following Fraction.java's instructions instead of webpage's
    // but that leaves me failing to throw ClassCastException as per webpage instructions
//    @Override
//    public int compareTo(Fraction o) {
//        if (o instanceof Fraction || o instanceof Integer){
//            double this_value = (double) this.numerator / (double) this.denominator;
//            int[] o_vals = get_f_values(o);
//            double o_value = (double) o_vals[0] / (double) o_vals[1];
//            return Double.compare(this_value, o_value);
//        }
//        else throw new ClassCastException("compareTo function received neither fraction nor int");
//    }

    @Override
    public int compareTo(Fraction o) {
        double this_value = (double) this.numerator / (double) this.denominator;
        int[] o_vals = get_f_values(o);
        double o_value = (double) o_vals[0] / (double) o_vals[1];
        return Double.compare(this_value, o_value);
    }


    @Override
    public String toString() {
        if (this.denominator == 1) return Integer.toString(this.numerator);
        else return this.numerator + "/" + this.denominator;
    }
}