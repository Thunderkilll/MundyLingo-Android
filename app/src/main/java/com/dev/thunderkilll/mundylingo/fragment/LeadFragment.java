package com.dev.thunderkilll.mundylingo.fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.thunderkilll.mundylingo.Adapters.UserAdapter;
import com.dev.thunderkilll.mundylingo.R;

import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.IPadress;
import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.currentUser;
import static com.dev.thunderkilll.mundylingo.Activities.MainActivity.getUseritemsList;
import static com.dev.thunderkilll.mundylingo.Activities.MainActivity.getmAdapter;
import static com.dev.thunderkilll.mundylingo.Activities.MainActivity.getmAdapterFR;
import static com.dev.thunderkilll.mundylingo.Activities.MainActivity.getmAdapterGR;
import static com.dev.thunderkilll.mundylingo.Activities.MainActivity.getmAdapterSP;

public class LeadFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private UserAdapter mAdapter, mAdapterfr, mAdaptersp, mAdaptergr;
    private RecyclerView recyclerView, rcViewFr, rcViewSp, rcViewGr;
    public static Typeface OrangeJuce, AgentOrange;
    TextView txtEng, txtFr, txtSp, txtGr;

    public LeadFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LeadFragment newInstance(String param1, String param2) {
        LeadFragment fragment = new LeadFragment();
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
        View view = inflater.inflate(R.layout.fragment_lead, container, false);

        //declaration
        recyclerView = view.findViewById(R.id.listEN);
        rcViewFr = view.findViewById(R.id.listFr);
        rcViewSp = view.findViewById(R.id.listSp);
        rcViewGr = view.findViewById(R.id.listGr);
        txtEng = view.findViewById(R.id.txtEng);
        txtFr = view.findViewById(R.id.txtFr);
        txtSp = view.findViewById(R.id.txtSp);
        txtGr = view.findViewById(R.id.txtGr);
        //style and design
        AgentOrange = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AgentOrange.ttf"); //style text
        OrangeJuce = Typeface.createFromAsset(getActivity().getAssets(), "fonts/orange juice 2.0.ttf"); //style text
        txtEng.setTypeface(AgentOrange);
        txtFr.setTypeface(AgentOrange);
        txtSp.setTypeface(AgentOrange);
        txtGr.setTypeface(AgentOrange);
        //adapters
        mAdapter = getmAdapter();
        mAdapterfr = getmAdapterFR();
        mAdaptersp = getmAdapterSP();
        mAdaptergr = getmAdapterGR();
        //recycleViews
        RecyclerView.LayoutManager mLayoutManager0 = new GridLayoutManager(getActivity(), 1);
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(), 1);
        RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getActivity(), 1);
        RecyclerView.LayoutManager mLayoutManager3 = new GridLayoutManager(getActivity(), 1);
        //english list
        recyclerView.setLayoutManager(mLayoutManager0);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        //french list
        rcViewFr.setLayoutManager(mLayoutManager1);
        rcViewFr.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        rcViewFr.setItemAnimator(new DefaultItemAnimator());
        rcViewFr.setAdapter(mAdapterfr);
        //spanish list
        rcViewSp.setLayoutManager(mLayoutManager2);
        rcViewSp.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        rcViewSp.setItemAnimator(new DefaultItemAnimator());
        rcViewSp.setAdapter(mAdaptersp);
        //german list
        rcViewGr.setLayoutManager(mLayoutManager3);
        rcViewGr.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        rcViewGr.setItemAnimator(new DefaultItemAnimator());
        rcViewGr.setAdapter(mAdaptergr);
        return view;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    //TODO: call 4 api differentes from our server to bring leaderboard in  each language
    //english

    public void fixeData() {

        getUseritemsList().add(currentUser);
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


    //TODO: contstruire 4 diffrent adapters to remplir our list (not optimizable)
    //TODO: remplir les 4 recycle view
    //TODO: part of the user adapters




}



