package crud.worker_utills;

public enum Position {
    TRAINEE, JUNIOR, MIDDLE, SENIOR, NS;
    @Override
    public String toString() {
        return switch (this) {
            case TRAINEE -> "Trainee";
            case JUNIOR -> "Junior";
            case MIDDLE -> "Middle";
            case SENIOR -> "Senior";
            case NS -> "No info";
        };
    }
}
