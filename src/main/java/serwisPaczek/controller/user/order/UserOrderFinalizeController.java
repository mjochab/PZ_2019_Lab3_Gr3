package serwisPaczek.controller.user.order;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import serwisPaczek.model.Adress;
import serwisPaczek.model.Courier;
import serwisPaczek.model.Parcel;
import serwisPaczek.model.UserOrder;
import serwisPaczek.utils.SceneManager;
import serwisPaczek.utils.SceneType;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.pdf.PdfWriter;

import static com.itextpdf.text.pdf.PdfFormField.createList;

@Controller
public class UserOrderFinalizeController {
    private SceneManager sceneManager;

    private UserOrder userOrder;
    private Adress received;
    private Adress sender;
    private Parcel parcel;
    private Courier courier;
    @FXML
    private Label TFfromHouseNumber;

    @FXML
    private Label TFcourier;

    @FXML
    private Label TFhouseNumber;

    @FXML
    private Label TFfromSurname;

    @FXML
    private Label TFzipCode;

    @FXML
    private Label TFfromZipCode;

    @FXML
    private Label TFfromStreet;

    @FXML
    private Label TFfromCity;

    @FXML
    private Label TFnr;

    @FXML
    private Label TFname;

    @FXML
    private Label TFstreet;

    @FXML
    private Label TFfromName;

    @FXML
    private Label TFmoney;

    @FXML
    private Label TFcity;

    @FXML
    private Label TFsurname;

    @FXML
    public void BackToMenu(ActionEvent event) {
        sceneManager.show(SceneType.MAIN);
    }

    @Autowired
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize(UserOrder userOrder, Adress sender,
                           Adress received, Courier courier, Parcel parcel) {

      this.userOrder = userOrder;
        this.sender = sender;
        this.received = received;
    this.courier=courier;
    this.parcel=parcel;
        TFnr.setText(userOrder.getId().toString());
        TFcourier.setText(courier.getName());
        TFmoney.setText(String.valueOf(userOrder.getPrice()));

        TFcity.setText(received.getCity());
        TFzipCode.setText(received.getZipCode());
        TFstreet.setText(received.getStreet());
        TFhouseNumber.setText(received.getHouseNumber().toString());
        TFname.setText(received.getName());
        TFsurname.setText(received.getSurname());

        TFfromSurname.setText(sender.getSurname());
        TFfromName.setText(sender.getName());
        TFfromCity.setText(sender.getCity());
        TFfromHouseNumber.setText(sender.getHouseNumber().toString());
        TFfromStreet.setText(sender.getStreet());
        TFfromZipCode.setText(sender.getZipCode());
    }

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    @FXML
    public void generatePDF(){
        Document document = new Document();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("Zamowenie " + userOrder.getId().toString()+".pdf"));
            document.open();
            Chunk glue = new Chunk(new VerticalPositionMark());

            Image image1 = Image.getInstance("src/main/resources/images/kurier.png");
            //Fixed Positioning
            image1.setAbsolutePosition(450f, 685f);
            //Scale to new height and new width of image
            image1.scaleAbsolute(110, 130);
            //Add to document
            document.add(image1);

            document.add(new Paragraph());
            addMetaData(document);
            Paragraph preface = new Paragraph();
            // We add one empty line
            addEmptyLine(preface, 1);
            // Lets write a big header
            preface.add(new Paragraph("Serwis Paczek distribution", catFont));

            addEmptyLine(preface, 1);
            // Will create: Report generated by: _name, _date
            preface.add(new Paragraph("Faktura wygenerowana dla:" + sender.getName() + " " +
                            sender.getSurname(), smallBold));
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Data utworzenia: " +userOrder.getDate()));
            addEmptyLine(preface, 4);
//            Paragraph addresses = new Paragraph();
//
//            addresses.add("      Dane nadawcy: ");
//            addresses.add(new Chunk(glue));
//            addresses.add("Dane Odbiorcy:      ");
//            addEmptyLine(addresses, 1);
//            addresses.add("      " + sender.getName() +" " + sender.getSurname());
//            addresses.add(new Chunk(glue));
//            addresses.add(received.getName()+ " " + received.getSurname() + "      ");
//            addEmptyLine(addresses, 1);
//            addresses.add("      " + sender.getCity() + ", " + sender.getStreet() + " " + sender.getHouseNumber());
//            addresses.add(new Chunk(glue));
//            addresses.add( received.getCity() + ", " + received.getStreet() + " " + received.getHouseNumber()+ "      ");
//            addEmptyLine(addresses, 1);
//            addresses.add("      " + sender.getEmail() +", " + sender.getTelephoneNumber());
//            addresses.add(new Chunk(glue));
//            addresses.add(received.getEmail()+ " " + received.getTelephoneNumber() + "      ");
//            addEmptyLine(preface, 4);

            PdfPTable table = new PdfPTable(3);
            preface.add(table);
            PdfPCell c1 = new PdfPCell(new Phrase());
            c1.setMinimumHeight(50);
            c1.setPadding(20);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(c1);
             c1 = new PdfPCell(new Phrase("Nadawca"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setMinimumHeight(50);
            c1.setPadding(20);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Odbiorca"));
            c1.setMinimumHeight(50);
            c1.setPadding(20);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            table.setHeaderRows(1);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("Imie i nazwisko");
            table.addCell(sender.getName() +" " + sender.getSurname());
            table.addCell(received.getName()+ " " + received.getSurname());
            table.addCell("Miasto");
            table.addCell(sender.getCity());
            table.addCell(received.getCity());
            table.addCell("Ulica i numer domu");
            table.addCell(sender.getStreet() + " " + sender.getHouseNumber());
            table.addCell(received.getStreet() + " " + received.getHouseNumber());
            table.addCell("Email");
            table.addCell(sender.getEmail());
            table.addCell(received.getEmail());
            table.addCell("Numer telefonu");
            table.addCell(sender.getTelephoneNumber().toString());
            table.addCell(received.getTelephoneNumber().toString());

addEmptyLine(preface,4);


            PdfPTable table2 = new PdfPTable(5);
            preface.add(table2);

            c1 = new PdfPCell(new Phrase("Typ"));
            c1.setMinimumHeight(40);
            c1.setPaddingTop(40);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(c1);

            c1 = new PdfPCell(new Phrase("Waga"));
            c1.setMinimumHeight(40);
            c1.setPaddingTop(10);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(c1);

            c1 = new PdfPCell(new Phrase("Dlugosc"));
            c1.setMinimumHeight(40);
            c1.setPaddingTop(10);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(c1);

            c1 = new PdfPCell(new Phrase("Szerokosc"));
            c1.setMinimumHeight(40);
            c1.setPaddingTop(10);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(c1);

            c1 = new PdfPCell(new Phrase("Wysokosc"));
            c1.setMinimumHeight(40);
            c1.setPaddingTop(10);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(c1);

            table2.setHeaderRows(1);

table2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(parcel.getType());
            table2.addCell(String.valueOf(parcel.getWeight())+ " kg");
            table2.addCell(String.valueOf(parcel.getLength())+ " cm");
            table2.addCell(String.valueOf(parcel.getWidth())+ " cm");
            table2.addCell(String.valueOf(parcel.getHeight())+ " cm");

            addEmptyLine(preface,2);

            PdfPTable table3 = new PdfPTable(1);

            table3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table3.setWidthPercentage(20);
            preface.add(table3);

            c1 = new PdfPCell(new Phrase("Do zapłaty"));
            c1.setPaddingTop(10);
            c1.setMinimumHeight(40);


            table3.addCell(c1);
            table3.addCell(String.valueOf(userOrder.getPrice()));
            document.add(preface);
            document.close();
            writer.close();
        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // now add all this to the document
        document.add(catPart);

    }
    private static void createTable(Section subCatPart)
            throws BadElementException {


    }
    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }
}
