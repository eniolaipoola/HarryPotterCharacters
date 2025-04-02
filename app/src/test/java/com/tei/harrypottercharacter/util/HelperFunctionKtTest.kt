package com.tei.harrypottercharacter.util

import org.junit.Assert.*
import org.junit.Test

class HelperFunctionKtTest {

    @Test
    fun `getCharacterStatus should return ALIVE when alive is true`() {
        val result = getCharacterStatus(true)
        assertEquals(ALIVE, result)
    }

    @Test
    fun `getCharacterStatus should return DEAD when alive is false`() {
        val result = getCharacterStatus(false)
        assertEquals(DEAD, result)
    }

    @Test
    fun `getCharacterStatus should return DEAD when alive is null`() {
        val result = getCharacterStatus(null)
        assertEquals("", result)
    }

    @Test
    fun `formatDateOfBirth should format a date  string appropriately`() {
        val result = formatDateOfBirth("31-07-1980")
        assertEquals("31 Jul 1980", result)
    }

    @Test
    fun `formatDateOfBirth should return empty string for invalid date`() {
        val result = formatDateOfBirth("invalid-date")
        assertEquals("", result)
    }

    @Test
    fun `formatDateOfBirth should return empty string for empty string`() {
        val result = formatDateOfBirth("")
        assertEquals("", result)
    }
}