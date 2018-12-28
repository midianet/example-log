package gov.goias.example.log.repository;

import gov.goias.example.log.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Repository
public class ProductRepository {

    private Long id;

    public void insert(Product product){
        log.debug("Persistindo um novo produto");
        log.trace("Hibernate: insert into produto(description)values({}", product.getDescription());
        product.setId(id++);
    }

    public void update(Product product){
        log.debug("Persistindo um novo produto");
        log.trace("Hibernate: update produto set description = '{}') where id = {}",product.getDescription(),product.getId());
    }

    public Optional<Product> findById(Long id){
        log.debug("Buscando produto no repositório");
        log.trace("Hibernate: select description from produto where id = {}",id);
        if(id == 18L) {
            return Optional.empty();
        }else if(id == 193){
            throw new RuntimeException("Banco de dados falhou...");
        }else{
            return Optional.of(Product.builder().id(id).description(String.format("Produto %d",id)).build());
        }
    }

    public List<Product> listAll(){
        log.debug("Buscando todos os rodutos no repositório");
        log.trace("Hibernate: select id, description from produto");
        List<Product> list = new ArrayList<>();
        list.add(Product.builder().id(1L).description("Produto 1").build());
        list.add(Product.builder().id(2L).description("Produto 2").build());
        list.add(Product.builder().id(3L).description("Produto 3").build());
        list.add(Product.builder().id(4L).description("Produto 4").build());
        list.add(Product.builder().id(5L).description("Produto 5").build());
        list.add(Product.builder().id(6L).description("Produto 6").build());
        return list;
    }

    public void delete(Long id){
        log.debug("Removendo produto do repositório");
        log.trace("Removendo produto do repositório com o identificador {}", id);
        log.trace("Hibernate: delete from produto where id = {}",id);
    }

}