package io.github.manoelcampos.vendas.api.feature.produto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository r;

    @Test
    void findByDescricaoLike() {
        List<Produto> prods = r.findByDescricaoLike("Not%");
        assertEquals(1, prods.size());
        var p = prods.get(0);
        assertEquals("Notebook", p.getDescricao());

    }

    @Test
    void deleteByIdExcluiProd() {
        long id = 6;
        r.deleteById(id);
        assertTrue(r.findById(id).isPresent());
    }

    @Test
    void deleteByINaodExcluiProd() {
        long id = 1;
        assertThrows(DataIntegrityViolationException.class, () -> {
            r.deleteById(id);
            r.flush();  // enviar imediatamente
        });

        assertTrue(r.findById(id).isPresent());
    }

}
