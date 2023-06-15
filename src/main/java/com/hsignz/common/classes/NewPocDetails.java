package com.hsignz.common.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity @Table( schema = "public")
public class NewPocDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poc_details_generator")
	@SequenceGenerator(allocationSize = 1, sequenceName = "tbl_poc_details_seq", name = "poc_details_generator", schema = "public")
	private long pocId;
	private String pocName;
	private String email;
	@Column(name = "from_date", nullable = true)
	private long fromDate;
	@Column(name = "to_date", nullable = true)
	private long toDate;
	@Column(name = "poc_type", nullable = true)
	private long pocType;
	@Column(name = "scan_presc", nullable = true)
	private boolean scanAndUploadPrescriptions;
//	@CreationTimestamp
//	@Column(name = "date_created", nullable = true)
//	private LocalDateTime dateCreated;
//	@UpdateTimestamp
//	@Column(name = "last_updated", nullable = true)
//	private LocalDateTime lastUpdated;
	private Address address;
	
}