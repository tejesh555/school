package com.open.school.app.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "initializationTracker")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class InitializationTrackerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String identifier;
	@Column(nullable = false)
	private boolean status;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
	private Date lastModified;
	@Column(nullable = false)
	private String modifiedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schoolId", nullable = true, foreignKey = @ForeignKey(name = "fk_schoolid_initialization"))
	@JsonBackReference(value = "schoolid")
	public SchoolEntity school;

}
