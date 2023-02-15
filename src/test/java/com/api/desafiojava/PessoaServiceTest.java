package com.api.desafiojava;

import com.api.desafiojava.service.PessoaService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PessoaServiceTest {

    @TestConfiguration
    static class BookingServiceTestConfiguration{

        @Bean
        public PessoaService pessoaService(){
            return new PessoaService();
        }
    }

}
