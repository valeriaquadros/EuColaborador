
# Eu, Colaborador - WebServer

TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project.

## Requisitos

Esta aplicação foi desenvolvida usando as seguintes ferramentas:

 - [C#](https://docs.microsoft.com/pt-br/dotnet/csharp)
 - [Docker](https://www.docker.com)
 - [Docker Compose](https://docs.docker.com/compose)
 - [PostgreSQL](https://www.postgresql.org)

## Subindo uma instância do servidor

A maneira mais simples de obter uma instância deste servidor em execução é através da ferramenta [Docker](https://www.docker.com).  Este projeto contém todas as configurações necessárias para a automação de tais processos. Se você deseja um ambiente totalmente isolado do sistema operacional, execute os seguintes passos:

 - **Passo 1**: Verifique se você possui a ferramenta Docker e docker-compose e possui as devidas permissões para executá-la. Para isso, você pode usar os seguintes comandos:
 
	 ```bash
	 docker --version
	 # exemplo de saída: Docker version 20.10.3, build 48d30b5
	 docker-compose --version
	 # exemplo de saída: docker-compose version 1.27.4, build 40524192
	 ```
		
 - **Passo 2**: Se estiver tudo certo, você deve fazer o build da aplicação para a construição de uma imagem com a versão mais recente da aplicação. Para isso copie o código fonte para sua maquina local, abra o console do sistema operacional neste diretório e execute o comando abaixo. Se houver novas modificações no código fonte, você deverá repetir esse passo.
 
	```bash
	docker-compose build --no-cache
	```

 - **Passo 4**: Por fim, basta subir uma versão completa da aplicação usando o seguinte comando:

	```bash
	docker-compose -f docker-compose.db.yml -f docker-compose.yml up -d
	```
	
	Se tudo foi feito corretamente, você poderá acessar a aplicação através do endereço http://127.0.0.1:32772. 
	O comando acima apresentado tem a seguinte intenção: (a) subir o banco de dados PostgreSQL com o trecho `-f docker-compose.db.yml` e o web server com o trecho `-f docker-compose.yml`.  Portanto, se você já possui o banco de dados instalado locamente ou deseja usar um serviço externo, basta remover o primeiro trecho e alterar as referências a este serviço nos arquivos docker-compose.yml (deste diretório) e o arquivo `appsettings.json` no diretório `Web` com as novas informações.
