import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessCardParser {
	public static void main(String args[]) throws FileNotFoundException
	{
		ContactInfo info = getContactInfo("path"); //add path to file here
		
		System.out.println("Name: " + info.getName());
		System.out.println("Phone: " + info.getPhoneNumber());
		System.out.println("Email: " + info.getEmailAddress());
	}
	
	public static ContactInfo getContactInfo (String document) throws FileNotFoundException
	{
		
		//finds file and begins to scan through it
		File file = new File(document);
		Scanner sc = new Scanner(file);
		Scanner sc2 = null;
		
		String name = null, pNum = null, add = null, cur = null;
		String prefix = null; //prefix of email
		boolean foundName = false; //continues looping unless a name is found
		
		while (sc.hasNext())
		{	
			cur = sc.next();
			
			
			//checks if there is a phone number, if there is, assign it to pNum variable
			if (stringContainsNumber(cur) && pNum == null)
			{ 
				
				String numB = ""; //builds phone number
				int numNums = 0; //number of numbers found so far
				int numStringB = 0; //number of strings found between numbers
				boolean endPNum = false; //checks if there is a phone number found yet
				
				//if still building the number continue loading next string
				while (!endPNum)
				{
					for(int i = 0; i < cur.length(); i++)
					{
						//checks if that character is a number
						try{
							Integer.parseInt(cur.substring(i, i+1));
							numNums++;
							numB += cur.substring(i, i+1);
							numStringB = 0;
							
						}
						
						//if it's not a number catch exception
						catch (NumberFormatException ex){
							numStringB++;
						}
						
						//if there are more than 2 non-number characters in a row, it is not a phone number
						if (numStringB > 1)
						{
							endPNum = true;
						}
						
						
					}
					
					//if there are already 10 numbers dont process next string
					if (numNums > 9)
					{
						pNum = numB;
						endPNum = true;
					}
					
					//check if next string has a number, if not dont process
					cur = sc.next();
					if (!stringContainsNumber(cur))
					{
						endPNum = true;
					}
						
					
					
				}
				
			}
			
			//checks if the email fits the acceptable format, if it does assign it to the add variable
			/** Must have only 1 @
			 * Must have a . after the @
			 * Must have at least one character before the @
			 * Must have at least one character between the @ and the .
			 * Must have at least two characters after the .
			 */
			if (cur.indexOf("@") > -1 && 
				cur.indexOf(".", cur.indexOf("@")) > -1 &&
				cur.indexOf("@") > 0 && 
				cur.indexOf(".", cur.indexOf("@")) - cur.indexOf("@") > 1 &&
				cur.length() - cur.indexOf(".", cur.indexOf("@")) > 2)
				
			{
				add = cur;
			}
			
			
		
		}
		
	
		//if there was an email, goes through the file a second time to find instances of name from email
		
	
		if (add != null){
			prefix = add.substring(0, add.indexOf("@"));
			sc2 = new Scanner(file);
		}
		
		//loops back through the card to find names matching the email
		while (sc2.hasNext() && !foundName)
		{
			cur = sc2.next();
	
			//checks if first letter of first name is in prefix
			if (prefix.toLowerCase().indexOf(cur.substring(0,1).toLowerCase()) > -1)
			{
				String f = cur;
				cur = sc2.next();
		
				//checks if first letter of second name is in prefix
				if (prefix.toLowerCase().indexOf(cur.substring(0,1).toLowerCase()) > -1)
				{
					//checks if one or both the first or last name is in the email prefix 
					if (prefix.toLowerCase().indexOf(cur.toLowerCase()) > -1 || prefix.toLowerCase().indexOf(f.toLowerCase()) > 1)
					{
						name = f + " " + cur;
						foundName = true;
					}
				}
			
		
			}
		}
		
		
		return new ContactInfo(name, pNum, add);
	}
	
	//checks if string contains a number
	public static boolean stringContainsNumber(String s)
	{
	    Pattern p = Pattern.compile( "[0-9]" );
	    Matcher m = p.matcher(s);

	    return m.find();
	}
}
