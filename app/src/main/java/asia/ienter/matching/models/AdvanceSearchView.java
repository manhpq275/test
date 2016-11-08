package asia.ienter.matching.models;

import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by phamquangmanh on 10/31/16.
 */
public class AdvanceSearchView extends BaseView {
    public AdvanceSearchView(String id, String name) {
        super(id, name);
    }

    private boolean isAvatar=false;
    private boolean isDescription=false;
    private long onlineAgo=0;
    private int minYearOld=0;
    private int maxYearOld=0;
    private int address=0;
    private int homeLand=0;
    private int height=0;
    private ArrayList<Integer> languages = null;
    private int externality=0;
    private int bloodGroup=0;
    private int job=0;
    private long gd=0;
    private int level=0;
    private ArrayList<Integer> genitive= null;
    private ArrayList<Integer> likeForm= null;
    private int forte=0;
    private int drinkWine=0;
    private int smoke=0;
    private ArrayList<Integer> siblings= null;
    private ArrayList<Integer> liveWith= null;
    private int holiday=0;
    private int minMarryTime=0;
    private int minMeetingTime=0;
    private int marriageHistory=0;
    private int children=0;
    private ArrayList<Integer> impressives= null;
    private boolean isMatchingGroup=false;
    private int matchingPay=0;
    private ArrayList<Integer> likes= null;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    private int distance =0;

    public boolean isAvatar() {
        return isAvatar;
    }

    public void setAvatar(boolean avatar) {
        isAvatar = avatar;
    }

    public boolean isDescription() {
        return isDescription;
    }

    public void setDescription(boolean description) {
        isDescription = description;
    }

    public long getOnlineAgo() {
        return onlineAgo;
    }

    public void setOnlineAgo(long onlineAgo) {
        this.onlineAgo = onlineAgo;
    }

    public int getMinYearOld() {
        return minYearOld;
    }

    public void setMinYearOld(int minYearOld) {
        this.minYearOld = minYearOld;
    }

    public int getMaxYearOld() {
        return maxYearOld;
    }

    public void setMaxYearOld(int maxYearOld) {
        this.maxYearOld = maxYearOld;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getHomeLand() {
        return homeLand;
    }

    public void setHomeLand(int homeLand) {
        this.homeLand = homeLand;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Integer> languages) {
        this.languages = languages;
    }

    public int getExternality() {
        return externality;
    }

    public void setExternality(int externality) {
        this.externality = externality;
    }

    public int getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(int bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public long getGd() {
        return gd;
    }

    public void setGd(long gd) {
        this.gd = gd;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Integer> getGenitive() {
        return genitive;
    }

    public void setGenitive(ArrayList<Integer> genitive) {
        this.genitive = genitive;
    }

    public ArrayList<Integer> getLikeForm() {
        return likeForm;
    }

    public void setLikeForm(ArrayList<Integer> likeForm) {
        this.likeForm = likeForm;
    }

    public int getForte() {
        return forte;
    }

    public void setForte(int forte) {
        this.forte = forte;
    }

    public int getDrinkWine() {
        return drinkWine;
    }

    public void setDrinkWine(int drinkWine) {
        this.drinkWine = drinkWine;
    }

    public int getSmoke() {
        return smoke;
    }

    public void setSmoke(int smoke) {
        this.smoke = smoke;
    }

    public ArrayList<Integer> getSiblings() {
        return siblings;
    }

    public void setSiblings(ArrayList<Integer> siblings) {
        this.siblings = siblings;
    }

    public ArrayList<Integer> getLiveWith() {
        return liveWith;
    }

    public void setLiveWith(ArrayList<Integer> liveWith) {
        this.liveWith = liveWith;
    }

    public int getHoliday() {
        return holiday;
    }

    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }

    public int getMinMarryTime() {
        return minMarryTime;
    }

    public void setMinMarryTime(int minMarryTime) {
        this.minMarryTime = minMarryTime;
    }

    public int getMinMeetingTime() {
        return minMeetingTime;
    }

    public void setMinMeetingTime(int minMeetingTime) {
        this.minMeetingTime = minMeetingTime;
    }

    public int getMarriageHistory() {
        return marriageHistory;
    }

    public void setMarriageHistory(int marriageHistory) {
        this.marriageHistory = marriageHistory;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public ArrayList<Integer> getImpressives() {
        return impressives;
    }

    public void setImpressives(ArrayList<Integer> impressives) {
        this.impressives = impressives;
    }

    public boolean isMatchingGroup() {
        return isMatchingGroup;
    }

    public void setMatchingGroup(boolean matchingGroup) {
        isMatchingGroup = matchingGroup;
    }

    public int getMatchingPay() {
        return matchingPay;
    }

    public void setMatchingPay(int matchingPay) {
        this.matchingPay = matchingPay;
    }

    public ArrayList<Integer> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Integer> likes) {
        this.likes = likes;
    }


}
