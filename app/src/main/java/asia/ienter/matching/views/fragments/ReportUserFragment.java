package asia.ienter.matching.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogListMultipleCallBack;
import asia.ienter.matching.models.enums.Languages;
import asia.ienter.matching.models.enums.Report;
import asia.ienter.matching.utils.Utils;
import asia.ienter.matching.utils.custom.TagGroup;
import asia.ienter.matching.views.dialogs.DialogListMultiple;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * Created by hoangtuan on 10/5/16.
 */
public class ReportUserFragment extends BaseFragment {
    @InjectView(R.id.tagGroupReport) TagGroup mTagReport;
    @InjectView(R.id.txtAddReport) TextView txtAddReport;
    @InjectView(R.id.edtMyOpinions) EditText edtOpinion;
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
        ButterKnife.inject(this, mView);
        mContext = getContext();
        initView();
        return mView;
    }

    @Override
    protected void initView() {
        if(isAdded()) {
            mTagReport.setOnTagClickListener(new TagGroup.OnTagClickListener() {
                @Override
                public void onTagClick(String tag) {
                    handleRemoveTag(tag);
                }
            });
        }
    }

    private void handleRemoveTag(String tag) {
        
    }

    @OnClick(R.id.txtAddReport)
    public void onClickAddReport(){
        Report reports[] = Report.class.getEnumConstants();
        int i = 0;

        final String listItems[] = new String[reports.length];
        for (Report genitive : reports) {
            listItems[i] = genitive.toString();
            i++;
        }
        ArrayList<Integer> selectedItem = new ArrayList<>();

        DialogListMultiple dialogList = new DialogListMultiple(mContext, selectedItem, new IDialogListMultipleCallBack() {
            ArrayList<Integer> listSelected = new ArrayList<>();

            @Override
            public void onClickBack() {

            }

            @Override
            public void onClickItem(ArrayList<Integer> position) {

                boolean isAll = false;
                for (int i = 0; i < position.size(); i++) {
                    if (Report.fromInteger(position.get(i)) == Report.All) {
                        isAll = true;
                        position.clear();
                        break;
                    }
                }
                if (isAll || (position.size() == 0)) {
                    position.add(Languages.All.getValue());
                }
                listSelected = position;
            }

            @Override
            public void onClickDone() {
                List<String> listLanguage = new ArrayList<>();
                for (int i = 0; i < listSelected.size(); i++) {
                    listLanguage.add(Report.fromInteger(listSelected.get(i)).toString());
                }
                mTagReport.setTags(listLanguage);
            }
        });
        dialogList.show(getString(R.string.txt_language), listItems);
    }

    @OnClick(R.id.btnBackReport)
    public void onClickBack(){
        getActivity().onBackPressed();
    }

    @OnClick(R.id.btnConfirmReport)
    public void onClickConfirmReport(){
        showDiaLogConfirmReport();
    }

    @OnTouch(R.id.edtMyOpinions)
    public boolean onClickChangeAbout(){
        if(edtOpinion.getText().length()==0) {
            final ScrollView scrollView = (ScrollView) mView.findViewById(R.id.layoutScrollView);
            final LinearLayout layoutBasic = (LinearLayout) mView.findViewById(R.id.layoutMyOpinion);
            Utils.scrollToView(scrollView, layoutBasic);
        }
        return false;
    }

    private void showDiaLogConfirmReport() {
        new MaterialDialog.Builder(getActivity())
                .title("Báo cáo vi phạm của người dùng")
                .positiveText("Đồng ý")
                .negativeText("Huỷ")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Toast.makeText(getActivity(), "Đã báo cáo vi phạm", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                }).show();
    }

    @Override
    protected void loadDataFromApi() {

    }
}
