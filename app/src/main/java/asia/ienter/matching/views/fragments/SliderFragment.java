package asia.ienter.matching.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        mMainView = inflater.inflate(R.layout.fragment_slider, null);
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
//                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen1));
                imageMain.setImageResource(R.mipmap.img_slider1);
//                txtComment.setText("Cùng nhau trò chuyện trong ứng dụng ");
                txtComment.setText("");
                break;
            case 1:
//                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen1));
                imageMain.setImageResource(R.mipmap.img_slider2);
//                txtComment.setText("Cùng nhau trò chuyện trong ứng dụng ");
                txtComment.setText("");
//                txtComment.setText("Cùng nhau đi du lịch khắp nơi ");
                break;
            case 2:
                //                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen1));
                imageMain.setImageResource(R.mipmap.img_slider3);
//                txtComment.setText("Cùng nhau trò chuyện trong ứng dụng ");
                txtComment.setText("");
//                txtComment.setText("Cùng ăn bữa ăn thân mật");
                break;
            case 3:
                //                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen1));
                imageMain.setImageResource(R.mipmap.img_slider4);
//                txtComment.setText("Cùng nhau trò chuyện trong ứng dụng ");
                txtComment.setText("");
               // txtComment.setText("Cùng nhau thưởng thức đồ uống");
                break;
            case 4:
                //                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen1));
                imageMain.setImageResource(R.mipmap.img_slider5);
//                txtComment.setText("Cùng nhau trò chuyện trong ứng dụng ");
                txtComment.setText("");
                // txtComment.setText("Cùng nhau thưởng thức đồ uống");
                break;
            case 5:
                //                layoutContent.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.bg_screen1));
                imageMain.setImageResource(R.mipmap.img_slider6);
//                txtComment.setText("Cùng nhau trò chuyện trong ứng dụng ");
                txtComment.setText("");
                // txtComment.setText("Cùng nhau thưởng thức đồ uống");
                break;
            default:
                break;
        }
    }
}
