package fraction;
/**
 * Representing fractions of the form numerator and denominator
 * The object should be immutable.
 */
interface Fraction extends Comparable<Fraction> {
    Fraction add(Fraction f);

    Fraction subtract(Fraction f);

    Fraction multiply(Fraction f);

    Fraction divide(Fraction f);

    Fraction abs();

    Fraction negate();

    Fraction inverse();

    @Override
    boolean equals(Object o);

    @Override
    int compareTo(Fraction f);

    @Override
    String toString();
}