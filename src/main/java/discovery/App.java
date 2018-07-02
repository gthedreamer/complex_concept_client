package discovery;

import discovery.processor.QueryFileProcessor;
import discovery.restclient.FileUploadClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
    		if(args.length != 2 ) {
    			System.out.println("Invalid command!! \n"+ "Correct format jarfile <absolute path day_one_set file>  <absolute path query file");
    			return;
    		}
    		
    		FileUploadClient uploadClient = new FileUploadClient();
    		System.out.println(uploadClient.uploadFile(args[0]));
    		
			QueryFileProcessor queryFileProcessor = new QueryFileProcessor();
			queryFileProcessor.processQueryFile(args[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
