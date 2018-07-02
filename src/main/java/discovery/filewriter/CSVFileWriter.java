package discovery.filewriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVFileWriter {
	
	public boolean writeResultsToCSVFile(List<String> docIdsLines,String fileName) {
		boolean status = false;
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(fileName);

			for(String line : docIdsLines) {
				fileWriter.append(line+"\n");
			}
			
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
