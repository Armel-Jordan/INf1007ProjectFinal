package agence.models;

public enum TypeVehicule {

    SIMPLE("Simple"),
    PRESTIGE("Prestige"),
    CAMION("Camion");

    private final String type;

    TypeVehicule(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
