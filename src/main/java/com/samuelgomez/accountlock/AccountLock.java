package com.samuelgomez.accountlock;

/**
 * Punto de partida para el comportamiento de bloqueo de cuenta.
 */
public class AccountLock {

    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final long LOCK_DURATION_MILLIS = 15 * 60 * 1_000L;

    private int failedAttempts;
    private long lockStartTimeMillis;

    public void registerFailedAttempt(long currentTimeMillis) {
        failedAttempts++;

        if (failedAttempts == MAX_FAILED_ATTEMPTS) {
            lockStartTimeMillis = currentTimeMillis;
        }
    }

    public boolean isAccountLocked(long currentTimeMillis) {
        return failedAttempts >= MAX_FAILED_ATTEMPTS
                && currentTimeMillis - lockStartTimeMillis < LOCK_DURATION_MILLIS;
    }
}
