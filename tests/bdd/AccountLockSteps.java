package com.samuelgomez.accountlock;

import static org.junit.jupiter.api.Assertions.assertFalse;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

public class AccountLockSteps {

    private static final long INITIAL_TIME_MILLIS = 0L;
    private static final long ONE_MINUTE_MILLIS = 60_000L;

    private AccountLock accountLock;
    private boolean accessAllowed;

    @Dado("una cuenta bloqueada por cinco intentos fallidos")
    public void unaCuentaBloqueadaPorCincoIntentosFallidos() {
        accountLock = new AccountLock();

        for (int attempt = 0; attempt < 5; attempt++) {
            accountLock.registerFailedAttempt(INITIAL_TIME_MILLIS);
        }
    }

    @Cuando("intento ingresar con credenciales correctas un minuto después")
    public void intentoIngresarConCredencialesCorrectasUnMinutoDespues() {
        accessAllowed = accountLock.canLogin(true, ONE_MINUTE_MILLIS);
    }

    @Entonces("el sistema rechaza el acceso")
    public void elSistemaRechazaElAcceso() {
        assertFalse(accessAllowed);
    }
}
