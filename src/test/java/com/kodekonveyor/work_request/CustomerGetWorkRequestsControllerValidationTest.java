package com.kodekonveyor.work_request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("input validation")
@TestedService("CustomerGetWorkRequestsController")

public class CustomerGetWorkRequestsControllerValidationTest extends CustomerWorkRequestControllerTestBase {

	@Test
	@DisplayName("When owner ID is null, the message is 'This field cannot be blank'")

	public void testCustomerGetWorkRequestsControllerNullOwnerId() {
		ThrowableTester.assertThrows(() -> customerGetWorkRequestsController.call(null))
				.assertMessageIs(workRequestTestData.NULL_OWNERID);
	}

	@Test
	@DisplayName("Owner Id cannot be negative")
	public void testCustomerGetWorkRequestsControllerCharacterCheck1() {
		ThrowableTester
				.assertThrows(() -> customerGetWorkRequestsController.call(workRequestTestData.NEGATIVE_OWNERID_ID))
				.assertMessageIs(workRequestTestData.NEGATIVE_OWNERID);
	}

	@Test
	@DisplayName("Owner Id cannot be negative")
	public void testCustomerGetWorkRequestsControllerCharacterCheck2() {
		ThrowableTester
				.assertThrows(() -> customerGetWorkRequestsController.call(workRequestTestData.ALPHACHAR_OWNERID_ID))
				.assertMessageIs(workRequestTestData.ALPHACHAR_OWNERID);
	}

	@Test
	@DisplayName("Owner Id cannot be negative")
	public void testCustomerGetWorkRequestsControllerCharacterCheck3() {
		ThrowableTester
				.assertThrows(() -> customerGetWorkRequestsController.call(workRequestTestData.LENGTHEXCEED_OWNERID_ID))
				.assertMessageIs(workRequestTestData.LENGTHEXCEED_OWNERID);
	}

	@Test
	@DisplayName("When owner Id is incorrect, the message is 'Invalid Owner Id'")
	public void testCustomerGetWorkRequestsControllerInvalidOwnerId() {
		ThrowableTester
				.assertThrows(() -> customerGetWorkRequestsController.call(workRequestTestData.INVALID_OWNERID_ID))
				.assertMessageIs(workRequestTestData.INVALID_OWNERID);

	}

}
