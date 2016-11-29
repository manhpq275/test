package asia.ienter.matching.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import asia.ienter.matching.views.fragments.ProfileFragment;

/**
 * Created by hoangtuan on 11/21/16.
 */
public class MatchingProfileAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> items = new ArrayList<>();

    public MatchingProfileAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return ProfileFragment.newInstance(false);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 6;
    }
}

