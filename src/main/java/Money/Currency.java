package Money;

public enum Currency {
    Rupees(1), Dollars(74.85);
    public double inRupees;
    Currency(double inRupees) {
        this.inRupees = inRupees;
    }
}