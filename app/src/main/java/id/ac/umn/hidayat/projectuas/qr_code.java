package id.ac.umn.hidayat.projectuas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class qr_code extends AppCompatActivity {
    private String user = FirebaseAuth.getInstance().getCurrentUser().getUid();

    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView emailTextView;

//    private static String value;
//    public static String getValue() {
//        return value;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

//        String email = getIntent().getStringExtra("email");
//        emailTextView = (TextView) findViewById(R.id.tvEmail);
//        emailTextView.setText(email);

        EditText et_plat = (EditText) findViewById(R.id.input_plat);
        Button btn_generate = (Button) findViewById(R.id.btn_generate);
        ImageView iv_qr = (ImageView) findViewById(R.id.output_qr);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_back);

        ImageButton back = (ImageButton) findViewById(R.id.back);

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date d1 = Calendar.getInstance().getTime();
                SimpleDateFormat time = new SimpleDateFormat("HH:mm");
                String jam_checkin = time.format(d1);

                database  = FirebaseDatabase.getInstance();
                myRef = database.getReference("User");

                String checkin = jam_checkin;

                String currentuser = user;

                myRef.child(currentuser).setValue(checkin).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //success
                    }
                });

                String plat = et_plat.getText().toString().trim();
                MultiFormatWriter writer = new MultiFormatWriter();

                try {
                    BitMatrix matrix = writer.encode(plat, BarcodeFormat.QR_CODE, 350, 350 );
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    iv_qr.setImageBitmap(bitmap);
                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    manager.hideSoftInputFromWindow(et_plat.getApplicationWindowToken(), 0);
                } catch (WriterException e) {
                    e.printStackTrace();
                }


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qr_code.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}