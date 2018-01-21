package jp.tst.audittool.apiresource.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "InspectionPlanDetail")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class InspectionPlanDetail implements java.io.Serializable {

	private static final long serialVersionUID = 495655988766283970L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "inspection_plan_detail_id", unique = true, nullable = false)
	private Integer inspectionPlanDetailId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "urgency", nullable = false)
	private Common common;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inspection_plan_id", nullable = false)
	private InspectionPlan inspectionPlan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_id", nullable = false)
	private UserInfo userInfoByAssigneeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@Column(name = "work_content", nullable = false)
	private String workContent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "scheduled_date", nullable = false, length = 23)
	private Date scheduledDate;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
