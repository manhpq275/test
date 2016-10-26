package asia.ienter.matching.views.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.SharedPreference;
import asia.ienter.matching.views.activities.HomeActivity;
import asia.ienter.matching.views.activities.LoginActivity;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/30/16.
 */
public class ChangeAboutMeFragment extends BaseFragment {
    private Button btnSelectImage;
    private EditText edtChangeAbout;

    public static ChangeAboutMeFragment newInstance() {

        Bundle args = new Bundle();

        ChangeAboutMeFragment fragment = new ChangeAboutMeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_change_about, container, false);
        mContext = this.getContext();
        initView();
        return mView;
    }

    @Override
    protected void initView() {
        if(isAdded()) {
            edtChangeAbout = (EditText) mView.findViewById(R.id.edtAbout);
            btnSelectImage = (Button) mView.findViewById(R.id.btnSaveAbout);
            btnSelectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edtChangeAbout.clearFocus();
                    ((HomeActivity)getActivity()).changeInformation(edtChangeAbout.getText().toString());
                    getActivity().onBackPressed();
                }
            });

            Button backButton = (Button) mView.findViewById(R.id.btnBack);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edtChangeAbout.clearFocus();
                    getActivity().onBackPressed();
                }
            });
        }
    }

    @Override
    protected void loadDataFromApi() {

    }

}
