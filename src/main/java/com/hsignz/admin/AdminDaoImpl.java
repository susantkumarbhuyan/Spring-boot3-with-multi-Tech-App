package com.hsignz.admin;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.GsonBuilder;
import com.hsignz.common.classes.NewPocDetails;
import com.hsignz.utils.LocalDateTimeTypeAdapter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository("adminDao")
public class AdminDaoImpl implements IAdminDao {
	private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	private Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}

	@Override
	public void insterAndUpdatePocInDb(NewPocDetails pocDetails) {
		logger.debug("Creating or updating POC>> Details>> {} ", new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create().toJson(pocDetails));
		entityManager.persist(pocDetails);
	}

	@Override
	public List<NewPocDetails> getPocDetailsList() {
		return entityManager.createQuery("from PocDetails", NewPocDetails.class).getResultList();
	}

	@Override
	public NewPocDetails getPocDetailsByPocId(long pocId) {
		return entityManager.find(NewPocDetails.class, pocId);
	}
}
