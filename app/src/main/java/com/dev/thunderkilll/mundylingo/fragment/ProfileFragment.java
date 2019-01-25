package com.dev.thunderkilll.mundylingo.fragment;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dev.thunderkilll.mundylingo.Helpers.DatabaseHelper;
import com.dev.thunderkilll.mundylingo.Models.User;
import com.dev.thunderkilll.mundylingo.R;

import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.currentUser;
import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.user;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView thumbnail;
    private Context context;
    private TextView username_prof, score_eng, score_fr, score_Sp, score_Ger;
    DatabaseHelper Mydb;
    Typeface OrangeJuce, AgentOrange;


    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        thumbnail = view.findViewById(R.id.img_profile);
        username_prof = view.findViewById(R.id.username_prof);
        score_eng = view.findViewById(R.id.score_eng);
        score_fr = view.findViewById(R.id.score_fr);
        score_Sp = view.findViewById(R.id.score_Sp);
        score_Ger = view.findViewById(R.id.score_Ger);
        AgentOrange = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AgentOrange.ttf");
        OrangeJuce = Typeface.createFromAsset(getActivity().getAssets(), "fonts/orange juice 2.0.ttf");

        //considering the that the user can login when he is ofline so i decided to add the user ifo in sqlite and get the stored info
        Mydb = new DatabaseHelper(view.getContext());

        User u = new User();
        Cursor c = Mydb.getAllusers();
        if (c.moveToLast()) {
            u.setUsername(c.getString(1));
            u.setEmail(c.getString(1));
            u.setImgUrl(c.getString(2));
            u.setScoreEng(c.getString(3));
            u.setScoreFr(c.getString(4));
            u.setScoreSpan(c.getString(5));
            u.setScoreGer(c.getString(6));
            u.setLevelFr(c.getString(7));
            u.setLevelEng(c.getString(8));
            u.setLevelSpan(c.getString(9));
            u.setLevelGer(c.getString(10));

        }
        System.out.println("   ");
        System.out.println("   ");
        System.out.println("   ");
        System.out.println(u.toString());
        System.out.println("   ");
        System.out.println("   ");
        System.out.println("   ");

        Glide.with(view.getContext())
                .load(currentUser.getImgUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(thumbnail);

        //username_prof.setText(user.getName());
        username_prof.setText(u.getUsername());
        username_prof.setTypeface(AgentOrange);
        score_eng.setText(u.getScoreEng());
        score_eng.setTypeface(OrangeJuce);
        score_fr.setText(u.getScoreFr());
        score_fr.setTypeface(OrangeJuce);
        score_Sp.setText(u.getScoreSpan());
        score_Sp.setTypeface(OrangeJuce);
        score_Ger.setText(u.getScoreGer());
        score_Ger.setTypeface(OrangeJuce);

        /*
        other methode
         */

        return view;
    }




}
