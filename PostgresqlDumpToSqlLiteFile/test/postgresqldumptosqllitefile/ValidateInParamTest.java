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

        String[] args4 = new String[]{"192.168.1.1", "1234", "test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args4);
        assertTrue(result == null);

        String[] args5 = new String[]{"1test", "test", "test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args5);
        assertTrue(result == StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_0_HOST);

        String[] args6 = new String[]{"_test", "1234", "test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args6);
        assertTrue(result == null);

        String[] args7 = new String[]{" _test", "test", "test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args7);
        assertFalse(result == null);
        
        String[] args8 = new String[]{"192.168.1.1", "s1234", "test", "test", "test"};
        result = ValidateInParam.ValidateFormal(args8);
        assertTrue(result == StringConst.ERROR_OF_SYNTAX_IN_PARAMETER_1_PORT);
        
    }

    /**
     * Test of isIPAddress method, of class ValidateInParam.
     */
    @Test
    public void testIsIPAddress() {
        System.out.println("isIPAddress");
        String text = "192.168.0.1";
        boolean expResult = true;
        boolean result = ValidateInParam.isIPAddress(text);
        assertEquals(expResult, result);
        text = "a1924.168.0.1";
        expResult = false;
        result = ValidateInParam.isIPAddress(text);
        assertEquals(expResult, result);
        text = "fdr";
        expResult = false;
        result = ValidateInParam.isIPAddress(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of isLocalHostName method, of class ValidateInParam.
     */
    @Test
    public void testIsLocalHostName() {
        System.out.println("isLocalHostName");
        String text = "1dfgh";
        boolean expResult = false;
        boolean result = ValidateInParam.isLocalHostName(text);
        assertEquals(expResult, result);

        text = "d fgh";
        expResult = false;
        result = ValidateInParam.isLocalHostName(text);
        assertEquals(expResult, result);

        text = "dfgh";
        expResult = true;
        result = ValidateInParam.isLocalHostName(text);
        assertEquals(expResult, result);

        text = "_dfgh";
        expResult = true;
        result = ValidateInParam.isLocalHostName(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of isPort method, of class ValidateInParam.
     */
    @Test
    public void testIsPort() {
        System.out.println("isPort");
        String text = "6122";
        boolean expResult = true;
        boolean result = ValidateInParam.isPort(text);
        assertEquals(expResult, result);
        text = "a6122";
        expResult = false;
        result = ValidateInParam.isPort(text);
        assertEquals(expResult, result);
        text = "61 22";
        expResult = false;
        result = ValidateInParam.isPort(text);
        assertEquals(expResult, result);
    }

}
