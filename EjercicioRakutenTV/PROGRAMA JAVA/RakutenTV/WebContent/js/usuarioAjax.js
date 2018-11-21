function login(userMail, contrasena) {
    var datos = "ACTION=Usuario.login&userMail="+userMail+"&contrasena="+contrasena;
    console.log(datos);
    $.ajax({
        url: 'Controller',
        type: 'POST', //'GET'
        data: datos,
        datatype: 'json',
        success: function (params) {
            console.log(params);
        }
    });
}