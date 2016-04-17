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
            "overview";
    @DataType(DataType.Type.INTEGER) @NotNull public static final String QUOTA_MAX =
            "poster_path";
    @DataType(DataType.Type.TEXT) @NotNull public static final String USER_PROFILE_IMAGE =
            "vote_average";
    @DataType(DataType.Type.TEXT) @NotNull public static final String USER_DISPLAY_NAME =
            "release_date";
    @DataType(DataType.Type.INTEGER) @NotNull public static final String SCORE =
            "backdrop_path";
    @DataType(DataType.Type.TEXT) @NotNull public static final String QUESTION_TITLE =
            "backdrop_path";
    @DataType(DataType.Type.TEXT) @NotNull public static final String QUESTION_LINK =
            "backdrop_path";
    @DataType(DataType.Type.INTEGER) @NotNull public static final String LAST_ACTIVITY =
            "backdrop_path";
    @DataType(DataType.Type.TEXT) @NotNull public static final String TAGS =
            "backdrop_path";

}
