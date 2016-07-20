/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postgresqldumptosqllitefile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ikress
 */
public class ValidateInParam {

    public static String ValidateFormal(String[] args) {
        if (args.length < 5) {
            return StringConst.ERROR_OF_PARAM_COUNT;
        }
        if (!isIPAddress(args[0]) && !isLocalHostName(args[0])) {
            return StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_0_HOST;
        }
        if (!isPort(args[1])) {
            return StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_1_PORT;
        }
        return null;
    }

    public static boolean isIPAddress(String text) {

        //Pattern p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Pattern p = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean isLocalHostName(String text) {
        Pattern p = Pattern.compile("^[a-zA-Z_]+$");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean isPort(String text) {

        Pattern p = Pattern.compile("^[1-9][0-9]*$");
        Matcher m = p.matcher(text);
        return m.matches();
    }
}

