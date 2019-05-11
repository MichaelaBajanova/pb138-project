package cz.muni.fi.pb138;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Class designed to extract the contents from CWA 16458-1_2018.pdf document into a Role object
 */
public class PDFReader {
    /**
     * Inner class representing the different deliverable elements
     */
    private class Deliverables {

        private int acc;
        private int res;
        private int con;

        /**
         * Constructs a new deliverable object
         * @param acc Accountable
         * @param res Responsible
         * @param con Constributor
         */
        public Deliverables(int acc, int res, int con) {
            this.acc = acc;
            this.res = res;
            this.con = con;
        }
    }
    private List<Deliverables> count = setCount();
    private int index = 1;

    /**
     * Parses the contents of the document
     * @param path to the pdf document
     * @return List of roles from the document
     */
    public List<Role> read(Path path) {
        List<Role> roles = new ArrayList<>();
        PDFTextStripper pdfStripper;
        PDDocument pdDoc;
        COSDocument cosDoc;
        File file = path.toFile();
        try {
            PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(new FileInputStream(file)));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);

            // Splits the document to individual pages
            Splitter splitter = new Splitter();
            List<PDDocument> pages = splitter.split(pdDoc);
            Iterator<PDDocument> iterator = pages.listIterator();

            // Loop goes through every page
            while (iterator.hasNext()) {
                PDDocument page = iterator.next();

                // Only relevant pages get parsed
                if (index >= 9 && index <= 38) {
                    String parsedText = pdfStripper.getText(page);
                    roles.add(parsePage(parsedText));
                }
                ++index;
            }
        } catch (IOException e) {
            System.err.println("Something went wrong");
            roles = null;
        }
        index = 1;
        return roles;
    }

    /**
     * Parses one page of the document
     * @param page to be parsed
     * @return Role object created from the page
     */
    private Role parsePage(String page) {
        Role role = new Role();
        Scanner sc = new Scanner(page);
        sc.useDelimiter("Profile title");
        sc.next();

        sc.useDelimiter("Summary");
        role.setTitle(parseTitle(sc.next().replace("\n", "").trim()));

        sc.useDelimiter("Mission");
        role.setSummary(parseSummary(sc.next().replace("\n", "").trim()));

        sc.useDelimiter("Deliverables");
        role.setMission(parseMission(sc.next().replace("\n", "").trim()));

        sc.useDelimiter("Contributor");
        sc.next();
        sc.useDelimiter("Main task/s");
        parseDeliverables(sc.next().replace("\n", "").trim(), role);

        sc.useDelimiter("\\(from e-CF\\)");
        parseMainTask(sc.next().replace("\n", "").trim(), role);

        sc.useDelimiter("KPI area");
        parseCompetences(sc.next().replace("\n", "").trim(), role);

        role.setKpiArea(sc.next().replace("\n", "").trim());


        return role;
    }

    /**
     * Extracts the profile title from the string
     * @param text string from the parsed page
     * @return name of the profile title
     */
    private String parseTitle(String text) {
        return text.substring(13, text.length() - 4).trim();
    }

    /**
     * Extracts the summary statement from the string
     * @param text string from the parsed page
     * @return summary statement
     */
    private String parseSummary(String text) {
        return text.substring(17).trim();
    }

    /**
     * Extracts the mission from the string
     * @param text string from the parsed page
     * @return mission
     */
    private String parseMission(String text) {
        return text.substring(7).trim();
    }

    /**
     * Extracts deliverables and stores them in the currently constructed role object
     * @param text string from the parsed page
     * @param role object currently being created
     */
    private void parseDeliverables(String text, Role role) {
        text = text.substring(11).trim();
        String[] deliverables = text.split("•");
        int listIndex = index - 9;

        for (int i = 0; i < count.get(listIndex).acc; i++) {
            role.getAccountable().add(deliverables[i + 1].trim());
        }

        for (int i = 0; i < count.get(listIndex).res; i++) {
            role.getResponsible().add(deliverables[i + 1 + count.get(listIndex).acc].trim());
        }

        for (int i = 0; i < count.get(listIndex).con; i++) {
            role.getContributor().add(deliverables[i + 1 + count.get(listIndex).acc + count.get(listIndex).res].trim());
        }
    }

    /**
     * Extracts main tasks and stores them into the currently constructed role object
     * @param text string from the parsed page
     * @param role object currently being created
     */
    private void parseMainTask(String text, Role role) {
        text = text.substring(11, text.length() - 13);
        String[] main = text.split("•");
        for (String i : main) {
            if (!i.trim().isEmpty()) {
                role.getMainTask().add(i.trim());
            }
        }
    }

    /**
     * Extracts competences and their level from the string, and stores them in a role.
     * @param text string from the parsed page
     * @param role object currently being created
     */
    private void parseCompetences(String text, Role role) {
        Scanner sc = new Scanner(text.substring(11));
        while (sc.hasNext()) {
            sc.useDelimiter("Level|level");
            String name = sc.next().trim();
            sc.useDelimiter("(\\s)");
            while(!sc.hasNextShort()) {
                sc.next();
            }
            short level = sc.nextShort();
            role.geteCompetence().add(new ECompetence(name, level));
        }
    }


    /**
     * Sets the distribution of accountable, responsible and contributor points
     * in deliverables table.
     * @return list of deliverables distribution
     */
    private List<Deliverables> setCount() {
        List<Deliverables> list = new ArrayList<>();
        list.add(new Deliverables(1, 1, 3));
        list.add(new Deliverables(1, 3, 2));
        list.add(new Deliverables(1, 1, 1));
        list.add(new Deliverables(3, 3, 0));
        list.add(new Deliverables(1, 1, 2));
        list.add(new Deliverables(1, 1, 3));
        list.add(new Deliverables(0, 2, 1));
        list.add(new Deliverables(1, 0, 4));
        list.add(new Deliverables(2, 1, 2));
        list.add(new Deliverables(1, 2, 1));
        list.add(new Deliverables(1, 2, 2));
        list.add(new Deliverables(1, 2, 3));
        list.add(new Deliverables(0, 1, 3));
        list.add(new Deliverables(0, 3, 2));
        list.add(new Deliverables(2, 1, 3));
        list.add(new Deliverables(0, 2, 2));
        list.add(new Deliverables(0, 2, 3));
        list.add(new Deliverables(1, 3, 2));
        list.add(new Deliverables(0, 1, 2));
        list.add(new Deliverables(2, 2, 2));
        list.add(new Deliverables(2, 1, 1));
        list.add(new Deliverables(1, 1, 1));
        list.add(new Deliverables(0, 3, 3));
        list.add(new Deliverables(1, 2, 1));
        list.add(new Deliverables(1, 1, 1));
        list.add(new Deliverables(1, 1, 2));
        list.add(new Deliverables(2, 1, 2));
        list.add(new Deliverables(1, 2, 1));
        list.add(new Deliverables(1, 1, 3));
        list.add(new Deliverables(3, 2, 1));
        return list;
    }
}