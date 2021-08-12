package com.thiagosilva.algalog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração de ModelMapper. Como o ModelMapper é um framework que
 * não tem relação com o Spring, fazendo com que o mesmo não seja reconhecido,
 * gerando um erro de bean validation (porque o Spring não consegue
 * identificá-lo como um um componente), é necessário criar uma classe para
 * mediar essa configuração.
 */
@Configuration // Declara que esta classe é um componente Spring com o objetivo de agrupar
               // métodos que definem Beans Spring
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        // Qualquer configuração para o ModelMapper neste ponto pode ser feira sem
        // problemas, pois ao retorná-lo dessa forma, tornamos ele disponível no
        // contexto do Spring para ser injetado em outros pontos de injeção, como por
        // exemplo no construtor de EntregaController
        return new ModelMapper();
    }

}
