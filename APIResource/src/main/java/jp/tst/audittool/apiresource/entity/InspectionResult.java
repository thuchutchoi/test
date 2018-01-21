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
@Table(name = "InspectionResult")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class InspectionResult implements Serializable {

	private static final long serialVersionUID = 3221797129629582923L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "inspection_result_id", unique = true, nullable = false)
	private Integer inspectionResultId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inspection_detail_id", nullable = false)
	private InspectionPlanDetail inspectionPlanDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "worker_id", nullable = false)
	private UserInfo userInfoByWorkerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@Column(name = "status", nullable = false)
	private boolean status;

	@Column(name = "work_result", nullable = false)
	private String workResult;

	@Temporal(TemporalType.DATE)
	@Column(name = "working_date", nullable = false, length = 23)
	private Date workingDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "working_starttime", nullable = false, length = 23)
	private Date workingStarttime;

	@Temporal(TemporalType.DATE)
	@Column(name = "working_endtime", nullable = false, length = 23)
	private Date workingEndtime;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
