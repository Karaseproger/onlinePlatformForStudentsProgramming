package com.shop.shop.polylab.controlers;

import com.shop.shop.polylab.models.postVideo;
import com.shop.shop.polylab.repoziory.PostVideoRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class VideoController {

    @Autowired
    private PostVideoRepozitory videoRepository;

    @PostMapping("/uploadVideo")
    public String uploadVideo(@RequestParam("videoFile") MultipartFile videoFile, @RequestParam String  description , String title, Model model) {
        if (videoFile.isEmpty()) {
            model.addAttribute("message", "Пожалуйста, выберите видео для загрузки.");
            return "uploadForm";
        }
        try {
            // Задаем путь для сохранения файлов (путь будет относительным к проекту)
            String uploadDirectory =("C:/uploads/videos/"); // Это будет папка внутри вашего проекта
            Path uploadPath = Paths.get(uploadDirectory);

            // Проверяем, существует ли директория, если нет — создаем её
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Сохраняем видеофайл
            String fileName = videoFile.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            videoFile.transferTo(filePath.toFile());

            postVideo video = new postVideo();
            video.setFileName(fileName);
            video.setFilePath(filePath.toString());
            video.setUploadDate(LocalDateTime.now());
            video.setDescription(description);
            video.setTitle(title);
            videoRepository.save(video);

            model.addAttribute("message", "Видео успешно загружено.");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Ошибка при загрузке видео: " + e.getMessage());
        }
        return "uploadStatus";
    }



    @GetMapping("/upload")
    public String showUploadForm() {
        return "uploadVideo"; // Возвращает HTML-шаблон с формой
    }


    @GetMapping("/watch")
    public String watch(Model model){
        model.addAttribute("postVideo", "вабалабадапдап" );
        return "defaultWatchPage";
    }
    @GetMapping("/watch/{id}")
    public String watchVideo(@PathVariable("id") Long id, Model model) {
        postVideo video = videoRepository.findById(id).orElse(null);
        if(video == null){
            model.addAttribute("postVideo", video);
            return "errorPage";
        }
        model.addAttribute("filePath", "/videos/" + video.getFileName() + video.getDescription() + video.getTitle());
        return "watchVideo";  // Страница по умолчанию
    }
    @GetMapping("/videos")
    public String listVideos(Model model) {
        List<postVideo> video = videoRepository.findAll(); // Извлекаем все видео из репозитория
        System.out.println("Загруженные видео: " + video);
        model.addAttribute("videos", video); // Добавляем список видео в модель
        return "videoList"; // Возвращаем имя HTML-шаблона, который будет отображать видео
    }

    @GetMapping("/videos/{filename}")
    public ResponseEntity<FileSystemResource> getVideo(@PathVariable String filename) {
        File file = new File("/uploads/videos/" + filename);
        if (file.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(new FileSystemResource(file));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

