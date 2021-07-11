package challenge.javarestful;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
class Image {
    private @Id @GeneratedValue Long id;
    private String title;
    private String description;

    @ManyToMany(targetEntity=Album.class)
    private List<Album> albums = new ArrayList<Album>();

    Image() {}

    Image(String title, String description){
        this.title = title;
        this.description = description;
    }

    // getters
    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public List<Album> getAlbums(){
        return this.albums;
    }

    // setters
    public void setId(Long id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setAlbum(Album album){
        this.albums.add(album);
    }
}