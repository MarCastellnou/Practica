package data;

public class Nif {
    private final String nif;
    public Nif(String nif) {this.nif = nif; }
    public String getNif() {
        return nif;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nif nif1 = (Nif) o;
        return nif.equals(nif1.nif);
    }
    @Override
    public int hashCode() {return nif.hashCode();}
    @Override
    public String toString() {
        return "Nif{" + "Nif='" + nif + '\'' + '}';
    }
}
