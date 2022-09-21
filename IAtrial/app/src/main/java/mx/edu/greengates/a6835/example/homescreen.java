package mx.edu.greengates.a6835.example;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class homescreen extends AppCompatActivity {

    IADatabaseHelper iadb; //variable importing from class IADatabaseHelper
    Button showAllinfo; //variable for using Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        iadb = new IADatabaseHelper(this); //instantiating variable iadb

        showAllinfo = (Button)findViewById(R.id.contactsbut); //defining button using id in xml file

        showAllStudents(); //calling the method showAllStudents
    }

    public void showAllStudents() {  //Method for showing all record stored in the table. Method based on
        showAllinfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor c = iadb.getAllStudents();
                        if (c.getCount() == 0){
                            message("ERROR!!!!", "THERE IS NOTHING STORED!!");
                        }
                        else{
                            StringBuffer buff = new StringBuffer();
                            //StringBuffer variable taken from library which is used for making mutable String meaning unchangeable variable
                            // so that I could display exactly how its stored in the database
                            while (c.moveToNext()) {
                                buff.append("ID: " + c.getString(0));
                                buff.append("\n" + "First Name: " + c.getString(1));
                                buff.append("\n" + "Last Name: " + c.getString(2));
                                buff.append("\n" + "Phone Number: " + c.getString(3));
                                buff.append("\n" + "Class Date: " + c.getString(4) + "\n");
                            }message("CONTACTS", buff.toString());
                        }
                    }
                }
        );
    }

    public void message(String title, String Message){ //method for displaying title and message in a small tab.
        // Method created based on Singh, Mithilesh. “Android SQLite Database Tutorial (Select, Insert, Update, Delete).” CodeBind.com, 24 July 2017,
        // www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/.
        AlertDialog.Builder b = new AlertDialog.Builder(this); //AlertDialog is also taken from library which will show dialog message on screen
        b.setCancelable(true);
        b.setTitle(title);
        b.setMessage(Message);
        b.show();
    }


    //two methods listed below are used for opening new screen with new class that are attached to xml files
    //Methods were created based on Android Developer. “Start Another Activity &nbsp;: &nbsp; Android Developers.”
    // Android Developers, developer.android.com/training/basics/firstapp/starting-activity.
    public void openShcedule (View view){
        Intent intents = new Intent(getBaseContext(), schedule.class);
        startActivity(intents);
    }

    public void openAddInfo (View view){
        Intent intents = new Intent(getBaseContext(), addinfo.class);
        startActivity(intents);
    }


}
