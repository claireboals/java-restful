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

    // GET all products
    @GetMapping("/products")
    List<Product> all(){
        return repository.findAll();
    }

    // GET product by id
    @GetMapping("/products/{id}")
    Product one(@PathVariable Long id){
        return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id));
    }

    // POST a new product
    @PostMapping(path="/products", consumes="application/json", produces="application/json")
    Product newProduct(@RequestBody Product newProduct){
        return repository.save(newProduct);
    }

}