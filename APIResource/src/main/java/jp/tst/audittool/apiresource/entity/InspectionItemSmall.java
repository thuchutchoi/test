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
@Table(name = "InspectionItemSmall")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class InspectionItemSmall implements Serializable {

	private static final long serialVersionUID = 9150897253240099035L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "inspection_item_small_id", unique = true, nullable = false)
	private Integer inspectionItemSmallId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inspection_large_id")
	private InspectionItemLarge inspectionItemLarge;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@Column(name = "type", nullable = false)
	private short type;

	@Column(name = "min", nullable = false)
	private int min;

	@Column(name = "max", nullable = false)
	private int max;

	@Column(name = "sort", nullable = false)
	private int sort;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "unit", nullable = false)
	private String unit;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "upper_error_message", nullable = false)
	private String upperErrorMessage;

	@Column(name = "lower_error_message", nullable = false)
	private String lowerErrorMessage;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
