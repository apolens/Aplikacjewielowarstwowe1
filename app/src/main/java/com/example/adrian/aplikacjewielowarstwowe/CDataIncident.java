package com.example.adrian.aplikacjewielowarstwowe;

import java.io.Serializable;

public class CDataIncident implements Serializable
{
    private String incidentId;
    private String category;
    private String priority;
    private String status;
    private String requestor;
    private String requestDate;
    private String title;
    private String description;
    private String solution;
    private String comment;

    public CDataIncident(String incidentId, String category, String priority, String status, String requestor, String requestDate, String title, String description, String solution, String comment)
    {
        this.incidentId = incidentId;
        this.category = category;
        this.priority = priority;
        this.status = status;
        this.requestor = requestor;
        this.requestDate = requestDate;
        this.title = title;
        this.description = description;
        this.solution = solution;
        this.comment = comment;
    }

    public String getIncidentId()
    {
        return incidentId;
    }

    public void setIncidentId(String incidentId)
    {
        this.incidentId = incidentId;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getRequestDate()
    {
        return requestDate;
    }

    public void setRequestDate(String requestDate)
    {
        this.requestDate = requestDate;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getSolution()
    {
        return solution;
    }

    public void setSolution(String solution)
    {
        this.solution = solution;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
