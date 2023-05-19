package activity5;

import java.util.Random;
/**
 * This version uses an array to hold the default responses.
 * @author Laurie White 
 * @version April 2012
 */
public class Magpie5 
{
    /**
     * Get a default greeting     
     * @return a greeting
     */    
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }

    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Say something, please.";
        }

        else if (findKeyword(statement, "no") >= 0)
        {
            response = "Why so negative?";
        }

        else if (findKeyword(statement, "What's your name?", 0) >= 0)
            response = "My name is Anne Frank.";

        else if (findKeyword(statement, "What are you famous for?", 0) >= 0)
            response = "I am a Holocaust victim and write experiences in my journal.";

        else if (findKeyword(statement, "Were you married?", 0) >= 0)
            response = "No, I was not.";

        else if (findKeyword(statement, "Did you have any children?", 0) >= 0)
            response = "No, I didn't.";

        else if (findKeyword(statement, "What are their names?", 0) >= 0)
            response = "I don't have any children.";

        else if (findKeyword(statement, "Where were you born?", 0) >= 0)
            response = "I was born in Frankfurt, Weimar Republic.";

        else if (findKeyword(statement, "Where did you live?", 0) >= 0)
            response = "I lived in Amsterdan most of my life.";

        else if (findKeyword(statement, "old", 0) >= 0)
            response = "I was 15 years old until my death.";

        else if (findKeyword(statement, "hello", 0) >= 0)
            response = "Hello.";

        else if (findKeyword(statement, "born", 0) >= 0)
            response = "I was born in Frankfurt, Weimar Republic.";

        else if (findKeyword(statement, "kid", 0) >= 0)
            response = "I did not have kids.";

        else if (findKeyword(statement, "kids", 0) >= 0)
            response = "I did not have kids";

        else if (findKeyword(statement, "child", 0) >= 0)
            response = "I did not have any children";

        else if (findKeyword(statement, "children", 0) >= 0)
            response = "I did not have children";

        else if (findKeyword(statement, "famous", 0) >= 0)
            response = "I am a Holocaust victim and write experiences in my journal.";

        else if (findKeyword(statement, "name", 0) >= 0)
            response = "My name is Anne Frank.";

        else if (findKeyword(statement, "married", 0) >= 0)
            response = "No, I was not";

        else if (findKeyword(statement, "live", 0) >= 0)
            response = "I lived in Amsterdan most of my life.";

        // Responses which require transformations
        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement);
        }
        //  Part of student solution
        else if (findKeyword(statement, "I want", 0) >= 0)
        {
            response = transformIWantStatement(statement);
        }
        else
        {
            int psn = findKeyword(statement, "you", 0);
            if (psn >= 0
            && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement);
            }
            else
            {
                psn = findKeyword(statement, "i", 0);
                if (psn >= 0
                && findKeyword(statement, "you", psn) >= 0)
                {
                    response = transformIYouStatement(statement);
                }
                else
                {
                    response = getRandomResponse();
                }
            }
        }
        return response;
    }

    private String transformIWantToStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }

    private String transformIWantStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }
        int psn = findKeyword (statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
    }

    private String transformYouMeStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }
        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }

    private String transformIYouStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }
        int psnOfI = findKeyword (statement, "I", 0);
        int psnOfYou = findKeyword (statement, "you", psnOfI);
        String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
        return "Why do you " + restOfStatement + " me?";
    }

    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
        while (psn >= 0) 
        {
            String before = " ", after = " "; 
            if (psn > 0)
            {
                before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }
            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
            && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
                return psn;
            }
            //  The last position didn't work, so let's find the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
        }
        return -1;
    }

    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }

    private String getRandomResponse ()
    {
        Random r = new Random ();
        return randomResponses [r.nextInt(randomResponses.length)];
    }
    private String [] randomResponses = {"Interesting, tell me more",
            "Hmmm.",
            "Do you really think so?",
            "You don't say.",
            "I can't help you with that.",
            "nope",
            "I may not be able to answer that but ChatGPT might: https://openai.com/",
            "Error: message too complex"
        };
}