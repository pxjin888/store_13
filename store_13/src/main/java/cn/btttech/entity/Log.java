package cn.btttech.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="log_type")
@DiscriminatorValue("log")
@Table(name="store_log"
    ,catalog="store"
)

public class Log  implements java.io.Serializable {


    // Fields    

	private static final long serialVersionUID = 4389475473056141143L;
	private Integer logId;
     private User user;
     private Group group;
     private String logDo;
     private String logRemark;
     
     private Date logTime;
     private String logCode;


    // Constructors

    /** default constructor */
    public Log() {
    }

    
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="log_id", unique=true, nullable=false)

    public Integer getLogId() {
        return this.logId;
    }
    
    public void setLogId(Integer logId) {
        this.logId = logId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="user_id")

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="group_id")

    public Group getGroup() {
        return this.group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
    @Column(name="log_do", length=25)

    public String getLogDo() {
        return this.logDo;
    }
    
    public void setLogDo(String logDo) {
        this.logDo = logDo;
    }
    
    @Column(name="log_remark", length=256)

    public String getLogRemark() {
        return this.logRemark;
    }
    
    public void setLogRemark(String logRemark) {
        this.logRemark = logRemark;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="log_time", length=10)

    public Date getLogTime() {
        return this.logTime;
    }
    
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
    
    @Column(name="log_code", length=25)

    public String getLogCode() {
        return this.logCode;
    }
    
    public void setLogCode(String logCode) {
        this.logCode = logCode;
    }

}