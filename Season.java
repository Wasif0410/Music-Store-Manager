import java.util.ArrayList;

// Name: Wasif Saeed
// ID: 501182244

/*
 * Simple class to model podcast or other content seasons
 */
public class Season
{
	public ArrayList<String> episodeFiles;
	public ArrayList<String> episodeTitles;
	public ArrayList<Integer> episodeLengths;
	
	public Season()
	{
		episodeFiles = new ArrayList<String>();
		episodeTitles = new ArrayList<String>();
		episodeLengths = new ArrayList<Integer>();
	}
}
