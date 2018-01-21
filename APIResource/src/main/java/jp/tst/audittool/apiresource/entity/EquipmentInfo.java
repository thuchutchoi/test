package jp.tst.audittool.apiresource.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "EquipmentInfo")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EquipmentInfo implements Serializable {

	private static final long serialVersionUID = 608210763933548190L;

	@Id
	@Column(name = "equipment_id", unique = true, nullable = false)
	private String equipmentId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "model", nullable = false)
	private String model;

	@Column(name = "serial_number", nullable = false)
	private String serialNumber;

	@Column(name = "manufacturer_name", nullable = false)
	private String manufacturerName;

	@Temporal(TemporalType.DATE)
	@Column(name = "replacement_date", length = 23)
	private Date replacementDate;
	
	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;
	
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;
	
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
