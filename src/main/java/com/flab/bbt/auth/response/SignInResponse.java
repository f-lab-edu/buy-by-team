package com.flab.bbt.auth.response;

public class SignInResponse {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNo;

    public static SignInResponse.SignInResponseBuilder builder(){
        return new SignInResponse.SignInResponseBuilder();
    }
    public static class SignInResponseBuilder{
        private Long id;
        private String email;
        private String password;
        private String name;
        private String phoneNo;

        SignInResponseBuilder(){}

        public SignInResponse.SignInResponseBuilder email(String email){
            this.email = email;
            return this;
        }

        public SignInResponse.SignInResponseBuilder password(String password){
            this.password = password;
            return this;
        }

        public SignInResponse.SignInResponseBuilder name(String name){
            this.name = name;
            return this;
        }

        public SignInResponse.SignInResponseBuilder phoneNo(String phoneNo){
            this.phoneNo = phoneNo;
            return this;
        }

        public SignInResponse build(){
            return new SignInResponse(this.email,this.password,this.name,this.phoneNo);
        }
    }

    public SignInResponse(String email, String password, String name, String phoneNo) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNo = phoneNo;
    }
}
