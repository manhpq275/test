package asia.ienter.matching.models;

import java.util.Date;

/**
 * Created by phamquangmanh on 10/28/16.
 */
public class UserView extends BaseView {
    public String getImageUser() {
        return ImageUser;
    }

    public void setImageUser(String imageUser) {
        ImageUser = imageUser;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getIncome() {
        return ((Income!=null)? Income : "");
    }

    public void setIncome(String income) {
        Income = income;
    }

    public String getJob() {
        return ((Job!=null)? Job : "");
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    public int getMyLike() {
        return MyLike;
    }

    public void setMyLike(int myLike) {
        MyLike = myLike;
    }

    public int getOtherLike() {
        return OtherLike;
    }

    public void setOtherLike(int otherLike) {
        OtherLike = otherLike;
    }

    public int getMyLikeSpecial() {
        return MyLikeSpecial;
    }

    public void setMyLikeSpecial(int myLikeSpecial) {
        MyLikeSpecial = myLikeSpecial;
    }

    public int getOtherLikeSpecial() {
        return OtherLikeSpecial;
    }

    public void setOtherLikeSpecial(int otherLikeSpecial) {
        OtherLikeSpecial = otherLikeSpecial;
    }

    String ImageUser;
    String UserID;
    String UserName;
    String AccessToken;
    String FirstName;
    String LastName;
    int Gender;
    int Weight;
    int Height;
    String BirthDay;
    String Income;
    String Job;
    String Other;
    int MyLike;
    int OtherLike;
    int MyLikeSpecial;
    int OtherLikeSpecial;

}
