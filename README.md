# Research Website
##### Desenvolvedores: Eduardo Miranda Pedrosa Filho, Igor Henrique Torati Ruy e João Pedro Fachini Alvarenga

## Passos para a implantação do site
> 1. Instalar JDK 8
> 2. Instalar e configurar tomcat
> 3. Instalar e configurar mysql-server - usuario root e senha a ser setada no codigo fonte do projeto
> 4. Instalar github
> 5. Após o tomcat já estar funcionando, usar o Manager App para implantar o arquivo .war gerado na construção do projeto
> 6. Clonar este repositório dentro da pasta home/"user" 
> 7. Entrar dentro da pasta do tomcat e depois webapps/Research_Website/
> 8. Criar a pasta storage caso nao exista e entrar dentro dela
> 9. Copiar a imagem no caminho home/"user"/Programacao_Web/web/img/default.png para o caminho atual
> 10. Entrar dentro da pasta home/"user"/Programacao_Web/DB/
> 11. Logar no mysql com o usuario e senha configurados em codigo
> 12. Abrir o arquivo BD_Prog_Web.sql no MySQL Workbench.
> 13. Inserir na linha 197 do arquivo aberto o seguinte "INSERT INTO `Pesquisador` (`PES_id`,`PES_email`) VALUES (null,'SEU_GMAIL');", sem aspas, onde SEU_GMAIL deve ser substituído pelo gmail do administrador/pesquisador inicial.
> 14. Salvar o arquivo BD_Prog_Web.sql com a alteração feita no passo 13.
> 15. Executar scripts BD_Prog_Web.sql e depois procedures.sql (usar comando source "nome do arquivo")
> 16. Acessar site pelo domínio ao qual foi implantado /Research_Website

## Erros corrigidos após apresentação
> 1. Erro 500 ao deletar resposta de um Teste corrigido, a resposta é deletada.
> 2. Título e descrição de um Teste mostrado antes do respondente informar os dados demográficos e responder as perguntas.
