package com.samuelgomez.accountlock;

/**
 * Punto de partida para el comportamiento de bloqueo de cuenta.
 */
public class AccountLock {

    public void registerFailedAttempt(long currentTimeMillis) {
        // La lógica se implementará en la etapa Verde.
    }

    public boolean isAccountLocked(long currentTimeMillis) {
        return false;
    }
}
