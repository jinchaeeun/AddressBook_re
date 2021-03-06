package com.example.addressbook;
//데이터를 저장하기 위한 기본단위인 Address 클래스
public class Address {
    //Member variable --------------------------
    private String name;
    private String phone;
    private String email;

    //Constructor Method ----------------------- 멤버 변수 초기화하는 메서드
    //Code (alt+c) - generate - Constructor 클릭 초기화시킬 변수 선택하면 자동 생성
    //           alt + insert - Constructor 도 가능
    // 생성자
    public Address(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    //Getter/Setter Method --------------------- 필요 없으면 안해도 되는 것임.
    public String getEmail() {
        return email;
    }

    //Custom Method -------------------------
    public String getInfo(){        //String 문자열 name, phone, email 다 넘어가도록 getInfo()라는 함수를 만듦
     return this.name+ " - " + this.phone + " - "+this.email;
    }
}
