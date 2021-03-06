/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postgresqldumptosqllitefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ikress
 */
public class ValidateInParam {

    public static String ValidateFormal(String[] args) {
        if (args.length < 6) {
            return StringConst.ERROR_OF_PARAM_COUNT;
        }
        if (!isIPAddress(args[0]) && !isLocalHostName(args[0])) {
            return StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_0_HOST;
        }
        if (!isPort(args[1])) {
            return StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_1_PORT;
        }
        if (!isDBName(args[2])) {
            return StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_2_DB;
        }
        if (!isUserName(args[3])) {
            return StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_3_USER;
        }
        if (!isFilePath(args[5])) {
            return StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_5_FILE_PATH_FORMAT;
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
        Pattern p = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]+$");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean isPort(String text) {

        Pattern p = Pattern.compile("^[1-9][0-9]*$");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean isDBName(String text) {
        Pattern p = Pattern.compile("^[a-zA-Z_][a-zA-Z_0-9]+$");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean isUserName(String text) {
        Pattern p = Pattern.compile("^[a-zA-Z_][a-zA-Z_0-9]+$");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean isFilePath(String text) {
        String regex = "[a-zA-Z]:(\\\\[a-zA-Z0-9_\\s.-]+)*\\\\[a-zA-Z0-9_\\s.-]+\\.[a-zA-Z0-9_.-]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean hostPing(String host) {
        boolean result = false;
        try {
            String pingResult = "";
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("cmd start cmd.exe /K \"C:\\Windows\\SysWOW64\\chcp.com 437 && ping " + host + "\"");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                pingResult += " new_line " + inputLine;
                if (MatchPingFail(pingResult.trim())) {
                    result = false;
                    break;
                }
                if (MatchPingSuccess(pingResult.trim())) {
                    result = true;
                    break;
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return result;

    }

    static boolean MatchPingFail(String pingResult) {
        String regex = "new_line\\sActive\\scode\\spage:\\s437\\snew_line\\sPing\\srequest\\scould\\snot\\sfind\\shost\\s[a-zA-Z_][a-zA-Z0-9_]+\\.\\sPlease\\scheck\\sthe\\sname\\sand\\stry\\sagain\\.";
        Pattern patr = Pattern.compile(regex);
        Matcher m = patr.matcher(pingResult);
        return m.matches();
    }

    static boolean MatchPingSuccess(String pingResult) {
        String regex = "new_line\\sActive\\scode\\spage:\\s437\\snew_line\\s\\snew_line\\sPinging\\s[a-zA-Z_][a-zA-Z0-9_]+.*Ping\\sstatistics\\sfor\\s[0-9]*:[0-9]*:[0-9]*:[0-9]*\\snew_line\\s+Packets:\\sSent\\s=\\s4,\\sReceived\\s=\\s[1-4],\\sLost\\s=\\s[0-3].*";
        Pattern patr = Pattern.compile(regex);
        Matcher m = patr.matcher(pingResult);
        return m.matches();
    }
}
