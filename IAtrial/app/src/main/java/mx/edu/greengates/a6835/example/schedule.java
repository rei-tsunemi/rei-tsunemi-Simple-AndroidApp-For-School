package mx.edu.greengates.a6835.example;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class schedule extends AppCompatActivity {

    CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatmonth = new SimpleDateFormat("MMMM - YYYY", Locale.getDefault()); // specifying the format of data shown on the calendar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shcedule);


        //Calendar view and how to format is taken from online source
        //SundeepK. “SundeepK/CompactCalendarView.” GitHub, 19 June 2018, github.com/SundeepK/compactcalendarview.
        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setTitle(null);

        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        Event dayoff = new Event(Color.RED, 1584715355L, "Day off"); //March 20th 2020
        compactCalendarView.addEvent(dayoff);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if ( dateClicked.toString().compareTo("Friday March 20th 2020" )== 0){
                    Toast.makeText(schedule.this, "Day off !!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(schedule.this, "I have a class to do", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionbar.setTitle(dateFormatmonth.format(firstDayOfNewMonth));

            }
        });
    }


}
