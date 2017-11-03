package com.example.adrian.aplikacjewielowarstwowe;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/*
Klasa CAdapterActiveIncidentList rozszerzająca klasę RecyclerView.Adapter jest odpowiedzialna za dostarczanie widoków reprezentujących elementy w zbiorze danych.
Prościej mówiąc klasa adapter przechowuje i zarządza danymi do wyświetlenia. Zbiorem danych o którym byłą mowa jest lista. Lista tylko wyświetla elementy, które dostaje właśnie od adaptera.
Nasza lista budowana jest z wykorzystaniem CardView oraz RecyclerView przez co rozszerzamy naszą klasę o klasę RecyclerView.Adapter.
Klasa RecyclerView.Adapter wykorzystuje wzorzec ViewHolde odpowiednik naszej klasy CViewHolderList. We wzorcu tym chodzi o ograniczenie wywołania metody findViewById() podczas uzupełniania listy elementów
co przekłada się na lepszą wydajność. Szczegółowy opis View Holder został zawarty w klasie CViewHolderList.
 */

public class CAdapterActiveIncidentList extends RecyclerView.Adapter<CViewHolderList>
{
    private List<CItemListIncident> incidentList;

    private ActivityMain currentActivity;
    private View currentView;

    private int currentPosition;

    public CAdapterActiveIncidentList(List<CItemListIncident> incidentList)
    {
        this.incidentList = incidentList;
    }

    /*
    Metoda onCreateViewHolder() tworzy obiekt layoutu elementu listy oraz na jego podstawie tworzy obiekt CViewHolderList
     */

    @Override
    public CViewHolderList onCreateViewHolder(ViewGroup parent, int viewType)
    {
        currentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_active_incident_item, parent, false);

        currentActivity = (ActivityMain) currentView.getContext();

        return new CViewHolderList(currentView);
    }

    /*
    Metoda onBindViewHolder() uzupełnia elementy listy odpowiednimi danymi
     */

    @Override
    public void onBindViewHolder(final CViewHolderList holder, final int position)
    {
        CItemListIncident cItemListIncidentTemp = incidentList.get(position);

        currentPosition = position;

        holder.getNo().setText(cItemListIncidentTemp.getTextIncidentId());
        holder.getTitle().setText(cItemListIncidentTemp.getTextTitle());
        holder.getRequestDate().setText(cItemListIncidentTemp.getTextRequestDate());
        holder.getCategory().setText(cItemListIncidentTemp.getTextCategory());
        holder.getPriority().setText(cItemListIncidentTemp.getTextPriority());

        /*
        ustawienie słuchacza na karcie - elemencie listy
         */

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*
                przekazywanie damych do kolejnej aktywności
                 */

                CDataIncident cDataIncidentTemp = incidentList.get(position).getDataIncident();

                currentActivity.getIntent().putExtra("dataCurrentIncident", cDataIncidentTemp);

                Fragment nextFragment = new FragmentActiveIncidentDetail();

                currentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.id_main_content, nextFragment).addToBackStack(null).commit();
            }
        });
    }

    /*
    Metoda getItemCount() zwraca ilość wszystkich elementów
     */

    @Override
    public int getItemCount()
    {
        return incidentList.size();
    }
}