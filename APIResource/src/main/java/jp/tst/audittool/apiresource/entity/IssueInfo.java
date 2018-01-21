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
@Table(name = "IssueInfo")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class IssueInfo implements Serializable {

	private static final long serialVersionUID = 7295785506972614221L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "issue_id", unique = true, nullable = false)
	private Integer issueId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "urgency", nullable = false)
	private Common common;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private UserInfo userInfoByCreatedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	private UserInfo userInfoByUpdatedBy;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "contents", nullable = false)
	private String contents;

	@Temporal(TemporalType.DATE)
	@Column(name = "expected_date", nullable = false, length = 23)
	private Date expectedDate;

	@Column(name = "work_number", nullable = false)
	private int workNumber;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
