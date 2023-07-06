package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.ProjectFileDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import com.idle.osmas.seller.service.ProjectFileService;
import com.idle.osmas.seller.service.ProjectService;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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

    private final ResourceLoader resourceLoader;

    public ImageFileController(ProjectService projectService, ProjectFileService projectFileService, ResourceLoader resourceLoader) {
        this.projectService = projectService;
        this.projectFileService = projectFileService;
        this.resourceLoader = resourceLoader;
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

            String ext = originFileName.substring(originFileName.lastIndexOf("."));

            String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;


            File savedFile = new File(SAVE_FILE_DIRECTORY_PATH + "/" + savedFileName);

            File saveDirectory = new File(SAVE_FILE_DIRECTORY_PATH);

            if (!saveDirectory.exists()) saveDirectory.mkdirs();

            Thumbnails.of(file.getInputStream()).size(400,400).toFile(savedFile);

            int result = 0;

            result = projectFileService.insertProjectFile(fileType, originFileName, savedFileName, "N", projectNo);

            if (result > 0) {
                return 1;
            } else {
                savedFile.delete();
                return 0;
            }
        }else {
            return 0;
        }
    }

    /**
     *
     * @param file cahngeFileName
     * @return success 1 fail 0
     */
    public int deleteFile(String file) {

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

        FileSystemResource resource = new FileSystemResource(saveFile);

        try {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(saveFile))).body(resource);
        } catch (IOException e) {
            return ResponseEntity.ok("");
        }
    }

    @GetMapping("/images/{type}/{file}")
    public ResponseEntity<?> staticImageLoad(@PathVariable String file, @PathVariable String type){

        Resource resource =  resourceLoader.getResource("classpath:/static/images/" + type + "/" + file);

        System.out.println("resource = " + resource);
        try {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(resource.getFile().toPath()))).body(resource);
        } catch (IOException e) {
            return ResponseEntity.ok("");
        }
    }
}
