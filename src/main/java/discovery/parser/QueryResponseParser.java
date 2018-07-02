package discovery.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResult;

public class QueryResponseParser {
	public List<String> getCommaSeperatedDocIds(List<QueryResponse> queryResponses) throws Exception
	{
		List<String> commaSeperatedDocIdAllQueries = new ArrayList<String>();
		
		for(QueryResponse queryResponse : queryResponses) {
			HashSet<String> docIdsSingleQuery = getDocIdsForSingleQuery(queryResponse);
			String commaSeperatedDocIdSingleQuery = seralizeWithComma(docIdsSingleQuery);
			commaSeperatedDocIdAllQueries.add(commaSeperatedDocIdSingleQuery);
		}
		
		return commaSeperatedDocIdAllQueries;
	}
	
	private HashSet<String> getDocIdsForSingleQuery(QueryResponse queryResponse){
		// Used Hashset to ensure unique doc ids in case duplicate document present in collection
		HashSet<String> docIdsSingleQuery = new HashSet<String>();
		
		if(queryResponse != null && queryResponse.getResults() != null ) {
			for( QueryResult result : queryResponse.getResults() ) {
				String docId = (String) result.getOrDefault("DOCID", "0");
				docIdsSingleQuery.add(docId);
			}
		}
		
		if(docIdsSingleQuery.size() == 0) {
			// Lets keep dummy result instead of empty space
			docIdsSingleQuery.add("0");
		}
		
		return docIdsSingleQuery;
	}
	
	String seralizeWithComma(HashSet<String> docIds) {
		StringBuffer serializedDocIds = new StringBuffer();
		for(String docId : docIds) {
			serializedDocIds.append(docId);
			serializedDocIds.append(',');
		}
		
		return serializedDocIds.toString();
	}
}
