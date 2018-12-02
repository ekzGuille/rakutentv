//CERRAR EL NAVEGADOR LATERAL
// $('.sidenav').sidenav('close');

if (sessionStorage['usuario'] === undefined || sessionStorage['usuario'] === "") {
	sessionStorage['usuario'] = "{}";
}

var usuarioSesion = sessionStorage['usuario'] === "{}" ? JSON.parse(sessionStorage['usuario']) : JSON.parse(sessionStorage['usuario'])[0];
var respuestaServer;

document.addEventListener('DOMContentLoaded', function () {
	M.AutoInit();

	cargarNavBar();

	if (window.location.pathname === "/RakutenTV/" || window.location.pathname === "/RakutenTV/index.html") {
		cargarPelisMasVotadas(5);
		cargarPelisEstrenos(5);
		cargarultimasPelisAdd(5);
	} else if (window.location.pathname === "/RakutenTV/peliculas.html") {
		cargarTodasPelis();
	}
	
});

//Deprecated
function cerrarSesionController() {
	var datos = "ACTION=Usuario.logout";
	$.ajax({
		url: 'Controller',
		type: 'POST', //'GET'
		data: datos,
		datatype: 'json',
		success: function (params) {
			$('.sidenav').sidenav('close');
			if (window.location.pathname === "/RakutenTV/" || window.location.pathname === "/RakutenTV/index.html") {
				cargarMenuIndex();
			} else if (window.location.pathname === "/RakutenTV/peliculas.html") {
				cargarMenuNoIndex();
			}
		}
	});
}
//Deprecated
function getSesionUserController() {
	var datos = "ACTION=Usuario.getSessionUser";
	$.ajax({
		url: 'Controller',
		type: 'POST', //'GET'
		data: datos,
		datatype: 'json',
		success: function (params) {
			usuarioSesion = JSON.parse(params);
			if (window.location.pathname === "/RakutenTV/" || window.location.pathname === "/RakutenTV/index.html") {
				cargarMenuIndex();
			} else if (window.location.pathname === "/RakutenTV/peliculas.html") {
				cargarMenuNoIndex();
			}
		}
	});
}

function cerrarSesion() {
	sessionStorage['usuario'] = "{}";
	cargarNavBar();
	M.toast({ html: 'Has cerrado sesión', classes: 'rounded' });
}

function getSesionUser() {
	usuarioSesion = JSON.parse(sessionStorage['usuario'])[0];
}

function login(formData) {
	var datos = "ACTION=Usuario.login&" + formData;
	$.ajax({
		url: 'Controller',
		type: 'POST', //'GET'
		data: datos,
		datatype: 'json',
		success: function (params) {
			respuestaServer = JSON.parse(params)[0];
			if(respuestaServer.hasOwnProperty('respuesta')){
				M.toast({ html: 'Error al entrar', classes: 'rounded' });
				sessionStorage['usuario'] = "{}";
			} else if (respuestaServer.hasOwnProperty("idUsuario")){
				sessionStorage['usuario'] = params;
				$('.sidenav').sidenav('close');
				cargarNavBar();
				$('#formEntra')[0].reset();
			}
		}
	});
}

function register(formData) {
	var datos = "ACTION=Usuario.register&" + formData;
	$.ajax({
		url: 'Controller',
		type: 'POST', //'GET'
		data: datos,
		datatype: 'json',
		success: function (params) {
			respuestaServer = JSON.parse(params)[0];
			if (respuestaServer.hasOwnProperty('respuesta')) {
				M.toast({ html: 'Error al registrar', classes: 'rounded' });
				sessionStorage['usuario'] = "{}";
			} else if (respuestaServer.hasOwnProperty("idUsuario")) {
				sessionStorage['usuario'] = params;
				$('.sidenav').sidenav('close');
				cargarNavBar();
				$('#formRegistrar')[0].reset();
			}
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
			crearImagenDiv('todasLasPelis', JSON.parse(params));
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
		if (contenidoFormulario[i].type === "checkbox") {
			texto = encodeURIComponent(contenidoFormulario[i].name) + "=";
			texto += encodeURIComponent(contenidoFormulario[i].checked);
		} else {
			texto = encodeURIComponent(contenidoFormulario[i].name) + "=";
			texto += encodeURIComponent(contenidoFormulario[i].value);
		}
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

function cargarNavBar() {
	if (sessionStorage['usuario'] === undefined || sessionStorage['usuario'] === "") {
		sessionStorage['usuario'] = "{}";
	}
	usuarioSesion = sessionStorage['usuario'] === "{}" ? JSON.parse(sessionStorage['usuario']) : JSON.parse(sessionStorage['usuario'])[0];

	if (usuarioSesion.hasOwnProperty("idUsuario")) {

		document.querySelector('#anchorEntra').style.display = 'none';
		document.querySelector('#anchorRegistrar').style.display = 'none';
		document.querySelector('#anchorEntraB').style.display = 'none';
		document.querySelector('#anchorRegistrarB').style.display = 'none';

		document.querySelector('#anchorPerfil').style.display = 'block';
		document.querySelector('#anchorSalir').style.display = 'block';
		document.querySelector('#anchorPerfilB').style.display = 'block';
		document.querySelector('#anchorSalirB').style.display = 'block';

	} else {

		document.querySelector('#anchorEntra').style.display = 'block';
		document.querySelector('#anchorRegistrar').style.display = 'block';
		document.querySelector('#anchorEntraB').style.display = 'block';
		document.querySelector('#anchorRegistrarB').style.display = 'block';

		document.querySelector('#anchorPerfil').style.display = 'none';
		document.querySelector('#anchorSalir').style.display = 'none';
		document.querySelector('#anchorPerfilB').style.display = 'none';
		document.querySelector('#anchorSalirB').style.display = 'none';
	}

	$('.sidenav').sidenav();
	cargarComponentes();
}

function cargarMenuNoIndex(idMenu) {

}

function cargarComponentes() {

	var anchorPeliciulas = document.getElementById('anchorPeliciulas');
	if (anchorPeliciulas !== null) {
		anchorPeliciulas.addEventListener('click', function () {

		});
	}

	var anchorEntra = document.getElementById('anchorEntra');
	if (anchorEntra !== null) {
		anchorEntra.addEventListener('click', function () {
			$('#formEntra')[0].reset();
			$('#menuEntra')[0].click();
		});
	}

	var anchorRegistrar = document.getElementById('anchorRegistrar');
	if (anchorRegistrar !== null) {
		anchorRegistrar.addEventListener('click', function () {
			$('#formRegistrar')[0].reset();
			$('#menuRegistrar')[0].click();
		});

		var anchorPerfil = document.getElementById('anchorPerfil');
		if (anchorPerfil !== null) {
			anchorPerfil.addEventListener('click', function () {
				$('#menuAjustes')[0].click();
			});
		}

		var salirUsuario = document.getElementById('anchorSalir');
		if (salirUsuario !== null) {
			salirUsuario.addEventListener('click', function () {
				cerrarSesion();
			});
		}

		var anchorPeliciulasB = document.getElementById('anchorPeliciulasB');
		if (anchorPeliciulasB !== null) {
			anchorPeliciulasB.addEventListener('click', function () {

			});
		}

		var anchorEntraB = document.getElementById('anchorEntraB');
		if (anchorEntraB !== null) {
			anchorEntraB.addEventListener('click', function () {
				$('#formEntra')[0].reset();
				$('#menuEntra')[0].click();
			});
		}

		var anchorRegistrarB = document.getElementById('anchorRegistrarB');
		if (anchorRegistrarB !== null) {
			anchorRegistrarB.addEventListener('click', function () {
				$('#formRegistrar')[0].reset();
				$('#menuRegistrar')[0].click();
			});
		}

		var anchorPerfilB = document.getElementById('anchorPerfilB');
		if (anchorPerfilB !== null) {
			anchorPerfilB.addEventListener('click', function () {
				$('#menuAjustes')[0].click();
			});
		}

		var salirUsuarioB = document.getElementById('anchorSalirB');
		if (salirUsuarioB !== null) {
			salirUsuarioB.addEventListener('click', function () {
				cerrarSesion();
			});
		}

		var btnSubmitLogin = document.getElementById('btnSubmitLogin');
		if (btnSubmitLogin !== null) {
			btnSubmitLogin.addEventListener('click', function () {
				login(getFormData('formEntra'));
			});
		}

		var btnSubmitRegister = document.getElementById('btnSubmitRegister');
		if (btnSubmitRegister !== null) {
			btnSubmitRegister.addEventListener('click', function () {
				register(getFormData('formRegistrar'));
			});
		}
	}
}