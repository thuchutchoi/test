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
@Table(name = "Attachment")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Attachment implements Serializable {

	private static final long serialVersionUID = 767212674902067092L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "attachment_id", unique = true, nullable = false)
	private Integer attachmentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inspection_result_id")
	private InspectionResult inspectionResult;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inspection_result_data_id")
	private InspectionResultData inspectionResultData;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@Column(name = "type", nullable = false)
	private short type;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
