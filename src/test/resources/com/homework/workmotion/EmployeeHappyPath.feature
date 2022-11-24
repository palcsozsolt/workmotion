Feature: Employee 🙌🏻 Happy path scenarios

  Scenario Outline: 🏃 Scenario 1-2-3
    Given there is no employee in the database
    When create an employee
    Then exactly one employee should exist with "ADDED" state
    And update state of the employee to <inCheck>
    And update substate of <inCheck> state the employee to <modifiedStateA>
    And update substate of <inCheck> state the employee to <modifiedStateB>
    And update substate of <inCheck> state the employee to <modifiedStateC>
    Then employee is auto-transitioned to <approved> state
    And update state of the employee to <active>

    Examples: 
      | inCheck    | approved   | active   | modifiedStateA            | modifiedStateB                           | modifiedStateC               |
      | "IN-CHECK" | "APPROVED" | "ACTIVE" | "SECURITY_CHECK_FINISHED" | "WORK_PERMIT_CHECK_PENDING_VERIFICATION" | "WORK_PERMIT_CHECK_FINISHED" |
      | "IN-CHECK" | "APPROVED" | "ACTIVE" | "WORK_PERMIT_CHECK_PENDING_VERIFICATION" | "WORK_PERMIT_CHECK_FINISHED" | "SECURITY_CHECK_FINISHED" |
      | "IN-CHECK" | "APPROVED" | "ACTIVE" | "WORK_PERMIT_CHECK_PENDING_VERIFICATION" | "SECURITY_CHECK_FINISHED" | "WORK_PERMIT_CHECK_FINISHED" |
