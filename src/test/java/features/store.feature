@SmokeFeature
Feature: Store

  Background: open store app
    Given open store app

  @smoke
  Scenario: GeneralStoreEndToEndTest
    Given End to End app test

  @smoke
  Scenario: check toast message
    Given check toast message

  @smoke
  Scenario: scroll and find product and choose
    Given find product and choose
    And click on "Visit to the website to complete purchase" button
    And switch driver to chrome
    And type "Aziz Dzhamolov linkedin" in search field
    And switch driver to app