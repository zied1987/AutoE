package file;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFcreator {

 public static final String CHEMAIN ="C:\\Users\\zied\\Desktop\\test2.pdf";
 private final static Logger LOGGER = Logger.getLogger(PDFcreator.class);
	
 public static void createSquelette () throws DocumentException, IOException  {
	
	Document document = new Document();
	
	try {
		PdfWriter.getInstance(document, new FileOutputStream(CHEMAIN)); 
		document.open();
		
		document.add(new Paragraph("ceci est un test"));
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));
		document.add(premierTableau());
		
	} catch (DocumentException de) {
		LOGGER.error(de);
	} catch(IOException iex) {
		LOGGER.error(iex);
	} finally {
		document.close();
		LOGGER.info("Pdf creqted");
	}
	
}

 public static PdfPTable premierTableau()
	  {
	      //On créer un objet table dans lequel on intialise ça taille.
	      PdfPTable table = new PdfPTable(3);
	      
	      //On créer l'objet cellule.
	      PdfPCell cell;
	      
	      cell = new PdfPCell(new Phrase("Fusion de chaque première cellule de chaque colonne"));
	      cell.setColspan(3);
	      table.addCell(cell);
	 
	      cell = new PdfPCell(new Phrase("Fusion de 2 cellules de la première colonne"));
	      cell.setRowspan(2);
	      table.addCell(cell);
	 
	      //contenu du tableau.
	      table.addCell("Colonne 1; Cellule 1");
	      table.addCell("Colonne 1; Cellule 2");
	      table.addCell("Colonne 2; Cellule 1");
	      table.addCell("Colonne 2; Cellule 2");
	      
	      return table;  
	  }
	
 public static void main (String[] args) throws DocumentException, IOException {
	 createSquelette ();
 }
 
}
	
