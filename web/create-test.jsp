<%-- 
    Document   : create-test
    Created on : 13/11/2019, 22:59:54
    Author     : Eduardo
--%>

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
        <% String error = (String) request.getAttribute("erro");%>
        <div class="row align-items-center full">
            <div class="container col-md-12">
                <form method="POST" name="createTestForm" action="TesteController.do" onSubmit="return validate()">
                    <div class="row justify-content-center">
                        <div class="col-md-5">
                            <div class='title'>
                                Criar Teste
                            </div>
                            <div class='form-group'>
                                <label for="name">Nome do Teste</label>
                                <input type="text" class="form-control" name="name" id="name" placeholder="Nome" required/>
                            </div>
                            <div class='form-group'>
                                <label for="description">Descrição do Teste</label>
                                <textarea id="description" name="description" class="form-control" rows="3" placeholder="Descrição" required></textarea>
                            </div>
                        </div>
                    </div>
                    <% if(error != null){ %>
                        <div class="row justify-content-center">
                            <p class="error-text" style="text-align: center"><%out.print(error);%></p>
                        </div>
                    <%}%>
                    <div class="row justify-content-center add-button-row">
                        <button type="submit" class="btn btn-primary button-with-icon">
                            Criar Teste
                            <i class="material-icons">navigate_next</i>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        function validate(){
            var name = document.forms["createTestForm"]["name"].value
            var description = document.forms["createTestForm"]["description"].value
            if(name !== "" && description !== ""){
                return true
            }
            return false
        }
    </script>   
</body>

</html>