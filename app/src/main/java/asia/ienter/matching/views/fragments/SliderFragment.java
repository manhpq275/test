package asia.ienter.matching.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import asia.ienter.matching.R;

/**
 * Created by hoangtuan on 9/6/16.
 */
public class SliderFragment extends Fragment {
    private View mMainView;
    private int position = 0;
    public static SliderFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("Position", position);
        SliderFragment fragment = new SliderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle!=null){
            position = bundle.getInt("Position", 0);
        }
        if(position!=0) {
            mMainView = inflater.inflate(R.layout.fragment_slider, null);
        }else{
            mMainView = inflater.inflate(R.layout.fragment_slider2, null);
        }
        buildView();
        return mMainView;
    }

    private void buildView() {
        if(isAdded()){

            setBackground();
        }
    }

    private void setBackground() {
        LinearLayout layoutContent = (LinearLayout) mMainView.findViewById(R.id.layoutContent);
        ImageView imageMain = (ImageView) mMainView.findViewById(R.id.imgMainSlide);
        TextView txtComment = (TextView) mMainView.findViewById(R.id.txtTextSlider);
        switch (position){
            case 0:
                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen1));
//                imageMain.setImageResource(R.drawable.ic_movie);
                txtComment.setText("Cùng nhau trò chuyện trong ứng dụng ");
                break;
            case 1:
                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen2));
                imageMain.setImageResource(R.drawable.ic_car_travel);
                txtComment.setText("Cùng nhau đi du lịch khắp nơi ");
                break;
            case 2:
                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen3));
                imageMain.setImageResource(R.drawable.ic_eat_food);
                txtComment.setText("Cùng ăn bữa ăn thân mật");
                break;
            case 3:
                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen4));
                imageMain.setImageResource(R.drawable.ic_bottle_wine);
                txtComment.setText("Cùng nhau thưởng thức đồ uống");
                break;
            default:
                break;
        }
    }
}
