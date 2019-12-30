# Passos necessários para a execução 
#### Requisitos :
 * Apache TomCat 6 ou superior
 * JDK 8 ou superior
 * Maven 
 * MySQL

#### Preparo antes da Execução
 * Intalar JDK devidamente, configurando a variável de Ambiente JAVA_HOME 
 * Intalar o maven, alterando devidamente a variavel de ambiente PATH 
 * Baixar o APACHE TomCat 6 ou superior, decompacta-lo na sua pasta de preferencia e configurar a variavel CATALINA_HOME
 * Instalar um  servidor MySql devidamete configurado, com usuario **root** e senha **root**

#### Execução do projeto
 * Pelo terminal, entrar na pasta raiz do projeto (mesmo local onde fica o arquivo pom.xml)
 * Executar o seguinte comando :
```
mvn clean compile install -X
```
 * Após a execução do comando acima, na pasta target, será gerado um arquivo com final **.war**
 * Copiar o arquivo 
 * Ir até a pasta do servidor
 * Abrir a pasta webapps e colar o arquivo dentro da pasta 
 * Iniciar o tomcat com o comando CATALINA START
 * No seu navegador, acessar a aplicação com o endereço http://localhost:8080/mercado/index.html
 