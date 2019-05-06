package cz.muni.fi.pb138;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("test.pdf");
        PDFReader pdfReader = new PDFReader();
        List<Role> roles = pdfReader.read(path);
        for (Role r : roles) {
            System.out.println(r.toString());
        }
    }
}
