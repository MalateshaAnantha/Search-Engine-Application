package crawerapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;


import org.xml.sax.SAXException;

public class ParserExtraction {
	
public static void main(final String[] args) throws IOException,SAXException, TikaException {

	String url = "C:\\Users\\Thejas\\Documents\\ShareVM\\CNNData\\CNNDownloadData\\CNNDownloadData";
	File dir = new File(url);
	Set<String> set = new HashSet<String>();
	FileWriter writer=new FileWriter("test\\big.txt");
	FileWriter bodywrite= null;
   //Assume sample.txt is in your current directory
  // File file = new File("C:\\Users\\Thejas\\workspace\\CrawlerProject\\src\\crawerapp\\sample.html");
	for(File file : dir.listFiles()){
   //parse method parameters
   HtmlParser parser = new HtmlParser();
   BodyContentHandler handler = new BodyContentHandler(-1);
   Metadata metadata = new Metadata();
   FileInputStream inputstream = new FileInputStream(file);
   ParseContext context = new ParseContext();
   bodywrite = new FileWriter("test\\"+file.getName());
   
   //parsing the file
   parser.parse(inputstream, handler, metadata, context);
   //System.out.println("File content : " + handler.toString());
   StringTokenizer str = new StringTokenizer(handler.toString());
   StringTokenizer lines = new StringTokenizer(handler.toString(),".\n");
   while(str.hasMoreTokens()){
	   String temp = str.nextToken();
	   if(StringUtils.isAlpha(temp))
		  set.add(temp);
   
   }
   
   while(lines.hasMoreTokens()){
	   String temp = lines.nextToken();
	  // System.out.println(temp);
	   //String [] ls =  temp.split(". ");
	   //if(temp.length()>2)
	   if(temp.length()>2)
	   bodywrite.append(StringUtils.normalizeSpace(temp)+".\n");
   
   }
   bodywrite.close();
   
   
   } 
	
	for(Object s : set.toArray())
		 writer.append(s.toString()+" ");
	writer.close();
}
}