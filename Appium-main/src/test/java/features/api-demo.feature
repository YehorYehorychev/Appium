@SmokeFeature
Feature: API-Demo

  Background: open api-demo app
    Given open api-demo app

  @smoke
  Scenario: Open app by activity
    Given open app by activity

  @smoke
  Scenario: Drag and drop
    When drag and drop

  @smoke
  Scenario: Long press
    When long press

  @smoke
  Scenario: Swipe left
    When swipe left

  @smoke
  Scenario: Swipe back
    When swipe back

  @smoke
  Scenario: Scroll down page
    When scroll down page