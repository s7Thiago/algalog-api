package com.thiagosilva.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagosilva.algalog.domain.exception.NegocioException;
import com.thiagosilva.algalog.domain.model.Cliente;
import com.thiagosilva.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

/* Define que esta classe será um componente Spring com a semêntica de um serviço, ou
 seja, que ela reprensenta um local onde são executados serviços / regras de negócio
 relacionadas a entidade Cliente.
 
 Esta é uma classe de serviço, prória para colocar as regras de negócio, desse modo, tirando
 do controller a responsabilidade de carregar essas regras, e tornando a aplicação mais
 organizada, fácil de manter e de evoluir
 */
@Service
@AllArgsConstructor
public class CatalogoClienteService {

//	Injetendo a dependência para o ClienteRepository
	private ClienteRepository clienteRepository;

	/*
	 * Declara que este método deve ser executado dentro de uma transação, ou seja,
	 * isso significa que se algo que acontecer dentro dele der errado,todas as
	 * operações desta transação que tiverem efeitos no banco de dados serão
	 * descartadas (ou TUDO é executado, ou nada será feito)
	 */
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
//		Garantindo que não será cadastrado o mesmo email mais de uma vez no banco de dados
//		como o método chamado retorna um Optional, foi necessário fazer um tratamento antes.
//		O Optional foi usado como retorno porque pode, ou não existir algum resultado para
//		esta consulta.
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				/*
				 * Se a consulta retornar um cliente, ele estará aqui, nesse caso, se o cliente
				 * encontrado no repositório for diferente do cliente que estamos tentando salvar
				 * vai ocorrer um match (ou seja, true), dessa forma podemos detectar quando o
				 * cliente já existir no banco e for o mesmo da tentativa de salvamento, podendo
				 * então optar por uma atualização nesse caso, ou de um lançamento de exceção, caso
				 * o match retorne falso, ou seja, o email já estiver em uso por uma outra pessoa
				 * já cadastrada no banco
				 */
				.anyMatch(clienteExistente ->  !clienteExistente.equals(cliente));
		
		System.out.println("Email em uso? " + (emailEmUso ? "Sim" : "Não"));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}
