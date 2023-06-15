package com.hsignz.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsignz.common.classes.NewPocDetails;

@Component
public class PocMethods {
	private static final Logger logger = LoggerFactory.getLogger(PocMethods.class);
	@Autowired
	private IAdminDao adminDao;

	public NewPocDetails setPocId(NewPocDetails pocDetails) {
		logger.debug("Inside setPocId Worker >>>>>>>>>> ");
		logger.debug("End setPocId Worker >>>>>>>>>> ");
		try {
			Thread.sleep(50);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		return setAddressId(pocDetails);
	}

	public NewPocDetails setAddressId(NewPocDetails pocDetails) {
		logger.debug("Inside setAddressId Worker >>>>>>>>>> ");
		try {
			Thread.sleep(50);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		logger.debug("End setAddressId Worker >>>>>>>>>> ");

		return insertPocDetails(pocDetails);
	}

	public NewPocDetails insertPocDetails(NewPocDetails pocDetails) {
		logger.debug("Inside insertPocDetails Worker >>>>>>>>>> " + pocDetails.toString());
		adminDao.insterAndUpdatePocInDb(pocDetails);
		logger.debug("End insertPocDetails Worker >>>>>>>>>> ");

		return fetchFromDb(pocDetails);
	}

	public NewPocDetails fetchFromDb(NewPocDetails pocDetails) {
		logger.debug("Inside fetchFromDb Worker >>>>>>>>>> ");
		NewPocDetails poc = adminDao.getPocDetailsByPocId(pocDetails.getPocId());
		logger.debug("In fetchFromDb Print >>>>>>>>>>>>>> " + pocDetails.toString());
		return poc;
	}

}
