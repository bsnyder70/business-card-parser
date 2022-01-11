# business-card-parser
Used as a training exercise for a past internship.

The BusinessCardParser class takes a text file with information from a business card and finds the name, email, and phone of the card's owner. BusinessCardParser relies on instances of the ContactInfo class to store the data from each business card.

## Introduction
### Prerequisites 
Both the BusinessCardParser class and the ContactInfo class were written in JavaSE- 1.6. 

### Implementation
The ContactInfo and BusinessCardParser must both be placed in the same package. In the main method in the BusinessCardParser class, replace the parameter "path" with a path(formatted as a string) linking to the text file of the business card. 

## Algorithms and Tests

### Moving through the File
The File and the Scanner classes are used to find the text file and move through each string. The Scanner.next() method is used to move from string to string. 

### Finding the Email
Each string is tested to see if it is an email. According to the RFC (https://tools.ietf.org/html/rfc2822), a valid email must:
- Must have only 1 @
- Must have a . after the @
- Must have at least one character before the @
- Must have at least one character between the @ and the .
- Must have at least two characters after the .

All of these requirements are tested. If the string meets all of the requirements, it is labeled as the email address.
If more than one email address is found on the business card, only the first email found will be printed.

### Finding the Phone Number
For this program, a valid phone number must:
- Have at least 10 numbers

The validity of the phone number is not tested in this program (a number with an invalid area code is still recognized as a phone number). Miscellanous symbols such as () or - are not included in the final string.

Each string is tested to see if it includes any numbers. If the string includes a number, each character of the springs is tested to see if it is an integer. If there are more than two non-integer characters in a row, it is assumed that the current string being analyzed is not part of a phone number. If the phone number being built has at least 10 numbers in a row, it is assumed that the phone number is complete. If the phone number beings built does not have 10 numbers in a row, the next string is checked to see if it contains an integer. If it does, it is parsed through using the same algorithm mentioned above.

The following formats and more are recognized by the program.
- 1112223333
- (111) 2223333
- +1 (111) 222-3333
- +1 111-222-3333
- 1 1 1 1 2 2 2 3333


### Finding the Name
The name is found using comparisons to the prefix of the email. It is assumed that for a business email, both the first and last name are found in the email in some capability. For this program, a name is assumed to consist of a first and last name. 

For a person, say John Doe, various prefix formats could include
- johndoe
- john.doe
- johndoe22
- jdoe
- johnd
- djohn
- doej555

The order of the first and last name is not important. Extraneous symbols are ignored unless a symbol splits either the first or last name. For example, the names will not be found in the following email formats:
- j.d_oe
- jo.nd
- j_dohn

If the last name or first name is not included in the email, a name will not be found.

When checking if a sequence string is a name, the following requirements must be met:
- the first character of the first name must be found in the prefix
- the first character of the last name must be found in the prefix
- EITHER the full first name OR the full last name must be found in the prefix

If all of the requirements are met, the first and last name are combined into one String and the Scanner will stop scanning through the rest of the text file.

## Limitations:

- If two emails are included, only the first email on the card will be recorded.
- If two phone numbers are given (telephone and fax), the first phone seen on the card will be recorded.
- If various other (non phone) long (>9 integers in a row) numbers are included on the business card, an inaccurate phone number will be recorded.
- If there is no email found, a name will not be found.
- To find the name, it is assumed that a business email has the first character of both the owner's first and last names as well as either their full first or their full last name.
- If there is other text that can be found in the prefix, the name may not be accurate. 
```
If an email was abcjohndoecompany@abc.com and a business card was formatted as follows:
Abc Company
John Doe
111-222-4444
abcjohndoecompany@abc.com

Abc Company would be recognized as the name since it is first in the text file.
```






