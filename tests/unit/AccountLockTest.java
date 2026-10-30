package com.samuelgomez.accountlock;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AccountLockTest {

    private static final long INITIAL_TIME_MILLIS = 0L;
    private static final long FIFTEEN_MINUTES_MILLIS = 15 * 60 * 1_000L;

    @Test
    void shouldBlockAccountAfterFifthConsecutiveFailedAttemptForFifteenMinutes() {
        AccountLock accountLock = new AccountLock();

        for (int attempt = 0; attempt < 4; attempt++) {
            accountLock.registerFailedAttempt(INITIAL_TIME_MILLIS);
        }

        boolean lockedAfterFourAttempts = accountLock.isAccountLocked(INITIAL_TIME_MILLIS);

        accountLock.registerFailedAttempt(INITIAL_TIME_MILLIS);

        assertAll(
                () -> assertFalse(lockedAfterFourAttempts),
                () -> assertTrue(accountLock.isAccountLocked(INITIAL_TIME_MILLIS)),
                () -> assertTrue(accountLock.isAccountLocked(FIFTEEN_MINUTES_MILLIS - 1)),
                () -> assertFalse(accountLock.isAccountLocked(FIFTEEN_MINUTES_MILLIS))
        );
    }
}
