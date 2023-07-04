package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.ProjectFileDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import com.idle.osmas.seller.service.ProjectFileService;
import com.idle.osmas.seller.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Controller
@RequestMapping("files")
public class ImageFileController {

    @Value("${customSaveFileDirectoryPath}")
    String SAVE_FILE_DIRECTORY_PATH;


    private final ProjectService projectService;

    private final ProjectFileService projectFileService;

    public ImageFileController(ProjectService projectService, ProjectFileService projectFileService) {
        this.projectService = projectService;
        this.projectFileService = projectFileService;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param fileType
     * @param file
     * @param projectNo
     * @return success 1, fail 0;
     * @throws IOException
     */
    public int registFile(ProjectFileType fileType, MultipartFile file, int projectNo) throws IOException {

        if(file.getSize() > 0) {
            String originFileName = file.getOriginalFilename();

            log.info("originFileName = " + originFileName);

            String ext = originFileName.substring(originFileName.lastIndexOf("."));

            log.info("ext = " + ext);

            String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;


            File savedFile = new File(SAVE_FILE_DIRECTORY_PATH + "/" + savedFileName);

            if (!savedFile.isDirectory()) savedFile.mkdirs();

            file.transferTo(savedFile);

            int result = 0;

            result = projectFileService.insertProjectFile(fileType, originFileName, savedFileName, "N", projectNo);

            if (result > 0) {
                log.info("파일이 정상으로 저장 됐습니다.");
                return 1;
            } else {
                log.info("정상적으로 ");
                savedFile.delete();
                return 0;
            }
        }else {
            return 0;
    }
}



    public int deleteFile(String file) {
        System.out.println("file = " + file);
        File deleteFile = new File(SAVE_FILE_DIRECTORY_PATH + "/" + file);

        if(deleteFile.isFile()) {
            deleteFile.delete();
            projectFileService.updateNonAvailableProjectFileByChangeName(file);
        }

        return 1;
    }

    @GetMapping(value = "/seller/project/{file}")
    public ResponseEntity<?> fileLoads(@PathVariable String file) {

        ProjectFileDTO projectFile =  projectFileService.selectByProjectSaveFileName(file);

        if (projectFile == null) return ResponseEntity.ok("");
        Path saveFile = new File(SAVE_FILE_DIRECTORY_PATH+"/"+projectFile.getChangeName()).toPath();
        log.info("saveFile"+ saveFile.toString());

        FileSystemResource resource = new FileSystemResource(saveFile);

        try {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(saveFile))).body(resource);
        } catch (IOException e) {
            return ResponseEntity.ok("");
        }

    }
}
