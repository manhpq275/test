package asia.ienter.matching.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import asia.ienter.matching.views.fragments.SliderFragment;


/**
 * Created by hoangtuan on 9/6/16.
 */
public class LoginSliderAdapter extends FragmentStatePagerAdapter {
    private int NUMBER_DAY_OF_WEAK = 4;

    public LoginSliderAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return SliderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return NUMBER_DAY_OF_WEAK;
    }


    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}