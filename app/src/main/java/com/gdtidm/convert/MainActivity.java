package com.gdtidm.convert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SwitchCompat switch_mode;
    private TextView tv_switch, tv_desimal, tv_biner, tv_oktal, tv_hexadecimal;
    private EditText form_input_bilangan;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_main);

        initWidget();
        switchMode();
        onClick();
    }

    public void initWidget() {
       switch_mode          =   findViewById(R.id.switch_modeapp);
       tv_switch            =   findViewById(R.id.teks_switchmodeapp);
       tv_desimal           =   findViewById(R.id.tvDesimal);
       tv_biner             =   findViewById(R.id.tvBiner);
       tv_oktal             =   findViewById(R.id.tvOktal);
       tv_hexadecimal       =   findViewById(R.id.tvHex);
       form_input_bilangan  =   findViewById(R.id.form_input);
       btn_submit           =   findViewById(R.id.bt_submit);
    }

    public void switchMode() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switch_mode.setChecked(true);
            tv_switch.setText("Dark Mode");
        }
    }

    public void onClick() {

        switch_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    InitApplication.getInstance().setIsNightModeEnabled(true);
                    tv_switch.setText("Dark Mode");
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);
                } else {
                    InitApplication.getInstance().setIsNightModeEnabled(false);
                    tv_switch.setText("Light Mode");
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);
                }
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (form_input_bilangan.getText().toString().isEmpty()) {
                    form_input_bilangan.setError("Oops, form inputan kosong!\nHarap diisi terlebih dahulu.");
                } else if (form_input_bilangan.getText().toString().length() > 3) {
                    form_input_bilangan.setError("Anda hanya dapat menambahkan angka sebanyak 3 angka saja.");
                } else {
                    int desimal         =   Integer.parseInt(form_input_bilangan.getText().toString());
                    String biner        =   Integer.toBinaryString(desimal);
                    String oktal        =   Integer.toOctalString(desimal);
                    String hexa         =   Integer.toHexString(desimal);

                    tv_desimal.setText(String.valueOf(desimal));
                    tv_biner.setText(String.valueOf(biner));
                    tv_oktal.setText(String.valueOf(oktal));
                    tv_hexadecimal.setText(String.valueOf(hexa));

                    closeKeyboardFromView();
                    Toast.makeText(MainActivity.this, "Anda sukses mengkonversi bilangan basis desimal!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void closeKeyboardFromView() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Apakah anda ingin keluar dari aplikasi?")
                .setCancelable(false);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.onBackPressed();
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}