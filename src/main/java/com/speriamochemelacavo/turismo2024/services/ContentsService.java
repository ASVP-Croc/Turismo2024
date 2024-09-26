package com.speriamochemelacavo.turismo2024.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.speriamochemelacavo.turismo2024.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.speriamochemelacavo.turismo2024.models.elements.Content;
import com.speriamochemelacavo.turismo2024.models.elements.ElementWithContents;

@Service
public class ContentsService extends ElementsService<Content>{
	
	private boolean isContentsLoaded;

	public ContentsService() {
		super();
	}
	
	
	public Content add(Content contentToAdd, ElementWithContents referenced, MultipartFile file) throws IOException {
		contentToAdd.setReferenced(referenced);
		contentToAdd.setPathToResource(fileUpload(file, add(contentToAdd)).get());
		return super.add(contentToAdd);
	}

	public boolean isLoaded() {
		return isContentsLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		isContentsLoaded = isLoaded;
	}
	
	private Optional<String> fileUpload(MultipartFile file, Content content) throws IOException{
		String url = "src/main/resources/static/files/" + content.getName() + "-" + content.getId() + "-" + content.getAuthor().getUsername() + ".jpg";
        File newFile=new File(url);
		try {
			newFile.createNewFile();
			FileOutputStream fileOut=new FileOutputStream(newFile);
	        fileOut.write(file.getBytes());
	        fileOut.close();
		} catch (IOException e) {
			throw new IOException("Il file non è stato salvato");
		}
        content.setPathToResource(url);
        return Optional.of(url);
	}
}
