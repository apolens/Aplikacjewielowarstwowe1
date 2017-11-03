package com.example.adrian.aplikacjewielowarstwowe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentAddIncident extends Fragment
{
    private EditText etIncidentId;
    private EditText etRequestor;
    private EditText etRequestDate;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add_incident, container, false);

        etIncidentId = (EditText) view.findViewById(R.id.id_form_incident_id);
        etRequestor = (EditText) view.findViewById(R.id.id_form_requestor);
        etRequestDate = (EditText) view.findViewById(R.id.id_form_requestDate);

        setInitialData();
        exampleData();

        return view;
    }

    private void setInitialData()
    {
        etIncidentId.setEnabled(false);
        etRequestor.setEnabled(false);
        etRequestDate.setEnabled(false);
    }

    private void exampleData()
    {
        etIncidentId.setText("1");
        etRequestor.setText("jKowalski");
        etRequestDate.setText("2017-11-01 23:40:00");
    }
}
