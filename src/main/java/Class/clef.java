package Class;

public class clef {
    private final int id;
    private final String nom;
    private final String ouvrir;
    private final String nomCouleur;

    public clef(int id, String nom, String ouvrir, String nomCouleur) {
        this.id = id;
        this.nom = nom;
        this.ouvrir = ouvrir;
        this.nomCouleur = nomCouleur;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getOuvrir() {
        return ouvrir;
    }

    public String getNomCouleur() {
        return nomCouleur;
    }
}
