package model;

public enum UserType {
    Normal("Normal"),
    Nurse("Krankenschwester"),
    Doctor("Arzt");

    private String displayString;

    private UserType(String displayString){
        this.displayString = displayString;
    }

    @Override
    public String toString() {
        return displayString != null ? displayString : super.toString();
    }
}
