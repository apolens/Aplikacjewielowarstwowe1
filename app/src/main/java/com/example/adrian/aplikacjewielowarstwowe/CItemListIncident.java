package com.example.adrian.aplikacjewielowarstwowe;

public class CItemListIncident
{
    private final static String PREFIX_INCIDENT_ID = "";
    private final static String PREFIX_TITLE = "";
    private final static String PREFIX_REQUESTOR = "Requestor: ";
    private final static String PREFIX_REQUEST_DATE = "Request date: ";
    private final static String PREFIX_CATEGORY = "Category: ";
    private final static String PREFIX_PRIORITY = "Priority: ";

    private CDataIncident cDataIncident;

    private String textIncidentId;
    private String textTitle;
    private String textRequestor;
    private String textRequestDate;
    private String textCategory;
    private String textPriority;

    public CItemListIncident(CDataIncident cDataIncident)
    {
        this.cDataIncident = cDataIncident;

        this.textIncidentId = PREFIX_INCIDENT_ID + cDataIncident.getIncidentId();
        this.textTitle = PREFIX_TITLE + cDataIncident.getTitle();
        this.textRequestor = PREFIX_REQUESTOR + cDataIncident.getRequestor();
        this.textRequestDate = PREFIX_REQUEST_DATE + cDataIncident.getRequestDate();
        this.textCategory = PREFIX_CATEGORY + cDataIncident.getCategory();
        this.textPriority = PREFIX_PRIORITY + cDataIncident.getPriority();
    }

    public String getTextIncidentId()
    {
        return textIncidentId;
    }

    public void setTextIncidentId(String textIncidentId)
    {
        this.textIncidentId = textIncidentId;
    }

    public String getTextTitle()
    {
        return textTitle;
    }

    public void setTextTitle(String textTitle)
    {
        this.textTitle = textTitle;
    }

    public String getTextRequestor()
    {
        return textRequestor;
    }

    public void setTextRequestor(String textRequestor)
    {
        this.textRequestor = textRequestor;
    }

    public String getTextRequestDate()
    {
        return textRequestDate;
    }

    public void setTextRequestDate(String textRequestDate)
    {
        this.textRequestDate = textRequestDate;
    }

    public String getTextCategory()
    {
        return textCategory;
    }

    public void setTextCategory(String textCategory)
    {
        this.textCategory = textCategory;
    }

    public String getTextPriority()
    {
        return textPriority;
    }

    public void setTextPriority(String textPriority)
    {
        this.textPriority = textPriority;
    }

    public CDataIncident getDataIncident()
    {
        return cDataIncident;
    }
}