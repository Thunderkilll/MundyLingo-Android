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
import com.dev.thunderkilll.mundylingo.Models.Cour;
import com.dev.thunderkilll.mundylingo.R;

import java.util.List;

import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.IPadress;
import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.currentUser;

public class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.MyViewHolders> {
    private Context context;
    public List<Cour> itemcourList;

    public class MyViewHolders extends RecyclerView.ViewHolder {

        public ImageView thumbnail_cours;
        public TextView titleCours;
        public TextView idLevel;
        public TextView id_langue_cour;
        public ImageView lock;

        public MyViewHolders(View view) {
            super(view);
            titleCours = view.findViewById(R.id.title_cours);
            id_langue_cour = view.findViewById(R.id.id_langue_cour);
            idLevel = view.findViewById(R.id.lvl_id_cour);
            thumbnail_cours = view.findViewById(R.id.thumbnail_cours);
            lock = view.findViewById(R.id.lock_thumb);
            //appel au cadnat
        }
    }


    public CoursAdapter(Context context, List<Cour> coursList) {
        this.context = context;
        this.itemcourList = coursList;
    }

    @Override
    public MyViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cours_item_row, parent, false);

        return new MyViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolders holder, final int position) {
        final Cour lg = itemcourList.get(position);
        //title mta3 el cour = id mte3ou fill base de donnée
        String setTitle = "Cours : " + lg.getId();
        holder.titleCours.setText(setTitle);

        Log.d("view holder", String.valueOf(itemcourList.get(position)) + "\n");
        //id du level c'est l'id dans la base de level du cour
        holder.idLevel.setText("Level  : " + lg.getIdLevel());

        String idLangue = lg.getLangue();
        Log.e("langue id", idLangue);

        //condition sur les langues if english > img english , ect
        if (idLangue.equals("2")) {
            //english
            //identify the language of this cours
            holder.id_langue_cour.setText("Langue  :  English");
            //convertir l'url en utilisant glade et l'afficher
            Glide.with(context)
                    .load(IPadress + "/miniProjetRessources/englishflag.jpg")
                    .into(holder.thumbnail_cours);

            if (ConvertToInt(lg.getIdLevel()) > ConvertToInt(currentUser.getLevelEng())) {

                holder.lock.setVisibility(View.VISIBLE);
            }
        }
        if (idLangue.equals("1")) {
            //french
            holder.id_langue_cour.setText("Langue   :  French");

            Glide.with(context)
                    .load(IPadress + "/miniProjetRessources/french_flag.jpg")
                    .into(holder.thumbnail_cours);

            if (ConvertToInt(lg.getIdLevel()) > ConvertToInt(currentUser.getLevelFr())) {

                holder.lock.setVisibility(View.VISIBLE);
            }

        }
        if (idLangue.equals("4")) {
            //spanish
            holder.id_langue_cour.setText("Langue  :    Spanish");
            Glide.with(context)
                    .load(IPadress + "/miniProjetRessources/spanishflag.jpg")
                    .into(holder.thumbnail_cours);


            if (ConvertToInt(lg.getIdLevel()) > ConvertToInt(currentUser.getLevelSpan())) {
                holder.lock.setVisibility(View.VISIBLE);
            }
        }
        if (idLangue.equals("3")) {
            //german
            holder.id_langue_cour.setText("Langue  :   German");
            Glide.with(context)
                    .load(IPadress + "/miniProjetRessources/germanflag.jpg")
                    .into(holder.thumbnail_cours);


            if (ConvertToInt(lg.getIdLevel()) > ConvertToInt(currentUser.getLevelGer())) {
                holder.lock.setVisibility(View.VISIBLE);
            }
        }


//if current player level < cours level = display cadnat

    }

    public int ConvertToInt(String numb) {
        return Integer.parseInt(numb);
    }

    @Override
    public int getItemCount() {
        Log.d("list size", String.valueOf(itemcourList.size()));
        return itemcourList.size();
    }


}
