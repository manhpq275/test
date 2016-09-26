package asia.ienter.matching.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import asia.ienter.matching.R;

/**
 * Created by hoangtuan on 9/21/16.
 */
public class SelectImageFragment extends BaseFragment {
    private Button btnSelectImage;
    public static SelectImageFragment newInstance() {

        Bundle args = new Bundle();

        SelectImageFragment fragment = new SelectImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_select_image, container, false);
        mContext = this.getContext();
        initView();
        return mView;
    }

    @Override
    protected void initView() {
        if(isAdded()) {
            btnSelectImage = (Button) mView.findViewById(R.id.btnSelectImage);
            btnSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });

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
