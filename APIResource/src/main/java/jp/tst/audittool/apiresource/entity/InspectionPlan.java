package jp.tst.audittool.apiresource.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
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
@Table(name = "InspectionPlan")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class InspectionPlan implements Serializable {

	private static final long serialVersionUID = 269883155809926019L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "inspection_plan_id", unique = true, nullable = false)
	private Integer inspectionPlanId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issue_id")
	private IssueInfo issueInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maintenance_plan_id", nullable = false)
	private MaintenancePlan maintenancePlan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@Column(name = "work_number")
	private Integer workNumber;

	@Column(name = "type", nullable = false)
	private boolean type;

	@Column(name = "repair_item")
	private Integer repairItem;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;

}
