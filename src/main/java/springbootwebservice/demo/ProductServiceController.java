/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springbootwebservice.demo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Belinda merlansyah
 */
 @RestController
public class ProductServiceController {
        private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    
    //Method untuk GET API
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
    //Method untuk POST API
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        
        //Untuk membuat kondisi dimana id tidak boleh sama
        if(productRepo.containsKey(product.getId())){
            
            //untuk menampilkan pesan jika id nya sama
            return new ResponseEntity<>("Id sudah di masukkan, silahkan masukkan id baru", HttpStatus.OK);
        }
        else{
            //untuk menampilkan pesan jika id tidak sama dan disimpan kedalan hashmap
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("product is created successfully", HttpStatus.CREATED);
        }
        
        
    }
    
    //Method untuk DELETE API
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete (@PathVariable("id") String id){
        
        //untuk membuat kondisi dimana id di cek terlebih dahulu apakan ada atau tidak
        if(!productRepo.containsKey(id)){
            
            //untuk menampilkan pesan jika id nya tidak ada
            return new ResponseEntity<>("Product id tidak di temukan", HttpStatus.OK);
        }
        else{
            //untuk menampilkan pesan bahwa data telah dihapus berdasarkan id
            productRepo.remove(id);
            return new ResponseEntity<>("Product is deleted succesccfully",HttpStatus.OK);
        }
        
        
    }
    
    //Method untuk POST API
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        
        //untuk membuat kondisi dimana id di cek terlebih dahulu apakan ada atau tidak
        if(!productRepo.containsKey(id)){
            
            //untuk menampilkan pesan jika id nya tidak ada
            return new ResponseEntity<>("Product id tidak bisa update karna id tidak ditemukan", HttpStatus.OK);
        }
        else{
            //untuk menampilkan pesan bahwa data telah update berdasarkan id
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id,product);
            return new ResponseEntity<>("product is update successfully", HttpStatus.OK);
        }
        
        
    }
}
