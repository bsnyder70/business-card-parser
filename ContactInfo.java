
public class ContactInfo {
	String name, pNum, add;
	
	//initializes name, phoneNum, and email
	public ContactInfo(String n, String p, String e)
	{
		name = n;
		pNum = p;
		add = e;
	}
	
	//returns name of contact as a string
	public String getName()
	{
		return name;
	}
	
	//returns phone number of contact as a string
	public String getPhoneNumber()
	{
		return pNum;
	}
	
	//returns email address of contact as a string
	public String getEmailAddress()
	{
		return add;
	}
}
