import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoStoreTest {

    private final double DELTA = .001;
    private Statement statement;
    private Movie newRelease1;
    private Movie newRelease2;
    private Movie childrens;
    private Movie regular1;
    private Movie regular2;
    private Movie release3;

    @Before
    public void setUp() {
        statement = new Statement("Customer");
        newRelease1 = new Movie("New Release 1", Movie.NEW_RELEASE);
        newRelease2 = new Movie("New Release 2", Movie.NEW_RELEASE);
        childrens = new Movie("Childrens", Movie.CHILDRENS);
        regular1 = new Movie("Regular 1", Movie.REGULAR);
        regular2 = new Movie("Regular 2", Movie.REGULAR);
        release3 = new Movie("Regular 3", Movie.REGULAR);
    }

    @Test
    public void testSingleNewReleaseStatementTotals() {
        statement.addRental(new Rental(newRelease1, 3));
        statement.generate();
        assertEquals(9.0, statement.getTotal(), DELTA);
        assertEquals(2, statement.getFrequentRenterPoints());

    }

    @Test
    public void testDualNewReleaseStatementTotals() {
        statement.addRental(new Rental(newRelease1, 3));
        statement.addRental(new Rental(newRelease2, 3));
        statement.generate();
        assertEquals(18.0, statement.getTotal(), DELTA);
        assertEquals(4, statement.getFrequentRenterPoints());
    }

    @Test
    public void testSingleChildrensStatementTotals() {
        statement.addRental(new Rental(childrens, 3));
        statement.generate();
        assertEquals(1.5, statement.getTotal(), DELTA);
        assertEquals(1, statement.getFrequentRenterPoints());

    }

    @Test
    public void testMultipleRegularStatementTotals() {
        statement.addRental(new Rental(regular1, 1));
        statement.addRental(new Rental(regular2, 2));
        statement.addRental(new Rental(release3, 3));
        statement.generate();
        assertEquals(7.5, statement.getTotal(), DELTA);
        assertEquals(3, statement.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementFormat() {
        statement.addRental(new Rental(regular1, 1));
        statement.addRental(new Rental(regular2, 2));
        statement.addRental(new Rental(release3, 3));
        assertEquals("Rental Record for Customer\n" +
                        "\tRegular 1\t2.0\n" +
                        "\tRegular 2\t2.0\n" +
                        "\tRegular 3\t3.5\n" +
                        "You owed 7.5\n" +
                        "You earned 3 frequent renter points\n",
                statement.generate());
    }
}