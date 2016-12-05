package asia.ienter.matching.models;

import com.sendbird.android.BaseMessage;
import com.sendbird.android.UserMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

import asia.ienter.matching.MCApp;

/**
 * Created by hoangtuan on 11/30/16.
 */
public class ChattingMessage {
    public String getContent() {
        return mainContent;
    }

    public int getTypeMessage() {
        return typeMessage;
    }

    public void setContent(String content) {
        mainContent = content;
    }

    public void setTypeMessage(int typeMessage) {
        this.typeMessage = typeMessage;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private String createTime;
    private String mainContent;
    private int typeMessage;

    public ChattingMessage(String content, int typeMessage){
        this.mainContent = content;
        this.typeMessage = typeMessage;
    }

    public ChattingMessage(BaseMessage message){
        UserMessage userMessage = (UserMessage) message;
        this.mainContent = userMessage.getMessage();
        if(userMessage.getSender().getUserId().equals(MCApp.getUserInstance().getUserId())){
            this.typeMessage = 0;
        }else{
            this.typeMessage = 1;
        }
        this.createTime = new SimpleDateFormat("MM/dd/yyyy").format(new Date(userMessage.getCreatedAt()));
    }

}
