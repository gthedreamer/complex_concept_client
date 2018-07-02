package discovery.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;

import discovery.filewriter.CSVFileWriter;
import discovery.parser.QueryResponseParser;
import discovery.restclient.ComplexConceptQueryClient;

public class QueryFileProcessor {
	private List<String> readQueryFromFile(String queryFileName) {
		List<String> queryList = new ArrayList<String>();
		try {
			File file = new File(queryFileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				queryList.add(line);
				stringBuffer.append(line+"\n");
			}
			fileReader.close();
			System.out.println("Contents of file:");
			System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return queryList;
	}
	
	public boolean processQueryFile(String queryFileName) throws Exception{
		boolean overallStatus = false;
		
		try {
			ComplexConceptQueryClient queryClient = new ComplexConceptQueryClient();
			QueryResponseParser responseParser = new QueryResponseParser();
			CSVFileWriter csvWriter = new CSVFileWriter();
			List<QueryResponse> queryResponses = new ArrayList<QueryResponse>();
			
			List<String> queries = readQueryFromFile(queryFileName);
			

			for(String query : queries) {
				try {
					// Hit REST Endpoint
					QueryResponse response = queryClient.sendGet(query);
					queryResponses.add(response);
				}catch(Exception e){
					System.out.println("Exception in processing this query..yet i will process the other queries"+e.getMessage());
					// Even if any query fails store empty QueryResponse to maintain result order
					queryResponses.add(new QueryResponse());
				}
			}
			
			List<String> commaSeperatedDocIdsAllQueries = responseParser.getCommaSeperatedDocIds(queryResponses);
			overallStatus = csvWriter.writeResultsToCSVFile(commaSeperatedDocIdsAllQueries, "ganesh.csv");
			
		}catch(Exception e) {
			System.out.println("Exception.. quit processing queries.."+e.getMessage());
			overallStatus = false;
		}
		
		return overallStatus;
	}
}
