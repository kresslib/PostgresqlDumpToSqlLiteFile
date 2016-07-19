/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postgresqldumptosqllitefile;

/**
 *
 * @author ikress
 */
public class ValidateInParam {

    public static String ValidateFormal(String[] args) {
        if (args.length < 4) {
            return StringConst.ERROR_OF_PARAM_COUNT;
        }
        if (!Character.isLetter(args[0].charAt(0)) && args[0].charAt(0) != '_') {
            return StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_0;
        }
        return null;
    }
}
