package com.dev.thunderkilll.mundylingo.fragment;

import android.app.ProgressDialog;
import android.content.Context;

import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import com.dev.thunderkilll.mundylingo.Helpers.RecyclerTouchListener;
import com.dev.thunderkilll.mundylingo.Models.Cour;
import com.dev.thunderkilll.mundylingo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.currentUser;

public class CoursFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String URLi = "http://192.168.1.6/miniProjetWebService/Langue/cours/getAllCourses.php";
    private static final String TAG = CoursFragment.class.getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<Cour>   courList;
    private CoursAdapter mAdapter;


    public CoursFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CoursFragment newInstance(String param1, String param2) {
        CoursFragment fragment = new CoursFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cours, container, false);
        //instantiate the recycle view
        recyclerView = view.findViewById(R.id.recycler_view_cours);
        courList = new ArrayList<>();
        mAdapter = new CoursAdapter(getActivity(), courList);

        //nombre d'element dans une ligne fill liste view 7atitha ena 3
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(30, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        prepareMovieData() ;
        getData(view);
//TODO : on recycleview select

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Cour cour = courList.get(position);
                Log.d("selected cours", "cour with id : "+cour.getId()+" is selected");
                //TODO: 1erement check if the idlevel < or = idLevel of player in the same language cathegory
                //TODO: si oui open the cours if not message d"erreur for now
                int levelID = Integer.parseInt(cour.getIdLevel());
                int langueID = Integer.parseInt(cour.getLangue());

                if (langueID == 1){
                    //english
                    if (levelID > ConvertToInt(currentUser.getLevelEng())){
                        Toast.makeText(getActivity() , "  your level is low for this course please finish the level first ",Toast.LENGTH_SHORT ).show();

                    }
                    //redirect to cours details
                    Toast.makeText(getActivity() , "  redirect to cour details id level cour"+levelID+"curent player plevel"+currentUser.getLevelEng(),Toast.LENGTH_SHORT ).show();


                }
                if (langueID == 2){
                    //french
                    if (levelID > ConvertToInt(currentUser.getLevelFr())){
                        Toast.makeText(getActivity() , "  your level is low for this course please finish the level first ",Toast.LENGTH_LONG ).show();

                    }
                    //redirect to cours details
                    Toast.makeText(getActivity() , "  redirect to cour details , id level cour"+levelID+"curent player plevel"+currentUser.getLevelFr(),Toast.LENGTH_LONG ).show();

                }
                if (langueID == 3){
                    //spanish

                    if (levelID > ConvertToInt(currentUser.getLevelSpan())){
                        Toast.makeText(getActivity() , "  your level is low for this course please finish the level first ",Toast.LENGTH_LONG ).show();

                    }
                    //redirect to cours details
                    Toast.makeText(getActivity() , "  redirect to cour details id level cour"+levelID+"curent player plevel"+currentUser.getLevelSpan(),Toast.LENGTH_LONG ).show();

                }
                if (langueID == 4){
                    //german

                    if (levelID > ConvertToInt(currentUser.getLevelGer())){
                        Toast.makeText(getActivity() , "  your level is low for this course please finish the level first ",Toast.LENGTH_LONG ).show();

                    }
                    //redirect to cours details
                    Toast.makeText(getActivity() , "  redirect to cour details id level cour"+levelID+"curent player plevel"+currentUser.getLevelGer(),Toast.LENGTH_LONG ).show();

                }



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        getData(view);
      return  view ;
    }



    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    public void prepareMovieData() {
        Cour langue = new Cour("1","1","grammar" ,"conjug"," hello this is grammaire10","1");
        courList.add(langue);
        Cour langue1 = new Cour("2","2","grammar" ,"1"," hello this is grammaire10","2");
        courList.add(langue1);
        Cour langue2 = new Cour("3","3","grammar" ,"1"," hello this is grammaire10","3");
        courList.add(langue2);
        Cour langue3 = new Cour("4","4","grammar" ,"1"," hello this is grammaire10","4");
        courList.add(langue3);




        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }



//TODO: read from the web service
    //operation Volley is on

    public void getData(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        JsonArrayRequest jsonArrayRequest = new  JsonArrayRequest(URLi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("index>>>>>>", jsonArray.toString());


                try {


                    for (int i = 0 ; i<jsonArray.length() ; i++) {

                        JSONObject jObj = jsonArray.getJSONObject(i);

                        Cour c = new Cour(
                                String.valueOf(jObj.getInt("id")),
                                jObj.getString("idLevel"),
                                jObj.getString("grammaire"),
                                jObj.getString("conjugaison"),
                                jObj.getString("orthographe"),
                                String.valueOf(jObj.getInt("langue")));

                        //a remplir : id , idlevel , grammaire , conjugaison , orthographe , langue
                        Log.d("affichageCours",c.toString());

                        courList.add(c);
                        for (int j= 0 ; j<courList.size() ; j++){
                            System.out.println(courList.get(j).toString());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mAdapter = new CoursAdapter(getActivity(), courList);
                Log.d("item list of courses", String.valueOf(courList.size()));
                mAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }







    // TODO: custom spacing between elements in the recycle view

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }



    public int ConvertToInt(String numb){

        return Integer.parseInt(numb);
    }

}

// TODO: this is my custom adapter instance

class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.MyViewHolders> {
    private Context context;
    private List<Cour> itemcourList;

    public class MyViewHolders extends RecyclerView.ViewHolder {

        public ImageView thumbnail_cours;
        public TextView titleCours ;
        public TextView idLevel ;
        public TextView id_langue_cour ;
        public ImageView lock ;

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
        String setTitle = "Cours : "+lg.getId() ;
        holder.titleCours.setText(setTitle);
      Log.d("view holder",String.valueOf(itemcourList.get(position))+"\n");

        holder.idLevel.setText("Level  : "+lg.getId());
        String idLangue  = lg.getLangue() ;
        Log.e("langue id" , idLangue);
   if (idLangue.equals("1")){
            //english
            holder.id_langue_cour.setText("Langue  :  English");
       Glide.with(context)
               .load("http://192.168.1.6/miniProjetRessources/englishflag.jpg")
               .into(holder.thumbnail_cours);
       if (ConvertToInt(lg.getIdLevel()) > ConvertToInt(currentUser.getLevelEng())){
         holder.lock.setVisibility(View.VISIBLE);
       }
   }
   if(idLangue.equals("2")){
            //french
      holder.id_langue_cour.setText("Langue   :  French");
       Glide.with(context)
               .load("http://192.168.1.6/miniProjetRessources/french_flag.jpg")
               .into(holder.thumbnail_cours);
       if (ConvertToInt(lg.getIdLevel()) > ConvertToInt(currentUser.getLevelFr())){
           holder.lock.setVisibility(View.VISIBLE);
       }
      }
      if (idLangue.equals("3")){
            //spanish
       holder.id_langue_cour.setText("Langue  :    French");
       Glide.with(context)
               .load("http://192.168.1.6/miniProjetRessources/spanishflag.jpg")
               .into(holder.thumbnail_cours);

          if (ConvertToInt(lg.getIdLevel()) > ConvertToInt(currentUser.getLevelFr())){
              holder.lock.setVisibility(View.VISIBLE);
          }
   }
   if (idLangue.equals("4")){
            //german
         holder.id_langue_cour.setText("Langue  :   German");
       Glide.with(context)
               .load("http://192.168.1.6/miniProjetRessources/germanflag.jpg")
               .into(holder.thumbnail_cours);
       if (ConvertToInt(lg.getIdLevel()) > ConvertToInt(currentUser.getLevelGer())){
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
        Log.d("list size" , String.valueOf(itemcourList.size()));
        return itemcourList.size();
    }






   //to convert a sring into integer if possible


}


