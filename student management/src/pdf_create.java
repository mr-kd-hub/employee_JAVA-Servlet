
import java.io.FileOutputStream;

import com.itextpdf.text.*;

import java.io.FileNotFoundException;
import java.sql.*;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class pdf_create{
	public static void main(String[] args)
	{
		pdf_create pdf=new pdf_create();
		pdf.create_order();
	}
	 public void create_order() {
	    	//String FILE_NAME = "F:\\chillyfacts.pdf";
	        //Document document = new Document(PageSize.A4);
	        try {
	        	 Document document = new Document();
	        	 PdfWriter.getInstance(document, new FileOutputStream("G://Employee_Details.pdf"));
	        	 document.open();
		            Paragraph p = new Paragraph();
		            p.add("Employee Details");
		            
		            p.setAlignment(Element.ALIGN_CENTER);
		            document.add(p);
		            document.add(Chunk.NEWLINE);
		            document.add(Chunk.NEWLINE);
	       	  PdfPTable table = new PdfPTable(new float[] { 4, 5, 5,4,5 });
	       	  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	       	  table.addCell("Employee Name");
	          table.addCell("City");
	          table.addCell("DOB");
	          table.addCell("Gender");
	          table.addCell("Phone");
	          
	       	  table.setHeaderRows(1);
	       	  PdfPCell[] cells = table.getRow(0).getCells(); 
	       	  for (int j=0;j<cells.length;j++){
	       	     cells[j].setBackgroundColor(BaseColor.LIGHT_GRAY);
	       	  }
	       	  Class.forName("com.mysql.jdbc.Driver");
	       	  Connection con=DriverManager.getConnection("jdbc:mysql://localhost/employee","root","");
	       	  String q="select * from emp";
	       	  Statement st=con.createStatement();
	       	  ResultSet rs=st.executeQuery(q);
	                 while(rs.next()){
	           	     table.addCell(rs.getString(3));
	                    table.addCell(rs.getString(4));
	                    table.addCell(rs.getString(5));
	                    table.addCell(rs.getString(6));
	                    table.addCell(rs.getString(7));
	                 }
	       	  
	       	  
	                 document.add(table);
	       	  document.close();
	       	  System.out.println("Done");
	       	  con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }
}
