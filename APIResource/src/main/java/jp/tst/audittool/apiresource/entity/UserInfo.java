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
@Table(name = "UserInfo")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 5744328985533234360L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer userId;
	
	@Column(name = "created_by", nullable = false)
	private Integer createdBy;
	
	@Column(name = "updated_by", nullable = false)
	private Integer updatedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private UserRole userRole;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "firstname_kanji", nullable = false)
	private String firstnameKanji;
	
	@Column(name = "lastname_kanji", nullable = false)
	private String lastnameKanji;
	
	@Column(name = "firstname_katakana", nullable = false)
	private String firstnameKatakana;
	
	@Column(name = "lastname_katakana", nullable = false)
	private String lastnameKatakana;
	
	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at", nullable = false, length = 23)
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false, length = 23)
	private Date updatedAt;
}
