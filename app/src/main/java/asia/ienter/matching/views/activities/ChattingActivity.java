package asia.ienter.matching.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sendbird.android.BaseMessage;
import com.sendbird.android.UserMessage;

import java.util.ArrayList;
import java.util.List;

import asia.ienter.matching.MCApp;
import asia.ienter.matching.R;
import asia.ienter.matching.models.ChattingMessage;
import asia.ienter.matching.models.UserView;
import asia.ienter.matching.services.SendBirdController;
import asia.ienter.matching.views.adapters.ChattingAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by hoangtuan on 11/30/16.
 */
public class ChattingActivity extends AppCompatActivity {
    private int SELECT_TEMPLATE = 1;
    private ChattingAdapter chattingAdapter;
    private UserView userView;
    private List<String> listUser = new ArrayList<>();
    @InjectView(R.id.listChatting)      RecyclerView listChatting;
    @InjectView(R.id.edtChatContent)    EditText edtChatContent;
    @InjectView(R.id.idMoreOption)      ImageView btnSettingOption;
    @InjectView(R.id.layoutNoMessage)   LinearLayout layoutNoMessage;
    @InjectView(R.id.mSwipeRefresh)     SwipeRefreshLayout layoutRefresh;
    @InjectView(R.id.txtUserChatName)   TextView txtUserChatName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.enter_from_right, R.anim.hold);
        setContentView(R.layout.activity_chatting);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            userView = (UserView) bundle.getSerializable("UserViewChatting");
            handleSetUser(userView);
            txtUserChatName.setText(userView.getUserName());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            listChatting.setLayoutManager(mLayoutManager);
            listChatting.setItemAnimator(new DefaultItemAnimator());
            chattingAdapter = new ChattingAdapter(getApplicationContext(), new ArrayList<BaseMessage>(), userView);
            listChatting.setAdapter(chattingAdapter);
            loadPreviousMessage();
        }
    }

    private void handleSetUser(UserView userView) {
        listUser.add(MCApp.getUserInstance().getUserId());
        listUser.add(userView.getUserID());
        Log.i("Data user", userView.getUserID());
//        listUser.add("chatter1");
    }

    private void loadPreviousMessage() {
        layoutRefresh.setRefreshing(true);
        SendBirdController.getInstance().connectUser(MCApp.getUserInstance().getUserId(), new SendBirdController.IConnectUserCallback() {
            @Override
            public void onSuccess() {
                handleCreateGroupUser();
            }

            @Override
            public void onError() {
                handleLoadDataError();
            }
        });
    }

    private void handleCreateGroupUser() {
        SendBirdController.getInstance().loadGroupForUser(listUser, new SendBirdController.IEnterOpenChannelCallback() {
            @Override
            public void onSuccess() {
                handleLoadGroupMessage();

            }

            @Override
            public void addChannelHandle(BaseMessage baseMessage) {
                showListChatting();
                ChattingMessage item = new ChattingMessage(baseMessage);
                chattingAdapter.addMessage(item);
                listChatting.scrollToPosition(chattingAdapter.getItemCount() - 1);
            }

            @Override
            public void onError() {
                handleLoadDataError();
            }
        });
    }

    /**
     * Load message from group of users
     */
    private void handleLoadGroupMessage() {
        SendBirdController.getInstance().loadGroupMessage(new SendBirdController.ILoadPreviousMessagesCallback() {
            @Override
            public void onSuccess(List<BaseMessage> messages) {
                if (messages.size() > 0) {
                    chattingAdapter.setDataAfterLoad(messages);
                    listChatting.scrollToPosition(chattingAdapter.getItemCount() - 1);
                } else {
                    listChatting.setVisibility(View.GONE);
                    layoutNoMessage.setVisibility(View.VISIBLE);
                }
                layoutRefresh.setRefreshing(false);
                layoutRefresh.setEnabled(false);
            }

            @Override
            public void onError() {
                handleLoadDataError();
            }
        });
    }

    /**
     * Enter to channel by channel id
     * @param idChannel
     */
    private void handleConnectChannel(String idChannel) {
        SendBirdController.getInstance().enterOpenChannel(SendBirdController.getInstance().listOpenChannel.get(0).getUrl(), new SendBirdController.IEnterOpenChannelCallback() {
            @Override
            public void onSuccess() {
                handleLoadMessage();
            }

            @Override
            public void onError() {
                handleLoadDataError();
            }

            @Override
            public void addChannelHandle(BaseMessage baseMessage) {
                showListChatting();
                ChattingMessage item = new ChattingMessage(baseMessage);
                chattingAdapter.addMessage(item);
                listChatting.scrollToPosition(chattingAdapter.getItemCount() - 1);
            }
        });
    }

    /**
     * Load previous Message after connect channel successfully
     */
    private void handleLoadMessage() {
        SendBirdController.getInstance().loadingPreviousMessages(new SendBirdController.ILoadPreviousMessagesCallback() {
            @Override
            public void onSuccess(List<BaseMessage> messages) {
                if (messages.size() > 0) {
                    chattingAdapter.setDataAfterLoad(messages);
                    listChatting.scrollToPosition(chattingAdapter.getItemCount() - 1);
                } else {
                    listChatting.setVisibility(View.GONE);
                    layoutNoMessage.setVisibility(View.VISIBLE);
                }
                layoutRefresh.setRefreshing(false);
                layoutRefresh.setEnabled(false);
            }

            @Override
            public void onError() {
                handleLoadDataError();
            }
        });
    }

    /**
     * Show view when cant load data chat
     */
    private void handleLoadDataError() {
        layoutRefresh.setRefreshing(false);
        layoutRefresh.setEnabled(false);
    }

    @OnClick(R.id.btnBackFragment)
    public void onClickBackActivity() {
        onBackPressed();

    }

//    @OnClick(R.id.edtChatContent)
//    public void onTouchEditView(){
//        listChatting.scrollToPosition(chattingAdapter.getItemCount() - 1);
//    }

    @OnClick(R.id.btnSendMessage)
    public void onClickSendMessage(){
        String content = edtChatContent.getText().toString();
        if(!content.isEmpty()) {
            showListChatting();
            chattingAdapter.addMessage(new ChattingMessage(content, 0));
            listChatting.scrollToPosition(chattingAdapter.getItemCount() - 1);
//            SendBirdController.getInstance().sendMessageToChannel(content, "", new SendBirdController.ISendMessageCallback() {
//                @Override
//                public void onSuccess(UserMessage userMessage) {
//                    Log.i("Check value", "Send message!");
//                }
//
//                @Override
//                public void onError() {
//                    Log.i("Check value", "Failed message!");
//                }
//            });
            SendBirdController.getInstance().sendMessageToGroup(content, "", new SendBirdController.ISendMessageCallback() {
                @Override
                public void onSuccess(UserMessage userMessage) {
                    Log.i("Check value", "Send message!");
                }

                @Override
                public void onError() {
                    Log.i("Check value", "Failed message!");
                }
            });
            edtChatContent.setText("");
        }
    }

    /**
     * Show list chatting when having data.
     */
    public void showListChatting(){
        if(layoutNoMessage.getVisibility() == View.VISIBLE){
            listChatting.setVisibility(View.VISIBLE);
            layoutNoMessage.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.idMoreOption)
    public void onClickOption(){
        showPopupSelectImage(btnSettingOption);
    }

    private void showPopupSelectImage(View btnClick){
        PopupMenu popup = new PopupMenu(ChattingActivity.this, btnClick);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_chatting_action, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                handlePopupClick(item);
                return true;
            }
        });

        popup.show();
    }

    @OnClick(R.id.btnMessTemplate)
    public void onClickMessageTemplate(){
        Intent iMessTemplate = new Intent(ChattingActivity.this, TemplateMessageActivity.class);
        startActivityForResult(iMessTemplate, SELECT_TEMPLATE);
    }


    private void handlePopupClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_profile:
                //handleBlockUser();
                break;
            case R.id.share_facebook:
                //handleReportUser();
                break;
            case R.id.block_user:
                handleBlockUser();
                break;
            default:
                break;
        }
    }

    private void handleBlockUser() {
        new MaterialDialog.Builder(this)
                .title("Chặn người dùng này")
                .positiveText("Đồng ý")
                .negativeText("Huỷ")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Toast.makeText(ChattingActivity.this, "Đã chặn người dùng!", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.hold, R.anim.exit_to_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_TEMPLATE){
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle = data.getExtras();
                String selectedTemplate = bundle.getString("result");
                edtChatContent.setText(selectedTemplate);
            }
        }
    }
}
