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
@Table(name = "ComponentReplacement")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ComponentReplacement implements Serializable {

	private static final long serialVersionUID = 6096805901360763269L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "component_replacement_id", unique = true, nullable = false)
	private Integer componentReplacementId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inspection_result_id", nullable = false)
	private InspectionResult inspectionResult;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@Column(name = "part_id_after", nullable = false)
	private int partIdAfter;

	@Column(name = "part_id_before", nullable = false)
	private int partIdBefore;

	@Column(name = "model_after", nullable = false)
	private String modelAfter;

	@Column(name = "model_before", nullable = false)
	private String modelBefore;

	@Column(name = "serial_no_after", nullable = false)
	private String serialNoAfter;

	@Column(name = "serial_no_before", nullable = false)
	private String serialNoBefore;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
