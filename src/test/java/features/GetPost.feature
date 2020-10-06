Feature: Test and manage data sources in the Google Fitness App

  Scenario: Create a Data Source for Steps Count
    Given payload for creating a "StepsDataSource16" data source
    When http "POST" action is done to the "fitnessDataSources" API to create the dataSource
    Then response with "200" status is received
    And response has "dataStreamName" equal to "StepsDataSource16"

  Scenario: Delete the Data resource created for Steps Count
    Given The "dataStreamName" "StepsDataSource16" dataSource is available in the "dataSource" list then find its "dataStreamId"
    When http "DELETE" action is done to the "fitnessDataSources" API to delete the dataSource
    Then response with "200" status is received