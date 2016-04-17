package ir.stackoverflow.sachinbakshi.Data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by SACHIN on 17-04-2016.
 */
public interface QuestionColumns {

   /* public int quotaRemaining;
    public int quotaMax;
    public String userProfileImage;
    public String userDisplayName;
    public int score;
    public String questionLink;
    public String questionTitle;
    public int lastActivity;
    public String tags;*/

    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    public static final String _ID =
            "_id";

    @DataType(DataType.Type.INTEGER) @NotNull public static final String QUOTA_REMAINING=
            "quota_remaining";
    @DataType(DataType.Type.INTEGER) @NotNull public static final String QUOTA_MAX =
            "quota_max";
    @DataType(DataType.Type.TEXT) @NotNull public static final String USER_PROFILE_IMAGE =
            "user_profile_image";
    @DataType(DataType.Type.TEXT) @NotNull public static final String USER_DISPLAY_NAME =
            "user_display_name";
    @DataType(DataType.Type.INTEGER) @NotNull public static final String SCORE =
            "score";
    @DataType(DataType.Type.TEXT) @NotNull public static final String QUESTION_TITLE =
            "question_title";
    @DataType(DataType.Type.TEXT) @NotNull public static final String QUESTION_LINK =
            "question_link";
    @DataType(DataType.Type.INTEGER) @NotNull public static final String LAST_ACTIVITY =
            "last_activity";
    @DataType(DataType.Type.TEXT) @NotNull public static final String TAGS =
            "tags";

}
