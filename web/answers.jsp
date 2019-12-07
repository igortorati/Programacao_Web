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
    <title>Aplicação</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="styles/styles.css" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>
    <div class="container col-md-12 justify-content-center">
        <%@include file="header2.jsp"%>
        <div class="row justify-content-center" style="margin-top: 60px">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Contato</th>
                        <th scope="col">Email</th>
                        <th scope="col">Idade</th>
                        <th scope="col">Sexo</th>
                        <th scope="col">CEP</th>
                        <th scope="col">Cor</th>
                        <th scope="col">Deficiência</th>
                        
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                        
                        <td>
                            <button class="delete-button">
                                <i class="material-icons">delete</i>
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>

    </script>
</body>

</html>