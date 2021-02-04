package edu.wgu.wguschedulerlg.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import edu.wgu.wguschedulerlg.Database.LocalDB;
import edu.wgu.wguschedulerlg.Entity.CourseMentor;
import edu.wgu.wguschedulerlg.R;

public class MentorDetails extends AppCompatActivity {
    LocalDB db;
    int termID;
    int courseID;
    int mentorID;
    Intent intent;
    TextView mdName;
    TextView mdPhone;
    TextView mdEmail;
    ExtendedFloatingActionButton mdEditFAB;
    boolean mentorDeleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_details);
        intent = getIntent();
        db = LocalDB.getInstance(getApplicationContext());
        termID = intent.getIntExtra("termID", -1);
        courseID = intent.getIntExtra("courseID", -1);
        mentorID = intent.getIntExtra("mentorID", -1);
        mdName = findViewById(R.id.mdName);
        mdPhone = findViewById(R.id.mdPhone);
        mdEmail = findViewById(R.id.mdEmail);
        mdEditFAB = findViewById(R.id.mdEditFAB);

        setValues();

        mdEditFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditMentor.class);
                intent.putExtra("termID", termID);
                intent.putExtra("courseID", courseID);
                intent.putExtra("mentorID", mentorID);
                startActivity(intent);
            }
        });

    }

    private void deleteMentor() {
        CourseMentor mentor = new CourseMentor();
        mentor = db.courseMentorDao().getMentor(courseID, mentorID);
        db.courseMentorDao().deleteMentor(mentor);
        Toast.makeText(this, "Mentor has been deleted", Toast.LENGTH_SHORT).show();
        mentorDeleted = true;
    }

    private void setValues() {
        CourseMentor mentor = new CourseMentor();
        mentor = db.courseMentorDao().getMentor(courseID, mentorID);
        String name = mentor.getMentor_name();
        String phone = mentor.getMentor_phone();
        String email = mentor.getMentor_email();

        mdName.setText(name);
        mdPhone.setText(phone);
        mdEmail.setText(email);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_mentor, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteMentorIC:
                deleteMentor();
                Intent intent = new Intent(getApplicationContext(), CourseDetails.class);
                intent.putExtra("termID", termID);
                intent.putExtra("courseID", courseID);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}