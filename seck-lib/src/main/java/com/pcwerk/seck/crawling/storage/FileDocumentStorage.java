package com.pcwerk.seck.crawling.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;
//import java.nio.Buffer;
import com.google.gson.Gson;
import com.pcwerk.seck.crawling.entities.ParsedDocument;

public class FileDocumentStorage {
	private ArrayList<ParsedDocument> documentBuffer;
	public FileDocumentStorage(){
		documentBuffer = new ArrayList<ParsedDocument>();
	}
	/**
	 * Loads the storage file that contains the saved
	 * 
	 * @param filePath
	 *          Path of the storage file
	 * @return true if loading of storage file is successful; false, otherwise
	 */
	public boolean loadStorage(String filePath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("storage.txt"));
			br.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Temporarily saves the document into a buffer for later writing to the
	 * individual files and
	 * 
	 * @param document
	 * @return true if saving to buffer is successful; false, otherwise
	 */
	public boolean save(ParsedDocument document) {
		for(int i=0;i<documentBuffer.size();i++){
			if(documentBuffer.get(i).getUrl().equals(document.getUrl())){
				return false;
			}
		}
		documentBuffer.add(document);
		if(documentBuffer.size()==50){
			flush(documentBuffer);
			documentBuffer = new ArrayList<ParsedDocument>();			
		}
		return true;
	}

	/**
	 * Check if the URL a parsed document already exists in the file.
	 * 
	 * @param url
	 * @return true if URL exists; false, otherwise
	 */
	public boolean contains(String url, Set<String> urlList) {
		return urlList.contains(url);
	}

	/**
	 * Appends the contents of the parsed document buffer to the storage file and
	 * empties it afterwards.
	 * 
	 * @return
	 */
	public void flush(ArrayList<ParsedDocument> document) {
		Gson gson = new Gson();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("storage.txt"));
			bw.write(gson.toJson(document));
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}