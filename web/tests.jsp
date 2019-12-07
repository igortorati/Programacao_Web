<%-- 
    Document   : tests
    Created on : 26/10/2019, 15:56:19
    Author     : Eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.Teste"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="styles/styles.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Research Website</title>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <% ArrayList<Teste> testes = (ArrayList<Teste>) request.getAttribute("testes");%>
        <div class="container col-md-12 justify-content-center">
            <div class="row justify-content-center">
                <div class="col-md-5 search">
                    <form method="GET" action="testes.do">
                        <input type="text" class="form-control" name="q" placeholder="Pesquisar" />
                        <button class="input-button" type="submit"><i class="material-icons input-icon">search</i></button>
                    </form>
                </div>
            </div>
            <div class="row col-md-11 justify-content-end add-button-row">
                <a  href="create-test.jsp">
                    <button class="btn btn-primary button-with-icon">
                        Criar teste
                        <i class="material-icons">add</i>
                    </button>
                </a>
                
            </div>
            <div class="container col-md-10 col-12 list">
                <% for(Teste t:testes){%>
                    <div class="item-list dense" >
                        
                            <% if(t.getVisibilidade() == 1){ %>
                            <button class="icon-button visibility" onClick="changeVisibility(<%out.print(t.getId());%>)">
                                   <i class="material-icons">visibility</i>
                                </button>
                            <%} else {%>
                                <button class="icon-button visibility-off" onClick="changeVisibility(<%out.print(t.getId());%>)">
                                   <i class="material-icons">visibility_off</i>
                                </button>
                            <%}%>
                            <div class="container justify-content-between">
                                <div class="row">
                                    <div class="col-9 item-text" onclick="location.href='TesteController.do?id=<% out.print(t.getId()); %>'">
                                        <span class="test-name"><%out.print(t.getTitulo());%></span>
                                        <p class="test-description d-none d-md-block"><%out.print(t.getDescricao());%></p>
                                        <span class="test-date d-none d-lg-block">Criado em: <%out.print(t.getCreatedAtDay());%></span>
                                    </div>
                                    <div class="col-3 icons">
                                        <button class="icon-button download" onclick="location.href='DownloadController.do?id=<% out.print(t.getId()); %>'">
                                            <i class="material-icons">cloud_download</i>
                                        </button>
                                        <form method="GET" action="deletarTeste.do" onsubmit="return confirm('Você tem certeza que deseja excluir este teste?')">
                                            <input name="id" type="hidden" value="<% out.print(t.getId()); %>" />
                                            <button class="icon-button delete" type="submit">
                                                <i class="material-icons">delete</i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                <% } %>
            </div>
            <% 
                int a = 0;
                if(request.getParameter("pag") != null){
                    a = Integer.parseInt(request.getParameter("pag"));
                }
            %>
            <% 
                String nome = request.getParameter("q"); 
                if(nome == null){
                    nome = "";
                }
            %>
            <div class="pagination container justify-content-around">
                <% if(a != 0){ %>
                    <a id="back-pagination" href="testes.do?pag=<% out.print(a - 1); %>&q=<% out.print(nome); %>"><i class="material-icons">arrow_back</i> Anterior</a> 
                <%}%>
                <% int ultimaPag = (Integer) request.getAttribute("ultimaPag"); %>
                <% if(a < ultimaPag) { %>
                     
                    <a href="testes.do?pag=<% out.print(a + 1); %>&q=<% out.print(nome); %>">Próximo<i class="material-icons">arrow_forward</i></a>
                <% } %>
            </div>
        </div>        
            
        <script>
            function changeVisibility(id){
                window.location.href = "alterarTeste.do?id="+id
            }
        </script>
    </body>
</html>
