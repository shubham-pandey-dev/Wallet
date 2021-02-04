package wallet;

public enum Currency {
    Rupees(1), Dollars(74.85);
    double inRupees;
    Currency(double inRupees) {
        this.inRupees = inRupees;
    }
}