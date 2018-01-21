package jp.tst.audittool.apiresource.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
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
@Table(name = "PartInfo")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PartInfo implements Serializable {

	private static final long serialVersionUID = 5355861013423500406L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "part_id", unique = true, nullable = false)
	private Integer partId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipment_id", nullable = false)
	private EquipmentInfo equipmentInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "model", nullable = false)
	private String model;

	@Column(name = "serial_number", nullable = false)
	private String serialNumber;

	@Column(name = "noted", nullable = false)
	private String noted;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
