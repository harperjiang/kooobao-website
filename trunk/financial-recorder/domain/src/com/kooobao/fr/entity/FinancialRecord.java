package com.kooobao.fr.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name="fr_record")
public class FinancialRecord extends VersionEntity {

	@Column(name="type", columnDefinition="varchar(15)")
	String type;
	
	@Column(name="category",columnDefinition="varchar(40)")
	String category;
	
	@Column(name="record_date",columnDefinition="datetime")
	@Temporal(TemporalType.TIMESTAMP)
	Date recordDate;
	
	@Column(name="create_date", columnDefinition="datetime")
	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;
	
	@Column(name="create_by", columnDefinition="varchar(30)")
	String createBy;
	
	@Column(name="amount", columnDefinition="decimal(10,2)")
	BigDecimal amount;
	
	
}
