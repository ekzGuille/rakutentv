document.addEventListener('DOMContentLoaded', function () {
	M.AutoInit();

	var anchorPeliciulas = document.getElementById('anchorPeliciulas');
	anchorPeliciulas.addEventListener('click', function () {

	});
	var anchorEntra = document.getElementById('anchorEntra');
	anchorEntra.addEventListener('click', function () {
		$('#menuEntra')[0].click();
	});
	var menuRegistrar = document.getElementById('anchorRegistrar');
	menuRegistrar.addEventListener('click', function () {
		$('#menuRegistrar')[0].click();
	});
	var menuAjustes = document.getElementById('anchorPerfil');
	menuAjustes.addEventListener('click', function () {
		$('#menuAjustes')[0].click();
	});
	var salirUsuario = document.getElementById('anchorSalir');
	salirUsuario.addEventListener('click', function () {

	});

	if (window.location.pathname === "/RakutenTV/" || window.location.pathname === "/RakutenTV/index.html") {
		cargarPelisMasVotadas(5);
		cargarPelisEstrenos(5);
		cargarultimasPelisAdd(5);
	} else if (window.location.pathname === "/RakutenTV/peliculas.html") {
		cargarTodasPelis();
	}
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
			// console.log(JSON.parse(params));
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
	var datos = "ACTION=Pelicula.listTitulosAZ"
	$.ajax({
		url: 'Controller',
		type: 'GET',
		data: datos,
		datatype: 'json',
		success: function (params) {
			crearImagenDiv('todasLasPelis',JSON.parse(params));
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
		anchorModal.setAttribute('href', '#modal' + pelicula.idPelicula);
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
		divModal.setAttribute('id', 'modal' + pelicula.idPelicula);
		divModal.setAttribute('class', 'modal modalContent');

		var subDivModal = document.createElement('div');
		subDivModal.setAttribute('class', 'modal-content fondoModal');
		subDivModal.setAttribute('id', 'submodal' + pelicula.idPelicula);

		var h4 = document.createElement('h4');
		h4.setAttribute('class', 'h5ModalClass')
		h4.innerText = pelicula.tituloPeli;

		var imgCaratulaModal = document.createElement('img');
		imgCaratulaModal.setAttribute('class', 'imagenCaratula');
		imgCaratulaModal.setAttribute('src', 'images/peliculas/movieCaratula/' + pelicula.caratulaPeli);

		var p = document.createElement('p');
		p.setAttribute('class', 'pModalClass');
		p.innerText = pelicula.resumenPeli;

		var divFooter = document.createElement('div');
		divFooter.setAttribute('class', 'modal-footer modalContent');

		var anchorFooter = document.createElement('a');
		anchorFooter.setAttribute('href', '#!');
		anchorFooter.setAttribute('class', 'modal-close waves-effect waves-green btn-flat');
		anchorFooter.innerText = 'Cerrar';

		divFooter.appendChild(anchorFooter);
		subDivModal.appendChild(h4);
		subDivModal.appendChild(imgCaratulaModal);
		subDivModal.appendChild(p);
		divModal.appendChild(subDivModal);
		divModal.appendChild(divFooter);

		divImgValoracion.appendChild(imgValoracion);
		anchorModal.appendChild(imgCaratula);
		divCaratulaPelicula.appendChild(anchorModal);
		divCaratulaPelicula.appendChild(divImgValoracion);
		divCaratulaPelicula.appendChild(divInfoValoracion);
		divCaratulaPelicula.appendChild(divModal)
		divWrapper.appendChild(divCaratulaPelicula);


		document.getElementById('submodal' + pelicula.idPelicula).style.background = "url('images/peliculas/movieFotos/" + pelicula.imagenPeli + "')";

		$('.modal').modal();
	});

}