package api.enums;

public enum Portion {
    LARGE(800),
    MEDIUM(400),
    SMALL(250);

    private int calories;

    Portion(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }
}
