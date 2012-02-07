package com.kooobao.fr.domain.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "fr_actor")
@NamedQuery(name="getActorById",query="select a from Actor a where a.id = :id")
public class Actor {

	@Id
	@Column(name = "id", columnDefinition = "varchar(15)")
	private String id;

	@Version
	@Column(name = "obj_version", columnDefinition = "decimal(10)")
	private long version;

	@ElementCollection
	@CollectionTable(name = "fr_actor_role", joinColumns = { @JoinColumn(name = "actor_id", referencedColumnName = "id") })
	@Column(name = "role", columnDefinition = "varchar(25)")
	private List<String> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
