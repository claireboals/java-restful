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
class ProductController {
    
    private final ProductRepository repository;

    ProductController(ProductRepository repository){
        this.repository = repository;
    }

    // gets all products
    @GetMapping("/products")
    List<Product> all(){
        return repository.findAll();
    }

    // create a new product
    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct){
        return repository.save(newProduct);
    }

}