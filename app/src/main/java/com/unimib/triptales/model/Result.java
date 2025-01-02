package com.unimib.triptales.model;

public abstract class Result {
    private Result(){}

    public boolean isSuccess(){
        return !(this instanceof Error);
    }

    public static final class UserSuccess extends Result{
        private final User user;
        public UserSuccess(User user){
            this.user = user;
        }
        public User getData(){
            return user;
        }
    }

    public static final class Error extends Result{
        private final String message;
        public Error(String message){
            this.message = message;
        }
        public String getMessage(){
            return message;
        }
    }
}
