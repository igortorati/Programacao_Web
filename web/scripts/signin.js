function onSignIn(response) {
    var message = document.getElementById("errorLogin")
    message.innerHTML = ""
    var perfil = response.getBasicProfile();
    var userID = perfil.getId();
    var userName = perfil.getName();
    var userEmail = perfil.getEmail();
    var userPicture = perfil.getImageUrl();
    var LoR = response.getAuthResponse().id_token;
    $.ajax({
        method: "POST",
        url: "login.do",
        data: { email: userEmail, token: LoR  },
    })
    .done(function(data) {
        if(data === 'true'){
            window.location.href = 'testes.do';
        } else {
            var message = document.getElementById("errorLogin")
            message.innerHTML = "Login inválido"
        }
    })
    
    response.disconnect()
};