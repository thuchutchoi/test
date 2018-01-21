package jp.tst.audittool.apiresource.entity;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "UserRole")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserRole implements Serializable {

	private static final long serialVersionUID = -5520130068249177979L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	private Integer roleId;
	
	@Column(name = "role_name", nullable = false)
	private String roleName;
}
