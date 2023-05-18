package com.med.medicalcentrecourse.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LOCKED)
public class ResourcePrivateException extends RuntimeException{
}