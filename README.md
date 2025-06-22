## Atividade V

Empresa criada: AutoBots

Parceiros: Toyota Motor Corporation e Grupo Volskwagen

Fundador, Dev e Engenheiro de Software: André Salerno

## Objetivo

"Após uma reunião com a cúpula dos grupos e seus CIOs se chegou à conclusão de que a melhor forma de disponibilizar as informações desejadas é através de APIs. Serão APIs RESTFull, que deverão ser protegidas por autenticação e autorização com JWT. Cada API deverá corresponder a um micro-serviço único e, dependendo do que cada uma deva fornecer, elas poderão se comunicar e trocar dados entre si para compor suas respostas."

- autenticação e autorização JWT

a) rodar o sistema

<img src="./atvv-autobots-microservico-spring/assets/run_system.gif" alt="Run System" width="800">

Teste realizado: OK

b) Chave segura

- rodar a classe ChaveSegura.java dentro de config
- inserir essa chave no arquivo application.properties

<img src="./atvv-autobots-microservico-spring/assets/chave_segura.gif" alt="Run System" width="800">

Teste realizado: OK

c) Realizar a autenticação no endpoint auth/login (Swagger)

#Autenticação usuário
Link: http://localhost:8082/swagger-ui/index.html

#Regras de negócio
Link: http://localhost:8081/swagger-ui/index.html

Observações importantes:

- conforme arquivo application.properties, o usuário e senha são: admin / admin123
- esse microserviço chamado sistema roda na porta 8082

<img src="./atvv-autobots-microservico-spring/assets/localhost_8082.gif" alt="Run System" width="800">

Teste realizado: OK

Um aprendizado importante que tive é que esse button "Authorize" no Swagger não faz validação de token junto ao Spring Security. Segue abaixo uma breve descrição:

"When authorizing in Swagger UI with Spring Security, particularly using basic authentication or similar schemes, the "Authorize" button in the Swagger UI interface itself does not typically perform a real-time validation of the credentials against your Spring Security configuration. Instead, it primarily sets the authorization headers (like Authorization: Basic <base64encodedusername:password>) that will be included in subsequent API requests made through the "Try it out" functionality."

## Token JWT

Abaixo uma requisição para dois endpoints como exemplo sem o token JWT. Perceba que aparece o erro 403, ou seja a requisição foi compreendida mas nao foi executada.

<img src="./atvv-autobots-microservico-spring/assets/sem_token.gif" alt="Run System" width="800">

Na demonstração abaixo já foi feita com a tokenização:

<img src="./atvv-autobots-microservico-spring/assets/com_token.gif" alt="Run System" width="800">

Testes realizados: OK

## Dados mockados

Para facilitar os testes, eu criei um package chamado mock em que ao iniciar a aplicação os dados são carregados (em média 10 resgitros por entidade).

## Exigências

a) Lista de todos os clientes cadastrados por empresa (loja), com suas informações completas (documentos, telefones, endereço etc.);

```java
# Entidade Clientes

@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/empresa/{empresaId}/clientes")
public ResponseEntity<List<ClienteDTO>> listarClientesPorEmpresa(@PathVariable Long empresaId) {
    List<Cliente> clientes = clienteRepository.findByEmpresaId(empresaId);
    
    if (clientes.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    List<ClienteDTO> clienteDTOs = clientes.stream()
        .map(clienteMapper::toDTO) // Supondo que você tem um mapper para converter Cliente para ClienteDTO
        .collect(Collectors.toList());

    return ResponseEntity.ok(clienteDTOs);
}

```
## As tarefas podem ser encontradas na base da página em uma área chamada EndPoints Críticos

<img src="./atvv-autobots-microservico-spring/assets/endpoint.png" alt="Run System" width="800">