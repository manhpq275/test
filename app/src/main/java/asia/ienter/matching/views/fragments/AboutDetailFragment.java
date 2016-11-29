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

    private int position;

    public static AboutDetailFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("AboutPosition",position);
        AboutDetailFragment fragment = new AboutDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle!=null){
            position = bundle.getInt("AboutPosition");
        }
        handlePositionAbout(inflater, position, container);
        //mView = inflater.inflate(R.layout.fragment_about_detail, container, false);
        mContext = this.getContext();
        initView();
        return mView;
    }

    private View handlePositionAbout(LayoutInflater inflater, int position, ViewGroup container) {
        switch (position){
            case 0:
                mView = inflater.inflate(R.layout.fragment_about_detail_0, container, false);
                break;
            case 1:
                mView = inflater.inflate(R.layout.fragment_about_detail_1, container, false);
                break;
            case 2:
                mView = inflater.inflate(R.layout.fragment_about_detail_2, container, false);
                break;
            case 3:
                mView = inflater.inflate(R.layout.fragment_about_detail_3, container, false);
                break;
            case 4:
                mView = inflater.inflate(R.layout.fragment_about_detail_4, container, false);
                break;
            case 5:
                mView = inflater.inflate(R.layout.fragment_about_detail_5, container, false);
                break;
            default:
                break;
        }
        return null;
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

//
//- Cam kết án toàn của các cuộc gặp gỡ.
//- Cam kết độ tin cậy và an ninh.
//- Vấn đề cấm.
//- Thông báo về app.
//- Đánh giá app.
//- Cách tăng khả năng cho các cuộc gặp gỡ.