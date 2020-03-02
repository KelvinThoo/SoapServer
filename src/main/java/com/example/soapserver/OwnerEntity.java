package com.example.soapserver;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test2")
public class OwnerEntity implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String refNo;
	private String msg;
    private String msgCode;
    private String submissionStatus;
       
        public OwnerEntity() {
		
	}

	public OwnerEntity(String refNo,String msg,String msgCode) {
		this.refNo = refNo;
		this.msg = msg;
		this.msgCode = msgCode;
	}
	
	public OwnerEntity(String refNo,String msg, String msgCode,String submissionStatus) {
		this.refNo = refNo;
        this.msg = msg;
        this.msgCode = msgCode;
        this.submissionStatus = submissionStatus;
	}

	@Column(name="ref_no")
	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	@Column(name = "msg")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Column(name = "msg_code")
	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
    }
	
	@Column(name = "submission_status")
    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
	}
	
	@Override
	public String toString() {
		return getRefNo()+getMsg()+getMsgCode()+getSubmissionStatus();
	}
}