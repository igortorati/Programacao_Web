<%-- 
    Document   : library
    Created on : 12/11/2019, 18:35:01
    Author     : Eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Research Website</title>
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
        <div class="modal" id="myModal">
            <div class="modal-dialog" style="max-width: 400px; width: 400px">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            Adicionar Pesquisador
                        </h5>
                    </div>
                    <form method="POST" name="addResearcherForm" id="addResearcherForm" action="pesquisador.do">
                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class="row justify-content-center">
                                    <div class='form-group'>
                                        <label for="email">Email do pesquisador</label>
                                        <input type="text" class="form-control" name="email" placeholder="Email" />
                                    </div>
                                    <div class="row justify-content-center">
                                        <p class="error-text" id="errorMessage"></p>
                                    </div>
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <div id="error-text" class="error-text"></div>  
                        </div>
                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" >Salvar</button>
                        </div>
                     </form>
                </div>
            </div>
        </div>
        <script>            
            $("#addResearcherForm").submit(function(e){
               e.preventDefault()
               var form = $(this)
               var url = form.attr("action")
               $.ajax({
                   type: "POST",
                   url: url,
                   data: form.serialize(),
                   success: function (msg) {
                       if(msg === "true"){
                           window.location.href= "pesquisador.do"
                       } else {
                           const errorText = document.getElementById('error-text')
                           errorText.innerHTML = msg;
                       }
                   },
                   error: function(jqXhr, textStatus, errorMessage){
                       window.location.href = "error500.html"
                   },
               })
           })
        </script>
    </body>

</html>