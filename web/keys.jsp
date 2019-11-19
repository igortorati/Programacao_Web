<%-- 
    Document   : library
    Created on : 12/11/2019, 18:35:01
    Author     : Eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Aplicação</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/styles.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    </head>

    <body>
        <!-- Button trigger modal -->
        <!-- Button trigger modal -->
        <!-- Button to Open the Modal -->

        <!-- The Modal -->
        <div class="modal" id="myModal3">
            <div class="modal-dialog" style="max-width: 500px; width: 500px">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="row justify-content-between" style="width: 100%">
                            <div>
                                <h5 class="modal-title" style="margin-left: 15px; margin-top: 5px">
                                    Chaves
                                </h5>
                            </div>
                            <div>
                                <button type="button" class="btn btn-primary" onClick="generateKeys()">Gerar chaves</button>
                            </div>
                        </div>
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="row justify-content-center">
                            <p class="subtitle">Chaves para o acesso às perguntas</p>
                        </div>
                        <div class="row justify-content-center">
                            <ul id="ul-keys" class="list-group" style="width: 80%">
                            </ul>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function getQueryVariable(variable)
            {
                   var query = window.location.search.substring(1);
                   var vars = query.split("&");
                   for (var i=0;i<vars.length;i++) {
                           var pair = vars[i].split("=");
                           if(pair[0] == variable){return pair[1];}
                   }
                   return(false);
            }
            
            function generateLis(keys){
                var ul = document.getElementById("ul-keys")
                ul.innerHTML = ""
                keys.forEach((key) => {
                    var keyLi = document.createElement('LI')
                    keyLi.className = "list-group-item list-item-key"
                    keyLi.innerHTML = key
                    ul.appendChild(keyLi)
                })
            }
            
            function showKeys(){
                var keys = []
                var id = getQueryVariable('id')
                var resp = ""
                $.ajax({
                    type: "GET",
                    url: 'codigo.do?id='+id,
                    success: function(response){
                        keys = response.split(',')
                        generateLis(keys)
                    }
                })
            }
            
            function generateKeys(){
                var id = getQueryVariable('id')
                $.ajax({
                    type: "POST",
                    url: "codigo.do?id="+id,
                    success: function(response) {
                        var keys = response.split(',')
                        generateLis(keys)
                    },
                })
            }
            
            showKeys()
        </script>
    </body>

</html>