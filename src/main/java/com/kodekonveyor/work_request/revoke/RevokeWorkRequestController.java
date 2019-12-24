package com.kodekonveyor.work_request.revoke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodekonveyor.webapp.ValidationException;
import com.kodekonveyor.work_request.WorkRequestConstants;

@Controller
public class RevokeWorkRequestController {

  @Autowired

  @GetMapping("/workRequest/@workRequestId")
  public void call(@RequestParam final long workRequestId) {
    inputValidation(workRequestId);

  }

  public void inputValidation(final long workRequestId) {

    final int workId = 0;
    if (workRequestId <= workId)
      throw new ValidationException(
          WorkRequestConstants.NON_POSITIVE_WORK_REQUEST_ID_EXCEPTION
      );

    //    final List<WorkRequestEntity> workRequestEntity =
    //        workRequestRepository.findByWorkRequestId(workRequestId);
    //    if (workRequestEntity.isEmpty())
    //      throw new ValidationException(
    //          WorkRequestConstants.INVALID_WORK_REQUEST_ID_EXCEPTION
    //      );
    //  }
  }
}