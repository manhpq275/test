package asia.ienter.matching.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import asia.ienter.matching.R;

/**
 * Created by hoangtuan on 9/21/16.
 */
public class AboutDetailFragment extends BaseFragment {
    public static AboutDetailFragment newInstance() {

        Bundle args = new Bundle();

        AboutDetailFragment fragment = new AboutDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_about_detail, container, false);
        mContext = this.getContext();
        initView();
        return mView;
    }

    @Override
    protected void initView() {
        Button btnBack = (Button) mView.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    protected void loadDataFromApi() {

    }
}
