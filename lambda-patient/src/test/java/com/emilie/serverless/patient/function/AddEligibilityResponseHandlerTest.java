package com.emilie.serverless.patient.function;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AddEligibilityResponseHandlerTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testAddLeadHandler() {
        AddEligibilityResponseHandler handler = new AddEligibilityResponseHandler();
        Context ctx = createContext();

//        String output = handler.handleRequest(input, ctx);
//
//        // TODO: validate output here if needed.
//        Assert.assertEquals("Hello from Lambda!", output);
    }
}
