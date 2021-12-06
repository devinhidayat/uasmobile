package id.ac.umn.hidayat.projectuas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class transaksi extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    private TextView check_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        TextView check_in = (TextView) findViewById(R.id.check_in);
        TextView check_out = (TextView) findViewById(R.id.check_out);
        TextView total = (TextView) findViewById(R.id.total);
        Button bayar = (Button) findViewById(R.id.bayar);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_back);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(transaksi.this,MainActivity.class);
                startActivity(intent);
            }
        });

        database  = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        
        Date d2 = Calendar.getInstance().getTime();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        String hour1 = hour.format(d2);
        String date3 = time.format(d2);
        check_out.setText(date3);

        int jam_check_out = Integer.parseInt(hour1);
        // int timeDiff = jam_check_out - jam_check_in;
        // int payment = 3000 + (timeDiff * 2000);
        // total.setText("Rp. "+String.valueOf(payment));

        getdata();
    }

    private void getdata() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                String value = snapshot.getValue(String.class);

                // after getting the value we are setting
                // our value to our text view in below line.
                check_in.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(transaksi.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}