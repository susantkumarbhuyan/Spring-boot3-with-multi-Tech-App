package com.hsignz.admin;

import java.util.List;

import com.hsignz.common.classes.NewPocDetails;

public interface IAdminDao {

	public void insterAndUpdatePocInDb(NewPocDetails pocDetails);

	public List<NewPocDetails> getPocDetailsList();

	public NewPocDetails getPocDetailsByPocId(long pocId);

}
