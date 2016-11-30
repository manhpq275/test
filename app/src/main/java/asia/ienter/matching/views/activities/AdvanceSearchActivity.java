package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogListCallBack;
import asia.ienter.matching.interfaces.IDialogListMultipleCallBack;
import asia.ienter.matching.models.AdvanceSearchView;
import asia.ienter.matching.models.enums.Regions;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.views.dialogs.DialogList;
import asia.ienter.matching.views.dialogs.DialogWheel;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/20/16.
 */
public class AdvanceSearchActivity extends AppCompatActivity {

    View pauseView;
    AdvanceSearchView advanceSearchView;
    //@InjectView(R.id.tvAddress)         TextView tvAddress;
    @InjectView(R.id.tvHomeLand)        TextView tvHomeLand;
    @InjectView(R.id.tvYearsOld)        TextView tvYearsOld;
    @InjectView(R.id.cbAvatar)          CheckBox cbAvatar;
    @InjectView(R.id.tvHeight)          TextView tvHeight;
    @InjectView(R.id.tvWeight)          TextView tvWeight;

//    @InjectView(R.id.tvExternality)     TextView tvExternality;
//    @InjectView(R.id.tvBloodGroup)      TextView tvBloodGroup;
//    @InjectView(R.id.tvLevel)           TextView tvLevel;
//    @InjectView(R.id.tvSmoke)           TextView tvSmoke;
//    @InjectView(R.id.tvDrinkWine)       TextView tvDrinkWine;
//    @InjectView(R.id.tvMarryTime)       TextView tvMarryTime;
//    @InjectView(R.id.tvMarriedHistory)  TextView tvMarriedHistory;
//    @InjectView(R.id.tvChildren)        TextView tvChildren;
//    @InjectView(R.id.tvMatchingPay)     TextView tvMatchingPay;
//    @InjectView(R.id.tvLanguages)       TextView tvLanguages;
//    @InjectView(R.id.tvOnlineAgo)       TextView tvOnlineAgo;
//    @InjectView(R.id.cbDescription)     CheckBox cbDescription;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_advance_search);
        advanceSearchView = MCApp.getAdvanceSearchView();
        pauseView = findViewById(R.id.PauseView);
        ButterKnife.inject(this);
        initView();
        loadData();
    }

    private void initView(){
    }


    private void loadData(){
        cbAvatar.setChecked(advanceSearchView.isAvatar());
       // tvAddress.setText(Regions.fromInteger(advanceSearchView.getAddress()).toString());
        String tmpHeight = (advanceSearchView.getMaxHeight()==AdvanceSearchView.DEFAULT_HEIGHT) ? getString(R.string.txtAll) : advanceSearchView.getMinHeight()+"-"+advanceSearchView.getMaxHeight()+" "+getString(R.string.txt_cm);
        String tmpWeight = (advanceSearchView.getMaxWeight()==AdvanceSearchView.DEFAULT_WEIGHT) ? getString(R.string.txtAll) : advanceSearchView.getMinWeight()+"-"+advanceSearchView.getMaxWeight()+" "+getString(R.string.txtKg);
        String tmpYearsOld = (advanceSearchView.getMaxYearOld()==AdvanceSearchView.DEFAULT_YEARS_OLD) ? getString(R.string.txtAll) : advanceSearchView.getMinYearOld()+"-"+advanceSearchView.getMaxYearOld()+" "+getString(R.string.txtTuoi);
        tvHomeLand.setText(Regions.fromInteger(advanceSearchView.getHomeLand()).toString());
        tvHeight.setText(tmpHeight);
        tvWeight.setText(tmpWeight);
        tvYearsOld.setText(tmpYearsOld);

//        cbDescription.setChecked(advanceSearchView.isDescription());
//        tvExternality.setText(Externality.fromInteger(advanceSearchView.getExternality()).toString());
//        tvBloodGroup.setText(BloodGroup.fromInteger(advanceSearchView.getBloodGroup()).toString());
//        tvLevel.setText(Level.fromInteger(advanceSearchView.getLevel()).toString());
//        tvSmoke.setText(SmokeWine.fromInteger(advanceSearchView.getSmoke()).toString());
//        tvDrinkWine.setText(SmokeWine.fromInteger(advanceSearchView.getDrinkWine()).toString());
//        tvMarryTime.setText(MarryTime.fromInteger(advanceSearchView.getMinMarryTime()).toString());
//        tvMarriedHistory.setText(MarriedHistory.fromInteger(advanceSearchView.getMarriageHistory()).toString());
//        tvChildren.setText(Children.fromInteger(advanceSearchView.getChildren()).toString());
//        tvMatchingPay.setText(LoveCost.fromInteger(advanceSearchView.getMatchingPay()).toString());
//        String tmp="";
//        MLog.d(AdvanceSearchActivity.class,"click Done() = "+tmp);
//
//        for(int i=0;i<advanceSearchView.getLanguages().size();i++){
//            tmp += Languages.fromInteger(advanceSearchView.getLanguages().get(i)).toString() + "\n";
//        }
//        tvLanguages.setText(tmp);
    }

    @Override
    protected void onResume() {
        this.pauseView.setVisibility(View.GONE);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }

//    @OnClick(R.id.tvAddress)
//    public void onClickAddress() {
//        Regions regions[] = Regions.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[regions.length];
//        for (Regions region : regions) {
//            listItems[i] = region.toString();
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getAddress();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setAddress(advanceSearchView.getAddress());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setAddress(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvAddress.setText(Regions.fromInteger(advanceSearchView.getAddress()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_address), listItems);
//    }

    @OnClick(R.id.tvHomeLand)
    public void onClickHomeLand() {
        Regions regions[] = Regions.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[regions.length];
        for (Regions region : regions) {
            listItems[i] = region.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int selectedItem =0;
        if (advanceSearchView != null) {
            selectedItem= advanceSearchView.getHomeLand();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
                advanceSearchView.setHomeLand(advanceSearchView.getHomeLand());
            }

            @Override
            public void onClickItem(int position) {
                advanceSearchView.setHomeLand(position);
            }

            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                tvHomeLand.setText(Regions.fromInteger(advanceSearchView.getHomeLand()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_homeland), listItems);
    }

    @OnClick(R.id.cbAvatar)
    public void onClickCBAvatar(){
        advanceSearchView.setAvatar(cbAvatar.isChecked());
    }

    @OnClick(R.id.layoutBackActivity)
    public void onClickBack(){
        onBackPressed();
    }

    @OnClick(R.id.btnMatching)
    public void onClickMatching(){
        MCApp.setAdvanceSearchView(advanceSearchView);
        setResult(AppConstants.BACK_FROM_ADVANCE_SEARCH_TO_HOME_RESULT_MATCHING);
        onBackPressed();
    }

    @OnClick(R.id.btnReset)
    public void onClickReset(){
        advanceSearchView = new AdvanceSearchView();
        setResult(AppConstants.BACK_FROM_ADVANCE_SEARCH_TO_HOME_RESULT_BACK);
        loadData();
    }

    @OnClick(R.id.lnHeight)
    public void onClickHeight(){
        final List<Integer> listItems = new ArrayList<>();
        for(int i=100;i<=230;i++){
            listItems.add(i);
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int minHeight=0,maxHeight=0 ;
        if (advanceSearchView != null) {
            minHeight= advanceSearchView.getMinHeight();
            maxHeight= advanceSearchView.getMaxHeight();
        }
        DialogWheel dialogList = new DialogWheel(this,minHeight,maxHeight, new IDialogListMultipleCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
            }

            @Override
            public void onClickItem(ArrayList<Integer> position) {
                advanceSearchView.setMinHeight(position.get(0));
                advanceSearchView.setMaxHeight(position.get(1));
            }


            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                String tmpHeight = (advanceSearchView.getMaxHeight()==AdvanceSearchView.DEFAULT_HEIGHT) ? getString(R.string.txtAll) : advanceSearchView.getMinHeight()+"-"+advanceSearchView.getMaxHeight()+" "+getString(R.string.txt_cm);

                tvHeight.setText(tmpHeight);
            }
        });
        dialogList.show(getString(R.string.txt_height), listItems);
    }

    @OnClick(R.id.lnYearsOld)
    public void onClickYearsOld(){
        final List<Integer> listItems = new ArrayList<>();
        for(int i=15;i<=80;i++){
            listItems.add(i);
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int minYearOld=0,maxYearOld=0 ;
        if (advanceSearchView != null) {
            minYearOld= advanceSearchView.getMinYearOld();
            maxYearOld= advanceSearchView.getMaxYearOld();
        }
        DialogWheel dialogList = new DialogWheel(this,minYearOld,maxYearOld, new IDialogListMultipleCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
            }

            @Override
            public void onClickItem(ArrayList<Integer> position) {
                advanceSearchView.setMaxYearOld(position.get(1));
                advanceSearchView.setMinYearOld(position.get(0));
            }


            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                String tmpYearsOld = (advanceSearchView.getMaxYearOld()==AdvanceSearchView.DEFAULT_YEARS_OLD) ? getString(R.string.txtAll) : advanceSearchView.getMinYearOld()+"-"+advanceSearchView.getMaxYearOld()+" "+getString(R.string.txtTuoi);

                tvYearsOld.setText(tmpYearsOld);
            }
        });
        dialogList.show(getString(R.string.txt_years_old), listItems);
    }



    @OnClick(R.id.lnWeight)
    public void onClickWeight(){
        final List<Integer> listItems = new ArrayList<>();
        for(int i=30;i<=100;i++){
            listItems.add(i);
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int minWeight=0,maxWeight=0 ;
        if (advanceSearchView != null) {
            minWeight= advanceSearchView.getMinWeight();
            maxWeight= advanceSearchView.getMaxWeight();
        }
        DialogWheel dialogList = new DialogWheel(this,minWeight,maxWeight, new IDialogListMultipleCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
            }

            @Override
            public void onClickItem(ArrayList<Integer> position) {
                advanceSearchView.setMaxWeight(position.get(1));
                advanceSearchView.setMinWeight(position.get(0));
            }


            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                String tmpWeight = (advanceSearchView.getMaxWeight()==AdvanceSearchView.DEFAULT_WEIGHT) ? getString(R.string.txtAll) : advanceSearchView.getMinWeight()+"-"+advanceSearchView.getMaxWeight()+" "+getString(R.string.txtKg);

                tvWeight.setText(tmpWeight);
            }
        });
        dialogList.show(getString(R.string.txt_weight), listItems);
    }


//    @OnClick(R.id.tvExternality)
//    public void onClickExternality() {
//        Externality externalities[] = Externality.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[externalities.length];
//        for (Externality externality : externalities) {
//            listItems[i] = externality.toString();
//            MLog.d(DialogList.class, "" + listItems[i]);
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getExternality();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setExternality(advanceSearchView.getExternality());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setExternality(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvExternality.setText(Externality.fromInteger(advanceSearchView.getExternality()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_externality), listItems);
//    }
//
//    @OnClick(R.id.tvBloodGroup)
//    public void onClickBloodGroup() {
//        BloodGroup bloodGroups[] = BloodGroup.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[bloodGroups.length];
//        for (BloodGroup bloodGroup : bloodGroups) {
//            listItems[i] = bloodGroup.toString();
//            MLog.d(DialogList.class, "" + listItems[i]);
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getBloodGroup();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setBloodGroup(advanceSearchView.getBloodGroup());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setBloodGroup(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvBloodGroup.setText(BloodGroup.fromInteger(advanceSearchView.getBloodGroup()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_blood_group), listItems);
//    }
//
//    @OnClick(R.id.tvLevel)
//    public void onClickLevel() {
//        Level levels[] = Level.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[levels.length];
//        for (Level level : levels) {
//            listItems[i] = level.toString();
//            MLog.d(DialogList.class, "" + listItems[i]);
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getLevel();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setLevel(advanceSearchView.getLevel());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setLevel(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvLevel.setText(Level.fromInteger(advanceSearchView.getLevel()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_level), listItems);
//    }
//
//    @OnClick(R.id.tvSmoke)
//    public void onClickSmoke() {
//        SmokeWine smokeWines[] = SmokeWine.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[smokeWines.length];
//        for (SmokeWine smokeWine : smokeWines) {
//            listItems[i] = smokeWine.toString();
//            MLog.d(DialogList.class, "" + listItems[i]);
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getSmoke();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setSmoke(advanceSearchView.getSmoke());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setSmoke(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvSmoke.setText(SmokeWine.fromInteger(advanceSearchView.getSmoke()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_is_smoke), listItems);
//    }
//
//    @OnClick(R.id.tvDrinkWine)
//    public void onClickDrinkWine() {
//        SmokeWine smokeWines[] = SmokeWine.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[smokeWines.length];
//        for (SmokeWine smokeWine : smokeWines) {
//            listItems[i] = smokeWine.toString();
//            MLog.d(DialogList.class, "" + listItems[i]);
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getSmoke();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setSmoke(advanceSearchView.getSmoke());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setSmoke(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvDrinkWine.setText(SmokeWine.fromInteger(advanceSearchView.getSmoke()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_drink_wine), listItems);
//    }
//
//    @OnClick(R.id.tvMarryTime)
//    public void onClickMarryTime() {
//        MarryTime marryTimes[] = MarryTime.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[marryTimes.length];
//        for (MarryTime marryTime : marryTimes) {
//            listItems[i] = marryTime.toString();
//            MLog.d(DialogList.class, "" + listItems[i]);
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getMinMarryTime();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setMinMarryTime(advanceSearchView.getMinMarryTime());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setMinMarryTime(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvMarryTime.setText(MarryTime.fromInteger(advanceSearchView.getMinMarryTime()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_marry_time), listItems);
//    }
//
//    @OnClick(R.id.tvMarriedHistory)
//    public void onClickMarriedHistory() {
//        MarriedHistory marriedHistorys[] = MarriedHistory.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[marriedHistorys.length];
//        for (MarriedHistory marriedHistory : marriedHistorys) {
//            listItems[i] = marriedHistory.toString();
//            MLog.d(DialogList.class, "" + listItems[i]);
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getMarriageHistory();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setMarriageHistory(advanceSearchView.getMarriageHistory());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setMarriageHistory(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvMarriedHistory.setText(MarriedHistory.fromInteger(advanceSearchView.getMarriageHistory()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_marriage_history), listItems);
//    }
//
//    @OnClick(R.id.tvChildren)
//    public void onClickChildren() {
//        Children childrens[] = Children.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[childrens.length];
//        for (Children children : childrens) {
//            listItems[i] = children.toString();
//            MLog.d(DialogList.class, "" + listItems[i]);
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getChildren();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setChildren(advanceSearchView.getChildren());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setChildren(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvChildren.setText(Children.fromInteger(advanceSearchView.getChildren()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_is_children), listItems);
//    }
//
//    @OnClick(R.id.tvMatchingPay)
//    public void onClickMatchingPay() {
//        LoveCost loveCosts[] = LoveCost.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[loveCosts.length];
//        for (LoveCost loveCost : loveCosts) {
//            listItems[i] = loveCost.toString();
//            MLog.d(DialogList.class, "" + listItems[i]);
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getMatchingPay();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setMatchingPay(advanceSearchView.getMatchingPay());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setMatchingPay(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvMatchingPay.setText(LoveCost.fromInteger(advanceSearchView.getMatchingPay()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_who_pay_matching), listItems);
//    }


//
//    @OnClick(R.id.cbDescription)
//    public void onClickCBDescription(){
//        advanceSearchView.setDescription(cbDescription.isChecked());
//    }
//
//    @OnClick(R.id.tvLanguages)
//    public void onClickLanguage() {
//        Languages languages[] = Languages.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[languages.length];
//        for (Languages language : languages) {
//            listItems[i] = language.toString();
//            i++;
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        ArrayList<Integer> selectedItem = null;
//        if (advanceSearchView != null) {
//            selectedItem= advanceSearchView.getLanguages();
//        }
//        if(selectedItem == null){
//            selectedItem = new ArrayList<>();
//            selectedItem.add(Languages.All.getValue());
//        }
//        DialogListMultiple dialogList = new DialogListMultiple(this,selectedItem, new IDialogListMultipleCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setLanguages(advanceSearchView.getLanguages());
//            }
//
//            @Override
//            public void onClickItem(ArrayList<Integer> position) {
//
//                boolean isAll=false;
//                for(int i=0;i<position.size();i++){
//                    if(Languages.fromInteger(position.get(i))==Languages.All){
//                        isAll = true;
//                        position.clear();
//                        break;
//                    }
//                }
//                if(isAll || (position.size() ==0)){
//                    position.add(Languages.All.getValue());
//                    advanceSearchView.setLanguages(position);
//                }
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                String tmp="";
//                MLog.d(AdvanceSearchActivity.class,"click Done() = "+tmp);
//
//                for(int i=0;i<advanceSearchView.getLanguages().size();i++){
//                    tmp += Languages.fromInteger(advanceSearchView.getLanguages().get(i)).toString() + "\n";
//                }
//                tvLanguages.setText(tmp);
//            }
//        });
//        dialogList.show(getString(R.string.txt_language), listItems);
//    }




//    @OnClick(R.id.tvOnlineAgo)
//    public void onClickOnlineAgo() {
//        final OnlineAgo onlineAgos[] = OnlineAgo.class.getEnumConstants();
//        int i = 0;
//        final String listItems[] = new String[onlineAgos.length];
//        for (OnlineAgo onlineAgo : onlineAgos) {
//            if(i==(onlineAgos.length-1)){
//                break;
//            }
//            listItems[i] = onlineAgo.toString();
//            i++;
//
//        }
//        this.pauseView.setVisibility(View.VISIBLE);
//        int selectedItem =0;
//        if (advanceSearchView != null) {
//
//                    selectedItem =advanceSearchView.getOnlineAgo();
//        }
//        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
//            @Override
//            public void onClickBack() {
//                pauseView.setVisibility(View.GONE);
//                advanceSearchView.setOnlineAgo(advanceSearchView.getOnlineAgo());
//            }
//
//            @Override
//            public void onClickItem(int position) {
//                advanceSearchView.setOnlineAgo(position);
//            }
//
//            @Override
//            public void onClickDone() {
//                pauseView.setVisibility(View.GONE);
//                tvOnlineAgo.setText(OnlineAgo.fromInteger(advanceSearchView.getOnlineAgo()).toString());
//            }
//        });
//        dialogList.show(getString(R.string.txt_online_ago), listItems);
//    }

}
