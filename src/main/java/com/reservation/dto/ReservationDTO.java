package com.reservation.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.reservation.util.JsonDateDeserializer;
import com.reservation.util.JsonDateSerializer;

public class ReservationDTO {
	private String reservationId;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date reservationDate;
	private Integer noOfGuest;
	private String reserveBy;
	private String reserveAt;
	public ReservationDTO() {
		super();
	}
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Integer getNoOfGuest() {
		return noOfGuest;
	}
	public void setNoOfGuest(Integer noOfGuest) {
		this.noOfGuest = noOfGuest;
	}
	public String getReserveBy() {
		return reserveBy;
	}
	public void setReserveBy(String reserveBy) {
		this.reserveBy = reserveBy;
	}
	public String getReserveAt() {
		return reserveAt;
	}
	public void setReserveAt(String reserveAt) {
		this.reserveAt = reserveAt;
	}
	

}
