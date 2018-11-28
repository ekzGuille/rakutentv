-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 29-11-2018 a las 00:28:39
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `rakutentv`
--
DROP DATABASE IF EXISTS `rakutentv`;
CREATE DATABASE IF NOT EXISTS `rakutentv` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `rakutentv`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actor`
--

CREATE TABLE `actor` (
  `idActor` int(11) NOT NULL,
  `nombreActor` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidosActor` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `fotoActor` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `actor`
--

INSERT INTO `actor` (`idActor`, `nombreActor`, `apellidosActor`, `fotoActor`) VALUES
(1, 'Keanu', 'Reeves', 'KeanuReeves.jpg'),
(2, 'Ryan', 'Reynolds', 'RyanReynolds.jpg'),
(3, 'Tom', 'Cruise', 'TomCruise.jpg'),
(4, 'Eddie', 'Murphy', 'EddieMurphy.jpg'),
(5, 'Zoe', 'Saldana', 'ZoeSaldana.jpg'),
(6, 'Milla', 'Jovovich', 'MillaJovovich.jpg'),
(7, 'James', 'McAvoy', 'JamesMcAvoy.jpg'),
(8, 'Nicolas', 'Cage', 'NicolasCage.jpg'),
(9, 'John', 'Travolta', 'JohnTravolta.jpg'),
(10, 'Freddie', 'Highmore', 'FreddieHighmore.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `idComentario` int(11) NOT NULL,
  `descComentario` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `fechaComentario` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idPelicula` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `idCompra` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL,
  `precioCompra` decimal(10,2) NOT NULL,
  `fechaCompra` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`idCompra`, `idUsuario`, `idPelicula`, `precioCompra`, `fechaCompra`) VALUES
(1, 3, 13, '4.50', '2018-11-27 08:21:54'),
(2, 3, 7, '2.00', '2018-11-27 19:53:12'),
(3, 3, 6, '4.00', '2018-11-27 19:55:51'),
(4, 2, 20, '2.50', '2018-11-28 21:35:44'),
(5, 1, 14, '7.50', '2018-11-28 21:38:07');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `director`
--

CREATE TABLE `director` (
  `idDirector` int(11) NOT NULL,
  `nombreDir` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidosDir` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `fotoDir` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `director`
--

INSERT INTO `director` (`idDirector`, `nombreDir`, `apellidosDir`, `fotoDir`) VALUES
(1, 'Christopher', 'Nolan', 'ChristopherNolan.jpg'),
(2, 'Áex', ' De la Iglesia', 'AlexDeLaIglesia.jpg'),
(3, 'Quentin', 'Tarantino', 'QuentinTarantino.jpg'),
(4, 'Matthew', 'Vaughn', 'MatthewVaughn.jpg '),
(5, 'David', 'Leitch', 'DavidLeitch.jpg'),
(6, 'Chad', 'Stahelski', 'ChadStahelski.jpg'),
(7, 'Tim', 'Miller', 'TimMiller.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `idFactura` int(11) NOT NULL,
  `descFactura` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `idCompra` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `idGenero` int(11) NOT NULL,
  `descGenero` varchar(20) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`idGenero`, `descGenero`) VALUES
(1, 'Acción'),
(2, 'Animación'),
(3, 'Ciencia ficción'),
(4, 'Cine español'),
(5, 'Comedia'),
(6, 'Thriller'),
(7, 'Romántico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `infopuntuacion`
--

CREATE TABLE `infopuntuacion` (
  `idInfoPuntuacion` int(11) NOT NULL,
  `descInfoPuntuacion` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `fotoInfoPuntuacion` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `infopuntuacion`
--

INSERT INTO `infopuntuacion` (`idInfoPuntuacion`, `descInfoPuntuacion`, `fotoInfoPuntuacion`) VALUES
(1, 'Mala', ''),
(2, 'Regular', ''),
(3, 'Buena', ''),
(4, 'Excelente', ''),
(5, 'Obra maestra', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcarfavorito`
--

CREATE TABLE `marcarfavorito` (
  `idMarcarFavorito` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `marcarfavorito`
--

INSERT INTO `marcarfavorito` (`idMarcarFavorito`, `idPelicula`, `idUsuario`) VALUES
(1, 7, 3),
(2, 15, 3),
(3, 12, 2),
(4, 3, 1),
(5, 6, 2),
(7, 8, 5),
(8, 12, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodopago`
--

CREATE TABLE `metodopago` (
  `idMetodoPago` int(11) NOT NULL,
  `descMetodoPago` varchar(30) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `metodopago`
--

INSERT INTO `metodopago` (`idMetodoPago`, `descMetodoPago`) VALUES
(-1, 'Ninguna'),
(1, 'Tarjeta Crédito'),
(2, 'PayPal'),
(3, 'Paysafecard');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `idPelicula` int(11) NOT NULL,
  `tituloPeli` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `resumenPeli` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `trailerPeli` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `caratulaPeli` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `imagenPeli` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `fechaEstreno` date NOT NULL,
  `audiosDisponibles` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `subtitulosDisponibles` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `duracionPeli` int(3) NOT NULL,
  `precioPeli` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`idPelicula`, `tituloPeli`, `resumenPeli`, `trailerPeli`, `caratulaPeli`, `imagenPeli`, `fechaEstreno`, `audiosDisponibles`, `subtitulosDisponibles`, `duracionPeli`, `precioPeli`) VALUES
(1, 'John Wick: Otro día para matar', 'La ciudad de Nueva York se convierte en el patio acribillado a balazos de un ex-asesino mientras él elimina a los gánsteres que destruyeron todo lo que él quería.', 'https://www.youtube.com/embed/RllJtOw0USI', 'JohnWick_caratula.jpg', 'JohnWick_foto.jpg', '2014-10-13', 'ES,EN,ITA', 'RU,EN,ES', 101, '5.50'),
(2, 'Deadpool', 'Un ex mercenario quien, tras haber sido sometido a un cruel experimento adquiere el súper poder de sanar rápidamente, pretende vengarse del hombre que destrozó su vida.', 'https://www.youtube.com/embed/ONHBaC-pfsk', 'Deadpool_caratula.jpg', 'Deadpool_foto.jpg', '2016-01-21', 'ES,RU', 'EN,ES', 109, '7.20'),
(3, 'Matrix', 'Un experto en computadoras descubre que su mundo es una simulación computarizada creada por malvada ciberinteligencia.', 'https://www.youtube.com/embed/m8e-FF8MsqU', 'Matrix_caratula.jpg', 'Matrix_foto.jpg', '1999-06-23', 'ES,EN', 'ES', 150, '4.50'),
(4, 'Piratas del Caribe', 'Piratas del Caribe es el título de una serie cinematográfica de aventura fantástica y piratas, producidas por Jerry Bruckheimer.', 'https://www.youtube.com/embed/ojvNfJjX8OQ', 'PiratasDelCaribe_caratula.jpg', 'PiratasDelCaribe_foto.jpg', '2006-08-11', 'EN,RU', 'ITA,BR', 151, '5.80'),
(5, 'X-Men: Primera generación', 'A principios de los años 60, Charles Xavier y Magneto se convierten en grandes amigos. Usando sus poderes, se unen para proteger al mundo de una guerra nuclear.', 'https://www.youtube.com/embed/UrbHykKUfTM', 'XMenFirstClass_caratula.jpg', 'XMenFirstClass_foto.jpg', '2011-05-25', 'ES,RU,ITA', 'EN', 132, '6.40'),
(6, 'El quinto elemento', 'La historia del director Luc Besson, sobre un taxista del futuro que tropieza con la mujer que puede salvar al mundo.\r\n', 'https://www.youtube.com/embed/aB-AUTGqUCU', 'ElQuintoElemento_caratula.jpg', 'ElQuintoElemento_foto.jpg', '1997-05-30', 'EN', 'ES', 127, '3.50'),
(7, 'Dr. Dolittle', 'Un cirujano se convierte en mejor doctor, cuando recobra su vieja y casi olvidada habilidad de hablar con los animales.', 'https://www.youtube.com/embed/ozMmf9Yi7TM', 'DrDolittle_caratula.jpg', 'DrDolittle_foto.jpg', '1998-09-11', 'ES', 'ES', 90, '4.50'),
(8, 'Mi gran noche', 'Un hombre desempleado consigue trabajo como extra en la filmación de un especial de Año Nuevo para TV, donde debe recibir el año sin parar y en pésimas condiciones.\r\n', 'https://www.youtube.com/embed/KBahnqECT7o', 'MiGranNoche_caratula.jpg', 'MiGranNoche_foto.jpg', '2015-10-23', 'ES,EN,GER', 'EN,FR', 100, '5.50'),
(9, 'Kingsman: Servicio secreto', 'Gary \"Eggsy\" Unwin, cuyo fallecido padre trabajó calladamente para una agencia de espionaje ultra secreta, vive en una urbanización del sur de Londres y parece dirigirse hacia las rejas. ', 'https://www.youtube.com/embed/m4NCribDx4U', 'Kingsman_caratula.jpg', 'Kingsman_foto.jpg', '2015-01-24', 'ESP,EN', 'ESP', 130, '6.70'),
(10, 'Origen', 'Dom Cobb (Leonardo DiCaprio) es un ladrón con una extraña habilidad para entrar a los sueños de la gente y robarle los secretos de sus subconscientes.', 'https://www.youtube.com/embed/YoHD9XEInc0', 'Origen_caratula.jpg', 'Origen_foto.jpg', '2010-08-06', 'ES,EN,PT', 'PT,FR', 148, '6.50'),
(11, 'Slumdog Millionaire', 'Mientras Jamal Malik (Dev Patel), de 18 años de edad, responde preguntas en la versión india de \"Quién Quiere Ser Millonario\", escenas retrospectivas muestran cómo llegó ahí. ', 'https://www.youtube.com/embed/AIzbwV7on6Q', 'Slum_Millionaire_caratula.jpg', 'Slum_Millionaire_foto.jpg', '2009-02-13', 'RU,RUS,GER', 'FR,ES', 120, '7.50'),
(12, 'V de Vendetta', 'Un vigilante conocido sólo como V utiliza tácticas terroristas para luchar contra el estado totalitario en el que ahora vive. ', 'https://www.youtube.com/embed/k_13fFIrhPk', 'VVendetta_caratula.jpg', 'VVendetta_foto.jpg', '2006-04-07', 'ES,EN', 'PT,RU', 133, '8.30'),
(13, 'El gran showman', 'P. T. Barnum es un padre de familia del siglo XIX que decide cambiar su vida cuando la empresa para la que trabaja cae en bancarrota. ', 'https://www.youtube.com/embed/AXCTMGYUg9A', 'GranShowman_caratula.jpg', 'GranShowman_foto.jpg', '2017-12-29', 'RU,GER', 'ES,PT', 106, '5.30'),
(14, 'La teoría del todo', 'Durante los años sesenta, el estudiante de la Universidad de Cambridge y futuro físico Stephen Hawking se enamora de su compañera Jane Wilde. ', 'https://www.youtube.com/embed/Salz7uGp72c', 'TeoriaDelTodo_caratula.jpg', 'TeoriaDelTodo_foto.jpg', '2017-11-07', 'EN', 'ES', 123, '7.50'),
(15, 'Jumanji', 'Una historia de Chris Allsburg sobre un juego que libera una estampida de peligros de la selva contra sus jugadores.', 'https://www.youtube.com/embed/GrlMYIYt-SI', 'Jumanji_caratula.jpg', 'Jumanji_foto.jpg', '1996-02-12', 'ES', 'ES', 104, '3.40'),
(16, 'The imitation game (Descifrando enigma)', 'El genio británico de la lógica y criptógrafo Alan Turing ayuda con el Código Enigma de Alemania durante la Segunda Guerra Mundial, pero es perseguido por su gobierno por actos homosexuales ilegales.', 'https://www.youtube.com/embed/nuPZUUED5uk', 'TheImiGame_caratula.jpg', 'TheImiGame_foto.jpg', '2015-01-07', 'PT,FR,GER', 'EN, RU', 114, '6.60'),
(17, 'Matilda', 'Una niña (Mara Wilson) desarrolla una capacidad mental extraordinaria, a pesar de sus padres (Danny DeVito, Rhea Perlman) descuidados y de una directora abusiva.', 'https://www.youtube.com/embed/hUGHWje7liM', 'Matilda_caratula.jpg', 'Matilda_foto.jpg', '1996-12-23', 'ES', 'ES', 102, '3.80'),
(18, 'La isla', 'En el año 2019, un mercenario (Djimon Hounsou) persigue a dos clones (Ewan McGregor, Scarlett Johansson) que escaparon de unas instalaciones de investigación tras descubrir su verdadero destino.', 'https://www.youtube.com/embed/xb4PR2zYUrk', 'LaIsla_caratula.jpg', 'LaIsla_foto.jpg', '2005-08-05', 'PT,FR', 'GER,RU', 138, '4.20'),
(19, 'La familia Bélier', 'Una adolescente, que vive con sus padres sordos, descubre que tiene un sorprendente don para el canto.', 'https://www.youtube.com/embed/y36W7P1FxJI', 'FamiliaBelier_caratula.jpg', 'FamiliaBelier_foto.jpg', '2014-12-25', 'FR', 'FR,ES,EN', 106, '4.40'),
(20, 'Focus', 'Un estafador veterano apoya a una joven y atractiva mujer, pero las cosas se complican cuando ellos se involucran románticamente.', 'https://www.youtube.com/embed/MxCRgtdAuBo', 'Focus_caratula.jpg', 'Focus_foto.jpg', '2015-01-26', 'ES,RU,EN', 'PT,FR', 105, '7.40');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puntuacion`
--

CREATE TABLE `puntuacion` (
  `idPuntuacion` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `idInfoPuntuacion` int(11) NOT NULL,
  `fechaPuntuacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `puntuacion`
--

INSERT INTO `puntuacion` (`idPuntuacion`, `idPelicula`, `idUsuario`, `idInfoPuntuacion`, `fechaPuntuacion`) VALUES
(1, 3, 1, 5, '2018-11-21 23:31:17'),
(2, 6, 3, 3, '2018-11-22 07:18:19'),
(4, 6, 2, 4, '2018-11-22 07:18:39'),
(5, 7, 2, 1, '2018-11-22 09:58:29'),
(10, 4, 1, 3, '2018-11-28 20:55:54'),
(11, 1, 1, 3, '2018-11-28 21:20:07'),
(12, 5, 5, 2, '2018-11-28 21:23:21'),
(13, 2, 5, 2, '2018-11-28 21:36:18'),
(14, 8, 5, 3, '2018-11-28 21:37:10'),
(15, 2, 1, 4, '2018-11-28 21:37:32'),
(16, 14, 1, 5, '2018-11-28 21:37:40'),
(17, 12, 2, 5, '2018-11-28 23:24:21');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teneractor`
--

CREATE TABLE `teneractor` (
  `idTenerActor` int(11) NOT NULL,
  `idActor` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `teneractor`
--

INSERT INTO `teneractor` (`idTenerActor`, `idActor`, `idPelicula`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 7, 5),
(4, 5, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tenerdirector`
--

CREATE TABLE `tenerdirector` (
  `idTenerDirector` int(11) NOT NULL,
  `idDirector` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tenerdirector`
--

INSERT INTO `tenerdirector` (`idTenerDirector`, `idDirector`, `idPelicula`) VALUES
(1, 7, 2),
(2, 5, 1),
(3, 6, 1),
(4, 1, 10),
(5, 2, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tenergenero`
--

CREATE TABLE `tenergenero` (
  `idTenerGenero` int(11) NOT NULL,
  `idGenero` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tenergenero`
--

INSERT INTO `tenergenero` (`idTenerGenero`, `idGenero`, `idPelicula`) VALUES
(1, 1, 2),
(2, 1, 1),
(3, 4, 8),
(4, 7, 19),
(5, 5, 2),
(6, 3, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `email` varchar(35) COLLATE utf8_spanish2_ci NOT NULL,
  `username` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `contrasena` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `fechaCreacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fotoUsuario` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `idMetodoPago` int(11) NOT NULL DEFAULT '-1',
  `infoMetodoPago` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `activoUsuario` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `email`, `username`, `contrasena`, `fechaCreacion`, `fotoUsuario`, `idMetodoPago`, `infoMetodoPago`, `activoUsuario`) VALUES
(1, 'ejemplo@ejemplo.com', 'ejemplo', 'ejemplo1', '2018-11-14 23:00:00', '', -1, '', 1),
(2, 'gss@svalero.com', 'gss', '1234', '2018-11-21 23:00:00', '', -1, '', 1),
(3, 'alberto@svalero.com', 'alberto', 'akk', '2018-11-21 23:00:00', '', -1, '', 1),
(4, 'ricardo@svalero.com', 'richi', 'ric', '2018-11-28 20:15:57', '', -1, '', 1),
(5, 'juanito@juan.com', 'juan', 'jon', '2018-11-28 20:16:27', '', -1, '', 1);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `valoracionglobalpelicula`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `valoracionglobalpelicula` (
`idPelicula` int(11)
,`valoracionesTotales` bigint(21)
,`mediaValoraciones` decimal(14,4)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `valoracionglobalpelicula`
--
DROP TABLE IF EXISTS `valoracionglobalpelicula`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `valoracionglobalpelicula`  AS  select `puntuacion`.`idPelicula` AS `idPelicula`,count(`puntuacion`.`idPelicula`) AS `valoracionesTotales`,avg(`puntuacion`.`idInfoPuntuacion`) AS `mediaValoraciones` from `puntuacion` group by `puntuacion`.`idPelicula` ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`idActor`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`idComentario`),
  ADD KEY `FK_idPeliculaCom` (`idPelicula`),
  ADD KEY `FK_idUsuario` (`idUsuario`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`idCompra`),
  ADD KEY `FK_idPeliculaCompra` (`idPelicula`),
  ADD KEY `FK_idUsuarioCompra` (`idUsuario`);

--
-- Indices de la tabla `director`
--
ALTER TABLE `director`
  ADD PRIMARY KEY (`idDirector`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`idFactura`),
  ADD KEY `FK_idCompra` (`idCompra`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`idGenero`);

--
-- Indices de la tabla `infopuntuacion`
--
ALTER TABLE `infopuntuacion`
  ADD PRIMARY KEY (`idInfoPuntuacion`);

--
-- Indices de la tabla `marcarfavorito`
--
ALTER TABLE `marcarfavorito`
  ADD PRIMARY KEY (`idMarcarFavorito`),
  ADD KEY `FK_idUsuarioFav` (`idUsuario`),
  ADD KEY `FK_idPeliculaFav` (`idPelicula`);

--
-- Indices de la tabla `metodopago`
--
ALTER TABLE `metodopago`
  ADD PRIMARY KEY (`idMetodoPago`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`idPelicula`);

--
-- Indices de la tabla `puntuacion`
--
ALTER TABLE `puntuacion`
  ADD PRIMARY KEY (`idPuntuacion`),
  ADD UNIQUE KEY `idPelicula` (`idPelicula`,`idUsuario`) USING BTREE,
  ADD KEY `FK_idInfoPuntuacionPuntuacion` (`idInfoPuntuacion`),
  ADD KEY `FK_idUsuarioPuntuacion` (`idUsuario`);

--
-- Indices de la tabla `teneractor`
--
ALTER TABLE `teneractor`
  ADD PRIMARY KEY (`idTenerActor`,`idActor`,`idPelicula`),
  ADD KEY `FK_idActor` (`idActor`),
  ADD KEY `FK_idPeliculaAct` (`idPelicula`);

--
-- Indices de la tabla `tenerdirector`
--
ALTER TABLE `tenerdirector`
  ADD PRIMARY KEY (`idTenerDirector`,`idDirector`,`idPelicula`),
  ADD KEY `FK_idDirector` (`idDirector`),
  ADD KEY `FK_idPeliculaDir` (`idPelicula`);

--
-- Indices de la tabla `tenergenero`
--
ALTER TABLE `tenergenero`
  ADD PRIMARY KEY (`idTenerGenero`,`idGenero`,`idPelicula`),
  ADD KEY `FK_idGenero` (`idGenero`),
  ADD KEY `FK_idPeliculaGen` (`idPelicula`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `FK_idMetodoPago` (`idMetodoPago`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actor`
--
ALTER TABLE `actor`
  MODIFY `idActor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `idComentario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `idCompra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `director`
--
ALTER TABLE `director`
  MODIFY `idDirector` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `idFactura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `idGenero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `infopuntuacion`
--
ALTER TABLE `infopuntuacion`
  MODIFY `idInfoPuntuacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `marcarfavorito`
--
ALTER TABLE `marcarfavorito`
  MODIFY `idMarcarFavorito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `metodopago`
--
ALTER TABLE `metodopago`
  MODIFY `idMetodoPago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `idPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `puntuacion`
--
ALTER TABLE `puntuacion`
  MODIFY `idPuntuacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `teneractor`
--
ALTER TABLE `teneractor`
  MODIFY `idTenerActor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tenerdirector`
--
ALTER TABLE `tenerdirector`
  MODIFY `idTenerDirector` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tenergenero`
--
ALTER TABLE `tenergenero`
  MODIFY `idTenerGenero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `FK_idPeliculaCom` FOREIGN KEY (`idPelicula`) REFERENCES `pelicula` (`idPelicula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `FK_idPeliculaCompra` FOREIGN KEY (`idPelicula`) REFERENCES `pelicula` (`idPelicula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idUsuarioCompra` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `FK_idCompra` FOREIGN KEY (`idCompra`) REFERENCES `compra` (`idCompra`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `marcarfavorito`
--
ALTER TABLE `marcarfavorito`
  ADD CONSTRAINT `FK_idPeliculaFav` FOREIGN KEY (`idPelicula`) REFERENCES `pelicula` (`idPelicula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idUsuarioFav` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `puntuacion`
--
ALTER TABLE `puntuacion`
  ADD CONSTRAINT `FK_idInfoPuntuacionPuntuacion` FOREIGN KEY (`idInfoPuntuacion`) REFERENCES `infopuntuacion` (`idInfoPuntuacion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idPeliculaPuntuacion` FOREIGN KEY (`idPelicula`) REFERENCES `pelicula` (`idPelicula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idUsuarioPuntuacion` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `teneractor`
--
ALTER TABLE `teneractor`
  ADD CONSTRAINT `FK_idActor` FOREIGN KEY (`idActor`) REFERENCES `actor` (`idActor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idPeliculaAct` FOREIGN KEY (`idPelicula`) REFERENCES `pelicula` (`idPelicula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tenerdirector`
--
ALTER TABLE `tenerdirector`
  ADD CONSTRAINT `FK_idDirector` FOREIGN KEY (`idDirector`) REFERENCES `director` (`idDirector`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idPeliculaDir` FOREIGN KEY (`idPelicula`) REFERENCES `pelicula` (`idPelicula`);

--
-- Filtros para la tabla `tenergenero`
--
ALTER TABLE `tenergenero`
  ADD CONSTRAINT `FK_idGenero` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idPeliculaGen` FOREIGN KEY (`idPelicula`) REFERENCES `pelicula` (`idPelicula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_idMetodoPago` FOREIGN KEY (`idMetodoPago`) REFERENCES `metodopago` (`idMetodoPago`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
