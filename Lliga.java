import java.util.List;

public class Lliga {
    private int idLliga;
    private String nomLliga;
    private String temporada;
    private List<Equip> equips;

    public int getIdLliga() {
        return idLliga;
    }

    public void setIdLliga(int idLliga) {
        this.idLliga = idLliga;
    }

    public String getNomLliga() {
        return nomLliga;
    }

    public void setNomLliga(String nomLliga) {
        this.nomLliga = nomLliga;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public List<Equip> getEquips() {
        return equips;
    }

    public void setEquips(List<Equip> equips) {
        this.equips = equips;
    }
}
