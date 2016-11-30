package asia.ienter.matching.views.fragments;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.interfaces.IDialogListCallBack;
import asia.ienter.matching.interfaces.IDialogListMultipleCallBack;
import asia.ienter.matching.models.User;
import asia.ienter.matching.models.enums.BloodGroup;
import asia.ienter.matching.models.enums.DayOfWeek;
import asia.ienter.matching.models.enums.Externality;
import asia.ienter.matching.models.enums.Genitives;
import asia.ienter.matching.models.enums.Languages;
import asia.ienter.matching.models.enums.LoveCost;
import asia.ienter.matching.models.enums.MarryTime;
import asia.ienter.matching.models.enums.Regions;
import asia.ienter.matching.services.UserServices;
import asia.ienter.matching.utils.AppConstants;
import asia.ienter.matching.utils.MLog;
import asia.ienter.matching.utils.ReplaceFragment;
import asia.ienter.matching.utils.Utils;
import asia.ienter.matching.utils.custom.CircleImageView;
import asia.ienter.matching.utils.custom.EditTextBackEvent;
import asia.ienter.matching.utils.custom.TagGroup;
import asia.ienter.matching.views.activities.AboutActivity;
import asia.ienter.matching.views.activities.SettingActivity;
import asia.ienter.matching.views.dialogs.DialogFbPictureSelect;
import asia.ienter.matching.views.dialogs.DialogList;
import asia.ienter.matching.views.dialogs.DialogListMultiple;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * Created by hoangtuan on 9/15/16.
 */
public class ProfileFragment extends BaseFragment{
    private ReplaceFragment fragmentHandle;

    public final int CHANGE_PROFILE = 1;
    public final int CHANGE_BACKGROUND = 2;
    private int whatChangePicture = -1;
    private boolean isMainProfile = false;
    private User mainUser = MCApp.getUserInstance()==null?new User():MCApp.getUserInstance();
    private String defaultString = "";
    @InjectView(R.id.mSwipeRefresh)     SwipeRefreshLayout mSwipeRefresh;
    @InjectView(R.id.scrollViewMain)    ScrollView mScrollView;
    @InjectView(R.id.layoutUserAge)     LinearLayout layoutUserAge;
    @InjectView(R.id.layoutActionAbout) LinearLayout layoutActionAbout;
    @InjectView(R.id.layoutUserPlace)   LinearLayout layoutUserPlace;
    @InjectView(R.id.layoutAddPicture)  LinearLayout layoutAddPicture;
    @InjectView(R.id.layoutProductImage)LinearLayout pictureContent;
    @InjectView(R.id.imgBackGround)     ImageView changeCover;
    @InjectView(R.id.imgCgnBackground)  ImageView imgChangeCover;
    @InjectView(R.id.imgCgnProfilePic)  ImageView imgChangePicProfile;
    @InjectView(R.id.imgProfilePicture) CircleImageView profilePicture;
    @InjectView(R.id.txtHomeTown)       TextView txtHomeTown;
    @InjectView(R.id.txtUserName)       TextView txtUserName;
    @InjectView(R.id.txtUserGender)     TextView txtUserGender;
    @InjectView(R.id.txtUserAges)       TextView txtUserAges;
    @InjectView(R.id.txtUserPlace)      TextView txtUserPlace;
    @InjectView(R.id.txtIntroduce)      TextView txtUserAbout;
    @InjectView(R.id.txtBirthday)       TextView txtUserBirthday;
    @InjectView(R.id.txtWork)           TextView txtUserWork;
    @InjectView(R.id.txtAddress)        TextView txtUserAddress;
    @InjectView(R.id.txtBodyForm)       TextView txtBodyForm;
    @InjectView(R.id.txtBloodType)      TextView txtBloodType;
    @InjectView(R.id.txtUserHeight)     TextView txtUserHeight;
    @InjectView(R.id.txtChangeHobby)    TextView txtAddHobby;
    @InjectView(R.id.txtAddLanguage)    TextView txtAddLanguage;
    @InjectView(R.id.txtChangeGenitive) TextView txtAddGenitive;
    @InjectView(R.id.txtAddAttraction)  TextView txtAddAttraction;
    @InjectView(R.id.txtUserWeight)     TextView txtUserWeight;
    @InjectView(R.id.txtCompany)        TextView txtUserCompany;
    @InjectView(R.id.txtWorkingPlace)   TextView txtWorkingPlace;
    @InjectView(R.id.txtIncome)         TextView txtUserIncome;
    @InjectView(R.id.txtWhenMarriage)   TextView txtWhenMarriage;
    @InjectView(R.id.txtCostFirstDate)  TextView txtCostForFirstDate;
    @InjectView(R.id.txtWhoWillPay)     TextView txtWhoWillPay;
    @InjectView(R.id.txtWhoLivingWith)  TextView txtWhoLivingWith;
    @InjectView(R.id.txtDayOff)         TextView txtDayOff;
    @InjectView(R.id.edtChangeAbout)    EditTextBackEvent ediChangeAbout;
    @InjectView(R.id.chkDrinking)       CheckBox chkDrinking;
    @InjectView(R.id.chkSmoking)        CheckBox chkSmoking;
    @InjectView(R.id.chkCoupleDate)     CheckBox chkCoupleDate;
    @InjectView(R.id.chkHavingChilds)   CheckBox chkHavingChilds;
    @InjectView(R.id.tagGroupLanguages) TagGroup mTagGroupLanguages;
    @InjectView(R.id.tagGroupTypePeople)TagGroup mTagGroupTypePeople;
    @InjectView(R.id.tagGenitive)       TagGroup mTagGenitive;
    @InjectView(R.id.tagGroupHobby)     TagGroup mTagHobby;

    public static Uri imageUri;

    /**
     *
     * @param isMainProfile true if view on main user/ false if view on others
     * @return
     */
    public static ProfileFragment newInstance(boolean isMainProfile, String userId) {
        Bundle args = new Bundle();
        args.putBoolean("IsMainProfile", isMainProfile);
        args.putString("UserId", userId);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile_user, container, false);
        ButterKnife.inject(this, mView);
        mContext = this.getContext();
        initView();
        return mView;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        String userId = null;
        if(bundle!=null){
            isMainProfile = bundle.getBoolean("IsMainProfile");
            userId = bundle.getString("UserId");
            if(isMainProfile){
                defaultString = getActivity().getResources().getString(R.string.tap_to_change);
            }
        }

        if(isMainProfile) {
            buildViewShowDetail();
        }else{
            mSwipeRefresh.setRefreshing(true);
            handleVisibilyView();
            UserServices.getInstance().getUserInformation(userId, new UserServices.IGetUserInformationFromIDCallBack() {
                @Override
                public void onSuccess(User userInfomation) {
                    mainUser = userInfomation;
                    buildViewShowDetail();
                    mSwipeRefresh.setRefreshing(false);
                    mScrollView.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void buildViewShowDetail(){
        handleHeaderProfile();
        handleBasicInformation();
        handleOtherInformation();
        mSwipeRefresh.setEnabled(false);
    }

    /**
     * Xu ly profile xem o che do nao
     */
    private void handleVisibilyView() {
        mScrollView.setVisibility(View.INVISIBLE);
        imgChangeCover.setVisibility(View.GONE);
        imgChangePicProfile.setVisibility(View.GONE);
        layoutAddPicture.setVisibility(View.INVISIBLE);
        txtAddHobby.setVisibility(View.GONE);
        txtAddLanguage.setVisibility(View.GONE);
        txtAddGenitive.setVisibility(View.GONE);
        txtAddAttraction.setVisibility(View.GONE);
        chkDrinking.setEnabled(false);
        chkSmoking.setEnabled(false);
        chkCoupleDate.setEnabled(false);
        chkHavingChilds.setEnabled(false);
        ediChangeAbout.setEnabled(false);
        ediChangeAbout.setFocusable(false);
    }

    private void handleHeaderProfile() {

        if(!mainUser.getUserCover().isEmpty()) {
            Glide.with(mContext).load(mainUser.getUserCover()).asBitmap().into(changeCover);
        }
        if(!mainUser.getUserProfilePic().isEmpty()) {
            Glide.with(mContext).load(mainUser.getUserProfilePic()).asBitmap().into(profilePicture);
        }
        txtUserName.setText(mainUser.getUserName());
        txtUserGender.setText(mainUser.isGender() == 0 ? "Nu" : "Nam");
        if(!mainUser.getBirthday().isEmpty() && !mainUser.getBirthday().equals("NA")) {
            txtUserAges.setText(Utils.birthDayToYearsOld(mainUser.getBirthday()));
            layoutUserAge.setVisibility(View.VISIBLE);
        }else{
            layoutUserAge.setVisibility(View.GONE);
        }
        if(!mainUser.getHometown().isEmpty()) {
            layoutUserPlace.setVisibility(View.VISIBLE);
            String[] stringHometown = mainUser.getHometown().split(",");
            txtUserPlace.setText(stringHometown[0]);
        }else{
            layoutUserPlace.setVisibility(View.GONE);
        }
    }

    private void handleOtherInformation() {
        layoutAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatChangePicture = 0;
                showDialogSelectMedia(0);
            }
        });
        mTagGroupLanguages.setTags(mainUser.getLanguage());
        mTagGroupTypePeople.setTags(mainUser.getAttraction());
        mTagGenitive.setTags(mainUser.getGenitive());

        if(!mainUser.getHometown().isEmpty()){
            txtHomeTown.setText(mainUser.getHometown());
        }else{
            txtHomeTown.setText(defaultString);
        }
        if(mainUser.getHeight()!=User.INIT_VALUE){
            txtUserHeight.setText(mainUser.getHeight()+"cm");
        }else{
            txtUserHeight.setText(defaultString);
        }
        if(mainUser.getWeight()!=User.INIT_VALUE){
            txtUserWeight.setText(mainUser.getWeight()+"kg");
        }else{
            txtUserWeight.setText(defaultString);
        }
        if(!mainUser.getExtrovert().isEmpty()){
            txtBodyForm.setText(mainUser.getExtrovert());
        }else{
            txtBodyForm.setText(defaultString);
        }
        if(!mainUser.getBloodType().isEmpty()){
            txtBloodType.setText(mainUser.getBloodType());
        }else{
            txtBloodType.setText(defaultString);
        }
        if(!mainUser.getUserCompany().isEmpty()){
            txtUserCompany.setText(mainUser.getUserCompany());
        }else{
            txtUserCompany.setText(defaultString);
        }
        if(!mainUser.getIncome().isEmpty()){
            txtUserIncome.setText(mainUser.getIncome());
        }else{
            txtUserIncome.setText(defaultString);
        }
        if(!mainUser.getWhenMarriage().isEmpty()){
            txtWhenMarriage.setText(mainUser.getWhenMarriage());
        }else{
            txtWhenMarriage.setText(defaultString);
        }
        if(!mainUser.getCostForFirstAppointments().isEmpty()){
            txtCostForFirstDate.setText(mainUser.getCostForFirstAppointments());
        }else{
            txtCostForFirstDate.setText(defaultString);
        }
        if(!mainUser.getWhoLiveWith().isEmpty()){
            txtWhoLivingWith.setText(mainUser.getWhoLiveWith());
        }else{
            txtWhoLivingWith.setText(defaultString);
        }
        if(!mainUser.getHoliday().isEmpty()){
            txtDayOff.setText(mainUser.getHoliday());
        }else{
            txtDayOff.setText(defaultString);
        }
        chkDrinking.setChecked(mainUser.getDrinking() != 0);
        chkSmoking.setChecked(mainUser.getSmoking() != 0);
        chkHavingChilds.setChecked(mainUser.isHavingChildren() != 0);
    }

    private void handleBasicInformation() {
        ediChangeAbout.setFocusable(false);
        ediChangeAbout.setFocusableInTouchMode(false);
        txtUserAbout.setText(mainUser.getOther());
        if(!mainUser.getBirthday().isEmpty()&& !mainUser.getBirthday().equals("NA")){
            txtUserBirthday.setText(mainUser.getBirthday());
        }else{
            txtUserBirthday.setText(defaultString);
        }
        if(!mainUser.getOther().isEmpty()) {
            ediChangeAbout.append(mainUser.getOther());
        }else{
            ediChangeAbout.setText(defaultString);
        }
        if(!mainUser.getUserAddress().isEmpty()) {
            txtUserAddress.setText(mainUser.getUserAddress());
        }else{
            txtUserAddress.setText(defaultString);
        }
        if(!mainUser.getJob().isEmpty()) {
            txtUserWork.setText(mainUser.getJob());
        }else{
            txtUserWork.setText(defaultString);
        }
        if(!mainUser.getHobby().isEmpty()) {
            mTagHobby.setTags(mainUser.getHobby());
        }
        mTagHobby.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                if (isMainProfile) {
                    handleDeleteTagHobby(tag);
                }
            }
        });
    }

    private void handleDeleteTagHobby(String tag) {
        mainUser.removeTagHobby(tag);
        mTagHobby.setTags(mainUser.getHobby());
    }

    public User getUser(){
        return mainUser;
    }

    private void changeHobbyFragment() {
        fragmentHandle.replaceWithAnimation(getActivity().getSupportFragmentManager(), ChangeHobbyFragment.newInstance(), R.id.layoutMainContent,
                R.anim.enter_from_right,
                R.anim.hold,
                R.anim.hold,
                R.anim.exit_to_right);
    }

    private void changeProfileFragment() {
        fragmentHandle.replaceWithAnimation(getActivity().getSupportFragmentManager(), ChangeAboutMeFragment.newInstance(), R.id.layoutMainContent,
                R.anim.enter_from_right,
                R.anim.hold,
                R.anim.hold,
                R.anim.exit_to_right);
    }

    private void changeSelectImageFragment(){
        fragmentHandle.replaceWithAnimation(getActivity().getSupportFragmentManager(), SelectImageFragment.newInstance(), R.id.layoutMainContent,
                R.anim.enter_from_bottom,
                R.anim.hold,
                R.anim.hold,
                R.anim.exit_from_top);
    }

    private void showDialogSelectMedia(final int whatChange) {
        new MaterialDialog.Builder(mContext)
                .title("Lấy ảnh từ:")
                .items(R.array.media_selection)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        handleAddPicture(which+1, whatChange);
                        dialog.dismiss();
                        return true;
                    }
                })
                .show();
    }
    @Override
    protected void loadDataFromApi() {
        Log.i("Data", "Go here");
    }

    public void handleGoSetting() {
        Intent iSetting = new Intent(mContext, SettingActivity.class);
        startActivity(iSetting);

    }

    public void handleGoAboutApp() {
        Intent iAbout = new Intent(mContext, AboutActivity.class);
        startActivity(iAbout);
    }

    public void onGoAbout() {
        handleGoAboutApp();

    }

    public void onGoSetting() {
        handleGoSetting();

    }

    @OnClick(R.id.imgCgnBackground)
    public void onClickChangeBackground(){
        whatChangePicture  = CHANGE_BACKGROUND;
        showDialogSelectMedia(CHANGE_BACKGROUND);
    }

    @OnClick(R.id.imgCgnProfilePic)
    public void onClickChangeProfile(){
        whatChangePicture = CHANGE_PROFILE;
        showDialogSelectMedia(CHANGE_PROFILE);
    }

    @OnClick(R.id.layoutHeight)
    public void onClickChangeHeight(){
        if(isMainProfile) {
            new MaterialDialog.Builder(getActivity())
                    .title("Chiều cao?")
                    .inputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input("Nhập chiều cao của bạn (cm)", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            // Do something
                            if(input.length()>0) {
                                txtUserHeight.setText(input + "cm");
                                mainUser.setHeight(Integer.parseInt(input.toString()));
                            }
                        }
                    })
                    .positiveText("Lưu")
                    .negativeText("Huỷ")
                    .show();
        }
    }

    @OnClick(R.id.layoutSongCungAi)
    public void onClickChangeWhoLivingWith(){
        if(isMainProfile) {
            new MaterialDialog.Builder(getActivity())
                    .title("Bạn sống cùng ai?")
                    .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input("vd. Nhập thông tin: Gia đình, bạn bè,...", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            // Do something
                            if(input.length()>0) {
                                txtWhoLivingWith.setText(input.toString());
                                mainUser.setWhoLiveWith(input.toString());
                            }
                        }
                    })
                    .positiveText("Lưu")
                    .negativeText("Huỷ")
                    .show();
        }
    }

    @OnClick(R.id.layoutAddress)
    public void onClickChangeAddress(){
        if(isMainProfile) {
            new MaterialDialog.Builder(getActivity())
                    .title("Thông tin địa chỉ?")
                    .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input("Nhập nơi hiện tại đang sinh sống", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            // Do something
                            if(input.length()>0) {
                                txtUserAddress.setText(input.toString());
                                mainUser.setUserAddress(input.toString());
                            }
                        }
                    })
                    .positiveText("Lưu")
                    .negativeText("Huỷ")
                    .show();
        }
    }

    @OnClick(R.id.layoutJob)
    public void onClickChangeWork(){
        if(isMainProfile) {
            new MaterialDialog.Builder(getActivity())
                    .title("Thông tin làm việc?")
                    .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input("Nhập thông tin về công việc bạn làm", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            // Do something
                            if(input.length()>0) {
                                txtUserWork.setText(input.toString());
                                mainUser.setJob(input.toString());
                            }
                        }
                    })
                    .positiveText("Lưu")
                    .negativeText("Huỷ")
                    .show();
        }
    }

    @OnClick(R.id.txtChangeHobby)
    public void onClickChangeHobby(){
        if(isMainProfile) {
            new MaterialDialog.Builder(getActivity())
                    .title("Thêm sở thích?")
                    .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input("vd: Chơi thể thao, dã ngoại,...", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            // Do something
                            if(input.length()>0) {
                                mainUser.addHobby(input.toString());
                                mTagHobby.setTags(mainUser.getHobby());
                            }
                        }
                    })
                    .positiveText("Lưu")
                    .negativeText("Huỷ")
                    .show();
        }
    }

    @OnClick(R.id.layoutCompany)
    public void onClickChangeCompanyName(){
        if(isMainProfile) {
            new MaterialDialog.Builder(getActivity())
                    .title("Thông tin của công ty?")
                    .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input("Nhập tên công ty bạn đang làm", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            // Do something
                            if(input.length()>0) {
                                txtUserCompany.setText(input);
                                mainUser.setUserCompany(input.toString());
                            }
                        }
                    })
                    .positiveText("Lưu")
                    .negativeText("Huỷ")
                    .show();
        }
    }

    @OnClick(R.id.layoutMucThuNhap)
    public void onClickChangeIncome(){
        if(isMainProfile) {
            new MaterialDialog.Builder(getActivity())
                    .title("Thông tin thu nhập cá nhân?")
                    .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input("vd. 1000$/tháng", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            // Do something
                            if (input.length()>0) {
                                txtUserIncome.setText(input);
                                mainUser.setIncome(input.toString());
                            }
                        }
                    })
                    .positiveText("Lưu")
                    .negativeText("Huỷ")
                    .show();
        }
    }

    @OnClick(R.id.chkDrinking)
    public void changeDrinkingStatus(){
        if(!chkDrinking.isChecked()){
            chkDrinking.setChecked(false);
        }else {
            chkDrinking.setChecked(true);
        }
        mainUser.setDrinking(chkDrinking.isChecked() ? 1 : 0);
    }

    @OnClick(R.id.chkSmoking)
    public void changeSmokingStatus(){
        if(!chkSmoking.isChecked()){
            chkSmoking.setChecked(false);
        }else {
            chkSmoking.setChecked(true);
        }
        mainUser.setSmoking(chkSmoking.isChecked() ? 1 : 0);
    }

    @OnClick(R.id.chkCoupleDate)
    public void changeCoupleDateStatus(){
        if(!chkCoupleDate.isChecked()){
            chkCoupleDate.setChecked(false);
        }else {
            chkCoupleDate.setChecked(true);
        }
        mainUser.setDrinking(chkCoupleDate.isChecked() ? 1 : 0);
    }

    @OnClick(R.id.chkHavingChilds)
    public void changeHavingChildStatus(){
        if(!chkHavingChilds.isChecked()){
            chkHavingChilds.setChecked(false);
        }else {
            chkHavingChilds.setChecked(true);
        }
        mainUser.setHavingChildren(chkHavingChilds.isChecked() ? 1 : 0);
    }

    @OnClick(R.id.layoutBirhday)
    public void onClickBirthday(){
        if(isMainProfile) {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                            handleChangeBirthday(year, monthOfYear, dayOfMonth);
                        }
                    },
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show(getActivity().getFragmentManager(), "Nhập thông tin ngày sinh");
        }
    }

    private void handleChangeBirthday(int year, int monthOfYear, int dayOfMonth) {
        mainUser.setBirthday((dayOfMonth < 10 ? String.format("%02d", dayOfMonth) : dayOfMonth) + "/" + ((monthOfYear + 1) < 10 ? String.format("%02d", monthOfYear + 1) : monthOfYear + 1) + "/" + year);
        if(layoutUserAge.getVisibility()==View.GONE){
            layoutUserAge.setVisibility(View.VISIBLE);
        }
        txtUserAges.setText(Utils.birthDayToYearsOld(mainUser.getBirthday()));
        txtUserBirthday.setText(mainUser.getBirthday());
    }

    @OnClick(R.id.layoutWeight)
    public void onClickChangeWeight(){
        if(isMainProfile) {
            new MaterialDialog.Builder(getActivity())
                    .title("Cân nặng?")
                    .inputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input("Nhập cân nặng của bạn (kg)", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            // Do something
                            if(input.length()>0) {
                                txtUserWeight.setText(input + "kg");
                                mainUser.setWeight(Integer.parseInt(input.toString()));
                            }
                        }
                    })
                    .positiveText("Lưu")
                    .negativeText("Huỷ")
                    .show();
        }
    }

    @OnClick(R.id.layoutCostFirstDate)
    public void onClickChangeCostDate(){
        if(isMainProfile) {
            new MaterialDialog.Builder(getActivity())
                    .title("Mong muốn chi phí cho buổi hẹn đầu?")
                    .inputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                    .input("vd. 100.000$", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            // Do something
                            txtCostForFirstDate.setText(input);
                            mainUser.setCostForFirstAppointments(input.toString());
                        }
                    })
                    .positiveText("Lưu")
                    .negativeText("Huỷ")
                    .show();
        }
    }

    @OnClick(R.id.layoutHomeTown)
    public void onClickChangeHomeTown(){
        if(isMainProfile) {
            Regions regions[] = Regions.class.getEnumConstants();
            int i = 0;
            final String listItems[] = new String[regions.length];
            for (Regions region : regions) {
                listItems[i] = region.toString();
                MLog.d(DialogList.class, "" + listItems[i]);
                i++;
            }
            int selectedItem = 0;
            DialogList dialogList = new DialogList(mContext, selectedItem, new IDialogListCallBack() {
                @Override
                public void onClickBack() {
                }

                @Override
                public void onClickItem(int position) {
                    String homeTown = listItems[position];
                    txtHomeTown.setText(listItems[position]);
                    mainUser.setHometown(listItems[position]);
                    txtUserPlace.setText(homeTown);
                    if (layoutUserPlace.getVisibility() == View.GONE) {
                        layoutUserPlace.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onClickDone() {
                }
            });
            dialogList.show(getString(R.string.txt_homeland), listItems);
        }
    }

    @OnClick(R.id.layoutWhenMarriage)
    public void onClickChangeWhenMarriage(){
        if(isMainProfile) {
            MarryTime regions[] = MarryTime.class.getEnumConstants();
            int i = 0;
            final String listItems[] = new String[regions.length];
            for (MarryTime region : regions) {
                listItems[i] = region.toString();
                i++;
            }
            int selectedItem = 0;
            DialogList dialogList = new DialogList(mContext, selectedItem, new IDialogListCallBack() {
                @Override
                public void onClickBack() {
                }

                @Override
                public void onClickItem(int position) {
                    txtWhenMarriage.setText(listItems[position]);
                    mainUser.setWhenMarriage(listItems[position]);
                }

                @Override
                public void onClickDone() {
                }
            });
            dialogList.show(getString(R.string.txt_marry_time), listItems);
        }
    }

    @OnClick(R.id.layoutWhoWillPay)
    public void onClickChangeWhoPay(){
        if(isMainProfile) {
            LoveCost regions[] = LoveCost.class.getEnumConstants();
            int i = 0;
            final String listItems[] = new String[regions.length];
            for (LoveCost region : regions) {
                listItems[i] = region.toString();
                i++;
            }
            int selectedItem = 0;
            DialogList dialogList = new DialogList(mContext, selectedItem, new IDialogListCallBack() {
                @Override
                public void onClickBack() {
                }

                @Override
                public void onClickItem(int position) {
                    txtWhoWillPay.setText(listItems[position]);
                    //mainUser.setW
                }

                @Override
                public void onClickDone() {
                }
            });
            dialogList.show(getString(R.string.txt_who_pay_matching), listItems);
        }
    }

    @OnClick(R.id.layoutNoiLamViec)
    public void onClickChangeWorkingPlace(){
        if(isMainProfile) {
            Regions regions[] = Regions.class.getEnumConstants();
            int i = 0;
            final String listItems[] = new String[regions.length];
            for (Regions region : regions) {
                listItems[i] = region.toString();
                i++;
            }
            int selectedItem = 0;
            DialogList dialogList = new DialogList(mContext, selectedItem, new IDialogListCallBack() {
                @Override
                public void onClickBack() {
                }

                @Override
                public void onClickItem(int position) {
                    txtWorkingPlace.setText(listItems[position]);
                }

                @Override
                public void onClickDone() {
                }
            });
            dialogList.show("Địa chỉ công ty", listItems);
        }
    }

    @OnClick(R.id.layoutNgoaiHinh)
    public void onClickChangeBodyForm(){
        if(isMainProfile) {
            Externality regions[] = Externality.class.getEnumConstants();
            int i = 0;
            final String listItems[] = new String[regions.length];
            for (Externality region : regions) {
                listItems[i] = region.toString();
                i++;
            }
            int selectedItem = 0;
            DialogList dialogList = new DialogList(mContext, selectedItem, new IDialogListCallBack() {
                @Override
                public void onClickBack() {
                }

                @Override
                public void onClickItem(int position) {
                    txtBodyForm.setText(listItems[position]);
                    mainUser.setExtrovert(listItems[position]);
                }

                @Override
                public void onClickDone() {
                }
            });
            dialogList.show(getString(R.string.txt_externality), listItems);
        }
    }

    @OnClick(R.id.layoutNhomMau)
    public void onClickChangeBloodType(){
        if(isMainProfile) {
            BloodGroup regions[] = BloodGroup.class.getEnumConstants();
            int i = 0;
            final String listItems[] = new String[regions.length];
            for (BloodGroup region : regions) {
                listItems[i] = region.toString();
                i++;
            }
            int selectedItem = 0;
            DialogList dialogList = new DialogList(mContext, selectedItem, new IDialogListCallBack() {
                @Override
                public void onClickBack() {
                }

                @Override
                public void onClickItem(int position) {
                    String bloodType = listItems[position];
                    txtBloodType.setText(bloodType);
                    mainUser.setBloodType(bloodType);
                }

                @Override
                public void onClickDone() {
                }
            });
            dialogList.show(getString(R.string.txt_blood_group), listItems);
        }
    }

    @OnTouch(R.id.edtChangeAbout)
    public boolean onClickChangeAbout(){
        //Utils.expand(layoutActionAbout, -1);
        ediChangeAbout.setFocusable(true);
        ediChangeAbout.setFocusableInTouchMode(true);
        final ScrollView scrollView = (ScrollView) mView.findViewById(R.id.scrollViewMain);
        final LinearLayout layoutBasic = (LinearLayout) mView.findViewById(R.id.layoutBasicInformation);
        Utils.scrollToView(scrollView, layoutBasic);
        ediChangeAbout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainUser.setOther(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ediChangeAbout.setOnEditTextImeBackListener(new EditTextBackEvent.EditTextImeBackListener() {
            @Override
            public void onImeBack(EditTextBackEvent ctrl, String text) {
                ediChangeAbout.setFocusable(false);
                ediChangeAbout.setFocusableInTouchMode(false);
            }
        });
        return false;
    }

    //    @OnClick(R.id.txtCancelAbout)
//    public void onClickCancelAbout(){
//        ediChangeAbout.clearFocus();
//        Utils.collapse(layoutActionAbout);
//    }
//
//    @OnClick(R.id.txtSaveAbout)
//    public void onClickaveAbout(){
//        ediChangeAbout.clearFocus();
//        Utils.collapse(layoutActionAbout);
//    }

    @OnClick(R.id.txtAddLanguage)
    public void onClickChangeLanguage() {
        if(isMainProfile) {
            Languages languages[] = Languages.class.getEnumConstants();
            int i = 0;

            final String listItems[] = new String[languages.length];
            for (Languages language : languages) {
                listItems[i] = language.toString();
                i++;
            }
            ArrayList<Integer> selectedItem = mainUser.getLanguageByInt();

            DialogListMultiple dialogList = new DialogListMultiple(mContext, selectedItem, new IDialogListMultipleCallBack() {
                ArrayList<Integer> listSelected = new ArrayList<>();

                @Override
                public void onClickBack() {

                }

                @Override
                public void onClickItem(ArrayList<Integer> position) {

                    boolean isAll = false;
                    for (int i = 0; i < position.size(); i++) {
                        if (Languages.fromInteger(position.get(i)) == Languages.All) {
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
                    mainUser.setLanguage(listSelected);
                    for (int i = 0; i < listSelected.size(); i++) {
                        listLanguage.add(Languages.fromInteger(listSelected.get(i)).toString());
                    }
                    mTagGroupLanguages.setTags(listLanguage);
                }
            });
            dialogList.show(getString(R.string.txt_language), listItems);
        }
    }

    @OnClick(R.id.txtChangeGenitive)
    public void onClickChangeGenitive() {
        if(isMainProfile) {
            Genitives genitives[] = Genitives.class.getEnumConstants();
            int i = 0;

            final String listItems[] = new String[genitives.length];
            for (Genitives genitive : genitives) {
                listItems[i] = genitive.toString();
                i++;
            }
            ArrayList<Integer> selectedItem = mainUser.getGenitiveByInt();

            DialogListMultiple dialogList = new DialogListMultiple(mContext, selectedItem, new IDialogListMultipleCallBack() {
                ArrayList<Integer> listSelected = new ArrayList<>();

                @Override
                public void onClickBack() {

                }

                @Override
                public void onClickItem(ArrayList<Integer> position) {

                    boolean isAll = false;
                    for (int i = 0; i < position.size(); i++) {
                        if (Languages.fromInteger(position.get(i)) == Languages.All) {
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
                    mainUser.setGenitive(listSelected);
                    for (int i = 0; i < listSelected.size(); i++) {
                        listLanguage.add(Genitives.fromInteger(listSelected.get(i)).toString());
                    }
                    mTagGenitive.setTags(listLanguage);
                }
            });
            dialogList.show(getString(R.string.txt_language), listItems);
        }
    }

    @OnClick(R.id.layoutNgayNghi)
    public void onClickChangeDayOff() {
        if(isMainProfile) {
            DayOfWeek dayOfWeeks[] = DayOfWeek.class.getEnumConstants();
            int i = 0;

            final String listItems[] = new String[dayOfWeeks.length];
            for (DayOfWeek genitive : dayOfWeeks) {
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
                        if (DayOfWeek.fromInteger(position.get(i)) == DayOfWeek.All) {
                            isAll = true;
                            position.clear();
                            break;
                        }
                    }
                    if (isAll || (position.size() == 0)) {
                        position.add(DayOfWeek.All.getValue());
                    }
                    listSelected = position;
                }

                @Override
                public void onClickDone() {
                    List<String> listLanguage = new ArrayList<>();

                    for (int i = 0; i < listSelected.size(); i++) {
                        listLanguage.add(DayOfWeek.fromInteger(listSelected.get(i)).toString());
                    }
                    String holiday = TextUtils.join(",", listLanguage);
                    mainUser.setHoliday(holiday);
                    txtDayOff.setText(holiday);
                }
            });
            dialogList.show("Ngày nghỉ", listItems);
        }
    }

    @OnClick(R.id.txtAddAttraction)
    public void onClickChangeAttraction() {
        if(isMainProfile) {
            Genitives genitives[] = Genitives.class.getEnumConstants();
            int i = 0;

            final String listItems[] = new String[genitives.length];
            for (Genitives genitive : genitives) {
                listItems[i] = genitive.toString();
                i++;
            }
            ArrayList<Integer> selectedItem = mainUser.getAttractionByInt();

            DialogListMultiple dialogList = new DialogListMultiple(mContext, selectedItem, new IDialogListMultipleCallBack() {
                ArrayList<Integer> listSelected = new ArrayList<>();

                @Override
                public void onClickBack() {

                }

                @Override
                public void onClickItem(ArrayList<Integer> position) {

                    boolean isAll = false;
                    for (int i = 0; i < position.size(); i++) {
                        if (Languages.fromInteger(position.get(i)) == Languages.All) {
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
                    mainUser.setAttraction(listSelected);
                    for (int i = 0; i < listSelected.size(); i++) {
                        listLanguage.add(Genitives.fromInteger(listSelected.get(i)).toString());
                    }
                    mTagGroupTypePeople.setTags(listLanguage);
                }
            });
            dialogList.show(getString(R.string.txt_language), listItems);
        }
    }

    public void updateInformationView(String aboutMe) {
        ((TextView) mView.findViewById(R.id.txtAboutMe)).setText(aboutMe);
    }

    private void handleAddPicture(int type, int whatchange) {
        requestForStorePermission(getActivity().getCurrentFocus(), type, whatchange);
    }

    /**
     * Xu ly call thu vien/camera de lay anh boi type
     * @param type 1 call facebook album, 2 call camera, 3 call thu vien trong may
     */
    private void handleCallMedia(int type, int whatChange){
        switch (type){
            case 1:
                handleCallFbAlbums();
                break;
            case 2:
                handleCallCamera();
                break;
            case 3:
                handleCallGallery();
                break;
            default:
                break;
        }
    }

    //Xu ly call facebook albums
    private void handleCallFbAlbums() {
        DialogFbPictureSelect dialog = new DialogFbPictureSelect(mContext, new DialogFbPictureSelect.IFbPictureSelectCallback() {
            @Override
            public void onClickBack() {

            }

            @Override
            public void onClickItem(String pickedUrl) {
                Uri myUri = Uri.parse(pickedUrl);
                addViewPicture(myUri, pickedUrl);
            }
        });
        dialog.show("Facebook ảnh");
    }

    //Xu ly call thu vien trong may
    private void handleCallGallery() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getActivity().startActivityForResult(intentGallery, AppConstants.CALL_GALLERY);
    }

    //Xu ly call camera
    private void handleCallCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        getActivity().startActivityForResult(intentCamera, AppConstants.CALL_CAMERA);
    }

    //Xu ly yeu cau xin quyen truy cap vao storage
    public void requestForStorePermission(View view, int type, int whatChange) {
        final String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, 1);
        } else {
            handleCallMedia(type, whatChange);
        }
    }


    //Add new anh moi vao san pham
    public void addViewPicture(Uri imgUri, String pathImage){
        if(whatChangePicture==CHANGE_PROFILE){
            //Picasso.with(mContext).load(imgUri).resize(80, 80).into(profilePicture);
            Glide.with(mContext).load(imgUri).asBitmap().into(profilePicture);
            mainUser.setUserProfilePic(pathImage);
        }else if (whatChangePicture == CHANGE_BACKGROUND){
            //Picasso.with(mContext).load(imgUri).resize(800, 300).into(changeCover);
            Glide.with(mContext).load(imgUri).asBitmap().into(changeCover);
            mainUser.setUserCover(pathImage);
        }else{
            final View vPicture = LayoutInflater.from(mContext).inflate(R.layout.product_picture_item, null, false);
            pictureContent.addView(vPicture, 0);
            ImageView delPicture = (ImageView) vPicture.findViewById(R.id.imgDeletePicture);
            ImageView proPicture = (ImageView) vPicture.findViewById(R.id.imgProductItem);
            //Picasso.with(mContext).load(imgUri).resize(600, 600).into(proPicture);
            Glide.with(mContext).load(imgUri).asBitmap().into(proPicture);
            delPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pictureContent.removeView(vPicture);
                }
            });
        }
        whatChangePicture = -1;
    }

    @Override
    public void onPause() {
        super.onPause();
        //Log.i("Log", MCApp.getUserInstance().getBirthday());
        if(isMainProfile) {
            UserServices.getInstance().updateUserToServer(mainUser);
        }
    }

}
