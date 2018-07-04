package discovery;

import discovery.processor.QueryFileProcessor;
import discovery.restclient.FileUploadClient;

/**
 * App
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
    		if(args.length != 2 ) {
    			System.out.println("Please follow the below format !! \n"+ "java -jar target/complex-concept-client-0.0.1-SNAPSHOT.jar day_one_set.txt  query_file.txt");
    			return;
    		}
    		
    		FileUploadClient uploadClient = new FileUploadClient();
    		String response = uploadClient.uploadFile(args[0]);
    		System.out.println("\n\n**************************** Day 1 Set uploaded *****************************"+ response);
    		
			QueryFileProcessor queryFileProcessor = new QueryFileProcessor();
			queryFileProcessor.processQueryFile(args[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
