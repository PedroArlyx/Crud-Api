package com.projeto.crud.controles;

import com.projeto.crud.dtos.ProductDto;
import com.projeto.crud.model.Product;
import com.projeto.crud.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController// ta infomndo para ide q esse classe ser trata de uma classe controle
@RequestMapping("/products")
public class ProductController {

    @Autowired //ele permite q vc faça a intacia do objeto com todas injecao de dependencia para o ojto(evitando a forma mais mnual)
    ProductRepository repository;

    @GetMapping// estar informando  o metoda abixo é do tipo get
    public ResponseEntity getAll(){
        List<Product> listProduct = repository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(listProduct);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Integer id){
        Optional product = repository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status((HttpStatus.FOUND)).body(product.get());

    }
    @PostMapping
    public ResponseEntity save(@RequestBody ProductDto dto){
        var product = new Product();
        BeanUtils.copyProperties(dto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Integer id){
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        repository.delete(product.get());
        return  ResponseEntity.status(HttpStatus.OK).body("Product deleted");

    }
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Integer id,@RequestBody ProductDto dto){
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = product.get();
        BeanUtils.copyProperties(dto, productModel);
        return  ResponseEntity.status(HttpStatus.OK).body(repository.save(productModel));

    }
}
