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

import asia.ienter.matching.R;
import asia.ienter.matching.views.adapters.AboutAdapter;
import asia.ienter.matching.views.adapters.SettingAdapter;

/**
 * Created by hoangtuan on 9/21/16.
 */
public class SettingFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private SettingAdapter aboutAdapter;
    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_setting, container, false);
        mContext = this.getContext();
        initView();
        return mView;
    }

    @Override
    protected void initView() {
        if(isAdded()) {
            aboutAdapter = new SettingAdapter(getActivity());
            recyclerView = (RecyclerView) mView.findViewById(R.id.listSetting);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(aboutAdapter);
            Button backButton = (Button) mView.findViewById(R.id.btnBack);
            backButton.setOnClickListener(new View.OnClickListener() {
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
