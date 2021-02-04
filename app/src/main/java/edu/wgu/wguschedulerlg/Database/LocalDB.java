package edu.wgu.wguschedulerlg.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import edu.wgu.wguschedulerlg.DAO.AssessmentDAO;
import edu.wgu.wguschedulerlg.DAO.CourseDAO;
import edu.wgu.wguschedulerlg.DAO.CourseMentorDAO;
import edu.wgu.wguschedulerlg.DAO.TermDAO;
import edu.wgu.wguschedulerlg.Entity.Assessment;
import edu.wgu.wguschedulerlg.Entity.Course;
import edu.wgu.wguschedulerlg.Entity.CourseMentor;
import edu.wgu.wguschedulerlg.Entity.Term;
import edu.wgu.wguschedulerlg.Utilities.Converters;

@androidx.room.Database(entities = {Term.class, Course.class, CourseMentor.class, Assessment.class}, exportSchema = false, version = 5)
@TypeConverters({Converters.class})
public abstract class LocalDB extends RoomDatabase {

    private static final String DB_Name = "wguschedulerc196.db";
    private static LocalDB instance;

    public static synchronized LocalDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), LocalDB.class, DB_Name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract TermDAO termDao();

    public abstract CourseDAO courseDao();

    public abstract CourseMentorDAO courseMentorDao();

    public abstract AssessmentDAO assessmentDao();
}
