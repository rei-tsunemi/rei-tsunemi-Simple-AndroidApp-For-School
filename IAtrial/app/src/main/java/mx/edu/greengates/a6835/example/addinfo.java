package mx.edu.greengates.a6835.example;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addinfo extends AppCompatActivity {

    IADatabaseHelper database; //importing variable from another class called IADatabaseHelper.
    EditText addfirst, addlast, addphone, adddate; //defining variables for data entry spaces provided in xml file
    Button addInfo, contacts; //defining buttons used in xml file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinfo);
        database = new IADatabaseHelper(this);

        addfirst = (EditText)findViewById(R.id.editText_first);
        addlast = (EditText)findViewById(R.id.editText_last);
        addphone = (EditText)findViewById(R.id.editText_phone);
        adddate = (EditText)findViewById(R.id.editText_date);
        addInfo = (Button)findViewById(R.id.button_add);
        contacts = (Button)findViewById(R.id.viewbut);

        AddInfo(); //calling method called addinfo defined below
        showAllStudents(); // calling method called showallstudent defined below
    }

    public void AddInfo(){ //method to add data inputted by the user to the database.
        // This method created based on: “Save Data Using SQLite &nbsp;: &nbsp; Android Developers.” Android Developers, developer.android.com/training/data-storage/sqlite.
        addInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isAdded = database.insertData(addfirst.getText().toString(), addlast.getText().toString(), addphone.getText().toString(), adddate.getText().toString());
                        if (isAdded = true)
                            Toast.makeText(addinfo.this, "Successfully added", Toast.LENGTH_SHORT).show(); // pop out message to tell user if data is added or not
                        else
                            Toast.makeText(addinfo.this, "NOT ADDED!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void showAllStudents() {  //Method for showing all record stored in the table. Method is copied from class homescreen
        contacts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor c = database.getAllStudents();
                        if (c.getCount() == 0){
                            message("ERROR!!!!", "THERE IS NOTHING STORED!!");
                        }
                        else{
                            StringBuffer buff = new StringBuffer(); //StringBuffer variable taken from library which is used for making mutable String meaning unchangeable variable so that I could display exactly how its stored in the database
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

    public void message(String title, String Message){ //method for displaying title and message in a small tab. and method is copied from class homescreen
        // Method created based on Singh, Mithilesh. “Android SQLite Database Tutorial (Select, Insert, Update, Delete).” CodeBind.com, 24 July 2017, www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/.
        AlertDialog.Builder b = new AlertDialog.Builder(this); //AlertDialog is also taken from library which will show dialog message on screen
        b.setCancelable(true);
        b.setTitle(title);
        b.setMessage(Message);
        b.show();
    }

    public void backHome (View view){ //same method used in class homescreenA
        Intent intents = new Intent(getBaseContext(), homescreen.class);
        startActivity(intents);
    }
}
