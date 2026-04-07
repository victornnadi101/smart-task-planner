package com.smartplanner.web.model;

/**
 * =======================================================
 * ENUM: Priority
 * =======================================================
 * PURPOSE
 * -------
 * Represents the priority level of a task
 * *
 * WHY ENUM?
 * ---------
 * Prevents invalid values like:
 * "high", "HIGH", "urgent", etc.
 * *
 * Only allows:
 * LOW, MEDIUM, HIGH
 */

public enum Priority {
    LOW,
    MEDIUM,
    HIGH
}
