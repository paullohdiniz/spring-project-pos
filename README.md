# PROJETO-REATIVO-POS

![GitHub Logo](/images/spring.png)

## Desenvolvido por Paulo Diniz como Prova de Conceito (POC) para artigo de cunho científico TCC em Arquitetura de Software. 
[LINKEDIN](https://www.linkedin.com/in/paullohdiniz/)

Projetos com intuito de simulação de performance com diferentes arquiteturas orientado à eventos.

1º) Projeto busca-melhor-caminho

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.1.RELEASE)

```
	
### Requisitos

JAVA JDK_1.8

Baixar o projeto Spring Boot + JPA + WEB + PostgreSQL - [GitHub](https://github.com/paullohdiniz/projeto-reativo-pos.git)	

Baixar CLI do Heroku - [HEROKU](https://devcenter.heroku.com/articles/heroku-cli)

### Implementação
	
O objetivo dessa é implementação é desenvolver um API de Block Chain que fará a inserção dos blocos segundo critério estabelecidos do conceito.
	
Além disso, após haverá um serviço que poderá consultar os blocos e poderá minerar para validar sua integridade dentro do bloco.
	
Criação da classe de Domain
- Block
	- id
	- data
	- hash
	- previousHash
- BlockList
	- id
	- nome

- Validação do Bloco
	Algoritmo capaz de validar o blockchair na classe ManagerBlock.

### Publicação na nuvem com HEROKU

No projeto os seguintes comando:
	
	λ cd busca-melhor-caminho\
	λ git init
	λ git add .
	λ git commit -am "Projeto Spring Reativo com a funcão de busca do melhor caminho."
	λ heroku login
		heroku: Press any key to open up the browser to login or q to exit:
		Logging in... done
		Logged in as paullohdiniz@gmail.com
	
	λ heroku create api-busca-melhor-caminho
	λ heroku addons:create heroku-postgresql:hobby-dev	
	λ git push heroku master
	λ heroku log --tail
	λ heroku open
	
[API-BUSCA-MELHOR-CAMINHO](https://api-busca-melhor-caminho.herokuapp.com/) - https://api-busca-melhor-caminho.herokuapp.com/

	
