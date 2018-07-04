package discovery.filewriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVFileWriter {
	private static final String OUTPUT_CSV_FILE = "query_output_file.csv";
	
	public boolean writeResultsToCSVFile(List<String> docIdsLines) {
		boolean status = false;
		FileWriter fileWriter = null;
		
		try {

			fileWriter = new FileWriter(OUTPUT_CSV_FILE);
			String outFileAbsPath = new File(OUTPUT_CSV_FILE).getAbsolutePath();
//			System.out.println("Writing query output to "+outFileAbsPath);

			for(String line : docIdsLines) {
				fileWriter.append(line+"\n");
			}
			
			System.out.println("*********** Query output written to "+outFileAbsPath + " successfully  "+"************************");
			status = true;
		}catch(Exception e) {
			status = false;
			e.printStackTrace();
		}
		finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return status;
	}
}
