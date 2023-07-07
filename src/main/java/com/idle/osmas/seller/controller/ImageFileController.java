package com.idle.osmas.seller.controller;

import com.idle.osmas.member.dto.UserImpl;
import com.idle.osmas.seller.dto.ProjectFileDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import com.idle.osmas.seller.service.ProjectFileService;
import com.idle.osmas.seller.service.ProjectService;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("files")
public class ImageFileController {

    @Value("${saveFileDirectoryPath}")
    String SAVE_FILE_DIRECTORY_PATH;

    private final ProjectFileService projectFileService;

    private final ProjectService projectService;

    private final ResourceLoader resourceLoader;


    public ImageFileController(ProjectFileService projectFileService,
                               ProjectService projectService,
                               ResourceLoader resourceLoader) {

        this.projectFileService = projectFileService;
        this.projectService = projectService;
        this.resourceLoader = resourceLoader;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param fileType
     * @param file
     * @param projectNo
     * @return fail : "fail", success :  "saveFileName"
     * @throws IOException
     */
    public String saveFile(ProjectFileType fileType, MultipartFile file, int projectNo) {
        if (file.getSize() == 0) return "fail";

        String originFileName = file.getOriginalFilename();

        String ext = originFileName.substring(originFileName.lastIndexOf("."));

        String savedFileName = fileType.toString().toLowerCase() + "_" + UUID.randomUUID().toString().replace("-", "") + ext;

//        File savedFile = new File(SAVE_FILE_DIRECTORY_PATH + "/" + savedFileName);

        File savedFile = new File(SAVE_FILE_DIRECTORY_PATH + "/"+"project" +"/" + projectNo + "/" + savedFileName);

        File saveDirectory = new File(SAVE_FILE_DIRECTORY_PATH+"/"+"project" +"/" + projectNo + "/");

        if (!saveDirectory.exists()) saveDirectory.mkdirs();

        try {

            Thumbnails.of(file.getInputStream()).size(400, 400).toFile(savedFile);

            if(ProjectFileType.BODY.equals(fileType)) return savedFileName;

            int result = projectFileService.insertProjectFile(
                    ProjectFileDTO.builder().projectNo(projectNo)
                    .deleteYN('N').changeName(savedFileName)
                    .originName(originFileName)
                    .type(fileType).build());

            if(result > 0 ) return savedFileName;

            return "fail";
        } catch (IOException e) {

            savedFile.delete();
            return "fail";
        }

}
    /**
     *
     * @param file cahngeFileName
     * @return success 1 fail 0
    r */
    public int deleteFile(String file) {

        File deleteFile = new File(SAVE_FILE_DIRECTORY_PATH + "/" + file);

        if (deleteFile.isFile()) {
            deleteFile.delete();
            projectFileService.updateNonAvailableProjectFileByChangeName(file);
        }

        return 1;
    }

    @PostMapping("/projectBodyUpload")
    @ResponseBody()
    public Map<String, Object> updateTest(@RequestParam("file-0") MultipartFile file,
                                          @RequestParam(required = false) Integer no,
                                          Principal principal){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null) {
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        Map<String, Object> response = new HashMap<>();
        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> image = new HashMap<>();

        result.add(image);

        String saveFileName = saveFile(ProjectFileType.BODY, file, no);

        if(saveFileName.equals("fail")){
            response.put("errorMessage","파일을 저장할 수 없습니다.");
            return response;
        }

        image.put("url","/files/project/" + no + "/"+ saveFileName);
        result.add(image);
        response.put("result",result);

        System.out.println("result = " + response);
        return response;
    }
}
