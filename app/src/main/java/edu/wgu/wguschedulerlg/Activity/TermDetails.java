package edu.wgu.wguschedulerlg.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import edu.wgu.wguschedulerlg.Database.LocalDB;
import edu.wgu.wguschedulerlg.Entity.Course;
import edu.wgu.wguschedulerlg.Entity.Term;
import edu.wgu.wguschedulerlg.R;

public class TermDetails extends AppCompatActivity {
    LocalDB db;
    ExtendedFloatingActionButton tdAddClassFAB;
    Intent intent;
    int termID;
    List<Course> allCourses;
    ListView tdClassList;
    TextView tdeDate;
    TextView tdName;
    TextView tdsDate;
    TextView tdStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        intent = getIntent();
        tdClassList = findViewById(R.id.tdClassList);
        db = LocalDB.getInstance(getApplicationContext());
        termID = intent.getIntExtra("termID", -1);
        tdName = findViewById(R.id.tdName);
        tdStatus = findViewById(R.id.tdStatus);
        tdName = findViewById(R.id.tdName);
        tdsDate = findViewById(R.id.tdSdate);
        tdeDate = findViewById((R.id.tdEdate));
        tdAddClassFAB = findViewById(R.id.tdAddClassFAB);
        updateClassList();
        setValues();
        tdAddClassFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCourse.class);
                intent.putExtra("termID", termID);
                startActivity(intent);
            }
        });
        tdClassList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), CourseDetails.class);
            intent.putExtra("termID", termID);
            intent.putExtra("courseID", allCourses.get(position).getCourse_id());
            startActivity(intent);
            System.out.println(id);
        });

    }

    private void setValues() {

        Term term = new Term();
        term = db.termDao().getTerm(termID);
        String name = term.getTerm_name();
        String status = term.getTerm_status();
        String sDate = DateFormat.format("MM/dd/yyyy", term.getTerm_start()).toString();
        String eDate = DateFormat.format("MM/dd/yyyy", term.getTerm_end()).toString();
        tdName.setText(name);
        tdStatus.setText(status);
        tdsDate.setText(sDate);
        tdeDate.setText(eDate);
    }

    private void updateClassList() {
        List<Course> allCourses = db.courseDao().getCourseList(termID);
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allCourses);
        tdClassList.setAdapter(adapter);
        this.allCourses = allCourses;

        adapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_term, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tdEditTermFAB:
                Intent intent = new Intent(getApplicationContext(), EditTerm.class);
                intent.putExtra("termID", termID);
                intent.putExtra("courseList", allCourses.size());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

