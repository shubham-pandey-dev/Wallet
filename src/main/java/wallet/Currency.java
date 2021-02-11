package wallet;

public enum Currency {
    RUPEE(1), DOLLAR(74.85);
    public double inRupees;

    Currency(double inRupees) {
        this.inRupees = inRupees;
    }
}