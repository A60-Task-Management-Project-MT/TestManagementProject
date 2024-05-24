package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.ActivityHistoryImpl;
import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityHistoryImplTest {
    public static final String VALID_DESCRIPTION = "C".repeat(50);

    @Test
    void constructor_Should_CreateHistory_WhenArguments_AreValid() {
        ActivityHistory activityHistory = new ActivityHistoryImpl(VALID_DESCRIPTION);

        assertEquals(VALID_DESCRIPTION, activityHistory.getDescription());
    }

    @Test
    void constructor_Should_ThrowException_WhenEmpty() {
        assertThrows(IllegalArgumentException.class, ActivityHistoryImpl::new);
    }

}