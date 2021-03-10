package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Member Variable ----------------------------------------
    // 디버깅을 위한 변수
    private final boolean       D           = true;             // 로그 디버그 찍을 때
    private final String        TAG         = "MainActivity";  //final은 상수기 때문에 변수를 대문자로 적기로 규약

    // View Object
    private EditText            nameETXT, phoneETXT, emailETXT;
    private TextView            addressTXT;
    //버튼은 클릭 이벤트 지정해줬기 때문에 선언 필요 X

    // Data
    private ArrayList<Address>  addressList = new ArrayList<>(); //동적으로 변하기 때문에 Array 사용 못함

    
    //Member Method - AppCompatActivity's override -----------이 액티비티에 오버라이드
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activity 화면 설정 필수!!
        setContentView(R.layout.activity_main);

        //XML View 객체 가져오기
        nameETXT = findViewById(R.id.nameETXT);
        phoneETXT = findViewById(R.id.phoneETXT);
        emailETXT = findViewById(R.id.emailETXT);
        addressTXT = findViewById(R.id.addressTXT);

        if(D) Log.i(TAG, "onCreate()"); //Log를 지우기 번거로우니 if문으로 처리

    }
    //Member Method - XML onClick Method----------------------------------
    public void click(View v){
        switch (v.getId()){
            case R.id.addBTN:
                // 3개의 EditText 값 읽어서 Address 객체 생성 및 추가
                // (1) EditText 3개 값 입력 여부 체크
                if(nameETXT.getText().length()>0 && phoneETXT.getText().length()>0 && emailETXT.getText().length()>0){
                    // (2-1) Address 객체 생성 및 ArrayList 추가
                    addressList.add(new Address(nameETXT.getText().toString(), 
                                                phoneETXT.getText().toString(), 
                                                emailETXT.getText().toString()));
                    Log.i(TAG, "add => "+ addressList.size()); //현재 입력된 갯수

                    // 3개 입력 필드 초기화(지우기)
                    initEXIT();

                    //TextView에 데이터 출력
                    displayAddress();

                }else{
                    // (2-2) 사용자에게 알림 띄우기
                    Toast.makeText(this, R.string.add_msg, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.delBTN:
                // 모두 삭제 또는 가장 최근에 추가한 데이터 삭제
                //가장 최근 삭제
                if(addressList.size()>0) {
                    int lastIdx = addressList.size() - 1;
                    addressList.remove(lastIdx);

                    //TextView에 Address 데이터 출력
                    displayAddress();
                }else{
                    Toast.makeText(this, R.string.del_msg, Toast.LENGTH_LONG).show();
                }

                //모두 삭제
                /*
                if(addressList.size()>0) {
                    addressList.clear();
                    addressTXT.setText(R.string.nothing);
                }else{
                    Toast.makeText(this, R.string.del_msg, Toast.LENGTH_LONG).show();
                }
                */

                break;
        }
    }
    //Member Method - Custom----------------------------------
    //3개 입력 필드 초기화
    private void initEXIT(){
        nameETXT.setText("");
        phoneETXT.setText("");
        emailETXT.setText("");
    }

    // AddressList 출력 메서드 --------------------------------- TextView 출력
    private void displayAddress(){
        String datas = "";
        for (int idx = 0; idx < addressList.size(); idx++) {
            datas += addressList.get(idx).getInfo() + "\n"; //줄 바꿔서 출력
        }
        //datas 값 존재 여부 체크
        if(datas.length()>0)
            addressTXT.setText(datas);
        else
            addressTXT.setText(R.string.nothing);
    }
}