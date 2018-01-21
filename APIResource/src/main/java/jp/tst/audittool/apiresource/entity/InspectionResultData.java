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
@Table(name = "InspectionResultData")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class InspectionResultData implements Serializable {

	private static final long serialVersionUID = 2007518062284261748L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "inspection_result_data_id", unique = true, nullable = false)
	private Integer inspectionResultDataId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inspection_small_id", nullable = false)
	private InspectionItemSmall inspectionItemSmall;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inspection_result_id", nullable = false)
	private InspectionResult inspectionResult;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@Column(name = "status", nullable = false)
	private boolean status;

	@Column(name = "measured_value", nullable = false)
	private int measuredValue;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
