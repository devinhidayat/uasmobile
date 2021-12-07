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
import java.util.HashMap;

public class transaksi extends AppCompatActivity {

//    FirebaseDatabase database;
//    DatabaseReference myRef;
    private TextView check_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        TextView check_out = (TextView) findViewById(R.id.check_out);
        TextView total = (TextView) findViewById(R.id.total);
        Button bayar = (Button) findViewById(R.id.bayar);

        Date d2 = Calendar.getInstance().getTime();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        String hour1 = hour.format(d2);
        String date3 = time.format(d2);
        check_out.setText(date3);

        TextView check_in = (TextView) findViewById(R.id.check_in);
        check_in.setText(MainActivity.getValue());
        String jam = check_in.getText().toString();
        String[] parts = jam.split(":");
        String jam_checkin = parts[0];

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

//        database  = FirebaseDatabase.getInstance();
//        myRef = database.getReference("User");

//        HashMap<String, Object> map = new HashMap<>();
//        map.put("Jam Check-in", check_in);
//        map.put("Jam Check-out", check_out);
//
//        FirebaseDatabase.getInstance().getReference("User").push().updateChildren(map);

        int jam_check_out = Integer.parseInt(hour1);
        int jam_check_in = Integer.parseInt(jam_checkin);
        int timeDiff = jam_check_out - jam_check_in;
        int payment = 3000 + (timeDiff * 2000);
        total.setText("Rp. "+String.valueOf(payment));

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (transaksi.this,login.class);
                Toast.makeText(transaksi.this, "Pembayaran berhasil!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}