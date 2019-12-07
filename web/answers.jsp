<%-- 
    Document   : test
    Created on : 12/11/2019, 23:49:48
    Author     : Eduardo
--%>

<%@page import="Models.Usuario"%>
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
        <% ArrayList<Usuario> users = (ArrayList<Usuario>) request.getAttribute("users");%>
        <%@include file="header2.jsp"%>
        <div class="row justify-content-end col-md-11" style="margin-top: 50px">
            <button class="btn btn-primary" onclick="downloadCSV()">
                Download CSV
                <i class="material-icons" style="vertical-align: text-top">cloud_download</i>
            </button>
        </div>
        <div class="row justify-content-center" style="margin-top: 60px">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Contato</th>
                        <th scope="col">Email</th>
                        <th scope="col">Idade</th>
                        <th scope="col">Sexo</th>
                        <th scope="col">CEP</th>
                        <th scope="col">Cor</th>
                        <th scope="col">Enfermidade</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Usuario u:users){%>
                        <tr>
                            <td><%out.print(u.getIdUsuario());%></td>
                            <td><% out.print(u.getContato()); %></td>
                            <td><% out.print(u.getEmail()); %></td>
                            <td><% out.print(u.getIdade()); %></td>
                            <td><% out.print(u.getSexo()); %></td>
                            <td><% out.print(u.getCep()); %></td>
                            <td><% out.print(u.getCor()); %></td>
                            <td><% out.print(u.getEnfermidade()); %></td>
                            <td>
                                <form method="GET" action="deletarUsuario.do">
                                    <input type="hidden" name="id" value="<%out.print(u.getIdUsuario());%>" />
                                    <input type="hidden" name="idTeste" id="idTeste" value="" />
                                    <button class="delete-button" type="submit">
                                        <i class="material-icons">delete</i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        function getQueryVariable(variable) {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i = 0; i < vars.length; i++) {
                var pair = vars[i].split("=");
                if (pair[0] == variable) { return pair[1]; }
            }
            return (false);
        }
        
        function downloadCSV(){
            console.log(getQueryVariable("id"))
            window.location.href = "DownloadController.do?id="+getQueryVariable("id")
        }
        
        const input = document.getElementById("idTeste")
        input.value = getQueryVariable("id")
    </script>
</body>

</html>