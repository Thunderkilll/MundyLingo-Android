package com.dev.thunderkilll.mundylingo.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.thunderkilll.mundylingo.Activities.ConjugaisonDetailActivity;
import com.dev.thunderkilll.mundylingo.Activities.CoursPrefActivity;
import com.dev.thunderkilll.mundylingo.Activities.MainActivity;
import com.dev.thunderkilll.mundylingo.Adapters.CoursAdapter;
import com.dev.thunderkilll.mundylingo.Helpers.RecyclerTouchListener;
import com.dev.thunderkilll.mundylingo.Models.Cour;
import com.dev.thunderkilll.mundylingo.R;

import java.util.ArrayList;
import java.util.List;

import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.IPadress;
import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.currentUser;
import static com.dev.thunderkilll.mundylingo.Activities.MainActivity.getCourList;
import static com.dev.thunderkilll.mundylingo.Activities.MainActivity.getM2Adapter;

public class CoursFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String URLi = IPadress + "/miniProjetWebService/Langue/cours/getAllCourses.php";
    private static final String TAG = CoursFragment.class.getSimpleName();
    public static Cour selectedCour;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<Cour> courList;
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
        View view = inflater.inflate(R.layout.fragment_cours, container, false);
        //instantiate the recycle view

        recyclerView = view.findViewById(R.id.recycler_view_cours);
        courList = getCourList();
        mAdapter = getM2Adapter();

        //nombre d'element dans une ligne fill liste view 7atitha ena 3
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(30, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        //prepareMovieData() ;

//TODO : on recycleview select

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Cour cour = courList.get(position);
                Log.d("selected cours", "cour with id : " + cour.getId() + " is selected");
                //TODO: 1erement check if the idlevel < or = idLevel of player in the same language cathegory
                //TODO: si oui open the cours if not message d"erreur for now
                int levelID = Integer.parseInt(cour.getIdLevel());
                int langueID = Integer.parseInt(cour.getLangue());

                if (langueID == 2) {
                    //english
                    if (levelID > ConvertToInt(currentUser.getLevelEng())) {
                        Toast.makeText(getActivity(), "  your level is low for this course please finish the level first ", Toast.LENGTH_SHORT).show();

                    } else {
                        //redirect to cours details
                        Toast.makeText(getActivity(), "  redirect to cour details id level cour" + levelID + "curent player plevel" + currentUser.getLevelEng(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), ConjugaisonDetailActivity.class);
                        getActivity().startActivity(intent);
                        selectedCour = cour;
                    }

                }
                if (langueID == 1) {
                    //french
                    if (levelID > ConvertToInt(currentUser.getLevelFr())) {
                        Toast.makeText(getActivity(), "  your level is low for this course please finish the level first ", Toast.LENGTH_SHORT).show();

                    } else {
                        //redirect to cours details
                        Toast.makeText(getActivity(), "  redirect to cour details , id level cour" + levelID + "curent player plevel" + currentUser.getLevelFr(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), ConjugaisonDetailActivity.class);
                        getActivity().startActivity(intent);
                        selectedCour = cour;
                    }
                }
                if (langueID == 4) {
                    //spanish

                    if (levelID > ConvertToInt(currentUser.getLevelSpan())) {
                        Toast.makeText(getActivity(), "  your level is low for this course please finish the level first ", Toast.LENGTH_SHORT).show();

                    } else {
                        //redirect to cours details
                        Toast.makeText(getActivity(), "  redirect to cour details id level cour" + levelID + "curent player plevel" + currentUser.getLevelSpan(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), ConjugaisonDetailActivity.class);
                        getActivity().startActivity(intent);
                        selectedCour = cour;
                    }
                }
                if (langueID == 3) {
                    //german

                    if (levelID > ConvertToInt(currentUser.getLevelGer())) {
                        Toast.makeText(getActivity(), "  your level is low for this course please finish the level first ", Toast.LENGTH_SHORT).show();

                    } else {
                        //redirect to cours details
                        Toast.makeText(getActivity(), "  redirect to cour details id level cour" + levelID + "curent player plevel" + currentUser.getLevelGer(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), ConjugaisonDetailActivity.class);
                        getActivity().startActivity(intent);
                        selectedCour = cour;
                    }
                }


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        return view;
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


//TODO: read from the web service
    //operation Volley is on


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


    public int ConvertToInt(String numb) {

        return Integer.parseInt(numb);
    }





}

// TODO: this is my custom adapter instance


