package com.example.user.error;

public class DeleteAuthorException extends RuntimeException{

        public DeleteAuthorException(Long id){
            super("Author " + id + " can not delete");
    }
}
