package challenge.javarestful;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ImageController {
    
    private final ImageRepository repository;

    ImageController(ImageRepository repository){
        this.repository = repository;
    }

    // gets all images
    @GetMapping("/images")
    List<Image> all(){
        return repository.findAll();
    }

    // GET image by id
    @GetMapping("/images/{id}")
    Image one(@PathVariable Long id){
        return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id));
    }

    // create a new image
    @PostMapping(path="/images", consumes="application/json", produces="application/json")
    Image newImage(@RequestBody Image newImage){
        return repository.save(newImage);
    }

}