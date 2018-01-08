package com.example.adrian.aplikacjewielowarstwowe;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class FragmentResolveIncident extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_resolve_incident, container, false);

        new CIncidentListAT(view, this).execute();

        return view;
    }

    private class CIncidentListAT extends AsyncTask<Void, Void, Boolean>
    {
        private View currentView;
        private Fragment fragment;
        private Context context;

        private ProgressDialog processDialog;
        private RecyclerView recyclerView;

        public CIncidentListAT(View currentView, Fragment fragment)
        {
            this.currentView = currentView;
            this.fragment = fragment;

            context = fragment.getContext();
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            processDialog = new ProgressDialog(context);

            processDialog.setMessage("Proszę czekać...");
            processDialog.setCancelable(false);
            processDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... arg0)
        {
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            super.onPostExecute(success);

            if (processDialog.isShowing())
            {
                processDialog.dismiss();
            }

            if(success)
            {
                ArrayList incidentList = exampleData();

                RecyclerView.Adapter adapter = new CAdapterResolveIncidentList(incidentList);

                recyclerView = (RecyclerView) currentView.findViewById(R.id.id_recycler_view);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
                recyclerView.setAdapter(adapter);
            }
        }

        private ArrayList exampleData()
        {
            ArrayList incidentList = new ArrayList<>();

            CDataIncident cDataIncidentExampleData1 = new CDataIncident("No356985", "Desktop problem", "High", "In progress", "jKowalski", "2017-06-27 13:00:00", "Komputer się nie uruchamia", "Blue screen", "Wymiana RAM", "");
            CDataIncident cDataIncidentExampleData2 = new CDataIncident("No156985", "App problem", "Medium", "Open", "pNowak", "2017-06-28 14:25:00", "Aplikacja się nie uruchamia", "Komunikat o braku uprawnień", "Nadanie uprawnień dla konta użytkownika", "");

            CItemListIncident cItemListIncidentExample1 = new CItemListIncident(cDataIncidentExampleData1);
            CItemListIncident cItemListIncidentExample2 = new CItemListIncident(cDataIncidentExampleData2);

            incidentList.add(cItemListIncidentExample1);
            incidentList.add(cItemListIncidentExample2);

            return incidentList;
        }
    }
}
