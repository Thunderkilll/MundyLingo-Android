package com.dev.thunderkilll.mundylingo.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dev.thunderkilll.mundylingo.Models.Cour;
import com.dev.thunderkilll.mundylingo.R;

import java.util.List;

import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.IPadress;


public class SavedCoursAdapters extends RecyclerView.Adapter<SavedCoursAdapters.MyViewHolders>{

private Context context;

public List<Cour> itemcourList;

public class MyViewHolders extends RecyclerView.ViewHolder {

    public ImageView thumbnail_cours;
    public ImageView imageView2;
    public TextView titleCours;
    public TextView idLevel;
    public TextView id_langue_cour;


    public MyViewHolders(View view) {
        super(view);
        titleCours = view.findViewById(R.id.Stitle_cours);
        id_langue_cour = view.findViewById(R.id.id_langue_Scour);
        thumbnail_cours = view.findViewById(R.id.thumbnail_Scours);
        imageView2 = view.findViewById(R.id.imageView2);

    }


}

    public SavedCoursAdapters(Context context, List<Cour> coursList) {
        this.context = context;
        this.itemcourList = coursList;
    }
@Override
    public SavedCoursAdapters.MyViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_cours_item_row, parent, false);

        return new SavedCoursAdapters.MyViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(SavedCoursAdapters.MyViewHolders holder, final int position) {
        final Cour lg = itemcourList.get(position);
        //title mta3 el cour = id mte3ou fill base de donnée

        holder.titleCours.setText("saved");

        Log.d("view holder", String.valueOf(itemcourList.get(position)) + "\n");
        //id du level c'est l'id dans la base de level du cour


        String idLangue = lg.getLangue();
        Log.e("langue id", idLangue);

        //condition sur les langues if english > img english , ect
        if (idLangue.equals("2")) {
            //english
            //identify the language of this cours
            holder.id_langue_cour.setText("English "+lg.getIdLevel());
            //convertir l'url en utilisant glade et l'afficher
            Glide.with(context)
                    .load(IPadress + "/miniProjetRessources/englishflag.jpg")
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.thumbnail_cours);



        }
        if (idLangue.equals("1")) {
            //french

            holder.id_langue_cour.setText("French");
            Glide.with(context)
                    .load(IPadress + "/miniProjetRessources/french_flag.jpg")
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.thumbnail_cours);



        }
        if (idLangue.equals("4")) {
            //spanish
            holder.id_langue_cour.setText("Spanish");
            Glide.with(context)
                    .load(IPadress + "/miniProjetRessources/spanishflag.jpg")
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.thumbnail_cours);


        }
        if (idLangue.equals("3")) {
            //german
            holder.id_langue_cour.setText("German");
            Glide.with(context)
                    .load(IPadress + "/miniProjetRessources/germanflag.jpg")
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.thumbnail_cours);



        }


//if current player level < cours level = display cadnat

    }


    @Override
    public int getItemCount() {
        Log.d("list size", String.valueOf(itemcourList.size()));
        return itemcourList.size();
    }

}
