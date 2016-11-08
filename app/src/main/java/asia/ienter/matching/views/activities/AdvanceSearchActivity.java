package asia.ienter.matching.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogListCallBack;
import asia.ienter.matching.models.AdvanceSearchView;
import asia.ienter.matching.models.enums.BloodGroup;
import asia.ienter.matching.models.enums.Externality;
import asia.ienter.matching.models.enums.Level;
import asia.ienter.matching.models.enums.MarriedHistory;
import asia.ienter.matching.models.enums.MarryTime;
import asia.ienter.matching.models.enums.Regions;
import asia.ienter.matching.models.enums.SmokeWine;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.views.dialogs.DialogList;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 9/20/16.
 */
public class AdvanceSearchActivity extends AppCompatActivity {

    View pauseView;
    AdvanceSearchView advanceSearchView;
    @InjectView(R.id.tvAddress)   TextView tvAddress;
    @InjectView(R.id.tvHomeLand)   TextView tvHomeLand;
    @InjectView(R.id.tvExternality)   TextView tvExternality;
    @InjectView(R.id.tvBloodGroup)   TextView tvBloodGroup;
    @InjectView(R.id.tvLevel)   TextView tvLevel;
    @InjectView(R.id.tvSmoke)   TextView tvSmoke;
    @InjectView(R.id.tvDrinkWine)   TextView tvDrinkWine;
    @InjectView(R.id.tvMarryTime)   TextView tvMarryTime;
    @InjectView(R.id.tvMarriedHistory)   TextView tvMarriedHistory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_advance_search);
        advanceSearchView = MCApp.getAdvanceSearchView();
        pauseView = findViewById(R.id.PauseView);
        ButterKnife.inject(this);

    }

    @Override
    protected void onResume() {
        this.pauseView.setVisibility(View.GONE);
        super.onResume();
    }

    @Override
    protected void onPause() {
        MCApp.setAdvanceSearchView(advanceSearchView);
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }


    private void showDialogChangePeople(final int idLayout) {
        new MaterialDialog.Builder(this)
                .title("Change number")
                .items(R.array.change_number_people)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        /**
                         * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected radio button to actually be selected.
                         **/
                        ((TextView) findViewById(idLayout)).setText(text + " >");
                        return true;
                    }
                })
                .positiveText("OK")
                .show();
    }

    @OnClick(R.id.tvAddress)
    public void onClickAddress() {
        Regions regions[] = Regions.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[regions.length];
        for (Regions region : regions) {
            listItems[i] = region.toString();
            i++;
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int selectedItem =0;
        if (advanceSearchView != null) {
            selectedItem= advanceSearchView.getAddress();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
                advanceSearchView.setAddress(advanceSearchView.getAddress());
            }

            @Override
            public void onClickItem(int position) {
                advanceSearchView.setAddress(position);
            }

            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                tvAddress.setText(Regions.fromInteger(advanceSearchView.getAddress()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_address), listItems);
    }

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

    @OnClick(R.id.tvExternality)
    public void onClickExternality() {
        Externality externalities[] = Externality.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[externalities.length];
        for (Externality externality : externalities) {
            listItems[i] = externality.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int selectedItem =0;
        if (advanceSearchView != null) {
            selectedItem= advanceSearchView.getExternality();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
                advanceSearchView.setExternality(advanceSearchView.getExternality());
            }

            @Override
            public void onClickItem(int position) {
                advanceSearchView.setExternality(position);
            }

            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                tvExternality.setText(Externality.fromInteger(advanceSearchView.getExternality()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_externality), listItems);
    }

    @OnClick(R.id.tvBloodGroup)
    public void onClickBloodGroup() {
        BloodGroup bloodGroups[] = BloodGroup.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[bloodGroups.length];
        for (BloodGroup bloodGroup : bloodGroups) {
            listItems[i] = bloodGroup.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int selectedItem =0;
        if (advanceSearchView != null) {
            selectedItem= advanceSearchView.getBloodGroup();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
                advanceSearchView.setBloodGroup(advanceSearchView.getBloodGroup());
            }

            @Override
            public void onClickItem(int position) {
                advanceSearchView.setBloodGroup(position);
            }

            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                tvBloodGroup.setText(BloodGroup.fromInteger(advanceSearchView.getBloodGroup()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_blood_group), listItems);
    }

    @OnClick(R.id.tvLevel)
    public void onClickLevel() {
        Level levels[] = Level.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[levels.length];
        for (Level level : levels) {
            listItems[i] = level.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int selectedItem =0;
        if (advanceSearchView != null) {
            selectedItem= advanceSearchView.getLevel();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
                advanceSearchView.setLevel(advanceSearchView.getLevel());
            }

            @Override
            public void onClickItem(int position) {
                advanceSearchView.setLevel(position);
            }

            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                tvLevel.setText(Level.fromInteger(advanceSearchView.getLevel()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_level), listItems);
    }

    @OnClick(R.id.tvSmoke)
    public void onClickSmoke() {
        SmokeWine smokeWines[] = SmokeWine.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[smokeWines.length];
        for (SmokeWine smokeWine : smokeWines) {
            listItems[i] = smokeWine.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int selectedItem =0;
        if (advanceSearchView != null) {
            selectedItem= advanceSearchView.getSmoke();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
                advanceSearchView.setSmoke(advanceSearchView.getSmoke());
            }

            @Override
            public void onClickItem(int position) {
                advanceSearchView.setSmoke(position);
            }

            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                tvSmoke.setText(SmokeWine.fromInteger(advanceSearchView.getSmoke()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_is_smoke), listItems);
    }

    @OnClick(R.id.tvDrinkWine)
    public void onClickDrinkWine() {
        MarryTime smokeWines[] = MarryTime.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[smokeWines.length];
        for (MarryTime smokeWine : smokeWines) {
            listItems[i] = smokeWine.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int selectedItem =0;
        if (advanceSearchView != null) {
            selectedItem= advanceSearchView.getMinMarryTime();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
                advanceSearchView.setMinMarryTime(advanceSearchView.getMinMarryTime());
            }

            @Override
            public void onClickItem(int position) {
                advanceSearchView.setMinMarryTime(position);
            }

            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                tvDrinkWine.setText(MarryTime.fromInteger(advanceSearchView.getDrinkWine()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_marry_time), listItems);
    }

    @OnClick(R.id.tvMarryTime)
    public void onClickMarryTime() {
        MarryTime marryTimes[] = MarryTime.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[marryTimes.length];
        for (MarryTime marryTime : marryTimes) {
            listItems[i] = marryTime.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int selectedItem =0;
        if (advanceSearchView != null) {
            selectedItem= advanceSearchView.getMinMarryTime();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
                advanceSearchView.setMinMarryTime(advanceSearchView.getMinMarryTime());
            }

            @Override
            public void onClickItem(int position) {
                advanceSearchView.setMinMarryTime(position);
            }

            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                tvMarryTime.setText(MarryTime.fromInteger(advanceSearchView.getMinMarryTime()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_marry_time), listItems);
    }

    @OnClick(R.id.tvMarriedHistory)
    public void onClickMarriedHistory() {
        MarriedHistory marriedHistorys[] = MarriedHistory.class.getEnumConstants();
        int i = 0;
        final String listItems[] = new String[marriedHistorys.length];
        for (MarriedHistory marriedHistory : marriedHistorys) {
            listItems[i] = marriedHistory.toString();
            MLog.d(DialogList.class, "" + listItems[i]);
            i++;
        }
        this.pauseView.setVisibility(View.VISIBLE);
        int selectedItem =0;
        if (advanceSearchView != null) {
            selectedItem= advanceSearchView.getMarriageHistory();
        }
        DialogList dialogList = new DialogList(this,selectedItem, new IDialogListCallBack() {
            @Override
            public void onClickBack() {
                pauseView.setVisibility(View.GONE);
                advanceSearchView.setMarriageHistory(advanceSearchView.getMarriageHistory());
            }

            @Override
            public void onClickItem(int position) {
                advanceSearchView.setMarriageHistory(position);
            }

            @Override
            public void onClickDone() {
                pauseView.setVisibility(View.GONE);
                tvMarriedHistory.setText(MarriedHistory.fromInteger(advanceSearchView.getMarriageHistory()).toString());
            }
        });
        dialogList.show(getString(R.string.txt_marriage_history), listItems);
    }


}
