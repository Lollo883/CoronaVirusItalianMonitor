import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataDownloader {
	
	int status = 0 ;
	String line = "";
	URL urlData;
	BufferedReader reader; 
	StringBuffer nationalData;
	StringBuffer regionalData;
	//StringBuffer content =  new StringBuffer();

	
	DataDownloader ()
	{
		
		nationalData=this.dataDownload("https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-andamento-nazionale.json");
		regionalData=this.dataDownload("https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-regioni.json");		
		
	}
	
	public StringBuffer dataDownload( String url )
	{
		StringBuffer content = new StringBuffer ();
		
		try {
			urlData = new URL (url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			HttpURLConnection connection = (HttpURLConnection) urlData.openConnection();
			connection.setInstanceFollowRedirects(true);
			status = connection.getResponseCode();
			//System.out.println("Stato = " + status);
			if (status==200)
			{
				int i = 0 ;
				reader = new BufferedReader (new InputStreamReader(connection.getInputStream()));

				while ((line=reader.readLine()) != null)
				{
					content.append(line);
					content.append("\n");
				}
					
			}
			connection.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}
	
	public StringBuffer getNationalData ()
	{
		return nationalData;
	}
	
	public StringBuffer getRegionalData()
	{
		return regionalData;
	}
	
		

}
