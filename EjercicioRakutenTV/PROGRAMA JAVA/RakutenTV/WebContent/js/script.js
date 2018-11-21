  document.addEventListener('DOMContentLoaded', function () {
      M.AutoInit();

      $("body").mousemove(function (e) {
          if (e.pageX < window.innerWidth * 0.1) {
              $('#anchorMenu')[0].click();
          }
      })
  });

  function login(userMail, contrasena) {
      var datos = "ACTION=Usuario.login&userMail=" + userMail + "&contrasena=" + contrasena;
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

  function cargarPelisMasVotadas(cantidad) {
      var datos = "ACTION=Pelicula.listMasVotadas&CANTIDAD=" + cantidad;
      $.ajax({
          url: 'Controller',
          type: 'GET',
          data: datos,
          datatype: 'json',
          success: function (params) {
              console.log(params);
          }
      });
  }

  function cargarPelisRecientes() {
      var datos = "ACTION=Pelicula.listAllNuevas"
      $.ajax({
          url: 'Controller',
          type: 'GET',
          data: datos,
          datatype: 'json',
          success: function (params) {
              console.log(params);
          }
      });
  }

  function cargarultimasPelisAdd(cantidad) {
      var datos = "ACTION=Pelicula.listUltimasAdd&CANTIDAD=" + cantidad;
      $.ajax({
          url: 'Controller',
          type: 'GET',
          data: datos,
          datatype: 'json',
          success: function (params) {
              console.log(params);
          }
      });
  }

  function getPelicula(idPelicula) {
      var datos = "ACTION=Pelicula.list&ID_PELICULA=" + idPelicula
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

  function cargarTodasPelis() {
      var datos = "ACTION=Pelicula.listTitulosAsc"
      $.ajax({
          url: 'Controller',
          type: 'GET',
          data: datos,
          datatype: 'json',
          success: function (params) {
              console.log(params);
          }
      });
  }
  //Utils
  function getFormData(idFormulario) {
      var formulario = document.getElementById(idFormulario);
      var contenidoFormulario = formulario.elements;
      var arrayDatos = new Array();
      var texto = "";
      for (var i = 0; i < contenidoFormulario.length; i++) {
          texto = encodeURIComponent(contenidoFormulario[i].name) + "=";
          texto += encodeURIComponent(contenidoFormulario[i].value);
          arrayDatos.push(texto);
      }
      texto = arrayDatos.join("&");
      return texto;
  }