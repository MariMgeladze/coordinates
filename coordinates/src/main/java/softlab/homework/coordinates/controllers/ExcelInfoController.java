package softlab.homework.coordinates.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import softlab.homework.coordinates.entities.ExcelInfo;
import softlab.homework.coordinates.services.FileService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping ("excelData")
public class ExcelInfoController {
    @Autowired
    private FileService fileService;

    @PostMapping ("upload")
    public ResponseEntity<?>uploadExcelInfo(@RequestParam("file")MultipartFile file){
        fileService.save(file);
        return ResponseEntity.ok(Map.of("message", "Data uploaded successfully and saved in Database"));

    }

    @GetMapping
    public ResponseEntity<List<ExcelInfo>> getExcelInfo(){
        return new ResponseEntity<>(fileService.getAllExcelInfo(),HttpStatus.FOUND);
    }


}
