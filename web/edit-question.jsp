<%-- 
    Document   : create-question
    Created on : 12/11/2019, 20:48:02
    Author     : Eduardo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Models.Pergunta"%>
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
        <% Pergunta pergunta = (Pergunta) request.getAttribute("pergunta");%>
        <div class="row ">
            <div class="container col-md-12">
                <div class="row justify-content-center">
                    <div class="col-md-5">
                        <div id="title" class='title'>

                        </div>
                        <div class="subtitle">
                            Você pode dar uma descrição ou permitir que o usuário informe o id do item a ser avaliado.
                        </div>
                        <div class="row justify-content-center">
                            <div class='form-group'>
                                <!-- Default checked -->
                                <div class="custom-control custom-switch">
                                    <% if( pergunta.getCodigo() == 0 ){%>
                                    <input type="checkbox" class="custom-control-input" id="customSwitch" checked
                                        onchange="customSwitch()" />
                                    <%} else {%>
                                    <input type="checkbox" class="custom-control-input" id="customSwitch"
                                        onchange="customSwitch()" />
                                    <%}%>
                                        <label class="custom-control-label" for="customSwitch">Fornecer descrição?</label>
                                    </div>
                                </div>
                            </div>
                            <div class='form-group'>
                                <% if(pergunta.getDescricao() != null){ %>
                                    <textarea id="description" class="form-control" rows="3" placeholder="Descrição" required><%out.print(pergunta.getDescricao());%></textarea>
                                <%} else { %>
                                    <textarea style="display: none" id="description" class="form-control" rows="3" placeholder="Descrição" required></textarea>
                                <%}%>
                                </div>
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <nav class="nav nav-pills">
                                <%if(pergunta.getTipo() == 1){%>
                                <a href="#" onClick="handleTab(1)"
                                    class="nav-item1 nav-item nav-link active">Contínuo</a>
                                <a href="#" onClick="handleTab(2)" class="nav-item1 nav-item nav-link">Ordinal</a>
                                <%} else {%>
                                <a href="#" onClick="handleTab(1)" class="nav-item1 nav-item nav-link">Contínuo</a>
                                <a href="#" onClick="handleTab(2)"
                                    class="nav-item1 nav-item nav-link active">Ordinal</a>
                                <%}%>
                        </nav>
                    </div>
                    <% ArrayList<String> imagens = (ArrayList<String>) request.getAttribute("imagens"); %>
                    <%if(pergunta.getTipo() == 1) {%>
                        <div class="tab">
                            <div class="row justify-content-center">
                                <% for (int i = 0; i < imagens.size(); i++){ %>
                                    <% if(imagens.size() == 3) { %>
                                        <div class="col-md-4">
                                    <% } else if (imagens.size() == 5) { %>
                                        <div class="col-md-2">
                                    <% } else { %>
                                        <div class="col-md-1">
                                    <% } %>
                                    <div class="row justify-content-center">
                                        <div class="img-visualization">
                                            <img class="image" src="<%out.print(imagens.get(i));%>" alt="imagem" />
                                                <div class="row justify-content-center">
                                                    <button class="icon-button" data-toggle="modal"
                                                        data-target="#myModal"
                                                        onClick="active(<% out.print(i); %>)">
                                                        <a>
                                                            <i class="material-icons">add</i>
                                                        </a>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <%}%>
                            </div>
                        </div>
                        <div class="tab" style="display: none">
                            <div class="row justify-content-center">
                                <div class="row col-md-10 justify-content-between label-number">
                                    <div>5</div>
                                    <div>7</div>
                                    <div>9</div>
                                </div>
                                <div class="row col-md-10 justify-content-center">
                                    <input class="custom-range" id="range" value="5" onChange="handleChangeQuantityOfImages()" type="range" min="5" max="9" step="2" />
                                </div>
                            </div>
                            <div class="row justify-content-around images-container" id="tab2"  >
                            </div>
                        </div>
                    <%} else { %>
                        <div class="tab" style="display:none">
                            <div class="row justify-content-center">
                                <% for(int i = 0; i < 3; i++){ %>
                                    <div class="col-md-4">
                                        <div class="row justify-content-center">
                                            <div class="img-visualization">
                                                <img class="image" src="storage/default.png" alt="imagem" />
                                                <div class="row justify-content-center">
                                                    <button class="icon-button" data-toggle="modal"
                                                        data-target="#myModal"
                                                        onClick="active(<%out.print(i);%>)">
                                                        <a>
                                                            <i class="material-icons">add</i>
                                                        </a>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <%}%>
                            </div>
                        </div>
                        <div class="tab">
                            <div class="row justify-content-center">
                                <div class="row col-md-10 justify-content-between label-number">
                                    <div>5</div>
                                    <div>7</div>
                                    <div>9</div>
                                </div>
                                <div class="row col-md-10 justify-content-center">
                                    <input class="custom-range" id="range" value="<% out.print(imagens.size()); %>"
                                        onChange="handleChangeQuantityOfImages()" type="range"
                                        min="5" max="9" step="2" 
                                    />
                                </div>  
                            </div>
                            <div class="row justify-content-around images-container" id="tab2">
                                <% for (int i = 0; i < imagens.size(); i++){ %>
                                    <% if(imagens.size() == 3) { %>
                                        <div class="col-md-4">
                                    <% } else if (imagens.size() == 5) { %>
                                        <div class="col-md-2">
                                    <% } else { %>
                                        <div class="col-md-1">
                                    <% } %>
                                        <div class="row justify-content-center">
                                            <div class="img-visualization">
                                                <img class="image" src="<%out.print(imagens.get(i));%>" alt="imagem" />
                                                <div class="row justify-content-center">
                                                    <button class="icon-button" data-toggle="modal" data-target="#myModal" onClick="active(<%out.print(i+3);%>)">
                                                        <a>
                                                            <i class="material-icons">add</i>
                                                        </a>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <%}%>
                            </div>
                        </div>
                    <%}%>
                </div>
            </div>
        </div>
    <% String error = (String) request.getAttribute("erro");%>
    <% if(error != null){ %>
        <div class="row justify-content-center">
            <p class="error-text" style="text-align: center"><%out.print(error);%></p>
        </div>
    <%}%>
    <div class="row justify-content-center align-items-end save-button">
        <button class="btn btn-primary button-with-icon"
            onClick="createQuestion()">
            Salvar
            <i class="material-icons">save</i>
        </button>
    </div>
    <%@include file="library.jsp"%>
</div>
<script>
    function customSwitch() {
        description = document.getElementById("description");
        customSwitch1 = document.getElementById("customSwitch");
        if (customSwitch1.checked) {
            description.style.display = "block";
        } else {
            description.style.display = "none";
        }
    }
    function handleTab(t) {
        tabs = document.querySelectorAll(".tab")
        tabs.forEach((tab) => {
            tab.style.display = "none"
        })
        tabs[t - 1].style.display = "block"
        tabsa = document.querySelectorAll(".nav-item1")
        tabsa.forEach((tab) => {
            tab.className = "nav-item1 nav-item nav-link"
        })
        tabsa[t - 1].className = "nav-item1 nav-item nav-link active"
        handleChangeQuantityOfImages()
    }

    function handleChangeQuantityOfImages() {
        var tab2 = document.getElementById('tab2')
        var qtd = document.getElementById('range').value
        let content = document.createElement('DIV')
        if (qtd === 3) {
            content.className = "col-md-4"
        } else if (qtd === 5) {
            content.className = "col-md-2"
        } else {
            content.className = "col-md-1"
        }

        tab2.innerHTML = ""

        for (let i = 0; i < qtd; i++) {
            const html = '<div class="row justify-content-center">' +
                '<div class="img-visualization">' +
                '<img class="image" src="storage/default.png" alt="imagem"/>' +
                '<div class="row justify-content-center">' +
                '<button class="icon-button" data-toggle="modal" data-target="#myModal" onClick="active(' + (i + 3) + ')">' +
                '<a>' +
                '<i class="material-icons">add</i>' +
                '</a>' +
                '</button>' +
                '</div>' +
                '</div>' +
                '</div>'
            content.innerHTML = html
            tab2.appendChild(content.cloneNode(true))
        }
    }

    function active(isActive) {
        const divs = document.querySelectorAll(".img-visualization")
        console.log(divs)
        for (div of divs) {
            var first
            for (child of div.childNodes) {
                if (child.className === "image") {
                    first = child
                }
            }
            first.id = ""
        }
        var img
        for (child of divs[isActive].childNodes) {
            if (child.className === "image") {
                img = child
            }
        }
        img.id = "active"
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


    function createQuestion() {
        var imagesInputs = document.querySelectorAll(".image")
        var tab = document.getElementsByClassName("nav-item1 nav-item nav-link active")[0].innerHTML
        var images = []
        if(tab === 'Contínuo'){
            for(let i = 0; i < 3; i++){
                var src = imagesInputs[i].src
                var str = src.split('/')
                images.push('./'+str[str.length-2]+'/'+str[str.length-1])
                console.log('./'+str[str.length-2]+'/'+str[str.length-1])
            }
        } else {
            var qtd = parseInt(document.getElementById("range").value)
            for(let i = 3; i < qtd + 3; i++){
                var src = imagesInputs[i].src
                var str = src.split('/')
                images.push('./'+str[str.length-2]+'/'+str[str.length-1])
            }
        }
        var hasDescription = document.getElementById("customSwitch").checked
        var description = ""
        if(hasDescription){
            description = document.getElementById('description').value
        }
        var question = {}
        if(hasDescription){
            question = {
                codigo: 0,
                descricao: description,
                tipo: tab === 'Contínuo' ? 1 : 0,
                imagens: images
            }
        } else {
            question = {
                codigo: 1,
                tipo: tab === 'Contínuo' ? 1 : 0,
                imagens: images
            }
        }

        var params = 'idTeste=' + getQueryVariable("idTeste") + '&indice=' + getQueryVariable("indice")
        var url = "alterarPergunta.do?" + params
        console.log(url, question)
        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            success: function (msg) {
                console.log(msg)
                window.location.href= "TesteController.do?id="+getQueryVariable("idTeste")
            },
            data: JSON.stringify(question)
        })
    }


    document.getElementById("title").innerHTML = "Pergunta " + getQueryVariable("indice")
    var tab = document.getElementsByClassName("nav-item1 nav-item nav-link active")[0].innerHTML




</script>
</body>

</html>