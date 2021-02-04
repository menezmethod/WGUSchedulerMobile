package edu.wgu.wguschedulerlg.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.wgu.wguschedulerlg.Entity.Term;

@Dao
public interface TermDAO {
    @Query("SELECT * FROM term_table ORDER BY term_id")
    List<Term> getTermList();

    @Query("SELECT * FROM term_table WHERE term_id = :termID ORDER BY term_id")
    Term getTerm(int termID);

    @Query("SELECT * FROM term_table")
    List<Term> getAllTerms();

    @Insert
    void insertTerm(Term term);

    @Insert
    void insertAllTerms(Term... term);

    @Update
    void updateTerm(Term term);

    @Delete
    void deleteTerm(Term term);
}
