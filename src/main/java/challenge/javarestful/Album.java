package challenge.javarestful;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Album {
    private @Id @GeneratedValue Long id;
    private String description;
    private List<Image> images;

    Album() {}

    Album(String name, String description, List<Image> images){
        this.name = name;
        this.description = description;
        this.images = images;
    }

    // getters
    public Long getId(){
        return this.id;
    }

    public String getDescription(){
        return this.description;
    }

    public List<Image> getImages(){
        return this.images;
    }

    // setters
    public void setId(Long id){
        this.id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setImages(List<Image> images){
        this.images = images;
    }
}