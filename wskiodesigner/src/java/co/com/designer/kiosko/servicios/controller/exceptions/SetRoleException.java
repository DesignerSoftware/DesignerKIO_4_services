/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.servicios.controller.exceptions;

/**
 *
 * @author Lenovo
 */
public class SetRoleException extends Exception {
    public SetRoleException(String message, Throwable cause) {
        super(message, cause);
    }
    public SetRoleException(String message) {
        super(message);
    }
}

