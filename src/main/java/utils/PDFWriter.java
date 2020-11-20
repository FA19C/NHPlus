package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Patient;
import model.Treatment;
import java.io.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Hilfsklasse um PDFs zu schreiben
 */
public abstract class PDFWriter
{
    /**
     * Schreibt die Patienten-Daten in den übergebenen Pfad
     * @param file der Ort wo das Doc hinsoll
     * @param patient der Patient
     * @param treatments die Behandlungen
     */
    public static void writePatientData(File file, Patient patient, List<Treatment> treatments)
    {
        if(file == null)
        {
            return;
        }

        bauePatientenDatenDokument(file.getAbsolutePath(),patient,treatments);
    }

    /**
     * Erstellt den Header für das PDF-Doc mit den PAtienteninfos
     * @param patient der Patient
     * @return den Header als String
     */
    private static String getPatienHeaderString(Patient patient)
    {
        return String.format("Nachnname: %s \n\r " +
                "Vorname: %s \n\r" +
                "Geburtstag: %s \n\r" +
                "Pflegegrad: %s \n\r" +
                "Raum: %s\n\r", patient.getSurname(), patient.getFirstName(), patient.getDateOfBirth(), patient.getCareLevel(),patient.getRoomnumber());
    }

    /**
     * Erstellt eine PDF der Patientendaten
     * @param pfad wo das Dok gespeichert wird
     * @param patient der Patient des Doks
     * @param treatments alle behandlungen des Patienten
     */
    private static void bauePatientenDatenDokument(String pfad, Patient patient, List<Treatment> treatments)
    {
        Document document = new Document();
        try
        {
            PdfWriter.getInstance(document, new FileOutputStream(pfad));
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        document.open();

        Font font = FontFactory.getFont(FontFactory.TIMES, 16, BaseColor.BLACK);
        Paragraph paragraph = new Paragraph(getPatienHeaderString(patient), font);

        try
        {
            document.add(paragraph);
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }

        PdfPTable table = new PdfPTable(6);
        addTableHeader(table);
        for(Treatment treatment : treatments)
        {
            addTreatmentRows(table, treatment);
        }

        try
        {
            document.add(table);
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        document.close();
    }

    /**
     * erstellt der Tabelle einen Tableheader
     * @param table die Tabelle die den Header bekommt
     */
    private static void addTableHeader(PdfPTable table) {
        Stream.of("Datum", "Beginn", "Ende", "Kurzbeschreibung", "Bemerkung", "PflegerID")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    /**
     * Fügt der Tabelle eine Zeile Treatments hinzu
     * @param table die Tabelle, die die neue Zeile bekommt
     * @param treatment das eingetragene Treatment
     */
    private static void addTreatmentRows(PdfPTable table, Treatment treatment)
    {
        table.addCell(treatment.getDate());
        table.addCell(treatment.getBegin());
        table.addCell(treatment.getEnd());
        table.addCell(treatment.getDescription());
        table.addCell(treatment.getRemarks());
        table.addCell(Long.toString(treatment.getNid()));
    }
}
