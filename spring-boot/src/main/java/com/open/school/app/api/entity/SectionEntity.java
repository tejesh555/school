package com.open.school.app.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "section", indexes = { @Index(name = "SECONDARY", columnList = "classId,schoolId") })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SectionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String sectionName;
	@Column(nullable = false)
	private boolean status;
	@Column(nullable = false)
	private String modifiedBy;
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
	private Date lastModified;
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classId", nullable = false, foreignKey = @ForeignKey(name = "fk_class_id_section"))
	@JsonBackReference(value = "classes")
	public ClassEntity classes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schoolId", nullable = false, foreignKey = @ForeignKey(name = "fk_school_id_section"))
	@JsonBackReference(value = "schoolid")
	public SchoolEntity school;

	@Transient
	@OneToMany(targetEntity = StudentMapEntity.class, mappedBy = "section", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value = "sectionid")
	public List<StudentMapEntity> studentsMap;

}
