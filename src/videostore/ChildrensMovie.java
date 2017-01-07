package videostore;

class ChildrensMovie extends Movie {
    ChildrensMovie(String title) {
        super(title);
    }

    @Override
    double determineRentalAmount(int daysRented) {
        double rentalAmount = 1.5;
        if (daysRented > 3)
            rentalAmount += (daysRented - 3) * 1.5;
        return rentalAmount;
    }

    @Override
    int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
