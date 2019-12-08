<%-- 
    Document   : test
    Created on : 12/11/2019, 23:49:48
    Author     : Eduardo
--%>

<%@page import="Models.Teste"%>
<%@page import="Models.Pergunta"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Research Website</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="styles/styles.css" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>
    <div class="container col-md-12 justify-content-center">
        <%@include file="header2.jsp"%>
        <% ArrayList<Pergunta> perguntas = (ArrayList<Pergunta>) request.getAttribute("perguntas");%>
        <% Teste teste = (Teste) request.getAttribute("teste");%>
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class='title'>
                    <%out.print(teste.getTitulo());%>
                </div>
                <div class='subtitle'>
                    <%out.print(teste.getDescricao());%>
                </div>
            </div>
        </div>

        <div class="row justify-content-center add-button-row">
            <div class="row col-md-10 justify-content-between">
                <button class="btn btn-primary button-with-icon" id="editSaveButton" onclick="edit(<%out.print(teste.getJaVisivel());%>)">
                    Editar
                    <i class="material-icons">edit</i>
                </button>
                <div class="col-9">
                    <div class="row justify-content-end">
                        <button class="btn btn-primary button-with-icon" style="margin-right: 20px"
                            onClick="visualizarRespostas()">
                            Visualizar respondentes
                        </button>
                        <button class="btn btn-primary button-with-icon" style="margin-right: 20px" data-toggle="modal"
                            data-target="#myModal3">
                            Chaves disponíveis
                        </button>
                        <button class="btn btn-primary button-with-icon" onClick="createQuestion(<%out.print(teste.getJaVisivel());%>)">
                            Criar pergunta
                            <i class="material-icons">add</i>
                        </button>
                    </div>

                </div>
            </div>

        </div>
        <div class="container col-md-10 col-12">
            <ul class="list sortable">
                <% for(Pergunta p:perguntas){%>
                    <li class="question-list " id="<% out.print(p.getIdPergunta()); %>">
                        <i class="material-icons handle">drag_indicator</i>
                        <div class="container">
                            <div class="row">
                                <div class="col-9 item-text" onClick="editQuestion(<% out.print(p.getIndice()); %>,<%out.print(teste.getJaVisivel());%>)">
                                    <span class="test-name"><%out.print("Pergunta "+p.getIndice()+".");%></span>
                                    <% if(p.getDescricao() == null){ %>
                                        <p class="test-description d-none d-md-block">
                                            <%out.print("(Pergunta sem descrição).");%>
                                        </p>
                                    <%} else {%>
                                        <p class="test-description d-none d-md-block"><%out.print(p.getDescricao());%></p>
                                    <%}%>
                                </div>
                                <div class="col-3 icons">
                                    <form method="GET" action="alterarPergunta.do" onsubmit="return confirm('Você tem certeza que deseja excluir esta pergunta?')">
                                        <input name="indice" type="hidden" value="<% out.print(p.getIndice());%>" />
                                        <input name="idTeste" type="hidden" value="<% out.print(teste.getId()); %>" />
                                        <button type="submit" class="icon-button delete">
                                            <i class="material-icons" >delete</i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </li>
                <% } %>
            </ul>
        </div>
        <%@include file="keys.jsp"%>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="scripts/jquery.sortable.js"></script>
    <script>
        function makeSortable() {
            $('.sortable').sortable({
                handle: '.handle',
            });
        }
        function makeUnsortable() {
            $('.sortable').sortable('disable');
        }

        function save(jaVisivel) {
            var btn = document.getElementById("editSaveButton")
            btn.innerHTML = 'Editar <i class="material-icons">edit</i>'
            btn.onclick = () => edit(jaVisivel)
            makeUnsortable()
            var handles = document.querySelectorAll(".handle")
            handles.forEach((item) => {
                item.style.visibility = 'hidden'
            })
            const questions = $('.sortable').children()
            const qList = Array.prototype.slice.call(questions)
            const listId = []
            qList.forEach((question) => {
                listId.push(question.id)
            })
            const req = {
                questions: listId,
                id: getQueryVariable("id")
            }
            $.ajax({
                type: "POST",
                url: "alterarOrdemPerguntas.do",
                dataType: "json",
                success: function (resp) {
                    console.log(resp)
                    window.location.reload()
                },
                data: JSON.stringify(req)
            });
        }
        function edit(jaVisivel) {
            if(jaVisivel==false){
                var btn = document.getElementById("editSaveButton")
                btn.innerHTML = 'Salvar <i class="material-icons">save</i>'
                btn.onclick = () => save(jaVisivel)
                makeSortable()
                var handles = document.querySelectorAll(".handle")
                handles.forEach((item) => {
                    item.style.visibility = 'visible'
                })
            }else{
                alert("Este teste já foi disponibilizado para ser respondido e não pode mais ser alterado!");
            }
        }
        function getQueryVariable(variable) {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i = 0; i < vars.length; i++) {
                var pair = vars[i].split("=");
                if (pair[0] == variable) { return pair[1]; }
            }
            return (false);
        }

        function createQuestion(jaVisivel) {
            if(jaVisivel==false){
                var idTest = getQueryVariable("id")
                window.location.href = 'create-question.jsp?id=' + idTest
            }else{
                alert("Este teste já foi disponibilizado para ser respondido e não pode mais ser alterado!");
            }
            
        }

        function editQuestion(indice,jaVisivel) {
            if(jaVisivel==false){
                var idTest = getQueryVariable("id")
                window.location.href = 'cadastroPergunta.do?idTeste=' + idTest + '&indice=' + indice
            }else{
                alert("Este teste já foi disponibilizado para ser respondido e não pode mais ser alterado!");
            }
        }

        function visualizarRespostas() {
            var idTest = getQueryVariable("id")
            window.location.href = 'visualizarRespostas.do?id=' + idTest
        }
    </script>
</body>

</html>