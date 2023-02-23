-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 23-02-2023 a las 21:59:57
-- Versión del servidor: 8.0.28
-- Versión de PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pruebatravelly`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aeropuerto`
--

DROP TABLE IF EXISTS `aeropuerto`;
CREATE TABLE IF NOT EXISTS `aeropuerto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `latitud` varchar(255) DEFAULT NULL,
  `longitud` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `pais_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7fgwpdvmb5xyc7yq23logh7ti` (`pais_id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `aeropuerto`
--

INSERT INTO `aeropuerto` (`id`, `codigo`, `latitud`, `longitud`, `nombre`, `region`, `pais_id`) VALUES
(7, 'AFA', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Suboficial Ayudante Santiago Germano', ' Mendoza', 6),
(6, 'AEP', '11° 11\' 1', '11° 11\' 11', 'Aeroparque Jorge Newbery', 'Buenos Aires', 6),
(5, 'ABC', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto de Albacete  ', ' Castilla-La Mancha', 5),
(8, 'AOL', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional de Paso de los Libres', 'Corrientes', 6),
(9, 'BRC', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Teniente Luis Candelaria ', 'Río Negro', 6),
(10, 'COR', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Ing. Ambrosio Taravella', 'Córdoba', 6),
(11, 'CLX', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Clorinda', 'Formosa', 6),
(12, 'CNQ', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Doctor Fernando Piragine Niveyro', 'Corrientes', 6),
(13, 'COC', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Comodoro Pierrestegui', 'Entre Ríos', 6),
(14, 'CTC', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Coronel Felipe Varela', 'Catamarca', 6),
(15, 'EPA', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto El Palomar', 'Buenos Aires', 6),
(16, 'EZE', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Ministro Pistarini', 'Buenos Aires', 6),
(17, 'FDO', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional de San Fernando', 'Buenos Aires', 6),
(18, 'FMA', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional de Formosa', 'Formosa', 6),
(19, 'FTE', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Comandante Armando Tola', 'Santa Cruz', 6),
(20, 'JUJ', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Gobernador Horacio Guzmán', 'Provincia de Jujuy', 6),
(21, 'LGS', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Comodoro Ricardo Salomón', ' Mendoza', 6),
(22, 'LEU', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto de Andorra-La Seu', 'Cataluña', 5),
(23, 'MAD', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Adolfo Suárez Madrid-Barajas', 'Comunidad de Madrid', 5),
(24, 'LAX', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional de Los Ángeles', 'California', 7),
(25, 'MIA', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional de Miami', 'Florida', 7),
(26, 'MCO', '11° 11\' 1', '11° 11\' 11', 'Orlando International Airport', 'Florida', 7),
(27, 'DAY', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional James M. Cox-Dayton', 'Ohio', 7),
(28, 'EGE', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Regional del Condado de Eagle', 'Colorado', 7),
(30, 'ENO', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Teniente Amín Ayub González', 'Itapúa', 8),
(31, 'FRA', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto de Fráncfort del Meno', 'Hesse', 9),
(32, 'BER', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto de Berlín-Brandeburgo Willy Brandt', 'Brandemburgo', 9),
(33, 'SCL', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional de los traidores Arturo Merino Benítez', 'Metropolitana de Santiago', 10),
(34, 'WLG', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional de Wellington', 'Wellington', 11),
(35, 'XPL', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional de Comayagua', 'Comayagua', 12),
(36, 'YUL', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Pierre Elliott Trudeau', 'Quebec', 13),
(37, 'UIO', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Mariscal Sucre', 'Pichincha', 14),
(38, 'SUF', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional de Lamezia Terme', 'Lamezia Terme', 15),
(39, 'SYD', '11° 11\' 1', '11° 11\' 11', 'Aeropuerto Internacional Kingsford Smith', 'Nueva Gales del Sur', 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asiento`
--

DROP TABLE IF EXISTS `asiento`;
CREATE TABLE IF NOT EXISTS `asiento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `asiento_columna` varchar(255) DEFAULT NULL,
  `clase` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT '1',
  `numero` int DEFAULT NULL,
  `avion_id` int NOT NULL,
  `pasajero_id` int DEFAULT NULL,
  `seleccionado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK4fw5ih4sgn6y6vggowpjo3t90` (`avion_id`),
  KEY `FKsngsjg7ks611rbhtkw12fpncp` (`pasajero_id`)
) ENGINE=MyISAM AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `asiento`
--

INSERT INTO `asiento` (`id`, `asiento_columna`, `clase`, `estado`, `numero`, `avion_id`, `pasajero_id`, `seleccionado`) VALUES
(26, 'A', 'Ejecutiva', 1, 2, 3, NULL, 0),
(25, 'A', 'Ejecutiva', 1, 1, 3, NULL, 0),
(24, 'A', 'Turista', 1, 10, 6, NULL, 0),
(23, 'B', 'Turista', 1, 9, 6, NULL, 0),
(22, 'B', 'Turista', 1, 8, 6, NULL, 0),
(17, 'B', 'Premium', 1, 3, 6, NULL, 0),
(16, 'A', 'Ejecutiva', 1, 2, 6, NULL, 0),
(15, 'A', 'Ejecutiva', 1, 1, 6, NULL, 0),
(21, 'B', 'Turista', 1, 7, 6, NULL, 0),
(20, 'A', 'Turista', 1, 6, 6, NULL, 0),
(19, 'A', 'Turista', 1, 5, 6, NULL, 0),
(18, 'B', 'Premium', 1, 4, 6, NULL, 0),
(27, 'B', 'Premium', 1, 3, 3, NULL, 0),
(28, 'B', 'Premium', 1, 4, 3, NULL, 0),
(29, 'A', 'Turista', 1, 5, 3, NULL, 0),
(30, 'A', 'Turista', 1, 6, 3, NULL, 0),
(31, 'A', 'Turista', 1, 7, 3, NULL, 0),
(32, 'B', 'Turista', 1, 8, 3, NULL, 0),
(33, 'B', 'Turista', 1, 9, 3, NULL, 0),
(34, 'B', 'Turista', 1, 10, 3, NULL, 0),
(35, 'A', 'Premium', 1, 1, 4, NULL, 0),
(36, 'A', 'Premium', 1, 2, 4, NULL, 0),
(37, 'B', 'Ejecutivo', 1, 3, 4, NULL, 0),
(38, 'B', 'Ejecutivo', 1, 4, 4, NULL, 0),
(39, 'A', 'Turista', 1, 5, 4, NULL, 0),
(40, 'A', 'Turista', 1, 6, 4, NULL, 0),
(41, 'A', 'Turista', 1, 7, 4, NULL, 0),
(42, 'B', 'Turista', 1, 8, 4, NULL, 0),
(43, 'B', 'Turista', 1, 9, 4, NULL, 0),
(44, 'B', 'Turista', 1, 10, 4, NULL, 0),
(45, 'A', 'Ejecutiva', 0, 1, 5, 25, 1),
(46, 'A', 'Ejecutiva', 1, 2, 5, NULL, 0),
(47, 'B', 'Premium', 0, 3, 5, 26, 1),
(48, 'B', 'Premium', 0, 4, 5, 27, 1),
(49, 'B', 'Turista', 1, 5, 5, NULL, 0),
(50, 'B', 'Turista', 1, 6, 5, NULL, 0),
(51, 'B', 'Turista', 0, 7, 5, 28, 1),
(52, 'A', 'Turista', 1, 8, 5, NULL, 0),
(53, 'A', 'Turista', 1, 9, 5, NULL, 0),
(54, 'A', 'Turista', 0, 10, 5, 24, 1),
(55, 'A', 'Ejecutivo', 1, 1, 7, NULL, 0),
(56, 'A', 'Ejecutivo', 1, 2, 7, NULL, 0),
(57, 'B', 'Premium', 1, 3, 7, NULL, 0),
(58, 'B', 'Premium', 1, 4, 7, NULL, 0),
(59, 'B', 'Turista', 1, 5, 7, NULL, 0),
(60, 'B', 'Turista', 1, 6, 7, NULL, 0),
(61, 'B', 'Turista', 1, 7, 7, NULL, 0),
(62, 'A', 'Turista', 1, 8, 7, NULL, 0),
(63, 'A', 'Turista', 1, 9, 7, NULL, 0),
(64, 'A', 'Turista', 1, 10, 7, NULL, 0),
(65, 'A', 'Ejecutiva', 1, 1, 8, NULL, 0),
(66, 'A', 'Ejecutiva', 1, 2, 8, NULL, 0),
(67, 'B', 'Premium', 1, 3, 8, NULL, 0),
(68, 'B', 'Premium', 1, 4, 8, NULL, 0),
(69, 'B', 'Turista', 1, 5, 8, NULL, 0),
(70, 'B', 'Turista', 1, 6, 8, NULL, 0),
(71, 'B', 'Turista', 1, 7, 8, NULL, 0),
(72, 'A', 'Turista', 1, 8, 8, NULL, 0),
(73, 'A', 'Turista', 1, 9, 8, NULL, 0),
(74, 'A', 'Turista', 1, 10, 8, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `avion`
--

DROP TABLE IF EXISTS `avion`;
CREATE TABLE IF NOT EXISTS `avion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cantidad_asientos` int NOT NULL,
  `marca` varchar(255) NOT NULL,
  `matricula` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `avion`
--

INSERT INTO `avion` (`id`, `cantidad_asientos`, `marca`, `matricula`) VALUES
(6, 10, 'Aerolineas Argentinas', 'A6-EDY'),
(3, 10, 'Gol', 'N5573N'),
(4, 10, 'Latam', 'N663JB'),
(5, 10, 'Jetsmart', '9V-SWA'),
(7, 10, 'Emirates', 'AFQ789'),
(8, 10, 'American Airlines', 'AFW1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

DROP TABLE IF EXISTS `pago`;
CREATE TABLE IF NOT EXISTS `pago` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fechayhora` datetime DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `reserva_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn8jkfq10o8ctrdwbr6nqjd8yd` (`reserva_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`id`, `fechayhora`, `monto`, `reserva_id`) VALUES
(10, '2023-02-23 05:17:43', 20570, 13),
(11, '2023-02-23 18:05:33', 30855, 14),
(12, '2023-02-23 19:52:59', 30855, 14),
(13, '2023-02-23 20:07:20', 20570, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pais`
--

DROP TABLE IF EXISTS `pais`;
CREATE TABLE IF NOT EXISTS `pais` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo_pais` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pais`
--

INSERT INTO `pais` (`id`, `codigo_pais`, `nombre`) VALUES
(7, 3, 'Estados Unidos'),
(6, 2, 'Argentina'),
(5, 1, 'España'),
(8, 4, 'Paraguay'),
(9, 5, 'Alemania'),
(10, 6, 'Chile'),
(11, 7, 'Nueva Zelanda'),
(12, 8, 'Honduras'),
(13, 9, 'Canadá'),
(14, 10, 'Ecuador'),
(15, 11, 'Italia'),
(16, 12, 'Autralia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero`
--

DROP TABLE IF EXISTS `pasajero`;
CREATE TABLE IF NOT EXISTS `pasajero` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pasajero`
--

INSERT INTO `pasajero` (`id`, `apellido`, `dni`, `nombre`) VALUES
(25, 'francia', 1234567, 'roberta'),
(24, 'Montiron', 38848564, 'Francisco'),
(28, 'preuba', 12312312, 'prueba'),
(27, 'Denis', 12341231, 'Ruben'),
(26, 'Marczuk', 42456456, 'lucas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE IF NOT EXISTS `reserva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `fechayhora` datetime DEFAULT NULL,
  `usuario_id` int NOT NULL,
  `vuelo_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiad9w96t12u3ms2ul93l97mel` (`usuario_id`),
  KEY `FK4tvli56vtc61fgd5dktdd24l` (`vuelo_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `estado`, `fechayhora`, `usuario_id`, `vuelo_id`) VALUES
(13, 'cancelada', '2023-02-23 18:06:17', 1, 8),
(14, 'cancelada', '2023-02-23 20:08:08', 1, 8),
(15, 'cancelada', '2023-02-23 21:55:51', 1, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rol_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `rol_nombre`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `dni` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `apellido`, `correo`, `direccion`, `dni`, `nombre`, `nombre_usuario`, `password`) VALUES
(1, 'user', 'user@user.com', 'user', 123123, 'user', 'user', '$2a$10$VEYElg5HLp/FUDJ0JKrppOUYTz0JfwisTBz..d3o72HTtWTszTDG.'),
(2, 'admin', 'admin@admin.com', 'admin', 99999999, 'admin', 'admin', '$2a$10$c9cbz/O4O08O6AZgiy4V9uqWqTU6d5RNzdQV3ch5mtO8zeuxuirlK'),
(5, 'Montiron', 'francisco', '11 1111 1A', 38848564, 'Francisco', 'francisco', '$2a$10$PoagXYQ04X20qpPXlPNKye5Gjxhpw4pRwt36iMaNrlE4tpgvzUMpG'),
(6, 'muruuc', 'user10', '58 415', 111111, 'Liquiñas', 'user10', '$2a$10$WF6c1yM9Ec7xW4cJF7ixgO4wi6Vwafg2sKsaxOh9O8C1QzAFp1k8O');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
CREATE TABLE IF NOT EXISTS `usuario_rol` (
  `usuario_id` int NOT NULL,
  `rol_id` int NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `FK610kvhkwcqk2pxeewur4l7bd1` (`rol_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuario_rol`
--

INSERT INTO `usuario_rol` (`usuario_id`, `rol_id`) VALUES
(1, 2),
(2, 1),
(2, 2),
(5, 2),
(6, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelo`
--

DROP TABLE IF EXISTS `vuelo`;
CREATE TABLE IF NOT EXISTS `vuelo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fechayhora_arribo` datetime DEFAULT NULL,
  `fechayhora_partida` datetime DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `aeropuero_llegada_id` int DEFAULT NULL,
  `aeropuero_partida_id` int DEFAULT NULL,
  `avion_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlltp2aw5h0vgr1ogyxyokal6` (`aeropuero_llegada_id`),
  KEY `FKikpwds9x6un7gnb0woidx7jme` (`aeropuero_partida_id`),
  KEY `FK8j5widj67y5mcf830eqkvth2p` (`avion_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `vuelo`
--

INSERT INTO `vuelo` (`id`, `fechayhora_arribo`, `fechayhora_partida`, `precio`, `aeropuero_llegada_id`, `aeropuero_partida_id`, `avion_id`) VALUES
(8, '2023-03-01 16:00:00', '2023-03-01 14:00:00', 17000, 7, 6, 5),
(9, '2023-03-01 12:00:00', '2023-03-01 16:00:00', 21600, 9, 6, 5),
(10, '2023-03-01 12:00:00', '2023-03-01 16:00:00', 21000, 20, 6, 5),
(11, '2023-03-02 12:00:00', '2023-03-02 09:30:00', 21000, 10, 6, 6),
(12, '2023-03-03 11:00:00', '2023-03-02 12:30:00', 370000, 22, 6, 4),
(13, '2023-03-03 11:00:00', '2023-03-02 11:30:00', 390000, 23, 6, 4),
(14, '2023-04-06 01:00:00', '2023-04-05 09:30:00', 327000, 24, 6, 4),
(15, '2023-04-06 01:00:00', '2023-04-05 09:30:00', 130000, 30, 6, 6),
(16, '2023-04-06 09:00:00', '2023-04-05 10:30:00', 460000, 31, 6, 6),
(17, '2023-04-15 05:00:00', '2023-04-13 22:30:00', 500010, 32, 6, 6),
(18, '2023-07-07 12:00:00', '2023-07-07 09:30:00', 190000, 33, 6, 6),
(19, '2023-07-09 01:00:00', '2023-07-07 09:30:00', 850000, 34, 6, 6),
(20, '2023-07-16 07:00:00', '2023-07-15 09:30:00', 536000, 35, 6, 6),
(21, '2023-07-21 07:00:00', '2023-07-20 09:30:00', 440000, 36, 6, 7),
(22, '2023-07-23 12:00:00', '2023-07-22 10:30:00', 700000, 37, 6, 7),
(23, '2023-07-23 12:00:00', '2023-07-22 10:30:00', 450000, 38, 6, 8),
(24, '2023-07-24 20:00:00', '2023-07-22 10:30:00', 1200000, 39, 6, 8);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
