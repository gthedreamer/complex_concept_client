package discovery.parser;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResult;

public class QueryResponseParser {
	public List<String> getCommaSeperatedDocIds(List<QueryResponse> queryResponses) throws Exception
	{
		List<String> commaSeperatedDocIdAllQueries = new ArrayList<String>();
		
		for(QueryResponse queryResponse : queryResponses) {
			List<String> docIdsSingleQuery = getDocIdsForSingleQuery(queryResponse);
			String commaSeperatedDocIdSingleQuery = seralizeWithComma(docIdsSingleQuery);
			commaSeperatedDocIdAllQueries.add(commaSeperatedDocIdSingleQuery);
		}
		
		return commaSeperatedDocIdAllQueries;
	}
	
	private List<String> getDocIdsForSingleQuery(QueryResponse queryResponse){
		List<String> docIds = new ArrayList<String>();
		
		if(queryResponse != null && queryResponse.getResults() != null ) {
			for( QueryResult result : queryResponse.getResults() ) {
				String docId = (String) result.getOrDefault("DOCID", "0");
				docIds.add(docId);
			}
		}
		
		if(docIds.size() == 0) {
			// Lets keep dummy result instead of empty space
			docIds.add("0");
		}
		
		return docIds;
	}
	
	String seralizeWithComma(List<String> docIds) {
		StringBuffer serializedDocIds = new StringBuffer();
		for(String docId : docIds) {
			serializedDocIds.append(docId);
			serializedDocIds.append(',');
		}
		
		return serializedDocIds.toString();
	}
}
