package io.github.aprendendo_spring_data_jpa.libraryapi.repository;

import io.github.aprendendo_spring_data_jpa.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacaoesTest {

    @Autowired
    TransacaoService transacaoService;
    /**
     * commit -> confirmar as alteraçãoes
     * Rollback -> desfazer as alterações
     */

    @Test
    void transacaoTest() {
       transacaoService.executar();
    }

    @Test
    void atualizarTransacaoSemOSaveTest() {
        transacaoService.atualizarSemAtualizar();
    }
}
