package src.app.classes;

import java.util.Objects;

public class AutobuzElectric extends Autobuz {
    private int autonomieKm;

    public AutobuzElectric(String numarInmatriculare, String marca, int capacitate, int autonomieKm) {
        super(numarInmatriculare, marca, capacitate);
        setAutonomieKm(autonomieKm);
    }

    @Override
    public String getTip() {
        return "Autobuz electric";
    }

    public int getAutonomieKm() {
        return autonomieKm;
    }

    public void setAutonomieKm(int autonomieKm) {
        if (autonomieKm <= 0) throw new IllegalArgumentException("Autonomia nu este valida!");
        this.autonomieKm = autonomieKm;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        AutobuzElectric that = (AutobuzElectric) o;
        return autonomieKm == that.autonomieKm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), autonomieKm);
    }

    @Override
    public String toString() {
        return super.toString() + ", autonomie: " + autonomieKm + " km";
    }
}