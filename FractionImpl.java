package fraction;

public class FractionImpl implements Fraction {
    private int numerator;
    private int denominator;

    /** constructor: creates fraction from numerator, denominator */
    public FractionImpl(int numerator, int denominator) {
        int[] result = simplify_fraction(numerator, denominator);
        this.numerator = result[0];
        this.denominator = result[1];
    }

    /** constructor: creates fraction from wholenumber */
    public FractionImpl(int wholeNumber) {
        this.numerator = wholeNumber;
        this.denominator = 1;
    }

    /** constructor: creates fraction from strings such as "4" or "-6/2" */
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

    /**
     * for internal use only (not for users).
     * takes a string fraction then return its numerator and denominator
     * does not simplify
     * assumes it is receiving a fraction, not a wholenumber */
    private int[] fraction_string_to_ints(String str_fraction) {
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

    /**
     * internal use only
     * simplifies fraction
     * accepts int numerator and int denominator
     * returns array containing the simplified int numerator and int denominator
     * throws ArithmeticException if denominator equals 0 */
    private int[] simplify_fraction(int numerator, int denominator) {
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

        // handling negatives: if num and denom negative, remove both signs
        if (local_numer < 0 && local_denom < 0) {
            local_numer *= -1;
            local_denom *= -1;
        }

        // handling negatives: If only one is negative, make numer negative and denom positive
        else if (local_numer < 0 ^ local_denom < 0) {
            if (local_numer > 0) local_numer *= -1;
            if (local_denom < 0) local_denom *= -1;
        }
        
        int[] numer_denom3 = new int[2];
        numer_denom3[0] = local_numer;
        numer_denom3[1] = local_denom;
        return numer_denom3;
    }

    /**
     * internal use
     * accepts whole number or Fraction object or String
     * returns object F's numerator and denominator
     */
    private <F> int[] get_f_values(F obj) {
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

    /**
     * accepts fraction f
     * adds current fraction to fraction f
     * returns result as a new fraction
     */
    @Override
    public Fraction add(Fraction f) {
        int fc = get_f_values(f)[0];
        int fd = get_f_values(f)[1];
        int top = (this.numerator * fd) + (this.denominator * fc);
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    /**
     * accepts fraction f
     * subtracts fraction f from current fraction
     * returns result as a new fraction
     */
    @Override
    public Fraction subtract(Fraction f) {
        int fc = get_f_values(f)[0];
        int fd = get_f_values(f)[1];
        int top = (this.numerator * fd) - (this.denominator * fc);
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    /**
     * accepts fraction f
     * multiplies current fraction with fraction f
     * returns result as a new fraction
     */
    @Override
    public Fraction multiply(Fraction f) {
        int top = this.numerator * get_f_values(f)[0];
        int bot = this.denominator * get_f_values(f)[1];
        return new FractionImpl(top, bot);
    }

    /**
     * accepts fraction f
     * divides current fraction by fraction f
     * returns result as a new fraction
     */
    @Override
    public Fraction divide(Fraction f) {
        int bot = this.denominator * get_f_values(f)[0];
        int top = this.numerator * get_f_values(f)[1];
        return new FractionImpl(top, bot);
    }

    /**
     * returns absolute value of current fraction
     * takes no arguments
     */
    @Override
    public Fraction abs() {
        return new FractionImpl(Math.abs(this.numerator), Math.abs(this.denominator));
    }

    /**
     * returns negated value of current fraction
     * takes no arguments
     */
    @Override
    public Fraction negate() {
        return new FractionImpl((this.numerator * -1), this.denominator);
    }

    /**
     * returns hashCode value of current fraction
     * takes no arguments
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * compares the value current fraction to Object o
     * returns true if o is a Fraction object of equal value to current fraction
     * otherwise, returns false
     * */
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

    /** returns the inverse of the current fraction */
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

    /** compares this fraction to Fraction o
     * returns 1 if this fraction is greater than Fraction o, 0 if equal, -1 if lesser
     */
    @Override
    public int compareTo(Fraction o) {
        double this_value = (double) this.numerator / (double) this.denominator;
        int[] o_vals = get_f_values(o);
        double o_value = (double) o_vals[0] / (double) o_vals[1];
        return Double.compare(this_value, o_value);
    }

    /** returns the String object representation of this fraction */
    @Override
    public String toString() {
        if (this.denominator == 1) return Integer.toString(this.numerator);
        else return this.numerator + "/" + this.denominator;
    }
}