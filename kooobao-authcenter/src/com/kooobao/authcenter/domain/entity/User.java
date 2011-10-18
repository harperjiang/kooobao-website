package com.kooobao.authcenter.domain.entity;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.codec.binary.Base64;

@Entity
@Table(name = "auc_user")
public class User {

	@Id
	@Column(name = "user_id", columnDefinition = "varchar(10)", nullable = false)
	private String id;

	@Version
	@Column(name = "version", columnDefinition = "decimal(10)")
	private long version;

	@Column(name = "desc_text", columnDefinition = "text", nullable = true)
	private String description;

	@Column(name = "enc_pass", columnDefinition = "varchar(255)", nullable = false)
	private String encryptedPass;

	@Column(name = "last_login_time", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;

	@ElementCollection
	@CollectionTable(name = "auc_user_system", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
	@MapKeyColumn(name = "system_name")
	@Column(name = "system_name", columnDefinition = "varchar(25)")
	private Map<String, String> systems;

	public String getEncryptedPass() {
		return encryptedPass;
	}

	public void setEncryptedPass(String encryptedPass) {
		this.encryptedPass = encryptedPass;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getSystems() {
		return systems;
	}

	public void setSystems(Map<String, String> systems) {
		this.systems = systems;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public static String encryptPass(String input) {
		try {
			byte[] digested = MessageDigest.getInstance("md5").digest(
					input.getBytes());
			return new String(Base64.encodeBase64(digested), "iso-8859-1");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
