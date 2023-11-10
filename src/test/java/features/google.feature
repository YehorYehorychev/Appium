@SmokeFeature
Feature: Device

  Background: open settings
    Given open settings

  @google
  Scenario: Check wi-fi is on
    Given click on "Network & internet"
    And click on "Internet"
    When Turn on switch
    Then Check switch is on



  @google
  Scenario: Check bluetooth is on
    Given click on "Connected devices"
    And click on "Connection preferences"
    And click on "Bluetooth"
    When Turn on switch
    Then Check switch is on