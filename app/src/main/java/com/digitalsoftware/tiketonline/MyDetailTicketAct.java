package com.digitalsoftware.tiketonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyDetailTicketAct extends AppCompatActivity {

    LinearLayout btn_back;
    DatabaseReference reference;
    TextView xnama_wisata, xlokasi, xdate_wisata, xtime_wisata, xketentuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail_ticket);

        xnama_wisata = findViewById(R.id.xnama_wisata);
        xlokasi = findViewById(R.id.xlokasi);
        xdate_wisata = findViewById(R.id.xdate_wisata);
        xtime_wisata = findViewById(R.id.xtime_wisata);
        xketentuan = findViewById(R.id.xketentuan);

        Bundle bundle = getIntent().getExtras();
        final String nama_wisata_baru = bundle.getString("nama_wisata");

        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(nama_wisata_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                xnama_wisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                xlokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                xdate_wisata.setText(dataSnapshot.child("date_wisata").getValue().toString());
                xtime_wisata.setText(dataSnapshot.child("time_wisata").getValue().toString());
                xketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent backtoprofileku = new Intent (MyDetailTicketAct.this, MyProfile.class);
                startActivity(backtoprofileku);

            }
        });

    }
}