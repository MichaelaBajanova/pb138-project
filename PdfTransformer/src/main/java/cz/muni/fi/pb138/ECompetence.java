package cz.muni.fi.pb138;

/**
 * Class representing E-Competence element
 */
public class ECompetence {
    private String competence;
    private short level;

    public ECompetence(String competence, short level ) {
        this.competence = competence;
        this.level = level;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "ECompetence{" +
                "competence='" + competence + '\'' +
                ", level=" + level +
                '}';
    }
}