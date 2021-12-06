package id.ac.umn.hidayat.projectuas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout qr_code = (LinearLayout) findViewById(R.id.qr_code);
        LinearLayout cameraView = (LinearLayout) findViewById(R.id.camera_view);
        LinearLayout transaksi = (LinearLayout) findViewById(R.id.transaksi);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_logout);

        ImageButton logout = (ImageButton) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });

        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this,qr_code.class);
                startActivity(intent);
            }
        });

        cameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this,cameraView.class);
                startActivity(intent);
            }
        });

        transaksi.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,transaksi.class);
                startActivity(intent);
            }
        }));

        TextView test = findViewById(R.id.date);
        Date d1 = Calendar.getInstance().getTime();
        SimpleDateFormat datenow = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String date2 = datenow.format(d1);
        test.setText(date2);

        TextView test1 = findViewById(R.id.time);
        Date d2 = Calendar.getInstance().getTime();
        SimpleDateFormat time= new SimpleDateFormat("HH:mm");
        String date3 = time.format(d2);
        test1.setText(date3);

    }

}