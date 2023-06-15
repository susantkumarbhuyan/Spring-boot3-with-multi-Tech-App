package com.hsignz.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hsignz.common.classes.NewPocDetails;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;

@Component
public class PocWorkers {
	private static final Logger logger = LoggerFactory.getLogger(PocWorkers.class);
	@Autowired
	private IAdminDao adminDao;

	@JobWorker(type = "setPocId")
	public NewPocDetails setPocId(@VariablesAsType NewPocDetails pocDetails) {
		logger.debug("Inside setPocId Worker >>>>>>>>>> ");

		logger.debug("End setPocId Worker >>>>>>>>>> ");
		return pocDetails;
	}
	@JobWorker(type = "setAddressId")
	public NewPocDetails setAddressId(@VariablesAsType NewPocDetails pocDetails) {
		logger.debug("Inside setAddressId Worker >>>>>>>>>> ");

		logger.debug("End setAddressId Worker >>>>>>>>>> ");

		return pocDetails;
	}

	@Transactional
	@JobWorker(type = "insertPocDetails")
	public NewPocDetails insertPocDetails(@VariablesAsType NewPocDetails pocDetails) {
		logger.debug("Inside insertPocDetails Worker >>>>>>>>>> " + pocDetails.toString());
		adminDao.insterAndUpdatePocInDb(pocDetails);
		logger.debug("End insertPocDetails Worker >>>>>>>>>> ");

		return pocDetails;
	}

	@Transactional
	@JobWorker(type = "fetchFromDb")
	public NewPocDetails fetchFromDb(@VariablesAsType NewPocDetails pocDetails) {
		logger.debug("Inside fetchFromDb Worker >>>>>>>>>> ");
		NewPocDetails poc = adminDao.getPocDetailsByPocId(pocDetails.getPocId());
		logger.debug("In fetchFromDb Print >>>>>>>>>>>>>> " + pocDetails.toString());
		return poc;
	}

}
