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
    		FileUploadClient uploadClient = new FileUploadClient();
    		System.out.println(uploadClient.uploadFile(args[0]));
    		
			QueryFileProcessor queryFileProcessor = new QueryFileProcessor();
			queryFileProcessor.processQueryFile(args[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println( "DONE");
    }
}
