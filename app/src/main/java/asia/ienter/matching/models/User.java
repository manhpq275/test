package asia.ienter.matching.models;

import android.text.TextUtils;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import asia.ienter.matching.models.enums.Genitives;
import asia.ienter.matching.models.enums.Languages;

/**
 * Created by hoangtuan on 11/15/16.
 */
public class User implements Serializable{
    public static final int INIT_VALUE = -1;
    public static final int DEFAULT_VALUE = 0;


    public String getAccessToken() {
        return AccessToken==null?"":AccessToken;
    }

    public String getUserName() {
        return UserName==null?"":UserName;
    }

    public String getFirstName() {
        return FirstName==null?"":FirstName;
    }

    public String getLastName() {
        return LastName==null?"":LastName;
    }

    public int isGender() {
        return  Gender;
    }

    public String getEmail() {
        return Email==null?"":Email;
    }

    public String getBirthday() {
        return BirthDay==null?"":BirthDay;
    }

    public String getPassword() {
        return Password==null?"":Password;
    }

    public int getHeight() {
        return Height;
    }

    public int getWeight() {
        return Weight;
    }

    public int getNo() {
        return No;
    }

    public String getSkills() {
        return Skills==null?"":Skills;
    }




    public String getUserAddress() {
        return Address==null?"":Address;
    }

    public String getHometown() {
        if(HomeTown.isEmpty()) return  "";
        if(HomeTown.contains("0,")){
            return HomeTown.split(",")[1];
        }else{
            return HomeTown;
        }
    }

    public List<String> getLanguage() {
        if(Language!=null) {
            List<String> result = new ArrayList<>();
            if (!Language.isEmpty()) {
                String[] listEnum = Language.split(",");
                for (int i = 0; i < listEnum.length; i++) {
                    result.add(Languages.fromInteger(Integer.parseInt(listEnum[i])).toString());
                }
            }
            return result;
        }
        return new ArrayList<String>();
    }

    public ArrayList<Integer> getLanguageByInt() {
        if(Language!=null) {
            ArrayList<Integer> result = new ArrayList<>();
            if(!Language.isEmpty()){
                String[] listEnum = Language.split(",");
                for(int i=0;i<listEnum.length;i++){
                    result.add(Integer.parseInt(listEnum[i]));
                }
            }
            return result;
        }
        return new ArrayList<Integer>();
    }

    public String getBloodType() {
        return BloodType==null?"":BloodType;
    }

    public String getJob() {

        return Job==null?"":Job;
    }

    public String getIncome() {
        return Income==null?"":Income;
    }

    public String getLiteracy() {
        return Literacy==null?"":Literacy;
    }



    public int getDrinking() {
        return Drinking;
    }

    public int getSmoking() {
        return Smoking;
    }

    public String getSibling() {
        return Sibling==null?"":Sibling;
    }

    public String getWhoLiveWith() {
        return WhoLivesWith==null?"":WhoLivesWith;
    }

    public String getHoliday() {
        return Holiday==null?"":Holiday;
    }

    public String getWhenMarriage() {
        return WhenMarriage==null?"":WhenMarriage;
    }

    public String getRequirement() {
        return Requirement==null?"":Requirement;
    }

    public String getMaritalStatus() {
        return MaritalStatus==null?"":MaritalStatus;
    }

    public int isHavingChildren() {
        return HavingChildren;
    }

    public String getCriteriaConsidered() {
        return CriteriaConsidered==null?"":CriteriaConsidered;
    }

    public String getWantAppointments() {
        return WantAppointments==null?"":WantAppointments;
    }

    public String getCostForFirstAppointments() {
        return CostForFirstAppointments==null?"":CostForFirstAppointments;
    }

    public String getOther() {
        return Other==null?"":Other;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public void setNo(int no) {
        No = no;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }

    public void setUserAddress(String userAddress) {
        Address = userAddress;
    }

    public void setHometown(String hometown) {
        HomeTown = hometown;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public void setJob(String job) {
        Job = job;
    }

    public void setIncome(String income) {
        Income = income;
    }

    public void setLiteracy(String literacy) {
        Literacy = literacy;
    }

    public void setGenitive(String genitive) {
        Genitive = genitive;
    }

    public void setExtrovert(String extrovert) {
        Extrovert = extrovert;
    }

    public void setAttraction(String attraction) {
        Attraction = attraction;
    }

    public void setDrinking(int drinking) {
        Drinking = drinking;
    }

    public void setSmoking(int smoking) {
        Smoking = smoking;
    }

    public void setSibling(String sibling) {
        Sibling = sibling;
    }

    public void setWhoLiveWith(String whoLiveWith) {
        WhoLivesWith = whoLiveWith;
    }

    public void setHoliday(String holiday) {
        Holiday = holiday;
    }

    public void setWhenMarriage(String whenMarriage) {
        WhenMarriage = whenMarriage;
    }

    public void setRequirement(String requirement) {
        Requirement = requirement;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }

    public void setHavingChildren(int havingChildren) {
        HavingChildren = havingChildren;
    }

    public void setCriteriaConsidered(String criteriaConsidered) {
        CriteriaConsidered = criteriaConsidered;
    }

    public void setWantAppointments(String wantAppointments) {
        WantAppointments = wantAppointments;
    }

    public void setCostForFirstAppointments(String costForFirstAppointments) {
        CostForFirstAppointments = costForFirstAppointments;
    }

    public void setOther(String other) {
        Other = other;
    }


    public String getFacebookId() {
        return FacebookId==null?"":FacebookId;
    }

    public void setFacebookId(String facebookId) {
        FacebookId = facebookId;
    }


    public String getUserCover() {
        return UserCover==null?"":UserCover;
    }

    public String getUserProfilePic() {
        return UserProfilePic==null?"":UserProfilePic;
    }

    public void setUserCover(String userCover) {
        UserCover = userCover;
    }

    public void setUserProfilePic(String userProfilePic) {
        UserProfilePic = userProfilePic;
    }

    public String getUserCompany() {
        return UserCompany==null?"":UserCompany;
    }

    public void setUserCompany(String userCompany) {
        UserCompany = userCompany;
    }

    public String getUserId() {
        return UserID==null?"":UserID;
    }

    public void setUserId(String userId) {
        UserID = userId;
    }

    private String UserID="";
    private String UserCompany="";
    private String UserCover="";
    private String UserProfilePic="";
    private String AccessToken="";
    private String FacebookId="";
    private String UserName="";
    private String FirstName="";
    private String LastName="";
    private String Email="";
    private String BirthDay="2015-12-30";
    private String Password="";
    private String ChangedDate="1799-01-01";
    private String Skills="";
    private String Hobby="";
    private String Address="";
    private String HomeTown="";
    private String Language="";
    private String BloodType="";
    private String Job="";
    private String Income="";
    private String Literacy="";
    private String Genitive="";
    private String Extrovert="";
    private String Attraction="";
    private String Sibling="";
    private String WhoLivesWith="";
    private String Holiday="";
    private String WhenMarriage="";
    private String Requirement="";
    private String MaritalStatus="";
    private String CriteriaConsidered="";
    private String WantAppointments="";
    private String CostForFirstAppointments="";
    private String Other ="";
    private int No;
    private int Gender = INIT_VALUE;
    private int Height = INIT_VALUE;
    private int Weight = INIT_VALUE;
    private int Drinking = INIT_VALUE;
    private int Smoking = INIT_VALUE;
    private int HavingChildren = INIT_VALUE;

    public int getAnonymous() {
        return Anonymous;
    }

    public void setAnonymous(int anonymous) {
        Anonymous = anonymous;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }

    public int getDistance() {
        return Distance;
    }

    public void setDistance(int distance) {
        Distance = distance;
    }

    private int Anonymous = DEFAULT_VALUE;
    private double Lat = DEFAULT_VALUE;
    private double Long = DEFAULT_VALUE;
    private int Distance = 50;

    public User(){

    }

    public void setLanguage(ArrayList<Integer> listSelected) {
        Language = TextUtils.join(",", listSelected);
        Log.i("Data", Language);
    }

    public void setGenitive(ArrayList<Integer> listSelected) {
        Genitive = TextUtils.join(",", listSelected);
        Log.i("Data", Genitive);
    }

    public void setAttraction(ArrayList<Integer> listSelected) {
        Attraction = TextUtils.join(",", listSelected);
        Log.i("Data", Attraction);
    }

    public void removeTagHobby(String tag) {
        if(Hobby == null){
            Hobby = "";
        }
        if(!Hobby.isEmpty()) {
            ArrayList<String> listHobby = new ArrayList<>();
            listHobby.addAll(Arrays.asList(Hobby.split(",")));
            listHobby.remove(tag);
            Hobby = TextUtils.join(",", listHobby);
        }
    }

    public void addHobby(String tag) {
        if(Hobby == null){
            Hobby = "";
        }
        if(!Hobby.isEmpty()) {
            ArrayList<String> listHobby = new ArrayList<>();
            listHobby.addAll(Arrays.asList(Hobby.split(",")));
            listHobby.add(tag);
            Hobby = TextUtils.join(",", listHobby);
        }else{
            Hobby+=tag;
        }
    }

    public void setBirthday(String birthday) {
        if(!birthday.isEmpty()) {
            String[] stringBrithday = birthday.split("/");
            if(Integer.parseInt(stringBrithday[2])>0) {
                BirthDay = (stringBrithday[2] + "-" + stringBrithday[1] + "-" + stringBrithday[0]).trim();
            }else{
                BirthDay = "NA";
            }
        }else{
            BirthDay = "NA";
        }
    }

    public List<String> getGenitive() {
        if(Genitive!=null) {
            List<String> result = new ArrayList<>();
            if (!Genitive.isEmpty()) {
                String[] listEnum = Genitive.split(",");
                for (int i = 0; i < listEnum.length; i++) {
                    result.add(Genitives.fromInteger(Integer.parseInt(listEnum[i])).toString());
                }
            }
            return result;
        }
        return new ArrayList<>();
    }

    public ArrayList<Integer> getGenitiveByInt() {
        if(Genitive!=null) {
            ArrayList<Integer> result = new ArrayList<>();
            if (!Genitive.isEmpty()) {
                String[] listEnum = Genitive.split(",");
                for (int i = 0; i < listEnum.length; i++) {
                    result.add(Integer.parseInt(listEnum[i]));
                }
            }
            return result;
        }
        return new ArrayList<Integer>();
    }

    public String getExtrovert() {
        return Extrovert==null?"":Extrovert;
    }

    public List<String> getAttraction() {
        if(Attraction!=null) {
            List<String> result = new ArrayList<>();
            if (!Attraction.isEmpty()) {
                String[] listEnum = Attraction.split(",");
                for (int i = 0; i < listEnum.length; i++) {
                    result.add(Genitives.fromInteger(Integer.parseInt(listEnum[i])).toString());
                }
            }
            return result;
        }
        return new ArrayList<String>();
    }

    public ArrayList<Integer> getAttractionByInt() {
        if(Attraction!=null) {
            ArrayList<Integer> result = new ArrayList<>();
            if (!Attraction.isEmpty()) {
                String[] listEnum = Attraction.split(",");
                for (int i = 0; i < listEnum.length; i++) {
                    result.add(Integer.parseInt(listEnum[i]));
                }
            }
            return result;
        }
        return new ArrayList<Integer>();
    }

    public List<String> getHobby() {
        if(Hobby == null){
            Hobby = "";
        }
        return (Hobby.isEmpty())? new ArrayList<String>() : Arrays.asList(Hobby.split(","));
    }

}
