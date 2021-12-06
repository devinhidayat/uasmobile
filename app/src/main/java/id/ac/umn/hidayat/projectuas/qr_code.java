package id.ac.umn.hidayat.projectuas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

//    FirebaseDatabase database;
//    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        EditText et_plat = (EditText) findViewById(R.id.input_plat);
        Button btn_generate = (Button) findViewById(R.id.btn_generate);
        ImageView iv_qr = (ImageView) findViewById(R.id.output_qr);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_back);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qr_code.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date d2 = Calendar.getInstance().getTime();
                SimpleDateFormat time = new SimpleDateFormat("HH:mm");
                String jam_checkin = time.format(d2);

//                database  = FirebaseDatabase.getInstance();
//                myRef = database.getReference("Users");
//
//                myRef.setValue(jam_checkin);

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
    }
}