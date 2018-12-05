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
        // contains either whole number like "-3" or fraction like "8/-12"
        if (!fraction.contains("/")) {
            // it's a whole number
            numerator = Integer.parseInt(fraction);
            simplify_fraction(numerator, 1);
        } else {
            // it's already a fraction. Just simplify
            int slash_index = fraction.indexOf("/");
            String numr = fraction.substring(0, slash_index);
            String denomr = fraction.substring(slash_index + 1, fraction.length());
            numr = numr.replace(" ", "");
            denomr = denomr.replace(" ", "");
            numerator = Integer.parseInt(numr);
            denominator = Integer.parseInt(denomr);

            simplify_fraction(numerator, denominator);
        }
    }

    private void simplify_fraction(int numerator, int denominator) {
        // according to instructions, 8, -12 input should give -2/3 and not 2/-3
        if (denominator == 0) {
            throw new ArithmeticException("Arithmetic Exception: denominator equals 0");
        }
        // normalize numerator and denominator
        for (int i = Math.max(Math.abs(denominator), Math.abs(numerator)); i > 1; --i) {
            if ((denominator % i == 0) && (numerator % i == 0)) {
                this.denominator /= i;
                this.numerator /= i;
            }
        }

        // handle negatives: if both negative, axe both signs. if one, then make num neg
        if (this.numerator < 0 && this.denominator < 0){
            this.numerator *= -1;
            this.denominator *= -1;
        }
        else if (this.numerator < 0 ^ this.denominator < 0){
            // set num to negative. If denom was the negative, then positive it
            if (this.numerator > 0) this.numerator *= -1;
            if (this.denominator > 0) this.denominator *= -1;
        }
    }

    @Override
    public Fraction add(Fraction f) {
        // Returns a new Fraction that is the sum of this and f:
        // a/b + c/d is (ad + bc)/bd
        int fc = f.numerator;
        int fd = f.denominator;
        int top = (this.numerator * fd) + (this.denominator * fc);
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction subtract(Fraction f) {
        // a/b - c/d is (ad - bc)/bd
        int fc = f.numerator;
        int fd = f.denominator;
        int top = (this.numerator * fd) - (this.denominator * fc);
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction multiply(Fraction f) {
        // (a/b) * (c/d) is (a*c)/(b*d)
        int fc = f.numerator;
        int fd = f.denominator;
        int top = this.numerator * fc;
        int bot = this.denominator * fd;
        return new FractionImpl(top, bot);
    }

    @Override
    public Fraction divide(Fraction f) {
        // (a/b) / (c/d) is (a*d)/(b*c)
        int fc = f.numerator;
        int fd = f.denominator;
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
        return new FractionImpl((this.numerator*-1), this.denominator);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // todo: simplify then compare to current object?
        return super.equals(obj);
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
        double o_value = o.numerator / o.denominator;
        if ((Math.abs(this_value - o_value) <= 0.000001)) return 0;
        else if (this_value > o_value) return 1;
        else return -1;
    }
    public int compareTo(int o) {
        double this_value = this.numerator / this.denominator;
        if (this_value < 0) return -1;
        else if (this_value > 0) return 1;
        else return 0;
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