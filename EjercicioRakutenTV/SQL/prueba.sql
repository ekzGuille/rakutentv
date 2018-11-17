-- Vista que recopila la cantidad de veces que una pelicula se ha votado así como la media de los votos de dicha pelicula
-- Parámetros de la vista:
--		- idPelicula
-- 		- Veces que se ha votado dicha pelicula 
-- 		- Media de las veces que se ha votado

-- SELECT pelicula.tituloPeli, usuario.email, infopuntuacion.descInfoPuntuacion from pelicula, usuario, infopuntuacion, puntuacion where pelicula.idPelicula = puntuacion.idPelicula and usuario.idUsuario = puntuacion.idUsuario and infopuntuacion.idInfoPuntuacion = puntuacion.idInfoPuntuacion

-- SELECT idPelicula, count(idPelicula) from puntuacion GROUP by idPelicula
-- select idPelicula, sum(idInfoPuntuacion) from puntuacion GROUP by idPelicula
-- select idPelicula,count(idPelicula) 'CUENTA' , sum(idInfoPuntuacion) 'SUMA' , AVG(idInfoPuntuacion) 'MEDIA' from puntuacion GROUP by idPelicula

CREATE VIEW `valoracionglobalpelicula`  AS  
SELECT idPelicula ,COUNT(idPelicula) 'valoracionesTotales', AVG(idInfoPuntuacion ) 'mediaValoraciones' FROM puntuacion GROUP BY idPelicula;
