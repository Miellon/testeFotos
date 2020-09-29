package com.example.exfoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.BitSet;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Button btnLeitura;
    TextView txtLeitura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retiraFoto();
        lerCodigos();
    }

    private void retiraFoto(){
        image = (ImageView)findViewById(R.id.SuaImagem);
        Button botaoFoto;
        botaoFoto = (Button)findViewById(R.id.btnTiraFoto);

        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }



    private void lerCodigos(){
        txtLeitura = (TextView)findViewById(R.id.edtLeitura);
        btnLeitura = (Button)findViewById(R.id.btnLerCodigo);
        btnLeitura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent, 0);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bundle bundle = data.getExtras();
        //if (data!=null){

        //    Bitmap bitmap = (Bitmap)bundle.get("data");
        //    image.setImageBitmap(bitmap);
       // }

        if (requestCode==0){
            txtLeitura.setText(data.getStringExtra("SCAN_RESULT"));
        }
    }
}