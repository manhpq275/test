package asia.ienter.matching.models;

import java.util.ArrayList;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public class SettingUserView extends BaseView {


    public int getMaxLike() {
        return maxLike;
    }

    public void setMaxLike(int maxLike) {
        this.maxLike = maxLike;
    }

    public int getMaxSpecialLike() {
        return maxSpecialLike;
    }

    public void setMaxSpecialLike(int maxSpecialLike) {
        this.maxSpecialLike = maxSpecialLike;
    }

    public ArrayList<Integer> getBlockListID() {
        return blockListID;
    }

    public void setBlockListID(ArrayList<Integer> blockListID) {
        this.blockListID = blockListID;
    }

    public boolean isActiveYearsOld() {
        return isActiveYearsOld;
    }

    public void setActiveYearsOld(boolean activeYearsOld) {
        isActiveYearsOld = activeYearsOld;
    }

    public boolean isGetEmail() {
        return isGetEmail;
    }

    public void setGetEmail(boolean getEmail) {
        isGetEmail = getEmail;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    private int maxLike=0;
    private int maxSpecialLike=0;
    private ArrayList<Integer> blockListID = new ArrayList<>();
    private boolean isActiveYearsOld = false;
    private boolean isGetEmail = false;
    private boolean isAnonymous = false;


    public SettingUserView(){
        maxLike=0;
        maxSpecialLike=0;
        blockListID = new ArrayList<>();
        isActiveYearsOld = false;
        isGetEmail = false;
        isAnonymous = false;
    }


}
