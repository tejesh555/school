package com.open.school.app.api.entity;

import java.util.Date;

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
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "subjectStaffMap", indexes = { @Index(name = "SECONDARY", columnList = "schoolId,staffId,subjectId") })
@DynamicUpdate
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SubjectStaffMapEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String modifiedBy;
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
	private Date lastModified;
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schoolId", nullable = false, foreignKey = @ForeignKey(name = "fk_school_id_staffMap"))
	@JsonBackReference(value = "schoolid")
	public SchoolEntity school;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classId", nullable = true, foreignKey = @ForeignKey(name = "fk_class_id_staffMap"))
	@JsonBackReference(value = "classes")
	public ClassEntity classes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sectionId", nullable = true, foreignKey = @ForeignKey(name = "fk_section_id_staffMap"))
	@JsonBackReference(value = "sectionid")
	public SectionEntity section;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subjectId", nullable = false, foreignKey = @ForeignKey(name = "fk_subject_id_staffMap"))
	@JsonBackReference(value = "subject")
	public SubjectEntity subject;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffId", nullable = false, foreignKey = @ForeignKey(name = "fk_staff_id_staffMap"))
	@JsonBackReference(value = "staff")
	public StaffEntity staff;

}
