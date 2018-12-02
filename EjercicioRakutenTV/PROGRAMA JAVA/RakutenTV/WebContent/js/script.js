//CERRAR EL NAVEGADOR LATERAL
// $('.sidenav').sidenav('close');

if (sessionStorage['usuario'] === undefined || sessionStorage['usuario'] === "") {
	sessionStorage['usuario'] = "{}";
}

var usuarioSesion = sessionStorage['usuario'] === "{}" ? JSON.parse(sessionStorage['usuario']) : JSON.parse(sessionStorage['usuario'])[0];
var respuestaServer;

document.addEventListener('DOMContentLoaded', function () {
	M.AutoInit();

	if (window.location.pathname === "/RakutenTV/" || window.location.pathname === "/RakutenTV/index.html") {
		cargarPelisMasVotadas(5);
		cargarPelisEstrenos(5);
		cargarultimasPelisAdd(5);
	} else if (window.location.pathname === "/RakutenTV/peliculas.html") {
		cargarTodasPelis();
	} else if (window.location.pathname === "/RakutenTV/ficha.html") {
		var idPelicula = parseInt(sessionStorage['idPeli']);
		loadInfoMovie(idPelicula);
	}
	cargarComponentes();
	cargarNavBar();
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
	M.toast({
		html: 'Has cerrado sesión',
		classes: 'rounded'
	});
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
			if (respuestaServer.hasOwnProperty('respuesta')) {
				M.toast({
					html: 'Error al entrar',
					classes: 'rounded'
				});
				sessionStorage['usuario'] = "{}";
			} else if (respuestaServer.hasOwnProperty("idUsuario")) {
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
				M.toast({
					html: 'Error al registrar',
					classes: 'rounded'
				});
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
			crearFichaPelicula(JSON.parse(params)[0]);
		}
	});
}

function getInfoPelicula(idPelicula, idUsuario) {

	var datos = `ACTION=Pelicula.infoPeliSeleccionada&ID_PELICULA=${idPelicula}&ID_USUARIO=${idUsuario}`
	$.ajax({
		url: 'Controller',
		type: 'GET', //'GET'
		data: datos,
		datatype: 'json',
		success: function (params) {
			crearFichaPelicula(JSON.parse(params)[0]);
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
			if (document.getElementById('informacionBusqueda') !== null) {
				document.getElementById('informacionBusqueda').innerText = `Se están mostrando todas las películas`;
			} else {
				document.getElementById('informacionBusqueda').innerText = ``;
			}
			crearImagenDiv('todasLasPelis', JSON.parse(params));
		}
	});
}

function buscarTitulo(titulo) {
	var datos = `ACTION=Pelicula.filtrarNombre&NOMBRE=${titulo}`;
	$.ajax({
		url: 'Controller',
		type: 'GET',
		data: datos,
		datatype: 'json',
		success: function (params) {
			if (document.getElementById('informacionBusqueda') !== null) {
				document.getElementById('informacionBusqueda').innerText = `Se están mostrando todas las películas relacionadas con '${titulo}'`;
			}else{
				document.getElementById('informacionBusqueda').innerText = ``;
			}
			crearImagenDiv('todasLasPelis', JSON.parse(params));
		}
	});
}

function buscarGenero(idGenero) {
	var datos = `ACTION=Pelicula.listGenero&GENERO=${idGenero}`;
	$.ajax({
		url: 'Controller',
		type: 'GET',
		data: datos,
		datatype: 'json',
		success: function (params) {
			if (document.getElementById('informacionBusqueda') !== null && document.getElementById('generoSelector') !== null) {
				document.getElementById('informacionBusqueda').innerText = `Se están mostrando todas las películas de '${document.getElementById('generoSelector')[document.getElementById('generoSelector').value].innerText}'`;
			} else {
				document.getElementById('informacionBusqueda').innerText = ``;
			}
			crearImagenDiv('todasLasPelis', JSON.parse(params));
		}
	});
}

function fichaPelicula(idPelicula) {
	sessionStorage['idPeli'] = idPelicula;
	document.location = 'ficha.html';
}

function loadInfoMovie(idPelicula) {
	if (sessionStorage['usuario'] === undefined || sessionStorage['usuario'] === "") {
		sessionStorage['usuario'] = "{}";
	}
	usuarioSesion = sessionStorage['usuario'] === "{}" ? JSON.parse(sessionStorage['usuario']) : JSON.parse(sessionStorage['usuario'])[0];

	if (usuarioSesion.hasOwnProperty("idUsuario")) {
		getInfoPelicula(idPelicula, usuarioSesion.idUsuario);
	} else {
		getPelicula(idPelicula);
	}
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

		//Crear "caratula" de la pelicula

		// <div class="caratulaPelicula" id="1">
		var divCaratulaPelicula = document.createElement('div');
		divCaratulaPelicula.setAttribute('class', 'caratulaPelicula');
		divCaratulaPelicula.setAttribute('id', pelicula.idPelicula);

		// <a href="#modal1" class="modal-trigger">
		var anchorModal = document.createElement('a');
		anchorModal.setAttribute('href', '#modal' + pelicula.idPelicula);
		anchorModal.setAttribute('class', 'modal-trigger');

		//<img class="imagenCaratula" src="images/peliculas/movieCaratula/Pelicula_caratula.jpg"
		var imgCaratula = document.createElement('img');
		imgCaratula.setAttribute('class', 'imagenCaratula');
		imgCaratula.setAttribute('src', 'images/peliculas/movieCaratula/' + pelicula.caratulaPeli);
		anchorModal.appendChild(imgCaratula);
		divCaratulaPelicula.appendChild(anchorModal);

		// <div class="imgValoracion">
		var divImgValoracion = document.createElement('div');
		divImgValoracion.setAttribute('class', 'imgValoracion');

		// <img class="estrellasValoracion" src="images/icon/estrellas/1Estrella.png" 
		var imgValoracion = document.createElement('img');
		imgValoracion.setAttribute('class', 'estrellasValoracion');
		imgValoracion.setAttribute('src', 'images/icon/estrellas/' + Math.round(pelicula.mediaValoraciones) + 'Estrella.png');
		divImgValoracion.appendChild(imgValoracion);
		divCaratulaPelicula.appendChild(divImgValoracion);

		// divInfoValoracion.setAttribute('class', 'infoValoracion');
		var divInfoValoracion = document.createElement('div');
		divInfoValoracion.setAttribute('class', 'infoValoracion');
		divInfoValoracion.innerText = pelicula.valoracionesTotales + ' votos';
		divCaratulaPelicula.appendChild(divInfoValoracion);

		//Crear modal

		// <div id="modal1" class="modal modalContent">
		var divModal = document.createElement('div');
		divModal.setAttribute('id', 'modal' + pelicula.idPelicula);
		divModal.setAttribute('class', 'modal modalContent');

		//<div class="modal-content fondoModal" id="submodal1>
		var subDivModal = document.createElement('div');
		subDivModal.setAttribute('class', 'modal-content fondoModal');
		subDivModal.setAttribute('id', 'submodal' + pelicula.idPelicula);

		// <div class="wrapperTituloAnio">
		var wrapperTituloAnio = document.createElement('div');
		wrapperTituloAnio.setAttribute('class', 'wrapperTituloAnio');

		// <h4>Titulo</h4>
		var h4 = document.createElement('h4');
		h4.setAttribute('class', 'h5ModalClass')
		h4.innerText = pelicula.tituloPeli;

		// <span>(Anio)</span>
		var spanAnio = document.createElement('span');
		var fecha = pelicula.fechaEstreno.substring(pelicula.fechaEstreno.length, pelicula.fechaEstreno.length - 4);
		spanAnio.innerText = `(${fecha})`;
		wrapperTituloAnio.appendChild(h4);
		wrapperTituloAnio.appendChild(spanAnio);
		subDivModal.appendChild(wrapperTituloAnio);

		// <div class="headerPeli">
		var headerPeli = document.createElement('div');
		headerPeli.setAttribute('class', 'headerPeli');

		// <img class="imagenCaratula" src="images/peliculas/movieCaratula/Pelicula_caratula.jpg">
		var imgCaratulaModal = document.createElement('img');
		imgCaratulaModal.setAttribute('class', 'imagenCaratula');
		imgCaratulaModal.setAttribute('src', 'images/peliculas/movieCaratula/' + pelicula.caratulaPeli);
		headerPeli.appendChild(imgCaratulaModal);

		// <p class="pModalClass">
		var p = document.createElement('p');
		p.setAttribute('class', 'pModalClass');
		p.innerText = pelicula.resumenPeli;
		headerPeli.appendChild(p);
		subDivModal.appendChild(headerPeli);

		// <div class="wrapperInfoBtn">
		var wrapperInfoBtn = document.createElement('div');
		wrapperInfoBtn.setAttribute('class', 'wrapperInfoBtn');

		// <div class="infoPeli">
		var infoPeli = document.createElement('div');
		infoPeli.setAttribute('class', 'infoPeli');

		// <div>
		var divCantVotaciones = document.createElement('div');

		// <span>NUMERO</span>
		var spanCantVotaciones = document.createElement('span');
		spanCantVotaciones.innerText = pelicula.valoracionesTotales;

		// <i class="material-icons">people</i>
		var iCantVotaciones = document.createElement('i');
		iCantVotaciones.setAttribute('class', 'material-icons');
		iCantVotaciones.innerText = 'people';
		spanCantVotaciones.appendChild(iCantVotaciones);
		divCantVotaciones.appendChild(spanCantVotaciones);

		// <div>
		var divValoraciones = document.createElement('div');

		// <span>NUMERO</span>
		var spanValoraciones = document.createElement('span');
		spanValoraciones.innerText = pelicula.mediaValoraciones;

		// <i class="material-icons">star</i>
		var iValoraciones = document.createElement('i');
		iValoraciones.setAttribute('class', 'material-icons');
		iValoraciones.innerText = 'star';
		spanValoraciones.appendChild(iValoraciones);
		divValoraciones.appendChild(spanValoraciones);

		// <div>
		var divPrecio = document.createElement('div');

		// <span>NUMERO</span>
		var spanPrecio = document.createElement('span');
		spanPrecio.innerText = `${pelicula.precioPeli}€`;

		// <i class="material-icons">local_activity</i>
		var iPrecio = document.createElement('i');
		iPrecio.setAttribute('class', 'material-icons');
		iPrecio.innerText = 'local_activity';
		spanPrecio.appendChild(iPrecio);
		divPrecio.appendChild(spanPrecio);

		infoPeli.appendChild(divCantVotaciones);
		infoPeli.appendChild(divValoraciones);
		infoPeli.appendChild(divPrecio);

		// <button class="btn waves-effect waves-light btnVerMas">Ver ficha completa
		var buttonVerFicha = document.createElement('button');
		buttonVerFicha.setAttribute('class', 'btn waves-effect waves-light btnVerMas');
		buttonVerFicha.setAttribute('onclick', `fichaPelicula(${pelicula.idPelicula})`);
		buttonVerFicha.innerText = 'Ver ficha completa';

		// <i class="material-icons right">forward</i>
		var iVerFicha = document.createElement('i');
		iVerFicha.setAttribute('class', 'material-icons right');
		iVerFicha.innerText = 'forward';

		buttonVerFicha.appendChild(iVerFicha);
		wrapperInfoBtn.appendChild(infoPeli);
		wrapperInfoBtn.appendChild(buttonVerFicha);

		subDivModal.appendChild(wrapperInfoBtn);

		divModal.appendChild(subDivModal);

		divCaratulaPelicula.appendChild(divModal);
		divWrapper.appendChild(divCaratulaPelicula);


		document.getElementById('submodal' + pelicula.idPelicula).style.background = "url('images/peliculas/movieFotos/" + pelicula.imagenPeli + "')";

		$('.modal').modal();
	});
}

function crearFichaPelicula(pelicula) {
	var fichaPelicula = document.getElementById('fichaPelicula');
	fichaPelicula.innerText = '';

	// <div class="caratulaPelicula" id="1">
	var divCaratulaPelicula = document.createElement('div');
	divCaratulaPelicula.setAttribute('class', 'caratulaFichaPelicula');
	divCaratulaPelicula.setAttribute('id', pelicula.idPelicula);

	//  <div class="fichaTituloAnio">
	var fichaTituloAnio = document.createElement('div');
	fichaTituloAnio.setAttribute('class', 'fichaTituloAnio');

	// <h4>Titulo</h4>
	var h4 = document.createElement('h4');
	h4.setAttribute('class', 'h5ModalClass')
	h4.innerText = pelicula.tituloPeli;

	// <span>(Anio)</span>
	var spanAnio = document.createElement('span');
	var fecha = pelicula.fechaEstreno.substring(pelicula.fechaEstreno.length, pelicula.fechaEstreno.length - 4);
	spanAnio.innerText = `(${fecha})`;
	fichaTituloAnio.appendChild(h4);
	fichaTituloAnio.appendChild(spanAnio);
	divCaratulaPelicula.appendChild(fichaTituloAnio);

	// <div id=headFicha class="headerFichaPeli">
	var headFicha = document.createElement('headFicha');
	headFicha.setAttribute('id', 'headFicha');
	headFicha.setAttribute('class', 'headerFichaPeli');

	// <img class="imagenCaratula" src="images/peliculas/movieCaratula/Pelicula_caratula.jpg">
	var imagenCaratula = document.createElement('img');
	imagenCaratula.setAttribute('class', 'caratulaFicha');
	imagenCaratula.setAttribute('src', 'images/peliculas/movieCaratula/' + pelicula.caratulaPeli);
	headFicha.appendChild(imagenCaratula);
	divCaratulaPelicula.appendChild(headFicha);

	// <div class="infoFichaPelicula">
	var infoFichaPelicula = document.createElement('div');
	infoFichaPelicula.setAttribute('class', 'infoFichaPelicula');

	// <p>Veces puntuada: <span>6</span></p>
	var pVP = document.createElement('p');
	pVP.innerText = 'Veces puntuada: ';
	var spanVP = document.createElement('span');
	spanVP.innerText = pelicula.valoracionesTotales;
	pVP.appendChild(spanVP);
	infoFichaPelicula.appendChild(pVP);

	// <p>Media valoraciones: <span>4/5</span></p>
	var pM = document.createElement('p');
	pM.innerText = 'Media valoraciones ';
	var spanM = document.createElement('span');
	spanM.innerText = parseInt(pelicula.mediaValoraciones);
	pM.appendChild(spanM);
	infoFichaPelicula.appendChild(pM);

	// <p>Precio: <span>6.5 €</span></p>
	var pP = document.createElement('p');
	pP.innerText = 'Precio ';
	var spanP = document.createElement('span');
	spanP.innerText = `${pelicula.precioPeli} €`;
	pP.appendChild(spanP);
	infoFichaPelicula.appendChild(pP);

	divCaratulaPelicula.appendChild(infoFichaPelicula);

	//<hr>
	var hr = document.createElement('hr');
	divCaratulaPelicula.appendChild(hr);

	// <div class="textoPeli">
	var textoPeli = document.createElement('div');
	textoPeli.setAttribute('class', 'textoPeli')
	var pSinopsis = document.createElement('p');
	pSinopsis.innerText = 'Sinopsis:';
	textoPeli.appendChild(pSinopsis);

	// <p class="pInfoPelicula">
	var pInfoPelicula = document.createElement('p');
	pInfoPelicula.setAttribute('class', 'pInfoPelicula');
	pInfoPelicula.innerText = pelicula.resumenPeli;
	textoPeli.appendChild(pInfoPelicula);

	// <p>Fecha de esteno: <span>fecha</span></p>
	var pFE = document.createElement('p');
	pFE.innerText = 'Fecha de estreno: ';
	var spanFE = document.createElement('span');
	spanFE.innerText = pelicula.fechaEstreno;
	pFE.appendChild(spanFE);
	textoPeli.appendChild(pFE);

	// <p>Audios disponibles: <span>audios</span></p>
	var pAD = document.createElement('p');
	pAD.innerText = 'Audios disponibles: ';
	var spanAD = document.createElement('span');
	spanAD.innerText = pelicula.audiosDisponibles;
	pAD.appendChild(spanAD);
	textoPeli.appendChild(pAD);

	// <p>Subtitulos disponibles: <span>subtitulos</span></p>
	var pSD = document.createElement('p');
	pSD.innerText = 'Subtitulos disponibles: ';
	var spanSD = document.createElement('span');
	spanSD.innerText = pelicula.subtitulosDisponibles;
	pSD.appendChild(spanSD);
	textoPeli.appendChild(pSD);

	// <p>Duración: <span>120 min.</span></p>
	var pD = document.createElement('p');
	pD.innerText = 'Duración: '
	var spanD = document.createElement('span');
	spanD.innerText = `${pelicula.duracionPeli} min.`;
	pD.appendChild(spanD);
	textoPeli.appendChild(pD);

	divCaratulaPelicula.appendChild(textoPeli);

	// <div class="userActionBtn">
	var userActionBtn = document.createElement('div');
	userActionBtn.setAttribute('class', 'userActionBtn');

	// <div class="input-field">
	var input_field = document.createElement('div');
	input_field.setAttribute('class', 'input-field');

	// <span>¿Ya la has visto? Puntúala!</span>
	var spanTxt = document.createElement('span');
	spanTxt.innerText = '¿Ya la has visto? ¡Puntúala!';
	input_field.appendChild(spanTxt);

	// <select>
	var select = document.createElement('select');

	// <option value="0">Puntua la película</option>
	var option0 = document.createElement('option');
	option0.setAttribute('value', '0');
	option0.innerText = 'Puntua la película';
	select.appendChild(option0);

	// <option value="1">Mala</option>
	var option1 = document.createElement('option');
	option1.setAttribute('value', '1');
	pelicula.idPuntuacion === 1 ? option1.setAttribute('selected','true'):false;
	option1.innerText = 'Mala';
	select.appendChild(option1);



	// <option value="2">Regular</option>
	var option2 = document.createElement('option');
	option2.setAttribute('value', '2');
	pelicula.idPuntuacion === 2 ? option2.setAttribute('selected', 'true') : false;
	option2.innerText = 'Regular';
	select.appendChild(option2);

	//  <option value="3">Buena</option>
	var option3 = document.createElement('option');
	option3.setAttribute('value', '3');
	pelicula.idPuntuacion === 2 ? option3.setAttribute('selected', 'true') : false;
	option3.innerText = 'Buena';
	select.appendChild(option3);

	// <option value="4">Excelente</option>
	var option4 = document.createElement('option');
	option4.setAttribute('value', '4');
	pelicula.idPuntuacion === 4 ? option4.setAttribute('selected', 'true') : false;
	option4.innerText = 'Excelente';
	select.appendChild(option4);

	// <option value="5">Obra maestra</option>
	var option5 = document.createElement('option');
	option5.setAttribute('value', '5');
	pelicula.idPuntuacion === 5 ? option5.setAttribute('selected', 'true') : false;
	option5.innerText = 'Obra Maestra';
	select.appendChild(option5);

	input_field.appendChild(select);
	userActionBtn.appendChild(input_field);

	// <button id = "btnFavorito" class="btn btn-large btn-floating waves-effect waves-light btnTrailer">
	var btnFavorito = document.createElement('button');
	btnFavorito.setAttribute('id', 'btnFavorito');
	if (pelicula.idMarcarFavorito === 0){
		btnFavorito.setAttribute('class', 'btn btn-large btn-floating waves-effect waves-light btnTrailer btnDefecto');
	}else{
		btnFavorito.setAttribute('class', 'btn btn-large btn-floating waves-effect waves-light btnTrailer btnTriggerFav');
	}

	//  <i class="material-icons right">favorite</i>
	var iBtnFavorito = document.createElement('i');
	iBtnFavorito.setAttribute('class', 'material-icons right');
	iBtnFavorito.innerText = 'favorite';

	btnFavorito.appendChild(iBtnFavorito);
	userActionBtn.appendChild(btnFavorito);

	// <button id="btnComprar" class="btn btn-large btn-floating waves-effect waves-light btnTrailer">
	var btnComprar = document.createElement('button');
	btnComprar.setAttribute('id', 'btnComprar');
	if(pelicula.idCompra === 0){
		btnComprar.setAttribute('class', 'btn btn-large btn-floating waves-effect waves-light btnTrailer btnDefecto');
	}else{
		btnComprar.setAttribute('class', 'btn btn-large btn-floating waves-effect waves-light btnTrailer btnTriggerCompra');
	}

	// <i class="material-icons right">local_grocery_store</i>
	var ibtnComprar = document.createElement('i');
	ibtnComprar.setAttribute('class', 'material-icons right');
	ibtnComprar.innerText = 'local_grocery_store';

	btnComprar.appendChild(ibtnComprar);
	userActionBtn.appendChild(btnComprar);

	// <button id="btnMostrarTrailer" class="btn btn-large btn-floating waves-effect waves-light btnTrailer">
	var btnMostrarTrailer = document.createElement('button');
	btnMostrarTrailer.setAttribute('id', 'btnMostrarTrailer');
	btnMostrarTrailer.setAttribute('class', 'btn btn-large btn-floating waves-effect waves-light btnTrailer btnDefecto')

	// <i class="material-icons right">live_tv</i>
	var ibtnMostrarTrailer = document.createElement('i');
	ibtnMostrarTrailer.setAttribute('class', 'material-icons right');
	ibtnMostrarTrailer.innerText = 'live_tv';

	btnMostrarTrailer.appendChild(ibtnMostrarTrailer);
	userActionBtn.appendChild(btnMostrarTrailer);

	divCaratulaPelicula.appendChild(userActionBtn);

	// <a id="anchorTrailer" class="modal-trigger" href="#trailerPelicula"></a>
	var anchorTrailer = document.createElement('a');
	anchorTrailer.setAttribute('id', 'anchorTrailer');
	anchorTrailer.setAttribute('class', 'modal-trigger');
	anchorTrailer.setAttribute('href', '#trailerPelicula');
	divCaratulaPelicula.appendChild(anchorTrailer);

	// <div id="modalTrailer">
	var modalTrailer = document.createElement('div');
	modalTrailer.setAttribute('id', 'modalTrailer');

	// <div id="trailerPelicula" class="modal">
	var trailerPelicula = document.createElement('div');
	trailerPelicula.setAttribute('id', 'trailerPelicula');
	trailerPelicula.setAttribute('class', 'modal');

	// <div class="modal-content trailer">
	var divModalContent = document.createElement('div');
	divModalContent.setAttribute('class', 'modal-content trailer');

	//<iframe src='...'>
	var iframe = document.createElement('iframe');
	iframe.setAttribute('src', `${pelicula.trailerPeli}?controls=0&autoplay=0&disablekb=0&fs=0&modestbranding=1&rel=0&showinfo=0`);
	iframe.setAttribute('frameborder', '0');
	iframe.setAttribute('allow', 'accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture');
	iframe.setAttribute('allowfullscreen', 'true');

	divModalContent.appendChild(iframe);
	trailerPelicula.appendChild(divModalContent);
	modalTrailer.appendChild(trailerPelicula);
	divCaratulaPelicula.appendChild(modalTrailer);

	fichaPelicula.appendChild(divCaratulaPelicula);

	document.getElementById('headFicha').style.background = `url('images/peliculas/movieFotos/${pelicula.imagenPeli}')`;
	var btnMostrarTrailer = document.getElementById('btnMostrarTrailer');
	if (btnMostrarTrailer !== null) {
		btnMostrarTrailer.addEventListener('click', function () {
			$('#anchorTrailer')[0].click();
		});
	}

	$('.modal').modal();
	$('select').formSelect();
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

		var btnFavorito = document.getElementById('btnFavorito');
		if (btnFavorito !== null) {
			btnFavorito.addEventListener('click', function () {

			});
		}

		var btnComprar = document.getElementById('btnComprar');
		if (btnComprar !== null) {
			btnComprar.addEventListener('click', function () {

			});
		}

		// var btnMostrarTrailer = document.getElementById('btnMostrarTrailer');
		// if (btnMostrarTrailer !== null) {
		// 	btnMostrarTrailer.addEventListener('click', function () {
		// 		$('#anchorTrailer')[0].click();
		// 	});
		// }

		var generoSelector = document.getElementById('generoSelector');
		if (generoSelector !== null) {
			generoSelector.addEventListener('change', function () {
				if (generoSelector.value === "0") {
					cargarTodasPelis();
				} else {
					buscarGenero(generoSelector.value);
				}
			});
		}

		var inputNombre = document.getElementById('inputNombre');
		if (inputNombre !== null) {
			inputNombre.addEventListener('keyup', function () {
				buscarTitulo(inputNombre.value);
			});
		}

	}
}