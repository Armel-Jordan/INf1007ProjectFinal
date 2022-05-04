package agence.models;

public class TypeVehicule {

    private final String type;

    public TypeVehicule(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
