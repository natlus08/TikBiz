package com.tikbiz.model;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="SHIFT_ROASTER")
public class ShiftRoaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="DATE", nullable=false, unique=true)
	@Temporal(TemporalType.DATE)
    private Date date;
	
	@ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<ShiftType, String> shifts = new HashMap<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Map<ShiftType, String> getShifts() {
		return shifts;
	}

	public void setShifts(Map<ShiftType, String> shifts) {
		this.shifts = shifts;
	}
	public enum ShiftType {
	    MORNING,
	    NOON,
	    NIGHT,
	    OFF
	}
}
