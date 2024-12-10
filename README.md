# grana-flow

## Instruções

O objetivo é fornecer uma cópia do projeto para fins de desenvolvimento e teste.

## Pré-requisitos

- Sistema Operacional Ubuntu(Versão em que a API foi testada: 24.04 LTS)
- Configurar seu ambiente para encode UTF8
- Instalar o Java 17.
- Instalar o Spring tool Suite 4
- Instalar a ferramenta para testes de requisições(Insomnia, Postman ou outro).
- Instalar o Lombok
- Instalar o Docker(version 24.0.7)
- Instalar o Maven
- Imagem docker: container-registry.oracle.com/database/express:21.3.0-xe
- (Opcional) [Link para instalação do sonarlint](https://marketplace.eclipse.org/content/sonarlint)
- (Opcional) [Link para instalação do Eclemma](https://www.eclemma.org/jacoco/)

## Importar

Importar o projeto como um projeto Maven existente.

## Variáveis de ambiente

- PROFILES_ACTIVE=dev
- APP_NAME={Adicionar o nome do serviço}
- CONTEXT_PATH={Adicionar o caminho do context do servidor}
- SECRET_KEY={Adicione a key}
- EXPIRE_LENGTH={Adicione o tempo de expiração}
- DS_DRIVER_CLASS_NAME={Adicione o driver do BD}
- DS_URL={Adicione a url de conexão com o BD}
- DS_USERNAME={Adicione o usuário de conexão com o BD}
- DS_PASSWORD={Adicione o password de conexão com o BD}
- DS_SCHEMA={Adicione o schema do banco de dados}
- DS_DIALECT={Adicione o dialeto do banco de dados}

## Instalação da docker image do container-registry.oracle.com/database/express:21.3.0-xe

## Pré-requisitos:
 - Cadastro no repositório oficial da Oracle nesse momento: [container-registry.oracle](https://container-registry.oracle.com/ords/f?p=113:10::::::)
 - Usário administrador(root)
 - Uma ferramenta para testar a conexão(DBeaver, Oracle SLQ Developer ou outra).

- Baixar a image e execute no terminal:

```
	docker run --name grana_flow_db \
	-p 1521:1521 -p 5500:5500 \
	-e ORACLE_PWD=gr4n4fl0w# \
	-e ORACLE_CHARACTERSET=UTF-8 \
	-v oracle-data:/opt/oracle/oradata \
	container-registry.oracle.com/database/express:21.3.0-xe
```

## Parâmetros explicados:

 - Nome do container= --name grana_flow_db
 - Mapeia a porta do localhost e do container= -p 1521:1521 -p 5500:5500
 - Define a senha BD= -e ORACLE_PWD=...
 - Define o charset BD= -e ORACLE_CHARACTERSET=UTF-8
 - Define o volume BD= -v oracle-data:/opt/oracle/oradata
 - Escolha da imagem a inicializar= container-registry.oracle.com/database/express:21.3.0-xe

## Comandos para gerenciar a imagem docker do MySQL:

- Verificar container em execução:

```bash
	sudo docker ps
```

- Parar o container por CONTAINER_ID ou o NAMES:

```bash
	sudo docker stop <CONTAINER_ID ou o NAMES>
```

- Inicializar o container por CONTAINER_ID ou o NAMES:

```bash
	sudo docker start <CONTAINER_ID ou o NAMES>
```

- Remover o container:

```bash
	sudo docker rm <CONTAINER ID ou o NAMES>
```

<span style="color:red"><strong>ATENÇÃO: </strong></span> 
Ter permissões de administrador no sistema operacional testado.
O Docker não vai deixar remover se ele estiver em execução.

## Instruções para executar os testes de cobertura 

- Adicionar a variável de ambiente abaixo no **Run Configuration** da IDE:

```
 PROFILES_ACTIVE=test
```