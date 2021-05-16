/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

/**
 *
 * @author super
 */
public class DataAccessException extends Exception {
    public DataAccessException() {   super(); }
    public DataAccessException(String message) {   super(message); }
    public DataAccessException(String message, Throwable cause) {   super(message, cause); }
    public DataAccessException(Throwable cause) {   super(cause); }
    
    
}
