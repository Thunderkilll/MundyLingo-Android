package com.dev.thunderkilll.mundylingo.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.dev.thunderkilll.mundylingo.Models.Langue;
import com.dev.thunderkilll.mundylingo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.IPadress;


public class DashboardFragment extends Fragment {

    private List<Langue> itemsList;
    private LangueAdapter mAdapter;

    private static final String URLi = IPadress + "/miniProjetWebService/Langue/selectAllLangues.php";
    private static final String TAGDash = DashboardFragment.class.getSimpleName();
    Typeface OrangeJuce, AgentOrange;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        itemsList = new ArrayList<>();
        AgentOrange = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AgentOrange.ttf");
        OrangeJuce = Typeface.createFromAsset(getActivity().getAssets(), "fonts/orange juice 2.0.ttf");

        mAdapter = new LangueAdapter(getActivity(), itemsList);
        getData(view);

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.side_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.side_fav:

            case R.id.side_settings:


            case R.id.side_logout:


        }
        return true;
    }


    public void getData(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("index>>>>>>", jsonArray.toString());


                try {


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jObj = jsonArray.getJSONObject(i);

                        Langue langue = new Langue(jObj.getString("title"), jObj.getString("imgUrl"), String.valueOf(jObj.getInt("score")));
                        Log.d("affichage", jObj.getString("title"));

                        System.out.println(langue.toString());

                        itemsList.add(langue);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mAdapter = new LangueAdapter(getActivity(), itemsList);

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


}

class LangueAdapter extends RecyclerView.Adapter<LangueAdapter.MyViewHolder> {
    private Context context;
    private List<Langue> langueList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView thumbnail;
        public Typeface orjuce;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.title);
            orjuce = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/orange juice 2.0.ttf");
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }


    public LangueAdapter(Context context, List<Langue> langueList) {
        this.context = context;
        this.langueList = langueList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final Langue lg = langueList.get(position);
        holder.name.setText(lg.getTitle());
        holder.name.setTypeface(holder.orjuce);

        Glide.with(context)
                .load(lg.getImage())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return langueList.size();
    }
}
