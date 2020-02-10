package com.dummy.shared.excetion;

public enum ErrorMessages {
    INVALID_INPUT(" input validation error "),
    INTERNAL_SERVER_ERROR("Internal server error");
    
   private String errorMessage;
   
   ErrorMessages(String errorMessage)
   {
      this.errorMessage = errorMessage;    
   } 
    public String getErrorMessage() {
        return errorMessage;
    } 
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
