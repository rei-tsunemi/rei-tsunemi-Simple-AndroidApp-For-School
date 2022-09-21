package mx.edu.greengates.a6835.example;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class contacts extends AppCompatActivity {

    IADatabaseHelper iaDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        // create our sqlite helper class
        iaDB = new IADatabaseHelper(this);
    }


}





