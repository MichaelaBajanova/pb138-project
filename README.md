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
The program first splits the PDF document into pages, and the extracts text from the relevant pages
using Apache PDFBox library. The resulting text is then parsed into the Role class, which contains attributes
based on the xml schema already developed. The program will then generate an xml file from the data in the class.

## Libraries used
- [Apache PDFBox](https://pdfbox.apache.org/)

---------
## Authors
- Michaela Bajánová
- Filip Daniel Fedin
- Ľudovít Kopcsányi
- Matej Macák


