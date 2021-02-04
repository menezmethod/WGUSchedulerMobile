package edu.wgu.wguschedulerlg.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.wgu.wguschedulerlg.Entity.CourseMentor;

@Dao
public interface CourseMentorDAO {
    @Query("SELECT * FROM course_mentor_table WHERE course_id_fk = :courseID ORDER BY mentor_id")
    List<CourseMentor> getMentorList(int courseID);

    @Query("SELECT * FROM  course_mentor_table WHERE course_id_fk = :courseID and mentor_id = :mentorID")
    CourseMentor getMentor(int courseID, int mentorID);

    @Insert
    void insertMentor(CourseMentor courseMentor);

    @Insert
    void insertAllCourseMentors(CourseMentor... courseMentor);

    @Update
    void updateMentor(CourseMentor courseMentor);

    @Delete
    void deleteMentor(CourseMentor courseMentor);
}
