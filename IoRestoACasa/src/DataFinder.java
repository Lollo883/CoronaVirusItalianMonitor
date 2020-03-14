import java.util.ArrayList;

public class DataFinder {
	
	ArrayList <DailyData> data = new ArrayList <DailyData> ();
	DailyData singleDay ;
	DataParser dataParser;
	static String region="";
	public static void setRegion(String regiond)
	{
		region=regiond;
	}
	public String getRegion()
	{
		return region;
	}
	
	public DataFinder() {
		dataParser=new DataParser();
	}
	
	
	public ArrayList <DailyData> getData()
	{
		data = dataParser.parse();
		return data;
	}
	public DailyData getLastDayInfo()
	{
		data = dataParser.parse();
		return data.get(data.size()-1);
	}
	
	public ArrayList<DailyData> getRegionData(String region)
	{
		return dataParser.parseRegion(region);
	}

}
