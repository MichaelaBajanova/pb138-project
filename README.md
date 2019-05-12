# PB138 project
European e-Competence Framework 3.0 in XML project for PB138 course.

## Goal
This projects aims to extract the 30 EUROPEAN ICT PROFESSIONAL ROLE PROFILES
from the CWA 16458-1 2018 PDF document and transform them into an xml file.

- [x] Creating xml schema
- [x] Creating program to extract the role from pdf
- [ ] Creating program to transform the data into a xml file valid by the schema
- [ ] Creating xslt and transforming the xml into html files.

## Description
The program first splits the already included PDF document into pages, and the extracts text from the relevant pages
using Apache PDFBox library. The resulting text is then parsed into the Role class, which contains attributes
based on the xml schema already developed. The second part of the program the generates an xml file from the data of the object,
and stores them locally on the disk.

## How to run
`javac Main.java`
`java Main`

## Libraries used
- [Apache PDFBox](https://pdfbox.apache.org/)

---------
## Authors
- Michaela Bajánová: Responsible for creating xml schema
- Filip Daniel Fedin: Responsible for creating the first part of the program
- Ľudovít Kopcsányi: Responsible for creating the second part of the program
- Matej Macák: Responsible for creating the xslt file


