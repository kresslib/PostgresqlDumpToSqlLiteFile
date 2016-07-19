/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postgresqldumptosqllitefile;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ikress
 */
public class ValidateInParamTest {

    public ValidateInParamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testValidateFormal() {
        String[] args1 = new String[]{"test"};
        String result = ValidateInParam.ValidateFormal(args1);
        assertTrue(result == StringConst.ERROR_OF_PARAM_COUNT);

        String[] args2 = new String[]{"test", "test"};
        result = ValidateInParam.ValidateFormal(args2);
        assertTrue(result == StringConst.ERROR_OF_PARAM_COUNT);

        String[] args3 = new String[]{"test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args3);
        assertTrue(result == StringConst.ERROR_OF_PARAM_COUNT);

        String[] args4 = new String[]{"test", "test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args4);
        assertTrue(result == null);

        String[] args5 = new String[]{"1test", "test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args5);
        assertTrue(result == StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_0);

        String[] args6 = new String[]{"_test", "test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args6);
        assertTrue(result == null);

        String[] args7 = new String[]{" _test", "test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args7);
        assertFalse(result == null);
    }

}
