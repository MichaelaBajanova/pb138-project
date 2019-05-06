package cz.muni.fi.pb138;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a role from the CWA 16458-1_2018.pdf document
 */
public class Role {

    private String title;
    private String summary;
    private String mission;
    private List<String> accountable = new ArrayList<>();
    private List<String> responsible = new ArrayList<>();
    private List<String> contributor = new ArrayList<>();
    private List<String> mainTask = new ArrayList<>();
    private List<ECompetence> eCompetence = new ArrayList<>();
    private String kpiArea;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public List<String> getMainTask() {
        return mainTask;
    }

    public void setMainTask(List<String> mainTask) {
        this.mainTask = mainTask;
    }

    public String getKpiArea() {
        return kpiArea;
    }

    public void setKpiArea(String kpiArea) {
        this.kpiArea = kpiArea;
    }

    public List<String> getAccountable() {
        return accountable;
    }

    public List<String> getResponsible() {
        return responsible;
    }

    public List<String> getContributor() {
        return contributor;
    }

    public List<ECompetence> geteCompetence() {
        return eCompetence;
    }

    //for testing purposes, can delete
    @Override
    public String toString() {
        return "Role{" + title + "} " + summary
                + " {" + mission + "} "
                + " ACC: " + accountable.toString()
                + " RES: " + responsible.toString()
                + " CON: " + contributor.toString()
                + "  MAIN  " + mainTask.toString()
                + " EEECO  " + eCompetence.toString()
                + "  KPIII   " + kpiArea;
    }
}
