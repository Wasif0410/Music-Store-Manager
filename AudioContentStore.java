import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

// Name: Wasif Saeed
// ID: 501182244

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents; 
		private HashMap<String, Integer> title_info;
		private HashMap<String, ArrayList<Integer>> artist_info;
		private HashMap<String, ArrayList<Integer>> genre_info;
			
		public AudioContentStore()
		{
			
			title_info = new HashMap<String, Integer>();
			artist_info = new HashMap<String, ArrayList<Integer>>();
			genre_info = new HashMap<String, ArrayList<Integer>>();
			contents = createAudioContents();
			
		}

		
		private ArrayList<AudioContent> createAudioContents(){

			ArrayList<AudioContent> audioContents = new ArrayList<>();


			try{

				// create file and file reader via scanner
				File file = new File("store.txt");
				Scanner in = new Scanner(file);

				// while loop to read from file
				while(in.hasNextLine()){

					// Get next the type
					String type = in.nextLine();

					// set index to audiocontent.size() as that tells you what position or index you are in
					int index = audioContents.size();

					// If type is song
					if(type.equals(Song.TYPENAME)){

						// BUFFER MESSAGE
						System.out.println("Loading " + type);

						// RETRIEVE INFORMATION
						String id = in.nextLine();
						String title = in.nextLine();
						int year = Integer.valueOf(in.nextLine());		// TURN STRING INPUT TO INTEGER
						int length = Integer.valueOf(in.nextLine());	// TURN STRING INPUT TO INTEGER
						String artist = in.nextLine();
						String composer = in.nextLine();
						String genre = in.nextLine();
						int number_of_lyrics = Integer.valueOf(in.nextLine());	// TURN STRING INPUT TO INTEGER
						String lyrics = "";
						
						// Add to lyrics by running a for loop of number of lyrics and adding each line
						for (int i = 0; i < number_of_lyrics; i++)
						{
							lyrics += in.nextLine() + "\n";
						}

						// TITLES

						// add title to hashmap and given index
						title_info.put(title.toUpperCase(), index);


						// ARTISTS

						// if the artist name is NOT already in the hashmap
						if(!artist_info.containsKey(artist.toUpperCase())){

							//initialize arraylist
							ArrayList<Integer> INX = new ArrayList<Integer>();

							//add index to arraylist
							INX.add(index);

							//put in key and value in map using artistname and index its at
							artist_info.put(artist.toUpperCase(), INX);

						}

						// when the artist name IS in the hashmap
						else{

							// get value that corresponds to the key(for certain artist name) and add index to it
							artist_info.get(artist.toUpperCase()).add(index);

							
						}


						// GENRES

						// if the genre is NOT already in the hashmap
						if(!genre_info.containsKey(genre.toUpperCase())){

							//initialize arraylist
							ArrayList<Integer> INX = new ArrayList<Integer>();

							//add index to arraylist
							INX.add(index);

							//put in key and value in map using artistname and index its at
							genre_info.put(genre.toUpperCase(), INX);

						}

						// when the genre IS in the hashmap
						else{
							// get value that corresponds to the key(for certain genre) and add integer index to it
							genre_info.get(genre.toUpperCase()).add(index);

							
						}

						// ADD INFO TO AUDIOCONTENTS
						audioContents.add(new Song(title, year, id, Song.TYPENAME, lyrics, length, artist, composer, Song.Genre.valueOf(genre), lyrics));
					}

					// If type is audiobook
					else if (type.equals(AudioBook.TYPENAME))
					{

						// BUFFER MESSAGE
						System.out.println("Loading " + type);

						// RETRIEVE INFORMATION
						String id = in.nextLine();
						String title = in.nextLine();
						int year = Integer.valueOf(in.nextLine());
						int length = Integer.valueOf(in.nextLine());	
						String author = in.nextLine();
						String narrator = in.nextLine();
						ArrayList<String> chapterTitles = new ArrayList<>();
						ArrayList<String> chapters = new ArrayList<>();

						int number_of_chapters = Integer.valueOf(in.nextLine());
						// add to chapter titles by running a for loop on number of chapters and adding every corresponding line
						for (int i = 0; i < number_of_chapters; i++)
						{
							chapterTitles.add(in.nextLine());
						}
						
						// add to chapters by running a for loop on number of lines in chapters and adding every corresponding line
						for (int i = 0; i < chapterTitles.size(); i++)
						{	
							// Initialize variable chap used for the information in the chapter
							String chap = "";

							// Create a integer for lines which gets the integer value of how many lines in next chapter
							int lines_in_chapter = Integer.valueOf(in.nextLine());

							// Run a forloop for the lines in chapter and collect every corresponding line
							for(int j = 0; j<lines_in_chapter; j++){
								chap += in.nextLine() + "\n";

							}
							
							// add the corresponding chapter information in chap to chapters arraylist
							chapters.add(chap);
						}
						
						// TITLES

						// add title to hashmap and given index
						title_info.put(title.toUpperCase(), index);


						// AUTHORS

						// if the author name is NOT already in the hashmap
						if(!artist_info.containsKey(author.toUpperCase())){

							//initialize arraylist
							ArrayList<Integer> indexes = new ArrayList<Integer>();

							//add index to arraylist
							indexes.add(index);

							//put in key and value in map using author name and index its at
							artist_info.put(author.toUpperCase(), indexes);

							
						}

						// when the author name IS in the hashmap
						else{

							// get value that corresponds to the key(for certain artist name) and add index to it
							artist_info.get(author.toUpperCase()).add(index);

						}
						// ADD INFO TO AUDIOCONTENT
						audioContents.add(new AudioBook(title, year, id, AudioBook.TYPENAME, "", length, author, narrator, chapterTitles, chapters));
				}

				}
				
				//close the file reader
				in.close();
			}

			// CATCH ANY EXCEPTIONS
			catch(FileNotFoundException e){
				System.out.println("NO SUCH FILE EXISTS");
			}

			// RETURN AUDIOCONTENTS
			return audioContents;

		}

		// 	METHOD FOR SEARCH TITLE
		public void search_title(String title){
			
			// if the title is a key in the hashmap
			if(title_info.containsKey(title.toUpperCase())){

				// set index to value corresopnding to the key in title_info
				int index = title_info.get(title.toUpperCase());

				// print out info of contents
				System.out.print((index+ 1) + ". ");
				contents.get(index).printInfo();
				
			}
			// else for if its not a title in hashmap
			else{
				// Throw exception
				throw new AudioContentNotFoundException("No such title " + title);
			}

		}

		// METHOD FOR SEARCH ARTIST
		public void search_artist(String artist){

			// get indexes of hashmap for artists
			ArrayList<Integer> indexes = artist_info.get(artist.toUpperCase());

			// check if the artist name key is in the hashmap
			if (artist_info.containsKey(artist.toUpperCase()))
			{
				
				// run for loop for indexes EX. [1,3,5]
				for(int i : indexes){
					
					// print out index + 1 as index starts 0
					System.out.print((i+1) + ". ");

					// print info of contents
					contents.get(i).printInfo();
					System.out.println("");

				}
				
			}

			// if artist name is not in hashmap
			else{

				// throw exception
				throw new AudioContentNotFoundException("No such artist " + artist);
			}
		
		}

		// METHOD FOR SEARCHING GENRE
		public void search_genre(String genre){

			// get indexes of hashmap for genre
			ArrayList<Integer> indexes = genre_info.get(genre.toUpperCase());

			// check if the genre key is in the hashmap
			if (genre_info.containsKey(genre.toUpperCase()))
			{
				
				// run for loop for indexes EX. [1,3,5]
				for(int i : indexes){

					// print out index + 1 as index starts 0
					System.out.print((i+1) + ". ");
					contents.get(i).printInfo();

					// print info of contents
					System.out.println("");
				}
			}

			// if genre is not in hashmap
			else{

				// throw exception
				throw new AudioContentNotFoundException("No such genre " + genre);
			}
		
		}


		// METHOD FOR RETRIEVED GENRE HASHMAP VALUE
		public ArrayList<Integer> genreDownload(String genre){

			// check if genre is in the hashmap
			if(genre_info.containsKey(genre.toUpperCase())){

				// get indexes of hashmap for genre
				ArrayList<Integer> indexes = genre_info.get(genre.toUpperCase());
				return indexes ;	//return indexes
			}

			// if genre not in hashmap
			else{

				// throw exception
				throw new FaultyValueException("Enter Valid Genre");
			}

			}
		
		// METHOD FOR RETRIEVING ARTIST HASHMAP VALUE
		public ArrayList<Integer> artistDownload(String artist){

			// check if artist is in the hashmap
			if(artist_info.containsKey(artist.toUpperCase())){

				// get indexess of hashmap for artist
				ArrayList<Integer> indexes = artist_info.get(artist.toUpperCase());
				return indexes ;	//return indexes
				}
			
			// if artist not in hashmap
			else{
				// throw exception
				throw new FaultyValueException("Enter Valid Artist");
			}

        }


		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print(index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		
		
}
