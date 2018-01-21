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
@Table(name = "MaintenancePlan")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MaintenancePlan implements Serializable {

	private static final long serialVersionUID = 3838051455744542754L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "maintenance_plan_id", unique = true, nullable = false)
	private Integer maintenancePlanId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipment_id", nullable = false)
	private EquipmentInfo equipmentInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@Column(name = "inspection_year", nullable = false)
	private int inspectionYear;

	@Column(name = "inspection_month", nullable = false)
	private int inspectionMonth;

	@Column(name = "inspection_done", nullable = false)
	private int inspectionDone;

	@Column(name = "inspection_total", nullable = false)
	private int inspectionTotal;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
