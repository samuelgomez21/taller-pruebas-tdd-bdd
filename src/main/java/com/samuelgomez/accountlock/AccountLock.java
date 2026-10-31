package com.samuelgomez.accountlock;

/**
 * Punto de partida para el comportamiento de bloqueo de cuenta.
 */
public class AccountLock {

    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final long LOCK_DURATION_MILLIS = 15 * 60 * 1_000L;

    private int consecutiveFailedAttempts;
    private long lockStartedAtMillis;

    public void registerFailedAttempt(long currentTimeMillis) {
        consecutiveFailedAttempts++;

        if (consecutiveFailedAttempts == MAX_FAILED_ATTEMPTS) {
            lockStartedAtMillis = currentTimeMillis;
        }
    }

    public boolean isAccountLocked(long currentTimeMillis) {
        return consecutiveFailedAttempts >= MAX_FAILED_ATTEMPTS
                && currentTimeMillis - lockStartedAtMillis < LOCK_DURATION_MILLIS;
    }
}
