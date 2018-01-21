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
@Table(name = "SyncHistory")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SyncHistory implements Serializable {

	private static final long serialVersionUID = 344124431101860590L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sync_history_id", unique = true, nullable = false)
	private Integer syncHistoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserInfo userInfo;
	
	@Column(name = "type", nullable = false)
	private boolean type;
	
	@Column(name = "status")
	private Short status;
	
	@Column(name = "device_id", nullable = false)
	private Serializable deviceId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_update", nullable = false, length = 23)
	private Date lastUpdate;
}
