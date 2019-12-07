<%-- 
    Document   : create-question
    Created on : 12/11/2019, 20:48:02
    Author     : Eduardo
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.Pergunta"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Research Website</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="styles/styles.css" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    </head>

    <body>
        <div class="container col-md-12 justify-content-center">
            <form action="resposta.do" method="post" name="answerQuestion">
                <div class="row ">
                    <% Pergunta pergunta = (Pergunta) request.getAttribute("pergunta");%>
                    <div class="container col-md-12">
                        <div class="row justify-content-center">
                            <div class="col-md-5">
                                <div class='title'>
                                    Pergunta <% out.print(pergunta.getIndice()); %>:
                                </div>
                                <%if (pergunta.getCodigo() == 0) { %>
                                <div class="subtitle">
                                    <% out.print(pergunta.getDescricao());%>
                                </div>  
                                <% } else { %>
                                <div class='form-group' id="id">
                                    <label for="idItem">Identificador do item a ser avaliado</label>
                                    <input type="text" id="idItem" name="idItem" class="form-control" placeholder="Identificador do item" />
                                </div>
                                <% } %>
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-11">
                                <div class="row justify-content-around" style="margin-top: 30px; margin-bottom: 30px" >
                                    <% ArrayList<String> imagens = (ArrayList<String>) request.getAttribute("imagens"); %>
                                    <%if (imagens.size() == 3) {%>
                                    <% for (String i : imagens) { %>
                                    <div class="col-md-4">
                                        <div class="row justify-content-center">
                                            <div class="img-visualization">
                                                <img src="<%out.print(i);%>" alt="imagem"/>
                                            </div>
                                        </div>
                                    </div>
                                    <%}%>
                                    <%} else if (imagens.size() == 5) { %>
                                    <% for (String i : imagens) { %>
                                    <div class="col-md-2">
                                        <div class="row justify-content-center">
                                            <div class="img-visualization">
                                                <img src="<%out.print(i);%>" alt="imagem"/>
                                            </div>
                                        </div>
                                    </div>
                                    <%}%>
                                    <%} else {%>
                                    <% for (String i : imagens) { %>
                                    <div class="col-md-1">
                                        <div class="row justify-content-center">
                                            <div class="img-visualization">
                                                <img src="<%out.print(i);%>" alt="imagem"/>
                                            </div>
                                        </div>
                                    </div>
                                    <%}%>
                                    <%}%>
                                </div>
                            </div>
                        </div>

                        <% if (pergunta.getTipo() == 0) { %>
                        <div class="row justify-content-center">
                            <div class="row col-md-10 justify-content-center">
                                <%if(imagens.size() == 5){%>
                                    <input class="custom-range" name="ordinal" value="3" type="range" min="1" max="5" step="1" />
                                <%} else if (imagens.size() == 7){ %>
                                    <input class="custom-range" name="ordinal" value="4" type="range" min="1" max="7" step="1" />
                                <%} else { %>
                                    <input class="custom-range" name="ordinal" value="5" type="range" min="1" max="9" step="1" />
                                <%}%>
                            </div>
                        </div>
                        <% } else { %>
                        <div class="row justify-content-center">
                            <div class="row col-md-10 justify-content-center">
                                <input class="custom-range" name="continua" value="5"  type="range" min="0" max="10" step="0.01"/>
                            </div>
                        </div>
                        <% }%>
                    </div>
                </div>
                <div class="row justify-content-center align-items-end save-button">
                    <button type="submit" class="btn btn-primary button-with-icon">
                        Próxima
                        <i class="material-icons">navigate_next</i>
                    </button>
                </div>
            </form>
        </div>
        <script>
        </script>
    </body>

</html>