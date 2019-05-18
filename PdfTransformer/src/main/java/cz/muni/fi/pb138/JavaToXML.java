package cz.muni.fi.pb138;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ludovit Kopcsanyi
 */
public class JavaToXML {

	public static void convert(Role role, String file_name) throws Exception {

		JAXBContext context = JAXBContext.newInstance(Profile.class);

		file_name = file_name.replace(" ", "_");
		File file = new File( "output/" + file_name + ".xml");

		if (file.getParentFile().mkdirs()) {
			System.out.println("Creating output folder...");
		}

		FileWriter writer = new FileWriter(file);

		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		Profile profile = new Profile();
		profile.setTitle(role.getTitle());
		profile.setSummary(role.getSummary());
		profile.setMission(role.getMission());

		Deliverables deliverables = new Deliverables();
		Accountable accountable = new Accountable();
		Responsible responsible = new Responsible();
		Contributor contributor = new Contributor();

		accountable.setDeliverable(role.getAccountable().toArray(new String[0]));
		responsible.setDeliverable(role.getResponsible().toArray(new String[0]));
		contributor.setDeliverable(role.getContributor().toArray(new String[0]));

		deliverables.setAccountable(accountable);
		deliverables.setResponsible(responsible);
		deliverables.setContributor(contributor);
		profile.setDeliverables(deliverables);

		Tasks tasks = new Tasks();
		tasks.setTask(role.getMainTask().toArray(new String[0]));
		profile.setTasks(tasks);

		Competences competences = new Competences();
		List<Competence> competence = new ArrayList<>();
		for (ECompetence ec : role.geteCompetence()) {
			Competence currCompetence = new Competence();
			currCompetence.setValue(ec.getCompetence());
			currCompetence.setLevel(Short.toString(ec.getLevel()));
			competence.add(currCompetence);
		}

		competences.setCompetence(competence.toArray(new Competence[0]));

		profile.setCompetences(competences);

		profile.setKpi(role.getKpiArea());

		m.marshal(profile, writer);

	}

}

@XmlRootElement
@XmlType(propOrder={"title", "summary", "mission", "deliverables", "tasks", "competences", "kpi"})
class Profile {

	private String title;
	private String summary;
	private String mission;
	private Deliverables deliverables;
	private Competences competences;
	private Tasks tasks;

	public Competences getCompetences() {
		return competences;
	}

	public void setCompetences(Competences competences) {
		this.competences = competences;
	}

	private String kpi;

	public Tasks getTasks() {
		return tasks;
	}

	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public void setDeliverables(Deliverables deliverables) {
		this.deliverables = deliverables;
	}

	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}

	public String getMission() {
		return mission;
	}

	public Deliverables getDeliverables() {
		return deliverables;
	}
}

@XmlRootElement
@XmlType(propOrder={"accountable", "responsible", "contributor"})
class Deliverables {

	private Accountable accountable;
	private Responsible responsible;
	private Contributor contributor;

	public void setAccountable(Accountable accountable) {
		this.accountable = accountable;
	}

	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;
	}

	public void setContributor(Contributor contributor) {
		this.contributor = contributor;
	}

	public Accountable getAccountable() {
		return accountable;
	}

	public Responsible getResponsible() {
		return responsible;
	}

	public Contributor getContributor() {
		return contributor;
	}
}

@XmlRootElement
class Accountable {

	private String[] deliverable;

	public void setDeliverable(String[] deliverable) {
		this.deliverable = deliverable;
	}

	public String[] getDeliverable() {
		return deliverable;
	}
}

@XmlRootElement
class Responsible {


	private String[] deliverable;

	public void setDeliverable(String[] deliverable) {
		this.deliverable = deliverable;
	}

	public String[] getDeliverable() {
		return deliverable;
	}
}

@XmlRootElement
class Contributor {

	private String[] deliverable;

	public void setDeliverable(String[] deliverable) {
		this.deliverable = deliverable;
	}

	public String[] getDeliverable() {
		return deliverable;
	}
}

@XmlRootElement
class Tasks {

	private String[] task;

	public String[] getTask() {
		return task;
	}

	public void setTask(String[] task) {
		this.task = task;
	}
}

@XmlRootElement
class Competences {

	private Competence[] competence;

	public Competence[] getCompetence() {
		return competence;
	}

	public void setCompetence(Competence[] competence) {
		this.competence = competence;
	}
}

@XmlRootElement
class Competence {

	private String level;

	private String value;

	@XmlAttribute
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@XmlValue
	public String getValue() {
		return value;
	}

	public void setValue(String competence) {
		this.value = competence;
	}
}
