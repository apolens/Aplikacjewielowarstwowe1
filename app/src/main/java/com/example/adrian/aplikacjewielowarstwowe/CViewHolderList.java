package com.example.adrian.aplikacjewielowarstwowe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
Klasa CViewHolderList opisuje widok elementu i metadane.
Przewijanie listy zawierającej dużą liczbę elementów wiąże się z częstym wywoływaniem metody findViewById,
co może znacznie obniżyć wydajność a w rezultacie spowodować, że lista nie będzie przewijała się płynnie.
Sposobem na obejście tego problemu jest wzorzec projektowy View Holder.
Idea wzorca polega na stworzeniu obiektu, który będzie przechowywał w swoich polach referencje do kontrolek widoku raz pobranych z layoutu metodą findViewById.
Kiedy będzie potrzeba odwołania się do kontrolki, robimy to po prostu po przez pole tego obiektu.
 */

public class CViewHolderList extends RecyclerView.ViewHolder
{
    private TextView tvNo;
    private TextView tvTitle;
    private TextView tvRequestor;
    private TextView tvRequestDate;
    private TextView tvCategory;
    private TextView tvPriority;

    private RelativeLayout rlCardView;

    private View currentView;

    public CViewHolderList(final View itemView)
    {
        super(itemView);

        tvNo = (TextView) itemView.findViewById(R.id.id_incident_no);
        tvTitle = (TextView) itemView.findViewById(R.id.id_incident_title);
        tvRequestor = (TextView) itemView.findViewById(R.id.id_incident_requestor);
        tvRequestDate = (TextView) itemView.findViewById(R.id.id_incident_request_date);
        tvCategory = (TextView) itemView.findViewById(R.id.id_incident_category);
        tvPriority = (TextView) itemView.findViewById(R.id.id_incident_priority);

        rlCardView = (RelativeLayout) itemView.findViewById(R.id.id_relative_layout_card_view);

        currentView = itemView;
    }

    public TextView getNo()
    {
        return tvNo;
    }

    public TextView getTitle()
    {
        return tvTitle;
    }

    public TextView getRequestor()
    {
        return tvRequestor;
    }

    public TextView getRequestDate()
    {
        return tvRequestDate;
    }

    public TextView getCategory()
    {
        return tvCategory;
    }

    public TextView getPriority()
    {
        return tvPriority;
    }

    public RelativeLayout getCardView()
    {
        return rlCardView;
    }

    public View getCurrentView()
    {
        return currentView;
    }
}