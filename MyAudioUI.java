
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

// Name: Wasif Saeed
// ID: 501182244

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your library
		AudioContentStore store = new AudioContentStore();
		
		// Create my music library
		Library library = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				library.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				library.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				library.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				library.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				library.listAllPlaylists(); 
			}
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{	

				try{

					// Variables for first and last index
					int first = 0;
					int last = 0;

				// COLLECT USER INPUT
				System.out.print("From Store Content #: ");
				if (scanner.hasNextInt())
				{
					first = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}

				System.out.print("To Store Content #: ");
				if (scanner.hasNextInt())
				{
					last = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}

				
				// TRY AND CACTCH BLOCK IN CASE THE USER ENTERS A FIRST VALUE BIGGER THAN THE LAST VALUE FOR INDEX
				try{
					if(first>last){
						throw new FaultyValueException("Enter Valid Indexes");
					}
				}
				catch(FaultyValueException e){
					System.out.println(e.getMessage());
				}


				// ADD EVERY VALUE IN RANGE TO DOWNLOAD CATCH ANY EXCEPTIONS
				for(int i=first; i <= last; i++){

					// TRY AND CATCH ANY EXCEPTIONS
					try{
					AudioContent A = store.getContent(i);	// CREATE A AUDIOCONTENT AT EVERY INDEX
					library.download(A);					// DOWNLOAD IT
					}			

					catch(AlreadyDownloadedException e){
						System.out.println(e.getMessage());

					}	
				}
				}

				//CATCH ANY EXCEPTIONS
					catch(FaultyValueException e){
						System.out.println(e.getMessage());
					}

									
			}
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index = 0;

				System.out.print("Song Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				// consume the nl character since nextInt() does not
					scanner.nextLine(); 
				}

				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.playSong(index);
				}

				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());	
				}
				
					
			}
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}

				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.printAudioBookTOC(index);
				}
				
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());	

				}
					
			}
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				}
				int chapter = 0;
				System.out.print("Chapter: ");
				if (scanner.hasNextInt())
				{
					chapter = scanner.nextInt();
					scanner.nextLine();
				}

				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.playAudioBook(index, chapter);
				}

				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());	

				}
			
					
			}
			
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				int index = 0;
				int season = 0;
				
				System.out.print("Podcast Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				}
				System.out.print("Season: ");
				if (scanner.hasNextInt())
				{
					season = scanner.nextInt();
					scanner.nextLine();
				}

				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.printPodcastEpisodes(index, season);
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());	
				}
				
			
			}
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				int index = 0;

				System.out.print("Podcast Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				int season = 0;
				System.out.print("Season: ");
				if (scanner.hasNextInt())
				{
					season = scanner.nextInt();
					scanner.nextLine();
				}
				int episode = 0;
				System.out.print("Episode: ");
				if (scanner.hasNextInt())
				{
					episode = scanner.nextInt();
					scanner.nextLine();
				}

				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.playPodcast(index, season, episode);
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}

					
			}
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}

				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.playPlaylist(title);
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}

			}
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String title = "";
        int index = 0;
        
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				System.out.print("Content Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}

				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.playPlaylist(title, index);
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}

				
			}
			// Delete a song from the library and any play lists it belongs to
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int songNum = 0;

				System.out.print("Library Song #: ");
				if (scanner.hasNextInt())
				{
					songNum = scanner.nextInt();
					scanner.nextLine();
				}
				
				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.deleteSong(songNum);
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}

			}
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}

				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.makePlaylist(title);
				}
				catch(AlreadyDownloadedException e){
					System.out.println(e.getMessage());
				}

			
			}
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					title = scanner.nextLine();

					
				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.printPlaylist(title);
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}

			
			}
			// Add content from library (via index) to a playlist
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				int contentIndex = 0;
				String contentType = "";
        String playlist = "";
        
        System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					playlist = scanner.nextLine();
        
				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
				if (scanner.hasNextLine())
					contentType = scanner.nextLine();
				
				System.out.print("Library Content #: ");
				if (scanner.hasNextInt())
				{
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				
				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.addContentToPlaylist(contentType, contentIndex, playlist);
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}

			
			}
			// Delete content from play list
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				int contentIndex = 0;
				String playlist = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					playlist = scanner.nextLine();
				
				System.out.print("Playlist Content #: ");
				if (scanner.hasNextInt())
				{
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}

				// TRY COMMAND AND CATCH ANY EXCEPTIONS
				try{
					library.delContentFromPlaylist(contentIndex, playlist);
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}
	
			}

			else if(action.equalsIgnoreCase("SEARCH")){

				// GET USER INPUT
				String title = "";
				System.out.print("Title: ");
				if (scanner.hasNextLine())
					title = scanner.nextLine();
					

					// TRY COMMAND AND CATCH ANY EXCEPTIONS
					try{
					store.search_title(title);
				
					}

					catch(AudioContentNotFoundException e){
						System.out.println(e.getMessage());

					}

			}

			else if(action.equalsIgnoreCase("SEARCHA")){

				// GET USER INPUT
				String artist = "";
				System.out.print("Artist: ");
				if (scanner.hasNextLine())
				artist = scanner.nextLine();
					

					// TRY COMMAND AND CATCH ANY EXCEPTIONS
					try{
					store.search_artist(artist);
				
					}

					catch(AudioContentNotFoundException e){
						System.out.println(e.getMessage());

					}

			}

			else if(action.equalsIgnoreCase("SEARCHG")){

				// GET USER INPUT
				String genre = "";
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]:  ");
				if (scanner.hasNextLine())
					genre = scanner.nextLine();
					

					// TRY COMMAND AND CATCH ANY EXCEPTIONS
					try{
					store.search_genre(genre);
					}

					catch(AudioContentNotFoundException e){
						System.out.println(e.getMessage());

					}

			}

			else if(action.equalsIgnoreCase("DOWNLOADA")){

				try{

					// GET USER INPUT
					String artist = "";
					System.out.print("Artist Name: ");
					if (scanner.hasNextLine())
						artist = scanner.nextLine();
						
						// DECLARE ARRAYLIST FOR INDEXES OF ARTISTS BY CALLING ARTISTDOWNLOAD METHOD
						ArrayList<Integer> art_indexes = store.artistDownload(artist);

						// RUN THROUGH INDEXES
						for(int i: art_indexes){	
							
							// TRY AND CATCH ANY EXCEPTIONS
							try{
								AudioContent A = store.getContent(i + 1);	// do index + 1 as getcontent does -1 for index anyways
								library.download(A);					// download it
							}

							catch(AlreadyDownloadedException e){
								System.out.println(e.getMessage());
							}
					}
						
					}
				// CATCH ANY EXCEPTIONS
				catch(FaultyValueException e){
						System.out.println(e.getMessage());
					}

			}

			else if(action.equalsIgnoreCase("DOWNLOADG")){

				
					try{

					// GET USER INPUT	
					String genre = "";
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					if(scanner.hasNextLine())
						genre = scanner.nextLine();
					

					// DECLARE ARRAYLIST FOR INDEXES OF ARTISTS BY CALLING GENREDOWNLOAD METHOD
					ArrayList<Integer> genre_indexes = store.genreDownload(genre);

					// RUN THROUGH INDEXES
					for(int i: genre_indexes){

					// TRY AND CATCH ANY EXCEPTIONS
					try{
						AudioContent G = store.getContent(i + 1);	// do index + 1 as getcontent does -1 for index anyways
						library.download(G);						// download it
						}

						catch(AlreadyDownloadedException e){
							System.out.println(e.getMessage());
						}
					}
					}

					// CATCH ANY EXCEPTIONS
					catch(FaultyValueException e){
						System.out.println(e.getMessage());

					}

			}

			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				library.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				library.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				library.sortSongsByLength();
			}

			System.out.print("\n>");
		}
	}
}
