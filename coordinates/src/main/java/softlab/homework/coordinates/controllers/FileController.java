package softlab.homework.coordinates.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import softlab.homework.coordinates.services.FileService;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/files")

public class FileController {

    @GetMapping()
    public List<String> getFiles() {
        File file = new File("D:\\upload file\\");
        var ans = new ArrayList<String>();
        if (file != null && file.isDirectory() && file.listFiles() != null) {
            for (var f : file.listFiles()) {
                ans.add(f.getName())
                ;
            }
        }
        return ans;
    }

    ///  @GetMapping("{fileName}")
    // public  ResponseEntity<?> getFile(@PathVariable String fileName, HttpServletResponse response){
  //  var prefix ="C:\Users\Marika\java\projects\file upload";
    //   File f = new File(prefix  + fileName);
    //var abs = f.getCanonicalPath();
    // if(!abs.startsWith(prefix)) {
    //      return ResponseEntity.notFound().build();
    //   }
   // if(!f.exists()){
     //   return ResponseEntity.notFound().build();
  //  }
    // try
    //  {
    //    MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
    //   response.setHeader(Http Headers.CONTENT_TYPE, fileTypeMap.getContentType(f));
    //  response.setHeader(Http Headers.CONTENT_DISPOSITION, value "attachment; fileName = \"" + f.getName() + "\"";
     // response.getOutputStream().write(new FileInputStream(f).readAllBytes());
    // }
    //  catch (Exception e) {
    //  e.printStackTrace();
    //  }
    //      return ResponseEntity.notFound().build();
    //    }
    @PostMapping
    public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            var prefix = "\\Users\\Marika\\java\\projects\\";
            File f = new File(prefix + file.getOriginalFilename());
            var abs = f.getCanonicalPath();

            if (!abs.startsWith(prefix)) {
                return ResponseEntity.notFound().build();
            }
            try (var is = file.getInputStream()) {
                Files.copy(is, f.toPath());
            }
            return ResponseEntity.ok().build();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }


    // @GetMapping("test")
    //  public void test() {
    //     File f = new File("D:\\upload file\\test.txt");
    //   try {
    //      var out=new PrintWriter(new FileOutputStream(f));
    //     out.println(555);
    //     out.flush();
    // } catch (
    //        FileNotFoundException e){
    //   throw new RuntimeException(e);
    //  }
}


// @Autowired
// private  FileService fileService;

//@PostMapping
// public void fileUpload(@RequestParam("file") MultipartFile file )throws IOException {
//   fileService.uploadFile(file);
// }





//}
