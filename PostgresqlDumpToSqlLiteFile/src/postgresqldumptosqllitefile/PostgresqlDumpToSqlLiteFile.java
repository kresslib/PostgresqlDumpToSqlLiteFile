/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postgresqldumptosqllitefile;

import java.util.Scanner;

/**
 *
 * @author ikress
 */
public class PostgresqlDumpToSqlLiteFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String validate_formal_result = ValidateInParam.ValidateFormal(args);
        if (validate_formal_result != null) {
            System.out.println(validate_formal_result);
        }
        Scanner in = new Scanner(System.in);
        in.nextInt();
        // TODO code application logic here
    }

}
