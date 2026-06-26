package io.github.manoelcampos.vendas.api.feature.produto;

import io.github.manoelcampos.vendas.api.feature.AbstractControllerIntegrationTest;
import org.junit.jupiter.api.Test;

public class ProdutoContolhertes extends AbstractControllerIntegrationTest {
    @Test
    void findByIdProduto() {
        final int id = 1;
        var prod = new Produto(id, "Notebook", 1000,0);
        client()
                .get()
                .uri("/produto/{id}", id)
                .exchange()  //emviar a requisição
                .expectStatus().isOk()  // espera um codigo de estatos ok (200)
                .expectBody(Produto.class)// espera que o json retornado seja de fato um objeto produto
                .isEqualTo(prod);
    }

    @Test
    void delitByIdnaoEcontrado() {
        final int id = 1000; //não existe
        client()
                .delete()
                .uri("/produto/{id}", id)
                .exchange()  //emviar a requisição
                .expectStatus().isNotFound()  // espera um codigo de estatos ok (404)
                .expectBody(Produto.class)// espera que o json retornado seja de fato um objeto produto
               ;
    }

    @Test
    void delitByIdnaoexclui() {
        final int id = 1; //não existe
        client()
                .delete()
                .uri("/produto/{id}", id)
                .exchange()  //emviar a requisição
                .expectStatus().isEqualTo(409)  // espera um codigo de estatos ok (404, confrito)
                .expectBody(Produto.class)// espera que o json retornado seja de fato um objeto produto
        ;
    }
}
