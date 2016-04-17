package ir.stackoverflow.sachinbakshi.Data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by SACHIN on 17-04-2016.
 */
@Database(version = QuestionDatabase.VERSION)
public final class QuestionDatabase {
    private QuestionDatabase() {
    }

    public static final int VERSION = 2;


    @Table(QuestionColumns.class)
    public static final String QUESTIONS= "questions";
}