package com.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @DisplayName("Testando o Save")
    @Test
    void testSaveProduto() {

        // Given / Arrange
        ProdutoRepository produto1 = new ProdutoRepository(null, "David", 43543656);

        // When / Act 
        ProdutoRepository saveProduto = produtoRepository.save(produto1);

        // Then / Assert
        assertNotNull(saveProduto);
        assertTrue(saveProduto.getId() > 0);
    }

    @DisplayName("Testando Get para todos os Produtos")
    @Test
    void testGetAllRepository() {
        // Given / Arrange 
        ProdutoRepository produto1 = new ProdutoRepository(null, "David", 43543656);
        ProdutoRepository produto2 = new ProdutoRepository(null, "Pedro", 43543659);
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);

        // When / Act 
        List<ProdutoRepository> produtoList = produtoRepository.findAll();

        // Then / Assert
        assertNotNull(produtoList);
        assertEquals(2, produtoList.size());
    }

    @DisplayName("Testando Get By ID")
    @Test
    void testGetById() {

        // Given / Arrange 
        ProdutoRepository produto1 = new ProdutoRepository(null, "David", 43543659);
        produtoRepository.save(produto1);

        // When / Act 
        ProdutoRepository saveProduto = produtoRepository.findById(produto1.getId()).get();

        // Then / Assert
        assertNotNull(saveProduto);
        assertEquals(produto1.getId(), saveProduto.getId());
    }

    @DisplayName("Testando o Update")
    @Test
    void testUpdateProduto() {
        // Given / Arrange 
        ProdutoRepository produto1 = new ProdutoRepository(null, "David", 43543659);
        produtoRepository.save(produto1);

        // When / Act 
        ProdutoRepository saveProduto = produtoRepository.findById(produto1.getId()).get();
        saveProduto.setNome("Victor");
        saveProduto.setPreco(997556473);

        ProdutoRepository updateProduto = produtoRepository.save(saveProduto);

        // Then / Assert
        assertNotNull(updateProduto);
        assertEquals("Victor", updateProduto.getNome());
        assertEquals(997556473, updateProduto.getPreco());
    }

    @DisplayName("Testando o Delete")
    @Test
    void testDeleteProduto() {

        // Given / Arrange 
        ProdutoRepository produto1 = new ProdutoRepository(null, "David", 43543659);
        produtoRepository.save(produto1);

        // When / Act 
        produtoRepository.deleteById(produto1.getId());

        Optional<ProdutoRepository> produtoOptional = produtoRepository.findById(produto1.getId());

        // Then / Assert
        assertTrue(produtoOptional.isEmpty());
    }

}