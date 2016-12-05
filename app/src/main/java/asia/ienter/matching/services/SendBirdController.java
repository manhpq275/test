package asia.ienter.matching.services;

import android.util.Log;

import com.google.gson.Gson;
import com.sendbird.android.BaseChannel;
import com.sendbird.android.BaseMessage;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.GroupChannelListQuery;
import com.sendbird.android.OpenChannel;
import com.sendbird.android.OpenChannelListQuery;
import com.sendbird.android.PreviousMessageListQuery;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;
import com.sendbird.android.UserMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoangtuan on 10/14/16.
 */
public class SendBirdController {
    private static SendBirdController _instance = new SendBirdController();

    public String userName = "";
    public User userChat;
    public OpenChannel mOpenChannel;
    public GroupChannel mGroupChannel;
    public ArrayList<OpenChannel> listOpenChannel = new ArrayList<>();

    public static SendBirdController getInstance(){
        if(_instance == null){
            _instance = new SendBirdController();
        }
        return _instance;
    }

    private SendBirdController(){
        //connectUser(ChattingActivity.UserNickName);
    }
    public void connectUser(String userId, final IConnectUserCallback callback){
        SendBird.connect(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {
                    // Error.
                    callback.onError();
                    return;
                }
                userChat = user;
                callback.onSuccess();
                //getListChannel(callback);
            }
        });
    }

    /**
     *
     * @param identifier
     * @param callback
     */
    private void addListenner(String identifier, final IEnterOpenChannelCallback callback) {
        SendBird.addChannelHandler("1", new SendBird.ChannelHandler() {
            @Override
            public void onMessageReceived(BaseChannel baseChannel, BaseMessage baseMessage) {
                //Log.i("Data", "Hahaha, having a message:" + ((UserMessage) baseMessage).getMessage());
                callback.addChannelHandle(baseMessage);
            }
        });
    }

    public void connectUserWithToken(String userId, String tokenId){
        SendBird.connect(userId, tokenId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {
                    // Error.
                    return;
                }
            }
        });
    }

    public void updateUser(String userId, final String nickName, final String profileUrl){
        SendBird.connect(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {
                    // Error.
                    return;
                }

                SendBird.updateCurrentUserInfo(nickName, profileUrl, new SendBird.UserInfoUpdateHandler() {
                    @Override
                    public void onUpdated(SendBirdException e) {
                        if (e != null) {
                            // Error.
                            return;
                        }
                    }
                });
            }
        });
    }

    public void updateProfileImage(String userId, final String nickName, final File profileUrl){
        SendBird.connect(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {
                    // Error.
                    return;
                }

                SendBird.updateCurrentUserInfoWithProfileImage(nickName, profileUrl, new SendBird.UserInfoUpdateHandler() {
                    @Override
                    public void onUpdated(SendBirdException e) {
                        if (e != null) {
                            // Error.
                            return;
                        }
                    }
                });
            }
        });
    }

    /**
     * Open a new channel for user
     * @param name
     * @param coverUrl
     * @param data
     */
    public void openChannel(String name, String coverUrl, String data){
        if(userChat!=null) {
            OpenChannel.createChannel(name, coverUrl, data, new OpenChannel.OpenChannelCreateHandler() {
                @Override
                public void onResult(OpenChannel openChannel, SendBirdException e) {
                    if (e != null) {
                        // Error.
                        return;
                    }
                }
            });
        }
    }

    /**
     * Create a own channel for list user
     * @param userIds
     */
    public void createChannelWithUserIds(List<String> userIds, final IEnterOpenChannelCallback callback){
        GroupChannel.createChannelWithUserIds(userIds, false, new GroupChannel.GroupChannelCreateHandler() {
            @Override
            public void onResult(GroupChannel groupChannel, SendBirdException e) {
                if (e != null) {
                    // Error.
                    callback.onError();
                    return;
                }
                Log.i("data", "Enter completely ~");
                mGroupChannel = groupChannel;
                addListenner("", callback);
                callback.onSuccess();
            }
        });
    }

    public void getGroupChannelList(){
        GroupChannelListQuery mQuery = GroupChannel.createMyGroupChannelListQuery();
        mQuery.setIncludeEmpty(true);
        mQuery.next(new GroupChannelListQuery.GroupChannelListQueryResultHandler() {
            @Override
            public void onResult(List<GroupChannel> list, SendBirdException e) {
                if (e != null) {
                    // Error.
                    return;
                }

                Log.i("Data", new Gson().toJson(list));

            }
        });
    }

    /**
     * Get all channel from server
     */
    public void getListChannel(final IConnectUserCallback callback){
        if(userChat!=null) {
            OpenChannelListQuery mChannelListQuery = OpenChannel.createOpenChannelListQuery();
            mChannelListQuery.next(new OpenChannelListQuery.OpenChannelListQueryResultHandler() {
                @Override
                public void onResult(List<OpenChannel> channels, SendBirdException e) {
                    listOpenChannel.addAll(channels);
//                    Log.i("data", channels.get(0).getUrl() + " ~");
//                    enterOpenChannel(channels.get(0).getUrl());
                    if (e != null) {
                        // Error.
                        callback.onError();
                        return;
                    }
                    callback.onSuccess();
                }
            });
        }
    }

    public void enterOpenChannel(String channelUrl, final IEnterOpenChannelCallback callback){
        OpenChannel.getChannel(channelUrl, new OpenChannel.OpenChannelGetHandler() {
            @Override
            public void onResult(OpenChannel openChannel, SendBirdException e) {

                if (e != null) {
                    // Error.
                    return;
                }
                mOpenChannel = openChannel;
                openChannel.enter(new OpenChannel.OpenChannelEnterHandler() {
                    @Override
                    public void onResult(SendBirdException e) {
                        if (e != null) {
                            // Error.
                            callback.onError();
                            return;
                        }
                        addListenner("", callback);
                        callback.onSuccess();
                    }
                });
            }
        });
    }

    public void exitOpenChannel(String channelUrl){
        OpenChannel.getChannel(channelUrl, new OpenChannel.OpenChannelGetHandler() {
            @Override
            public void onResult(OpenChannel openChannel, SendBirdException e) {
                if (e != null) {
                    // Error.
                    return;
                }

                openChannel.exit(new OpenChannel.OpenChannelExitHandler() {
                    @Override
                    public void onResult(SendBirdException e) {
                        if (e != null) {
                            // Error.
                            return;
                        }
                    }
                });
            }
        });
    }

    /**
     * Handle get all old message
     * @param callback
     */
    public void loadingPreviousMessages(final ILoadPreviousMessagesCallback callback){
        if(mOpenChannel!=null) {
            PreviousMessageListQuery mPrevMessageListQuery = mOpenChannel.createPreviousMessageListQuery();
            mPrevMessageListQuery.load(30, true, new PreviousMessageListQuery.MessageListQueryResult() {
                @Override
                public void onResult(List<BaseMessage> messages, SendBirdException e) {
                    if (e != null) {
                        // Error.
                        callback.onError();
                        return;
                    }
                    callback.onSuccess(messages);
                }
            });
        }
    }


    public void sendMessageToChannel(String message, String data, final ISendMessageCallback callback){
        if(mOpenChannel!=null) {
            mOpenChannel.sendUserMessage(message, data, new BaseChannel.SendUserMessageHandler() {
                @Override
                public void onSent(UserMessage userMessage, SendBirdException e) {
                    if (e != null) {
                        // Error.
                        callback.onError();
                        return;
                    }
                    callback.onSuccess(userMessage);
                    //Log.i("data", "Success ~" + userMessage.getMessage() + " ~ " + userMessage.getSender());
                }
            });
        }
    }

    public void sendMessageToGroup(String message, String data, final ISendMessageCallback callback){
        if(mGroupChannel!=null) {
            mGroupChannel.sendUserMessage(message, data, new BaseChannel.SendUserMessageHandler() {
                @Override
                public void onSent(UserMessage userMessage, SendBirdException e) {
                    if (e != null) {
                        // Error.
                        callback.onError();
                        return;
                    }
                    callback.onSuccess(userMessage);
                    //Log.i("data", "Success ~" + userMessage.getMessage() + " ~ " + userMessage.getSender());
                }
            });
        }
    }

    public void deleteMessage(BaseMessage baseMessage){
        if(mOpenChannel!=null) {
            mOpenChannel.deleteMessage(baseMessage, new BaseChannel.DeleteMessageHandler() {
                @Override
                public void onResult(SendBirdException e) {
                    if (e != null) {
                        // Error.
                        return;
                    }
                }
            });
        }
    }

    /**
     * Get group for users
     * @param listUser
     */
    public void loadGroupForUser(final List<String> listUser, final IEnterOpenChannelCallback callback) {
        Log.i("Group", "Go here");
        GroupChannelListQuery mQuery = GroupChannel.createMyGroupChannelListQuery();
        mQuery.setIncludeEmpty(true);
        mQuery.next(new GroupChannelListQuery.GroupChannelListQueryResultHandler() {
            @Override
            public void onResult(List<GroupChannel> list, SendBirdException e) {
                Log.i("Group", new Gson().toJson(list));
                if (e != null) {
                    // Error.
                    callback.onError();
                    return;
                }

                mGroupChannel = handleCheckExistsGroup(list, listUser);
                if(mGroupChannel!=null){
                    addListenner("", callback);
                    callback.onSuccess();
                }else {
                    createChannelWithUserIds(listUser, callback);
                }
            }
        });
    }

    /**
     * Check group of users is exists or not
     * @param list
     * @param listUser
     * @return
     */
    private GroupChannel handleCheckExistsGroup(List<GroupChannel> list, List<String> listUser) {
        for(int i=0;i<list.size();i++){
            List<User> listUserGroup = list.get(i).getMembers();
            List<String> stringList = new ArrayList<>();
            for(int j=0;j<listUserGroup.size();j++){
                stringList.add(listUserGroup.get(j).getUserId());
            }
            if(stringList.contains(listUser.get(0))&&stringList.contains(listUser.get(1))) {
                return list.get(i);
            }
        }
        return null;
    }

    public void loadGroupMessage(final ILoadPreviousMessagesCallback callback) {
        PreviousMessageListQuery mPrevMessageListQuery = mGroupChannel.createPreviousMessageListQuery();
        mPrevMessageListQuery.load(30, true, new PreviousMessageListQuery.MessageListQueryResult() {
            @Override
            public void onResult(List<BaseMessage> messages, SendBirdException e) {
                if (e != null) {
                    // Error.
                    callback.onError();
                    return;
                }
                Log.i("data",messages .size() + " ~" );
                callback.onSuccess(messages);
            }
        });
    }


    public interface ILoadPreviousMessagesCallback{
        public void onSuccess(List<BaseMessage> messages);
        public void onError();
    }

    public interface IConnectUserCallback{
        public void onSuccess();
        public void onError();
    }

    public interface IEnterOpenChannelCallback {
        public void onSuccess();
        public void addChannelHandle(BaseMessage baseMessage);
        public void onError();
    }

    public interface ISendMessageCallback{
        public void onSuccess(UserMessage userMessage);
        public void onError();
    }
}
