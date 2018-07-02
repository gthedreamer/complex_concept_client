package discovery.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResult;

public class ComplexConceptQueryClient {
		private static final String BASE_URL = "http://localhost:9080/api/discovery/query?";
		private static int MAX_QUERY_RESULT_COUNT = 30;
		
		// HTTP GET request
		public QueryResponse sendGet(String queryString) throws Exception {
			QueryResponse queryResponse = null;
			
			String completeURLStr = BASE_URL + "count="+MAX_QUERY_RESULT_COUNT+"&queryString="+URLEncoder.encode(queryString, "UTF-8");
			URL url = new URL(completeURLStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// optional default is GET
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + completeURLStr);
			System.out.println("Response Code : " + responseCode);

			if(responseCode == 200) {
				queryResponse = mapResponse(connection);
			}else {
				System.out.println("GET Request failed. http response : "+responseCode);
			}
			
			return queryResponse;
		}
		
		public QueryResponse mapResponse(HttpURLConnection connection) {
			QueryResponse queryResponse = null;
			
			try {
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				//print result
//				System.out.println(response.toString());
				Gson gson = new Gson();
				queryResponse = gson.fromJson(response.toString(),QueryResponse.class);
			}catch(Exception e) {
				System.out.println("Exception in mapResponse "+e.getMessage());
			}

			
			return queryResponse;
		}
}
