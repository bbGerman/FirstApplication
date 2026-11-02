package com.example.firstapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test


class WaterTrackerTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // 1. Проверка наличия и корректной работы кнопки добавления жидкости
    @Test
    fun testAddWaterButton_existsAndUpdatesWaterCount() {
        composeTestRule.setContent { WaterTracker() }

        val addButton = composeTestRule.onNodeWithText("+250 мл")
        addButton.assertIsDisplayed()
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()

        addButton.performClick()

        composeTestRule.onNodeWithText("250 мл").assertIsDisplayed()
    }

    // 2. Проверка наличия и корректной работы кнопки “Завершить день”
    @Test
    fun testEndDayButton_existsAndResetsWaterCount() {
        composeTestRule.setContent { WaterTracker() }

        val endDayButton = composeTestRule.onNodeWithText("Завершить день")
        val addButton = composeTestRule.onNodeWithText("+250 мл")

        endDayButton.assertIsDisplayed()

        addButton.performClick()
        addButton.performClick()
        composeTestRule.onNodeWithText("500 мл").assertIsDisplayed()

        endDayButton.performClick()

        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
    }

    // 3. Проверка текста со значением серии успешных дней (включая склонения)
    @Test
    fun testStreakText_displaysCorrectValueAndPlurals() {
        composeTestRule.setContent { WaterTracker() }

        val addButton = composeTestRule.onNodeWithText("+250 мл")
        val endDayButton = composeTestRule.onNodeWithText("Завершить день")

        composeTestRule.onNodeWithText("0 дней подряд, когда выпито >1500 мл").assertIsDisplayed()

        repeat(6) { addButton.performClick() }
        endDayButton.performClick()

        composeTestRule.onNodeWithText("1 день подряд, когда выпито >1500 мл").assertIsDisplayed()

        repeat(6) { addButton.performClick() }
        endDayButton.performClick()

        composeTestRule.onNodeWithText("2 дня подряд, когда выпито >1500 мл").assertIsDisplayed()

        repeat(2) { addButton.performClick() }
        endDayButton.performClick()

        composeTestRule.onNodeWithText("0 дней подряд, когда выпито >1500 мл").assertIsDisplayed()
    }
}