package com.example.adrian.aplikacjewielowarstwowe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

public class FragmentResolveIncidentDetail extends Fragment
{
    private EditText etIncidentId;
    private Spinner sStatus;
    private EditText etRequestor;
    private EditText etRequestDate;
    private Spinner sPriority;
    private Spinner sAssignTo;
    private EditText etTitle;
    private EditText etDescription;
    private EditText etSolution;
    private EditText etComment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_resolve_incident_detail, container, false);

        etIncidentId = (EditText) view.findViewById(R.id.id_form_incident_id);
        sStatus = (Spinner) view.findViewById(R.id.id_form_status);
        etRequestor = (EditText) view.findViewById(R.id.id_form_requestor);
        etRequestDate = (EditText) view.findViewById(R.id.id_form_requestDate);
        sPriority = (Spinner) view.findViewById(R.id.id_form_priority);
        etTitle = (EditText) view.findViewById(R.id.id_form_title);
        etDescription = (EditText) view.findViewById(R.id.id_form_description);
        etSolution = (EditText) view.findViewById(R.id.id_form_solution);
        etComment = (EditText) view.findViewById(R.id.id_form_comment);

        CDataIncident dataIncident = (CDataIncident) getActivity().getIntent().getSerializableExtra("dataCurrentIncident");

        setInitialData(dataIncident);

        return view;
    }

    /*
    Metoda setInitialData() służy do ustawienia wartości początkowych dla pól formularza.
     */

    private void setInitialData(CDataIncident dataIncident)
    {
        etIncidentId.setText(dataIncident.getIncidentId());

        for(int i = 0; i < sStatus.getAdapter().getCount(); i++)
        {
            if(sStatus.getAdapter().getItem(i).toString().equals(dataIncident.getStatus()))
            {
                sStatus.setSelection(i);
            }
        }

        etRequestor.setText(dataIncident.getRequestor());
        etRequestDate.setText(dataIncident.getRequestDate());

        for(int i = 0; i < sPriority.getAdapter().getCount(); i++)
        {
            if(sPriority.getAdapter().getItem(i).toString().equals(dataIncident.getPriority()))
            {
                sPriority.setSelection(i);
            }
        }

        etTitle.setText(dataIncident.getTitle());
        etDescription.setText(dataIncident.getDescription());
        etSolution.setText(dataIncident.getSolution());
        etComment.setText(dataIncident.getComment());

        etIncidentId.setEnabled(false);
        sStatus.setEnabled(true);
        etRequestor.setEnabled(false);
        etRequestDate.setEnabled(false);
        sPriority.setEnabled(false);
        etTitle.setEnabled(false);
        etDescription.setEnabled(false);
        etSolution.setEnabled(true);
        etComment.setEnabled(true);
    }
}
