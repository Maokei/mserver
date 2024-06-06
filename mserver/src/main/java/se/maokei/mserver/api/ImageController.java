package se.maokei.mserver.api;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.maokei.mserver.model.Photo;
import se.maokei.mserver.services.PhotoService;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ImageController {
    private final PhotoService photoService;

    @PostMapping("/image")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        String id = photoService.addPhoto(title, image);
        return "redirect:/image/" + id;
    }

    @Timed
    @GetMapping("/image/{id}")
    public String getPhoto(@PathVariable UUID id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        //TODO fix base64
        //model.addAttribute("image",
        //        Base64.getEncoder().encodeToString(photo.getImage().getData()));
        model.addAttribute("image", "todo");
        return "photos";
    }
}
