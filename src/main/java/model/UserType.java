package model;

public enum UserType {
    Normal("Normal", 0),
    Nurse("Krankenschwester", 1),
    Doctor("Arzt", 2);

    private String displayString;
    public int dataBaseValue;

    private UserType(String displayString, int dataBaseValue){
        this.displayString = displayString;
        this.dataBaseValue = dataBaseValue;
    }

    @Override
    public String toString() {
        return displayString != null ? displayString : super.toString();
    }

    public static UserType getUserTypeFromDataBaseValue(int value){
        switch (value){
            case 0 : return UserType.Normal;
            case 1 : return UserType.Nurse;
            case 2 : return UserType.Doctor;
        }
        return null;
    }
}
