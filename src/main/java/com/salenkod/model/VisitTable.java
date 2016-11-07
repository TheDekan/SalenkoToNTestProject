package com.salenkod.model;

/*import java.sql.Date;
import java.util.Calendar;
import java.util.Date;*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visits")
public class VisitTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "visit_time")
	private String visitTime;

	@Column(name = "place_id")
	private Long placeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="place_id", referencedColumnName = "id", insertable = false, updatable = false)
	private PlaceTable places;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}
		
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		VisitTable tb = (VisitTable) o;

		return id.equals(tb.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
