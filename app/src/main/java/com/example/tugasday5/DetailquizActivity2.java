package com.example.tugasday5;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugasday5.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DetailquizActivity2 extends AppCompatActivity {
    private TextView textViewNamaPelanggan, textViewKodeBarang, textViewNamaBarang,
            textViewHarga, textViewTotalHarga, textViewDiskonHarga, textViewDiskonMember, textViewJumlahBayar;
    private Button sharebtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailquiz2); textViewNamaPelanggan = findViewById(R.id.nama);
        textViewKodeBarang = findViewById(R.id.kode);
        textViewNamaBarang = findViewById(R.id.namabarang);
        textViewHarga = findViewById(R.id.harga);
        textViewTotalHarga = findViewById(R.id.totalharga);
        textViewDiskonHarga = findViewById(R.id.diskonharga);
        textViewDiskonMember = findViewById(R.id.diskonmember);
        textViewJumlahBayar = findViewById(R.id.jumlahbayar);
        sharebtn = findViewById(R.id.share);

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData();
            }
        });

        Intent intent = getIntent();
        String namaPelanggan = intent.getStringExtra("Nama Pelanggan");
        String kodeBarang = intent.getStringExtra("Kode Barang");
        String namaBarang = intent.getStringExtra("Nama Barang");
        long harga = intent.getLongExtra("Harga", 0);
        int jumlahBarang = intent.getIntExtra("Jumlah Barang", 0);
        long totalHarga = intent.getLongExtra("Total Harga", 0);
        long discountHarga = intent.getLongExtra("Diskon Harga", 0);
        long discountMember = intent.getLongExtra("Diskon Member", 0);
        long jumlahBayar = intent.getLongExtra("Jumlah Bayar", 0);

        NumberFormat formatter = new DecimalFormat("#,###,###,###");
        String formattedHarga = "Rp " + formatter.format(harga);
        String formattedTotalHarga = "Rp " + formatter.format(totalHarga);
        String formattedDiscountHarga = "Rp " + formatter.format(discountHarga);
        String formattedDiscountMember = "Rp " + formatter.format(discountMember);
        String formattedJumlahBayar = "Rp " + formatter.format(jumlahBayar);

        textViewNamaPelanggan.setText("nama pelanggan:" + namaPelanggan);
        textViewKodeBarang.setText("kode barang:" + kodeBarang);
        textViewNamaBarang.setText("Nama Barang" + namaBarang);
        textViewHarga.setText( "Harga:" + formattedHarga);
        textViewTotalHarga.setText("Total Harga:" + formattedTotalHarga);
        textViewDiskonHarga.setText("Diskon Harga:" + formattedDiscountHarga);
        textViewDiskonMember.setText("Diskon Member:" + formattedDiscountMember);
        textViewJumlahBayar.setText("Jumlah Bayar:" + formattedJumlahBayar);
    }

    private void shareData() {
        String dataToShare = "Detail Transaksi\n" +
                textViewNamaPelanggan.getText().toString() + "\n" +
                textViewKodeBarang.getText().toString() + "\n" +
                textViewNamaBarang.getText().toString() + "\n" +
                textViewHarga.getText().toString() + "\n" +
                textViewTotalHarga.getText().toString() + "\n" +
                textViewDiskonHarga.getText().toString() + "\n" +
                textViewDiskonMember.getText().toString() + "\n" +
                textViewJumlahBayar.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, dataToShare);
        startActivity(Intent.createChooser(shareIntent, "Bagikan Melalui"));
    }
}