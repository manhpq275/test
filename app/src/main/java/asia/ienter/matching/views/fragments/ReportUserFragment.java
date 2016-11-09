package asia.ienter.matching.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import asia.ienter.matching.R;

/**
 * Created by hoangtuan on 10/5/16.
 */
public class ReportUserFragment extends BaseFragment {

    public static ReportUserFragment newInstance() {

        Bundle args = new Bundle();

        ReportUserFragment fragment = new ReportUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_report_user, container, false);
        mContext = getContext();
        initView();
        return mView;
    }

    @Override
    protected void initView() {
        if(isAdded()) {
            Button confirmReport = (Button) mView.findViewById(R.id.btnConfirmReport);
            confirmReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDiaLogConfirmReport();
                }
            });

            Button backButton = (Button) mView.findViewById(R.id.btnAboutApp);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }
    }

    private void showDiaLogConfirmReport() {
        new MaterialDialog.Builder(getActivity())
                .title("Report this user")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Toast.makeText(getActivity(), "Report user", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                }).show();
    }

    @Override
    protected void loadDataFromApi() {

    }
}
