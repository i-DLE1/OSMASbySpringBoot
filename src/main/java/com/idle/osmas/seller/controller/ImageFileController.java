package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectFileDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import com.idle.osmas.seller.service.RegistProjectService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Controller
@RequestMapping("files")
public class ImageFileController {

    @Value("${customSaveFileDirectoryPath}")
    String SAVE_FILE_DIRECTORY_PATH;

    public ImageFileController(RegistProjectService registProjectService) {
        this.registProjectService = registProjectService;
    }

    private final RegistProjectService registProjectService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void registFile(ProjectFileType fileType, MultipartFile file) throws IOException {
        if(file.getSize() > 0){
            String originFileName = file.getOriginalFilename();

            log.info("originFileName = " + originFileName);

            String ext = originFileName.substring(originFileName.lastIndexOf("."));

            log.info("ext = " + ext);

            String savedFileName = UUID.randomUUID().toString().replace("-","")+ext;

            File savedFile = new File(SAVE_FILE_DIRECTORY_PATH+"/"+savedFileName);
            file.transferTo(savedFile);

            int result = 0;

            Integer projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");
            result = registProjectService.insertProjectFile(fileType,originFileName,savedFileName,"N",projectNo);

            if (result > 0){
                log.info("파일이 정상으로 저장 됐습니다.");
            }else {
                log.info("정상적으로 ");
                savedFile.delete();
            }

        }
    }

    @GetMapping(value = "/seller/project/{file}")
    public ResponseEntity<?> fileLoads(@PathVariable String file) {

        ProjectFileDTO projectFile =  registProjectService.selectByProjectSaveFileName(file, 53);

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
