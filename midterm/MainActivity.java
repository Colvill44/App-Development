package edu.uncc.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Grade> grades = new ArrayList<Grade>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadGradesForTesting(); //TODO: added entries testing purposes only .. should be removed later.

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentView, new CourseGradesFragment())
                .commit();
    }

    private void loadGradesForTesting(){
        grades.add(new Grade("A", 4.0, DataServices.getAllCourses().get(0)));
        grades.add(new Grade("B", 3.0, DataServices.getAllCourses().get(1)));
        grades.add(new Grade("C", 2.0, DataServices.getAllCourses().get(2)));
        grades.add(new Grade("D", 1.0, DataServices.getAllCourses().get(3)));
    }
}