package ir.stackoverflow.sachinbakshi;

/**
 * Created by SACHIN on 17-04-2016.
 */
public class ListItemModel {


    public int quotaRemaining;
    public int quotaMax;
    public String userProfileImage;
    public String userDisplayName;
    public int score;
    public String questionLink;
    public String questionTitle;
    public int lastActivity;
    public String tags;

    public int getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(int quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

    public int getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(int quotaMax) {
        this.quotaMax = quotaMax;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getQuestionLink() {
        return questionLink;
    }

    public void setQuestionLink(String questionLink) {
        this.questionLink = questionLink;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public int getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(int lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
