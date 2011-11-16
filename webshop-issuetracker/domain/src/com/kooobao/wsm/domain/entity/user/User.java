package com.kooobao.wsm.domain.entity.user;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.codec.binary.Base64;

import com.kooobao.wsm.domain.entity.VersionEntity;

@Entity
@Table(name = "profile_user", uniqueConstraints = @UniqueConstraint(columnNames = "id", name = "profile_user_uk_id"))
public class User extends VersionEntity {

	@Column(name = "id", columnDefinition = "varchar(10)", nullable = false)
	private String id;

	@Column(name = "desc_text", columnDefinition = "text", nullable = true)
	private String description;

	@Column(name = "enc_pass", columnDefinition = "varchar(255)", nullable = false)
	private String encryptedPass;

	@Column(name = "last_login_time", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;

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
