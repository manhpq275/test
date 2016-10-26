package asia.ienter.matching.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import asia.ienter.matching.R;
import asia.ienter.matching.views.activities.HomeActivity;
import asia.ienter.matching.views.adapters.AboutAdapter;
import asia.ienter.matching.views.adapters.HobbyAdapter;

/**
 * Created by hoangtuan on 9/30/16.
 */
public class ChangeHobbyFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private HobbyAdapter hobbyAdapter;
    public static ChangeHobbyFragment newInstance() {

        Bundle args = new Bundle();

        ChangeHobbyFragment fragment = new ChangeHobbyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_change_hobby, container, false);
        mContext = this.getContext();
        initView();
        return mView;
    }

    @Override
    protected void initView() {
        if(isAdded()) {
            hobbyAdapter = new HobbyAdapter(getActivity());
            recyclerView = (RecyclerView) mView.findViewById(R.id.listHobby);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(hobbyAdapter);
            Button backButton = (Button) mView.findViewById(R.id.btnBack);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
            Button saveButton = (Button) mView.findViewById(R.id.btnSave);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }
    }

    @Override
    protected void loadDataFromApi() {

    }
}
