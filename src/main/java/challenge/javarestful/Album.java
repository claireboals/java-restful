package challenge.javarestful;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
class Album {
    private @Id @GeneratedValue Long id;
    private String description;

    @ManyToMany(targetEntity=Image.class)
    private List<Image> images = new ArrayList<Image>();

    Album() {}

    Album(String description){
        this.description = description;
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

    public void setImages(Image image){
        this.images.add(image);
    }
}