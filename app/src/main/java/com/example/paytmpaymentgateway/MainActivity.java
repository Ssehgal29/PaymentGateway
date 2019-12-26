package com.example.paytmpaymentgateway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_orderId,edt_orderName;
    Button btn_pay;
    String strOrderId,strOrderName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askForPermissions();
        setId();
        setListener();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pay:
                setStringValues();
                    if (strOrderId.equals("")){
                        edt_orderId.setError("Please Enter Order Id!");
                        edt_orderId.requestFocus();
                    }else if (strOrderName.equals("")){
                        edt_orderName.setError("Please Enter Order Name!");
                        edt_orderName.requestFocus();
                    }else {
                        clearValues();
                        Toast.makeText(this, "Payment gateway!", Toast.LENGTH_SHORT).show();
                    }
                break;
        }
    }
    public void setId(){
        edt_orderId=findViewById(R.id.orderId);
        edt_orderName=findViewById(R.id.orderName);
        btn_pay=findViewById(R.id.pay);
    }
    public void setListener(){
        btn_pay.setOnClickListener(this);
    }
    public void setStringValues(){
        strOrderId=edt_orderId.getText().toString().trim();
        strOrderName=edt_orderName.getText().toString().trim();
    }
    public void clearValues(){
        edt_orderId.setText("");
        edt_orderName.setText("");
    }
    public void askForPermissions(){
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS},101);
        }
    }
}
