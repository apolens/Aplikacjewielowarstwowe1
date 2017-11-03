package com.example.adrian.aplikacjewielowarstwowe;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CAdapterResolveIncidentList extends RecyclerView.Adapter<CViewHolderList>
{
    private List<CItemListIncident> incidentList;

    private ActivityMain currentActivity;
    private View currentView;

    private int currentPosition;

    public CAdapterResolveIncidentList(List<CItemListIncident> incidentList)
    {
        this.incidentList = incidentList;
    }

    @Override
    public CViewHolderList onCreateViewHolder(ViewGroup parent, int viewType)
    {
        currentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_resolve_incident_item, parent, false);

        currentActivity = (ActivityMain) currentView.getContext();

        return new CViewHolderList(currentView);
    }

    @Override
    public void onBindViewHolder(final CViewHolderList holder, final int position)
    {
        CItemListIncident cItemListIncidentTemp = incidentList.get(position);

        currentPosition = position;

        holder.getNo().setText(cItemListIncidentTemp.getTextIncidentId());
        holder.getTitle().setText(cItemListIncidentTemp.getTextTitle());
        holder.getRequestor().setText(cItemListIncidentTemp.getTextRequestor());
        holder.getRequestDate().setText(cItemListIncidentTemp.getTextRequestDate());
        holder.getCategory().setText(cItemListIncidentTemp.getTextCategory());
        holder.getPriority().setText(cItemListIncidentTemp.getTextPriority());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CDataIncident cDataIncidentTemp = incidentList.get(position).getDataIncident();

                currentActivity.getIntent().putExtra("dataCurrentIncident", cDataIncidentTemp);

                Fragment nextFragment = new FragmentResolveIncidentDetail();

                currentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.id_main_content, nextFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return incidentList.size();
    }
}

