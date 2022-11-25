package com.homework.workmotion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.homework.workmotion.generated.model.EmployeeSecurityCheckState;
import com.homework.workmotion.generated.model.EmployeeState;
import com.homework.workmotion.generated.model.EmployeeStateTransition;
import com.homework.workmotion.generated.model.EmployeeWorkPermitCheckState;

@Configuration
@EnableStateMachine
public class StatemachineConfig extends StateMachineConfigurerAdapter<String, EmployeeStateTransition> {

	private static final String ADDED = EmployeeState.ADDED.getValue();
	private static final String IN_CHECK = EmployeeState.IN_CHECK.getValue();
	private static final String SECURITY_CHECK_STARTED = EmployeeSecurityCheckState.STARTED.getValue();
	private static final String SECURITY_CHECK_FINISHED = EmployeeSecurityCheckState.FINISHED.getValue();
	private static final String WORK_PERMIT_CHECK_STARTED = EmployeeWorkPermitCheckState.STARTED.getValue();
	private static final String WORK_PERMIT_CHECK_PENDING_VERIFICATION = EmployeeWorkPermitCheckState.PENDING_VERIFICATION.getValue();
	private static final String WORK_PERMIT_CHECK_FINISHED = EmployeeWorkPermitCheckState.FINISHED.getValue();
	private static final String APPROVED = EmployeeState.APPROVED.getValue();
	private static final String ACTIVE = EmployeeState.ACTIVE.getValue();

	@Override
	public void configure(StateMachineStateConfigurer<String, EmployeeStateTransition> states) throws Exception {

		states.withStates() //
				.initial(ADDED) //
				.fork(IN_CHECK) //
				.join(APPROVED) //
				.end(ACTIVE) //
				.and() //
				.withStates() //
				/**/.parent(IN_CHECK) //
				/**/.initial(SECURITY_CHECK_STARTED) //
				/**/.end(SECURITY_CHECK_FINISHED) //
				.and() //
				.withStates() //
				/**/.parent(IN_CHECK) //
				/**/.initial(WORK_PERMIT_CHECK_STARTED) //
				/**/.state(WORK_PERMIT_CHECK_PENDING_VERIFICATION) //
				/**/.end(WORK_PERMIT_CHECK_FINISHED);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<String, EmployeeStateTransition> transitions) throws Exception {

		transitions.withExternal() //
				/**/.source(ADDED).target(IN_CHECK) //
				/**/.event(EmployeeStateTransition.BEGIN_CHECK) //
				.and() //
				.withExternal() //
				/**/.source(APPROVED).target(ACTIVE) //
				/**/.event(EmployeeStateTransition.ACTIVATE) //
				.and() //
				.withFork() //
				/**/.source(IN_CHECK).target(SECURITY_CHECK_STARTED).target(WORK_PERMIT_CHECK_STARTED) //
				.and() //
				.withExternal() //
				/**/.source(SECURITY_CHECK_STARTED).target(SECURITY_CHECK_FINISHED) //
				/**/.event(EmployeeStateTransition.FINISH_SECURITY_CHECK) //
				.and() //
				.withExternal() //
				/**/.source(WORK_PERMIT_CHECK_STARTED).target(WORK_PERMIT_CHECK_PENDING_VERIFICATION) //
				/**/.event(EmployeeStateTransition.COMPLETE_INITIAL_WORK_PERMIT_CHECK) //
				.and() //
				/**/.withExternal() //
				.source(WORK_PERMIT_CHECK_PENDING_VERIFICATION).target(WORK_PERMIT_CHECK_FINISHED) //
				/**/.event(EmployeeStateTransition.FINISH_WORK_PERMIT_CHECK) //
				.and() //
				/**/.withJoin() //
				/**/.source(SECURITY_CHECK_FINISHED).source(WORK_PERMIT_CHECK_FINISHED).target(APPROVED);
	}

}
