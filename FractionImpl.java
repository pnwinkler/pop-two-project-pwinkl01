// todo: all methods should have comments. Follow the instructions at the bottom of the instructions page
package fraction;

public class FractionImpl implements Fraction {
    private int numerator;
    private int denominator; // may not be zero. May be represented only internally as negative

    public FractionImpl(int numerator, int denominator) {
        simplify_fraction(numerator, denominator);
    }

    public FractionImpl(int wholeNumber) {
        simplify_fraction(wholeNumber, 1);
    }

    public FractionImpl(String fraction) {
        // fraction contains either whole number like "-3" or fraction like "8/-12"
        if (!fraction.contains("/")) {
            // it's a whole number
            int numr2 = Integer.parseInt(fraction);
            simplify_fraction(numr2, 1);
        } else {
            // it's already a fraction. Just simplify
            int[] numer_denom = fraction_string_to_ints(fraction);
            numer_denom = simplify_fraction(numer_denom[0], numer_denom[1]);
            this.numerator = numer_denom[0];
            this.denominator = numer_denom[1];
        }
    }

    private int[] fraction_string_to_ints(String str_fraction) {
        // take a string fraction then return its nom and denom
        // does not simplify
        // does not test if it is actually receiving a fraction
        int slash_index = str_fraction.indexOf("/");
        String s_numr = str_fraction.substring(0, slash_index);
        String s_denom = str_fraction.substring(slash_index + 1, str_fraction.length());
        int numr = Integer.parseInt(s_numr.replace(" ", ""));
        int denom = Integer.parseInt(s_denom.replace(" ", ""));

        int[] numer_denom2 = new int[2];
        numer_denom2[0] = numr;
        numer_denom2[1] = denom;
        return numer_denom2;
    }

    private int[] simplify_fraction(int numerator, int denominator) {
        // according to instructions, 8, -12 input should give -2/3 and not 2/-3
        // local_numer and local_denom so that this can be operate on other instances of class fraction
        int local_numer = numerator;
        int local_denom = denominator;

        if (denominator == 0) {
            throw new ArithmeticException("Arithmetic Exception: denominator equals 0");
        }
        // normalize numerator and denominator
        for (int i = Math.max(Math.abs(local_denom), Math.abs(local_numer)); i > 1; --i) {
            if ((local_denom % i == 0) && (local_numer % i == 0)) {
                local_numer /= i;
                local_denom /= i;
            }
        }

        // handle negatives: if both negative, axe both signs. if one, then make num neg
        if (local_numer < 0 && local_denom < 0) {
            local_numer *= -1;
            local_denom *= -1;
        } else if (local_numer < 0 ^ local_denom < 0) {
            // set num to negative. If denom was the negative, then positive it
            if (local_numer > 0) local_numer *= -1;
            if (local_denom > 0) local_denom *= -1;
        }
        int[] numer_denom3 = new int[2];
        numer_denom3[0] = local_numer;
        numer_denom3[1] = local_denom;
        return numer_denom3;
    }

    private int[] get_f_values(Fraction f) {
        // process object f's toString, extract its numerator and denom
        // then return those
        // accepts either whole number or fraction

        // test if fraction or whole number
        // assumes that toString returns whole numbers in form: "X" or "XX" and fractions as "X/Y"
        String fstr = f.toString();
        int[] f_num_denom = new int[2];
        if (fstr.contains("/")) {
            f_num_denom = fraction_string_to_ints(f.toString());
        }
        else {
            f_num_denom[0] = Integer.parseInt(f.toString());
            f_num_denom[1] = 1;
        }
        return f_num_denom;
    }

    @Override
    public Fraction add(Fraction f) {
        // Returns a new Fraction that is the sum of this and f:
        // a/b + c/d is (ad + bc)/bd
        int fc = get_f_values(f)[0];
        int fd = get_f_values(f)[1];
        int top = (this.numerator * fd) + (this.denominator * fc);
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction subtract(Fraction f) {
        // a/b - c/d is (ad - bc)/bd
        int fc = get_f_values(f)[0];
        int fd = get_f_values(f)[1];
        int top = (this.numerator * fd) - (this.denominator * fc);
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction multiply(Fraction f) {
        // (a/b) * (c/d) is (a*c)/(b*d)
        int fc = get_f_values(f)[0];
        int fd = get_f_values(f)[1];
        int top = this.numerator * fc;
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction divide(Fraction f) {
        // (a/b) / (c/d) is (a*d)/(b*c)
        int fc = get_f_values(f)[0];
        int fd = get_f_values(f)[1];
        int top = this.numerator * fd;
        int bot = this.denominator * fc;
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
        // assumed that if "o" arrives here, it's not a Fraction
        return false;
//        return super.equals(obj);
    }
    public boolean equals(Fraction o){
        // do the comparison
        int f_numr = get_f_values(o)[0];
        int f_denom = get_f_values(o)[1];
        f_numr = simplify_fraction(f_numr, f_denom)[0];
        f_denom = simplify_fraction(f_numr, f_denom)[1];

        return (Integer.compare(this.numerator, f_numr) == 0 &&
                Integer.compare(this.denominator, f_denom) == 0);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Fraction inverse() {
        // a/b --> b/a
        int orignumerator = this.numerator;
        this.numerator = this.denominator;
        this.denominator = orignumerator;
        return null;
    }

    @Override
    public int compareTo(Fraction o) {
        double this_value = this.numerator / this.denominator;

        int[] o_vals = get_f_values(o);
        double o_value = (double) o_vals[0] / (double) o_vals[1];
//        if ((Math.abs(this_value - o_value) <= 0.000001)) return 0;
//        else if (this_value > o_value) return 1;
//        else return -1;
        return Double.compare(this_value, o_value);
    }

    public int compareTo(int o) {
        double this_value = this.numerator / this.denominator;
//        if (this_value < o) return -1;
//        else if (this_value > o) return 1;
//        else return 0;
        return Double.compare(this_value, (double) o);
    }

    public int compareTo() {
        // neither Fraction nor int. Throw ClassCastException
        throw new ClassCastException("compareTo function given neither int nor Fraction object");
    }

    @Override
    public String toString() {
        //If the fraction represents a negative number, a minus sign should precede n
        if (this.denominator == 1) return Integer.toString(this.numerator);
        else return this.numerator + "/" + this.denominator;
    }
}