package com.example.tugasday5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText et1, et2, et3;
    private RadioGroup rdG;
    private Button Masukbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.pelangganku);
        et2 = findViewById(R.id.kodebarangku);
        et3 = findViewById(R.id.jumlahbarangku);
        rdG = findViewById(R.id.radiogroup);
        Masukbtn = findViewById(R.id.btnmasuk);

        Masukbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesTransaksi();
            }
        });
    }

    private void prosesTransaksi() {
        String namaPelanggan = et1.getText().toString();
        String kodeBarang = et2.getText().toString();
        String jumlahBarangString = et3.getText().toString();

        if (namaPelanggan.isEmpty() || kodeBarang.isEmpty() || jumlahBarangString.isEmpty()) {
            Toast.makeText(MainActivity.this, "Mohon isi semua field", Toast.LENGTH_SHORT).show();
            return;
        }

        int jumlahBarang;
        try {
            jumlahBarang = Integer.parseInt(jumlahBarangString);
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Jumlah barang tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        long harga = getHarga(kodeBarang);
        if (harga == -1) {
            Toast.makeText(MainActivity.this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        long totalHarga = harga * jumlahBarang;
        long diskonHarga = hitungDiskonHarga(totalHarga);
        long diskonMember = hitungDiskonMember(totalHarga);

        long jumlahBayar = totalHarga - diskonHarga - diskonMember;

        Intent intent = new Intent(MainActivity.this, com.example.tugasday5.DetailquizActivity2.class);
        intent.putExtra("Nama Pelanggan", namaPelanggan);
        intent.putExtra("Kode Barang", kodeBarang);
        intent.putExtra("Nama Barang", getNamaBarang(kodeBarang));
        intent.putExtra("Harga", harga);
        intent.putExtra("Jumlah Barang", jumlahBarang);
        intent.putExtra("Total Harga", totalHarga);
        intent.putExtra("Diskon Harga", diskonHarga);
        intent.putExtra("Diskon Member", diskonMember);
        intent.putExtra("Jumlah Bayar", jumlahBayar);
        startActivity(intent);
    }

    private long getHarga(String kodeBarang) {
        switch (kodeBarang) {
            case "AA5":
                return 9999999;
            case "OAS":
                return 1989123;
            case "AAE":
                return 8676981;
            default:
                return -1;
        }
    }

    private String getNamaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "AA5":
                return "Acer Aspire 5";
            case "OAS":
                return "Oppo a5s";
            case "AAE":
                return "Acer Aspire E14";
            default:
                return "";
        }
    }

    private long hitungDiskonHarga(long totalHarga) {
        if (totalHarga > 10000000) {
            return 100000;
        }
        return 0;
    }

    private long hitungDiskonMember(long totalHarga) {


        RadioButton radioButton = findViewById(rdG.getCheckedRadioButtonId());
        String membership = radioButton.getText().toString();
        switch (membership) {
            case "Gold":
                return (long) (totalHarga * 0.10);
            case "Silver":
                return (long) (totalHarga * 0.05);
            case "Biasa":
                return (long) (totalHarga * 0.02);
            default:
                return 0;
        }
    }
}
