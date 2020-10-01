# API Sicredi Votações de Sessões/Pautas
A API pode ser acessada nas "nuvens/cloud" utilizando o caminho:
```
https://tranquil-escarpment-91974.herokuapp.com

```
API desenvolvida para as funções de:
- cadastrar de pauta;
- busca de pauta por id;
- abrir sessão para votação de pauta;
- computar votos de associados e
- contabilizar a votação.
--> OBS 1: A persistência de dados é feita em banco de dados em memória (H2)
--> OBS 2: 

## Instruções
### POSTMAN
```
[POST] https://tranquil-escarpment-91974.herokuapp.com/api/v1/pautas
[BODY]:
{
	"descricao": "Teste um"
}
[GET] https://tranquil-escarpment-91974.herokuapp.com/api/v1/pautas/1

[POST] https://tranquil-escarpment-91974.herokuapp.com/api/v1/pautas/abrir-sessao?idPauta=1

[POST] https://tranquil-escarpment-91974.herokuapp.com/api/v1/pautas/abrir-sessao?idPauta=1&periodoMinutos=2

[POST] https://tranquil-escarpment-91974.herokuapp.com/api/v1/pautas/computar-voto
[BODY]: 
{
	"cpf": 7211222217,
	"resposta": false,
	"pauta": {
		"id": 1,
		"descricao": "teste"
	}
}

[GET] https://tranquil-escarpment-91974.herokuapp.com/api/v1/pautas/1/resultado-votacao
```
### Análise de código e execução local
```
git clone https://github.com/thiagohernandes/heroku-sicredi-votacao.git
cd heroku-sicredi-votacao
mvn clean package
mvn spring-boot:run
acessar o Swagger com os endpoints e contratos de objetos disponíveis: http://localhost:8080/swagger-ui.html (local)
```
### Clean Architecture
- foi utilizada o pattern "Clean Architecture" para facilitar o desenvolvimento e a testabilidade dos enpoints da API;
- para testes, foi utilizado o JUnit, juntamente do Mockito;

### Handler Genérico de Erros
-> Para evitar tratativas individuais por serviços, foi construído um handler genérico de tratativas de exceções/erros de qualquer chamada HTTP

-> contrato do objeto a ser retornado em qualquer ocasião para facilitar a manipulação por um possível projeto de front-end:
```bash
{
    "errorMessage": "URL não encontrada para a requisição",
    "dateTime": "01/10/2020 10:10:57",
    "calledUrl": "/api/v1/pautas-invalid",
    "statusCode": 404,
    "method": "GET"
}
```
### Regras - Controle
-> o sistema faz o contole por tempo de abertura de uma sessão;
-> é permitido passar o parâmetro adicional de minutos ao abrir uma sessão;
-> antes de iniciar todo o processo, conforme endpoints, deve-se cadastrar uma pauta, e em seguida seguir o fluxo de abertura de sessão, votação e resultado final computado por id de uma pauta;
