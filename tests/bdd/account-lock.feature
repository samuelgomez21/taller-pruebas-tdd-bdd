# language: es
Característica: Bloqueo temporal de cuenta

  Escenario: Acceso rechazado durante el bloqueo
    Dado una cuenta bloqueada por cinco intentos fallidos
    Cuando intento ingresar con credenciales correctas un minuto después
    Entonces el sistema rechaza el acceso
