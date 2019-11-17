package com.kodekonveyor.work_request;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.authentication.UserEntityRepositoryStubs;
import com.kodekonveyor.authentication.UserTestData;

public class CustomerWorkRequestControllerTestBase {

	@InjectMocks
	protected CustomerGetWorkRequestsController customerGetWorkRequestsController;
	@Mock
	protected WorkRequestRepository workRequestRepository;
	@Mock
	UserEntityRepository userEntityRepository;
	protected UserTestData userTestData;
	protected WorkRequestTestData workRequestTestData;

	public CustomerWorkRequestControllerTestBase() {
		super();
	}

	@BeforeEach
	private void setUp() {
		userTestData = new UserTestData();
		workRequestTestData = new WorkRequestTestData(userTestData);
	
		WorkRequestRepositoryStub.behaviour(workRequestTestData, workRequestRepository, userTestData);
		UserEntityRepositoryStubs.behaviour(userEntityRepository, userTestData);
	}

}