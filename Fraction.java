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

    /**
     * Returns <pre>true</pre> if <pre>o</pre> is a <pre>fraction</pre> equal to <pre>this</pre>,
     * and <pre>false</pre> in all other cases.
     * @param o the object to compare this one to
     * @return whether the true fractions are equal
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns:
     * <ul>
     *     <li>A negative <pre>int</pre> if <pre>this</pre> is less than <pre>o</pre>.</li>
     *     <li>Zero if <pre>this</pre> is equal to <pre>o</pre>.</li>
     *     <li>A positive <pre>int</pre> if <pre>this</pre> is greater than <pre>o</pre>.</li>
     * </ul>
     *
     * @param f the fraction to compare <pre>this</pre> to
     * @return the result of the comparison
     */
    @Override
    int compareTo(Fraction f);
//    int compareTo(Fraction f);

    /**
     * Returns a <pre>String</pre> of the form <pre>n/d</pre>, where <pre>n</pre> is the
     * <em>numerator</em> and <pre>d</pre> is the <em>denominator</em>.
     * However, if <pre>d</pre> is <pre>1</pre>, just return <pre>n</pre> (as a <pre>String</pre>).
     *
     * The returned <pre>String</pre> should not contain any blanks.
     * If the fraction represents a negative number, a minus sign should precede <pre>n</pre>
     *
     * @return the string representation fo the fraction
     */
    @Override
    String toString();
}