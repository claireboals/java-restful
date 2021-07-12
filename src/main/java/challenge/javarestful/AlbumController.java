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
class AlbumController {
    
    private final AlbumRepository repository;

    AlbumController(AlbumRepository repository){
        this.repository = repository;
    }

    // gets all albums
    @GetMapping("/albums")
    List<Album> all(){
        return repository.findAll();
    }

    // create a new album
    @PostMapping(path="/albums", consumes="application/json", produces="application/json")
    Album newAlbum(@RequestBody Album newAlbum){
        return repository.save(newAlbum);
    }

}