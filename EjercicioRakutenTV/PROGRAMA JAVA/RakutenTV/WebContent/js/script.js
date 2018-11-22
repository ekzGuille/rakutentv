document.addEventListener('DOMContentLoaded', function () {
	M.AutoInit();

	// $("body").mousemove(function (e) {
	// 	if (e.pageX < window.innerWidth * 0.1) {
	// 		$('#anchorMenu')[0].click();
	// 	}
	// })

	cargarPelisMasVotadas(5);
	cargarPelisEstrenos(5);
	cargarultimasPelisAdd(5);
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
			console.log(JSON.parse(params));

			crearImagenDiv('PelisMasVotadasWrapper', JSON.parse(params));
		}
	});
}

function cargarPelisEstrenos(cantidad) {
	var datos = "ACTION=Pelicula.listEstrenos&CANTIDAD=" + cantidad;
	$.ajax({
		url: 'Controller',
		type: 'GET',
		data: datos,
		datatype: 'json',
		success: function (params) {
			crearImagenDiv('ultimosEstrenosCarteleraWrapper', JSON.parse(params));
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
			crearImagenDiv('UltimasPelisAddWrapper', JSON.parse(params));
		}
	});
}

function getPelicula(idPelicula) {
	var datos = "ACTION=Pelicula.list&ID_PELICULA=" + idPelicula
	$.ajax({
		url: 'Controller',
		type: 'GET', //'GET'
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

function crearImagenDiv(id, data) {
	var divWrapper = document.getElementById(id);
	divWrapper.innerText = "";

	data.forEach(pelicula => {

		var divCaratulaPelicula = document.createElement('div');
		divCaratulaPelicula.setAttribute('class', 'caratulaPelicula');
		divCaratulaPelicula.setAttribute('id', pelicula.idPelicula);

		var anchorModal = document.createElement('a');
		anchorModal.setAttribute('href', '#modalPelicula' + pelicula.idPelicula);
		anchorModal.setAttribute('class', 'modal-trigger');

		var imgCaratula = document.createElement('img');
		imgCaratula.setAttribute('class', 'imagenCaratula');
		imgCaratula.setAttribute('src', 'images/peliculas/movieCaratula/' + pelicula.caratulaPeli);

		var divImgValoracion = document.createElement('div');
		divImgValoracion.setAttribute('class', 'imgValoracion');

		var imgValoracion = document.createElement('img');
		imgValoracion.setAttribute('class', 'estrellasValoracion');
		imgValoracion.setAttribute('src', 'images/icon/estrellas/' + Math.round(pelicula.mediaValoraciones) + 'Estrella.png');

		var divInfoValoracion = document.createElement('div');
		divInfoValoracion.setAttribute('class', 'infoValoracion');
		divInfoValoracion.innerText = pelicula.valoracionesTotales + ' votos';

		var divModal = document.createElement('div');
		divModal.setAttribute('id', 'modalPelicula' + pelicula.idPelicula);
		divModal.setAttribute('class', 'modal modalContent');

		var subDivModal = document.createElement('div');
		subDivModal.setAttribute('class', 'modal-content');

		var h5 = document.createElement('h5');
		h5.setAttribute('class', 'h5ModalClass')
		h5.innerText = pelicula.tituloPeli;

		var p = document.createElement('p');
		p.setAttribute('class', 'pModalClass');
		p.innerText = pelicula.resumenPeli;

		subDivModal.appendChild(h5);
		subDivModal.appendChild(p);
		divModal.appendChild(subDivModal);

		divImgValoracion.appendChild(imgValoracion);
		anchorModal.appendChild(imgCaratula);
		divCaratulaPelicula.appendChild(anchorModal);
		divCaratulaPelicula.appendChild(divImgValoracion);
		divCaratulaPelicula.appendChild(divInfoValoracion);
		divCaratulaPelicula.appendChild(divModal)
		divWrapper.appendChild(divCaratulaPelicula);

	});
}