package gov.goias.example.log.resource;

import gov.goias.example.log.domain.Product;
import gov.goias.example.log.repository.ProductRepository;
import gov.goias.example.log.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(Product product, HttpServletResponse response){
        product.setId(null);
        log.debug("Solicitação rest de criação de um novo produto");
        log.trace("Solicitação rest de criação do produto {0}", product.toString());
        service.save(product);
        response.addHeader(HttpHeaders.LOCATION,String.format("/api/products/%d", product.getId()));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void put(@PathVariable Long id,  @RequestBody Product product){
        product.setId(id);
        log.debug("Solicitação rest de alteração de produto");
        log.trace("Solicitação rest de alteração do produto {}", product.toString());
        service.save(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> list(){
        log.debug("Solicitação rest de lista de produto");
        return repository.listAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product get(@PathVariable Long id){
        log.debug("Solicitação rest de um produto");
        log.trace("Solicitação rest de produto com identificador {}", id);
        return service.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        log.debug("Solicitação rest de exclusão de um produto");
        log.trace("Solicitação rest de exclusão do produto com identificador {}", id);
        service.delete(id);
    }

}