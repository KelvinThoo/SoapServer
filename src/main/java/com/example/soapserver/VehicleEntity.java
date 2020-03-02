package com.example.soapserver;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vehicletest1")
public class VehicleEntity implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String model_id;
    private String color;
    private String refNo;
       
        public VehicleEntity() {
		
	}

	public VehicleEntity(String model_id,String color) {
		this.model_id = model_id;
		this.color = color;
	}
	
	@Column(name="model_id")
	public String getModelID() {
		return model_id;
	}

	public void setModelID(String model_id) {
		this.model_id = model_id;
	}

	@Column(name = "color")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
    }

    @Column(name = "ref_no")
    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

	
	@Override
	public String toString() {
		return getModelID()+getColor();
	}
}