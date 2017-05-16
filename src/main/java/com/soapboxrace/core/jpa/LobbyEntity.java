package com.soapboxrace.core.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LOBBY")
@NamedQueries({ //
		@NamedQuery(name = "LobbyEntity.findAll", query = "SELECT obj FROM UserEntity obj"), //
		@NamedQuery(name = "LobbyEntity.findByEventStarted", query = "SELECT obj FROM LobbyEntity obj WHERE obj.event = :event and obj.lobbyDateTimeStart between :dateTime1 and :dateTime2") //
})
public class LobbyEntity {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "EVENTID", referencedColumnName = "ID")
	private EventEntity event;

	@OneToMany(mappedBy = "lobby", targetEntity = LobbyEntrantEntity.class, cascade = CascadeType.MERGE)
	private List<LobbyEntrantEntity> entrants;

	private Date lobbyDateTimeStart = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventEntity getEvent() {
		return event;
	}

	public void setEvent(EventEntity event) {
		this.event = event;
	}

	public List<LobbyEntrantEntity> getEntrants() {
		return entrants;
	}

	public void setEntrants(List<LobbyEntrantEntity> entrants) {
		this.entrants = entrants;
	}

	public Date getLobbyDateTimeStart() {
		return lobbyDateTimeStart;
	}

	public void setLobbyDateTimeStart(Date lobbyDateTimeStart) {
		this.lobbyDateTimeStart = lobbyDateTimeStart;
	}

	public boolean add(LobbyEntrantEntity e) {
		if (entrants == null) {
			entrants = new ArrayList<>();
		}
		return entrants.add(e);
	}

}
