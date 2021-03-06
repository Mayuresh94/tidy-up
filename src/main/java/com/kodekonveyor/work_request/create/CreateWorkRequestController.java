
package com.kodekonveyor.work_request.create;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.webapp.ValidationException;
import com.kodekonveyor.work_request.AddressEntity;
import com.kodekonveyor.work_request.WorkRequestConstants;
import com.kodekonveyor.work_request.WorkRequestEntity;
import com.kodekonveyor.work_request.WorkRequestRepository;

@Controller
public class CreateWorkRequestController {
	@Autowired
	public WorkRequestRepository workRequestRepository;
	@Autowired
	public UserEntityRepository userEntityRepository;
	@Autowired
	public AuthenticatedUserService authenticatedUserService;

	@PostMapping("/work-request")
	public void call(@RequestBody final CreateWorkRequestDTO createWorkRequestDTO) {
		checkInputs(createWorkRequestDTO);
//		createWorkRequest(createWorkRequestDTO);
		final WorkRequestEntity workRequestEntity = new WorkRequestEntity();
		workRequestEntity.setWorkType(createWorkRequestDTO.getWorkType());
		final UserEntity userEntity = authenticatedUserService.call();
		final AddressEntity addressEntity = new AddressEntity();
		addressEntity.setId(Long.toString(createWorkRequestDTO.getCustomerId()));
		addressEntity.setAddress(createWorkRequestDTO.getAddress().getAddress());
		addressEntity.setCity(createWorkRequestDTO.getAddress().getCity());
		addressEntity.setCountry(createWorkRequestDTO.getAddress().getCountry());
		workRequestEntity.setCustomer(userEntity);
		workRequestEntity.setId(createWorkRequestDTO.getCustomerId());
		workRequestEntity.setDescription(createWorkRequestDTO.getDescription());
		workRequestEntity.setAddress(addressEntity);

		workRequestRepository.save(workRequestEntity);

	}

	public WorkRequestEntity createWorkRequest(final CreateWorkRequestDTO createWorkRequestDTO) {

		return null;
	}

	public void checkInputs(final CreateWorkRequestDTO createWorkRequestDTO) {
		if (null == createWorkRequestDTO.getWorkType())
			throw new ValidationException(WorkRequestConstants.NULL_WORKTYPE);

		if (null == createWorkRequestDTO.getDescription())
			throw new ValidationException(WorkRequestConstants.NULL_DESCRIPTION);

		if (null == createWorkRequestDTO.getAddress())
			throw new ValidationException(WorkRequestConstants.NULL_ADDRESS);

		if (null == createWorkRequestDTO.getCustomerId())
			throw new ValidationException(WorkRequestConstants.NULL_CUSTOMERID);

		if (createWorkRequestDTO.getCustomerId() < 0)
			throw new ValidationException(WorkRequestConstants.NEGATIVE_CUSTOMERID);

		if (null == createWorkRequestDTO.getAddress().getAddress())
			throw new ValidationException(WorkRequestConstants.NULL_ADDRESS_STRING);

		if (null == createWorkRequestDTO.getAddress().getCity())
			throw new ValidationException(WorkRequestConstants.NULL_CITY);

		if (null == createWorkRequestDTO.getAddress().getCountry())
			throw new ValidationException(WorkRequestConstants.NULL_COUNTRY);

		if (createWorkRequestDTO.getAddress().getCountry().length() != 2)
			throw new ValidationException(WorkRequestConstants.COUNTRY_LENGTH);

		if (!createWorkRequestDTO.getAddress().getCountry().matches("^[a-z]*$"))
			throw new ValidationException(WorkRequestConstants.COUNTRY_ALPHABET);

		if (createWorkRequestDTO.getAddress().getAddress().length() > 120)
			throw new ValidationException(WorkRequestConstants.ADDRESS_LENGTH);

		if (createWorkRequestDTO.getWorkType().matches("^[a-zA-Z]*$") == false)
			throw new ValidationException(WorkRequestConstants.DIGIT_SPECIAL_CHARACTER_WORKTYPE);

		final List<String> workType = new ArrayList<String>();
		workType.add("PLUMBING");
		workType.add("ELECTRICAL REPAIRMENT");
		workType.add("CLEANING");
		workType.add("OTHER");

		if (!workType.contains(createWorkRequestDTO.getWorkType()))
			throw new ValidationException(WorkRequestConstants.INVALID_WORKTYPE);

	}

}
