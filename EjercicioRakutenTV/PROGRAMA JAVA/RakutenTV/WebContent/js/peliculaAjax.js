function cargarPelisRecientes(){
	var datos = "ACTION=Pelicula.listAll"
    //http://api.jquery.com/jquery.ajax/
    $.ajax({
        url: 'Controller',
        type: 'POST', //'GET'
        data: datos,
        datatype: 'json',
        success: function(params) {
            console.log(params);
        }
    });
}

function ultimasPelisAdd() {
    var datos = "ACTION=Pelicula.listAll"
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

function getPelicula(idPelicula) {
    var datos = "ACTION=Pelicula.listAll"
    //http://api.jquery.com/jquery.ajax/
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
