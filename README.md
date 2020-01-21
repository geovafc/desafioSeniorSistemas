# Desafio Senior Sistemas

# Desenvolvimento
### Bibliotecas Utilizadas
- Spring Boot 2.2.1
- Spring Data Jpa
- Lombok
- Spring web
 - Obs: É necessário configurar o projeto Lombok  na IDE para que o projeto funcione corretamente, senão o código apresentará problemas de compilação quando se tentar usar algum método getter ou setter (por exemplo). Caso esteja usando o Eclipse siga os passos descritos no link (https://projectlombok.org/setup/eclipse) e caso esteja usando IntelliJ IDEA instale o plugin descrito no link (https://plugins.jetbrains.com/plugin/6317-lombok-plugin). No link do projeto (https://projectlombok.org/) também pode encontrar os passos para outras IDES e editores ou se preferir, sugiro comentar as anotações : @AllArgsConstructor, @NoArgsConstructor, @Data das classes de entidade e do DTO e gerar seus respectivos getter ou setter.
- H2
	- Obs: Foi utilizado o banco H2 por não haver a necessidade de uma permanencia constante dos dados.


### Funcionalidades implementadas
- Endpoints para Create/Read/Update/Delete/List com paginação para as seguintes entidades: Cidade, Estado e Pais.
	- Para a criação do estado é necessário passar o código do IBGE na url.Ex: código do estado do Pará é 15, então http://localhost:8080/estados/15;
	- Para a criação da cidade é necessário passar o código do IBGE do estado na url.Ex: código da cidade de Belém é 1501402, então  http://localhost:8080/estados/1501402
	- Dentro do controller de Pais existe um método chamado 'search' que utiliza paginação e para utilizar-lo é necessário informar: 
		- SearchTerm: nome do pais;
		- Page: página que será feita a consulta;
		- Size: quantos elementos irão retornar na busca paginada;
		- Ex: http://localhost:8080/paises/search?searchTerm=brasil&page=0&size=5
	- Dentro do controller de Estado existe um método chamado 'search' que utiliza paginação e para utilizar-lo é necessário informar: 
		- SearchTerm: nome do Estado;
		- Page: página que será feita a consulta;
		- Size: quantos elementos irão retornar na busca paginada;
		- Ex: http://localhost:8080/estados/search?searchTerm=santa&page=0&size=5
	- Dentro do controller de Cidade existe um método chamado 'search' que utiliza paginação e para utilizar-lo é necessário informar: 
		- SearchTerm: nome da Cidade;
		- Page: página que será feita a consulta;
		- Size: quantos elementos irão retornar na busca paginada
		- Ex: http://localhost:8080/cidades/search?searchTerm=joinville&page=0&size=5
	- Dentro do controller de cidade existe um endpoint para buscar cidade por cep. Para utilizar-lo basta informar a url junto com o cep. Ex: http://localhost:8080/cidades/byCep/68721000

