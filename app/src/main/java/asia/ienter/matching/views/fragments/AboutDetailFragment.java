package asia.ienter.matching.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import asia.ienter.matching.R;
import asia.ienter.matching.utils.SharedPreference;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        ButterKnife.inject(this, mView);
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

    @OnClick(R.id.btnBackFragment)
    public void onClickBackActivity() {
        getActivity().onBackPressed();

    }

    @Override
    protected void initView() {
        if(position == 4){
            final RatingBar ratingApp = (RatingBar) mView.findViewById(R.id.ratingBarApplication);
            ratingApp.setRating(SharedPreference.getInstance().getFloat("RatingApp", 0f));
//            ratingApp.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//                @Override
//                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                    SharedPreference.getInstance().putFloat("RatingApp", rating);
//                }
//            });

            Button btnConfirm = (Button) mView.findViewById(R.id.btnConfirm);
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreference.getInstance().putFloat("RatingApp", ratingApp.getRating());
                    getActivity().onBackPressed();
                }
            });
        }
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