package com.satiric.doit.Tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "planners")
public class Planner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String user;

    private String category;

    private String task;

    private Date dateStart;

    private String status;

    private Date dateEnd;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return user;
    }

    public void setName(String name) {
        this.user = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
