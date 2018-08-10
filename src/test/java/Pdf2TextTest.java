import java.io.*;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/***
 * 使用PDFbox将PDF转为text
 */
public class Pdf2TextTest {

	public static void main(String[] args) throws IOException{
		String string = null;
		String filePath = "./samples/test.";
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
                filePath + "txt"), "utf-8"), true);
        try {
            PDFParser pdfParser = new PDFParser(new RandomAccessFile(new File(filePath + "pdf"), "r"));
            pdfParser.parse();
            PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            string = pdfTextStripper.getText(pdDocument);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] file = new String[0];
        if (string != null) {
            file = string.split("\n");
        }
        for(String f:file){
            pw.println(f);
        }
        pw.close();
	}

}
