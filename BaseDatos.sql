-- Crear la tabla Cliente
CREATE TABLE public.cliente (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(10) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(20) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT null,
    contrasenia VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL
);


-- Script para la base de datos de Cuentas y Movimientos
CREATE TABLE public.cuenta (
    id BIGSERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(20),
    saldo_inicial DOUBLE PRECISION,
    cliente_id BIGINT,
    estado BOOLEAN,
    FOREIGN KEY (cliente_id) REFERENCES public.cliente(id)
);

CREATE TABLE public.movimientos (
    id BIGSERIAL PRIMARY KEY,
    fecha TIMESTAMP,
    tipo_movimiento VARCHAR(20),
    valor DOUBLE PRECISION,
    saldo DOUBLE PRECISION,
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES public.cuenta(id)
);
