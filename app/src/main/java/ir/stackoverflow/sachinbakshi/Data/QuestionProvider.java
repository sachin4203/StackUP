package ir.stackoverflow.sachinbakshi.Data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by SACHIN on 17-04-2016.
 */
@ContentProvider(authority = QuestionProvider.AUTHORITY, database = QuestionDatabase.class)
public final class QuestionProvider {
    public static final String AUTHORITY =
            "ir.stackoverflow.sachinbakshi.Data.QuestionProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String QUESTIONS = "questions";

    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = QuestionDatabase.QUESTIONS)
    public static class Questions {
        @ContentUri(
                path = Path.QUESTIONS,
                type = "vnd.android.cursor.dir/questions",
                defaultSort = QuestionColumns.SCORE + " ASC")
        public static final Uri CONTENT_URI = buildUri(Path.QUESTIONS);

        @InexactContentUri(
                name = "QUESTION_ID",
                path = Path.QUESTIONS + "/#",
                type = "vnd.android.cursor.item/questions",
                whereColumn = QuestionColumns._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.QUESTIONS, String.valueOf(id));
        }
    }
}
