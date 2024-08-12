package projet.gtssystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projet.gtssystem.entities.FileData;
import projet.gtssystem.repositories.FileDataRepository;

import java.io.File;
import java.io.IOException;

@Service

public class StorageService {
    private final String FOLDER_PATH="C:/Users/ahmed/OneDrive/Desktop/images/";
@Autowired
    FileDataRepository fileDataRepository;


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            System.out.println("no file");
        }
        System.out.println(file.getOriginalFilename());
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

}
