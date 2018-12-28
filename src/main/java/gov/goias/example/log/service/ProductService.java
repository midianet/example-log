package gov.goias.example.log.service;

import gov.goias.example.log.domain.Product;
import gov.goias.example.log.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.ValidationException;
import java.lang.reflect.InaccessibleObjectException;
import java.util.Optional;

@Log4j2
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public void save(Product product){
        log.debug("Executando serviço de salvar o produto");
        log.trace("Executando serviço e salvar o produto com os valores {}", product.toString());
        Optional.ofNullable(product.getDescription()).orElseThrow(() -> new ValidationException("Descrição não informada"));
        if(ObjectUtils.isEmpty(product.getId())){
            repository.insert(product);
        }else{
            repository.update(product);
        }
    }

    public Product findById(Long id){
        log.debug("Executando serviço de obter produto por ID");
        log.trace("Executando serviço de obter produto  com o ID {}",id);
        if(id == 190){
            throw new RuntimeException("Erro fatal...");
        }
        return repository.findById(id).orElseThrow(() -> new InaccessibleObjectException(String.format("Produto com o identificador %d não econtrado",id)));
    }

    public void delete(Long id){
        log.debug("Executando serviço de remover produto");
        log.trace("Executando serviço de remover o produto com o ID {}", id);
        if(id == 18L ){
            throw new ValidationException(String.format("Produto não pode ser removido com o identificador %d",id));
        }
        repository.delete(id);
    }

}