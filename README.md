# Acervo-Receitas
back-end do Acervo de Receitas

Para iniciar o projeto em java spring, deve-se ter baixado o spring tools ou então as extensões do vscode, no vscode você deve baixar o spring boot extension pack e o extension pack de java.

Após a instalação das extensões você deve reiniciar o vscode e esperar o build do java(pode demorar um pouco).

Agora deve-se configurar o aplication-properties, em spring.datasource.url=jdbc:mysql://localhost:<porta do mysql>/<banco que será utilizado> você deve colocar a porta em que o seu banco está sendo o 3306 ou outra da qual você configurou, deve por o nome do banco logo após, assim: spring.datasource.url=jdbc:mysql://localhost:3307/cozinha_criativa.

o banco ja deve ter sido criado no mysqlworckbench, com isso deve ser colocado o username e a senha do seu mysql, exemplo: spring.datasource.username=root spring.datasource.password=123

Com tudo pronto, você deve iniciar o spring e todas as tabelas serão criadas AUTOMATICAMENTE dentro do banco, você deverá esperar a criação completa das tabelas antes de testar qualquer coisa.

para testar o CRUD sem a utilização do front, deve-se usar um software como o postman(foi o que usei), nele você deve colocar a rota do endpoint, como por exemplo em cargos: http://localhost:8080/cargo, com a rota estabelecida você coloca em metodo post e em body, coloque pra enviar em json, na criação do cargo deve ficar assim: {"nome":"admin"}.

você consegue ver o que deve ser enviado pelo Json nos requestDTOS criados, e quais endpoints usar nos controllers, tudo deve ser enviado pelo formato json.

Os CRUDS de criação de receitas,de livros e avaliações(degustação da receita), deve ser usado a autenticação por token, para fazer isso, deve colocar em header no postman, e colocar em 
KEY : Authorization, e em Value: Barear <Token do usuario ao logar> exemplo: Bearer RoLWFwaSIsInN1YiI6InRvbnl. 

Ao criar os Livros e uma avalição ao inves de "Barear" você deve colocar "Bearer" no header.
