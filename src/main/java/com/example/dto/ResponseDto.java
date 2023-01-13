package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {
	  private String message;
	    private Object object;

	    public ResponseDto(String string, String response) {
	        this.message = string;
	        this.object = response;
	    }
	    public ResponseDto(String s, Object response) {
	        this.message=s;
	        this.object=response;
	    }
}
