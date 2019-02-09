package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import model.Intervention;

public class PdfExporter {

	public static boolean exportPDF(Intervention intervention) {
		try {
			Document document = new Document();
			String reportName=makeFolder()+File.separator+TimeUtility.localDateToString(intervention.getReportTime().toLocalDate())+"izvjestaj-"+intervention.getId()+".pdf";
			File file = new File(reportName);
			if(file.exists())
				return false;
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(reportName));
			document.open();
			Paragraph naslov = new Paragraph(
					new Phrase(5, "Izvjestaj " + intervention.getId(), FontFactory.getFont(FontFactory.COURIER, 15)));
			naslov.setAlignment(1);
			document.add(naslov);

			Paragraph p = new Paragraph("\n \nIntervencija:  " + intervention.getId());
			document.add(p);
			Paragraph time = new Paragraph(
					"Vrijeme kreiranja:  " + TimeUtility.localDateTimeToString((intervention.getReportTime())));
			document.add(time);
			Paragraph supervisor = new Paragraph("Supervizor:  "+intervention.getSupervisor());
			document.add(supervisor);
			//document.add(Chunk.NEWLINE);
			LineSeparator ls1 = new LineSeparator();
			document.add(new Chunk(ls1));
			Paragraph client=new Paragraph("Podaci o klijentu");
			document.add(client);
			Paragraph name = new Paragraph(intervention.getClient());
			document.add(name);
			Paragraph phoneNumber = new Paragraph("Broj telefona:  "+intervention.getPhoneNumber());
			document.add(phoneNumber);
			LineSeparator ls2 = new LineSeparator();
			document.add(new Chunk(ls2));
			Paragraph podaciVozilo = new Paragraph("Podaci o klijentovom vozilu");
			document.add(podaciVozilo);
			Paragraph registration = new Paragraph("Registarski broj:  "+intervention.getVehicleLicencePlate());
			document.add(registration);
			Paragraph model = new Paragraph("Model:  "+intervention.getVehicleModel());
			document.add(model);
			Paragraph maker = new Paragraph("Proizvodjac:  "+intervention.getVehicleManu());
			document.add(maker);
			Paragraph year = new Paragraph("Godina proizvodnje:  "+intervention.getVehicleYear());
			document.add(year);
			document.add(new Chunk(ls2));
			Paragraph data = new Paragraph("Podaci o intervenciji:  ");
			document.add(data);
			Paragraph call = new Paragraph("Poziv primljen:  "+TimeUtility.localDateTimeToString(intervention.getOpenedOn()));
			document.add(call);
			Paragraph open = new Paragraph("Primio poziv:  "+intervention.getUserOpened());
			document.add(open);
			Paragraph close = new Paragraph("Zatvorio intervenciju:  "+intervention.getUserClosed());
			document.add(close);
			Paragraph remarkOperator = new Paragraph("Napomena operatera:  "+intervention.getOperaterReport());
			document.add(remarkOperator);
			document.add(new Chunk(ls2));
			Paragraph fieldReport = new Paragraph("Podaci o terenskom izvjestaju");
			document.add(fieldReport);
			Paragraph fieldTechnician = new Paragraph("Terenski radnik:  "+intervention.getFieldTechnician());
			document.add(fieldTechnician);
			Paragraph v = new Paragraph("Vrsta usluge:  "+intervention.getService());
			document.add(v);
			Paragraph timeV= new Paragraph("Vrijeme usluge:  "+TimeUtility.localDateTimeToString(intervention.getServiceTime()));
			document.add(timeV);
			Paragraph report= new Paragraph("Napomena terenskog radnika: "+intervention.getFieldReport());
			document.add(report);
			document.add(new Chunk(ls2));
			Paragraph n = new Paragraph("Napomena:  "+intervention.getSupervisorReport());
			document.add(n);
			document.close();
			writer.close();
			return true;
		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static String makeFolder() {
		String userHome = System.getProperty("user.home");
		File f = new File(userHome+File.separator+"Izvjestaji");
		if (!f.exists()) {
			f.mkdir();
		}
		return f.getAbsolutePath();
	}
	
	
	//public static void main (String[]args) {
	//	Intervention i = new Intervention("2", "Pavle Peric", "555-333", "43532", "bembara",
	//			"bwm", "2222", "Operater otvorenko", "radnik teren", LocalDateTime.now(),
		//		"bilo sta", "slepanje", LocalDateTime.now(), "popeo se autom na sljivu", "zatvorenko",
		//		LocalDateTime.now(), "Umro na suvozacevom mjestu", "Supervizor supervizor", "ovi moji idioti",
		//		LocalDateTime.now());
	//	System.out.println(makeFolder());
		//PdfExporter.exportPDF(i);
	//}
}