Feature: Employee 😟 Unhappy path scenarios

  Scenario Outline: 💣 Scenario 1-2
    Given there is no employee in the database
    When create an employee
    And update state of the employee to <inCheck>
    And update substate of <inCheck> state the employee to <securityCheckFinished>
    And update state of the employee to <wrongState> from <currentState> is not allowed

    Examples: 
      | inCheck    | securityCheckFinished     | currentState                | wrongState                   |
      | "IN-CHECK" | "SECURITY_CHECK_FINISHED" | "IN-CHECK"                  | "ACTIVE"                     |
      | "IN-CHECK" | "SECURITY_CHECK_FINISHED" | "WORK_PERMIT_CHECK_STARTED" | "WORK_PERMIT_CHECK_FINISHED" |
