# PB138 project
European e-Competence Framework 3.0 in XML project for PB138 course.

## Goal
This projects aims to extract the 30 EUROPEAN ICT PROFESSIONAL ROLE PROFILES
from the CWA 16458-1 2018 PDF document and transform them into XML file.

- [x] Creating XML Schema
- [x] Creating PDF parser for extracting role profiles from CWA 16458-1 2018 document.
- [ ] Creating program to transform the data into a XML file valid by the XML Schema
- [ ] Creating XSLT and transforming the XML into HTML files.

## Documentation
The PDF parser was created for purposes of parsing the CWA 16458-1 2018 document and it is not a general solution to parsing PDF documents. 

Code for the PDF parser is located in `PdfTransformer` directory. The dircetory also contains the CWA 16458-1 2018 document named as `test.pdf`. 

`Role` is a class representing role profile in the CWA 16458-1 2018 document. Structure of role profiles is described in CWA 16458-2 2018 document on page 15 and all attributes were added based on that structure.

`ECompetence` represents e-competence from role profile in the CWA 16458-1 2018 document. 

`PDFReader` is a class responsible for parsing PDF contents. The main logic of the parser is in this class. The PDF document is split into pages and text is extracted from relevant pages using Apache PDFBox library. Resulting text is then parsed into `Role` class.

The part of the program responsible for transforming parsed data into XML file generates XML files from data in `Role` objects and stores them locally on the disk. //TODO when this part is finished

## How to run
`javac Main.java`
`java Main`

## Libraries used
- [Apache PDFBox](https://pdfbox.apache.org/)

---------
## Authors
- Michaela Bajánová: Responsible for XML Schema, wiki page and documentation.
- Filip Daniel Fedin: Responsible for PDF parser, README file and documentation. 
- Ľudovít Kopcsányi: Responsible for transforming parsed data to XML file.
- Matej Macák: Responsible for XSLT file.


