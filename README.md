<a name="readme-top"></a>
<h1 align="center">


## Table of Contents
1. [The Encoder File](encoder)
2. [The Frontend](#frontend)
3. [The Backend](#backend)
4. [Languages & Technology & Packages & Frameworks](#languages)

***
<a name="encoder"></a>
## The Encoder File
The solutions to the Encoder can be found at [Link text](https://github.com/Coderh0u/dxc-test/tree/main/Encoder)

### Requirement:
You are to write an encoder that takes in a plaintext and encode it to another obfuscated string. The logic of the encoding / decoding is given below:
### Logic:
Choose any character in the reference table as the offset. The first character of the encoded message will be the offset character. Any character not in the reference table will mapped back to the same character.
For example, if the offset character is B, the entire table will shift by 1 element down (Refer to Shift Table #1). Thus, given the plaintext HELLO WORLD, it will be encoded as BGDKKN VNQKC:

	H	E	L	L	O		W	O	R	L	D
B	G	D	K	K	N		V	N	Q	K	C

Let’s take F as the offset character for another example. The entire table will shift 5 elements down (Refer to Shift Table #2). Given the same plaintext, the encoded message will be:
	H	E	L	L	O		W	O	R	L	D
F	C	/	G	G	J		R	J	M	G	.

To decode it, you need to take the first character for offset and match it backwards to get the original plaintext.
Constraints
The solution must implement the following 2 methods:
public String encode (String plainText);
public String decode (String encodedText);
### Bonus
The solution should also demonstrate the concept of OOP.

***
<a name="frontend"></a>
## The Frontend
The frontend is created using TypeScript and React.

The landing page is a simple log in page with 2 buttons, "Login" button and "Register" button.
![loginlanding](https://github.com/Coderh0u/dxc-test/assets/122144956/077219a5-0ad8-44b8-98c1-354dcd0edaac)

The "Login" button leads to the login page where the user will be prompted to input their username and password.
![login](https://github.com/Coderh0u/dxc-test/assets/122144956/b2b35c3f-8f86-47e7-b268-be1d096c15d3)
Invalid username or invalid passwords will return an error message.
![invalid login](https://github.com/Coderh0u/dxc-test/assets/122144956/4251e4d3-8bf5-4c30-8a6e-e79cd9066e1b)


The "Register" button leads to the login page where the user will be prompted to input their desired username and password, and select their role.
![register](https://github.com/Coderh0u/dxc-test/assets/122144956/af8bff1d-f230-47fa-ab2f-734f61144aea)
If the desired username is already taken, an error message will be returned, else a "successfully added user" message will be returned.
![registerre](https://github.com/Coderh0u/dxc-test/assets/122144956/b4456e01-6c88-4069-8f01-236cc5aaca6e)

Once logged in, the user will be greeted with a welcome page.
![image](https://github.com/Coderh0u/dxc-test/assets/122144956/d5c19899-6a2c-4682-abc8-ae022e0dbd90)

If the user is a manager, they will be able to see a list of all users. (Sorry for the poor UI styling)
![image](https://github.com/Coderh0u/dxc-test/assets/122144956/dc20f0f8-36a5-47be-8b6f-902eb58dd59e)



<a name="backend"></a>
## The Backend

The backend is created using Java, Spring Boot, Maven and PostgreSQL.

There are 3 tables in PostgreSQL that will be generated from the Java project, "users", "roles" and "access_tokens".

### To summarise the logic:
#### Register
After the user inputs the desired username and password, and selected the desired role on registration, the backend will first do a GET request to the database to see if the desired username already exists in the "users" table.
If the username does not exist in the "users" table,
1. The selected role will be compared to the "roles" table to get the id of that role. 
2. The password will be salted and hashed.
3. Then the username, encrypted password and role will be stored in the "users" table.

#### Log in
During a log in, a GET request will be sent to the "users" table to compare the input username. If the input username does not exist in the database, an error will be thrown.
If it exists, the input password will be salted and hashed, then compared to the stored encrypted password. If it is not a match, an error will be thrown.
If both the input username and input password completes the checks without any errors, the user will be considered "logged in" then a Json Web Token (an access token) will be generated and returned.

#### Log out
When the user clicks the "Log Out" button, the access token will be added to the "access_tokens" table. (Blacklist the token).

#### Subsequent log in instances
On subsequent log in instances, the backend will issue a new access token to the frontend.


***
<a name="languages"></a>
## Languages & Technology & Packages & Frameworks
### Frontend
- HTML
- CSS
- TypeScript
- React (VITE)
### Backend
- Java
- Spring Boot
- PostgreSQL
- Maven
- Java JWT
- jBCrypt
***

<p align="right">(<a href="#readme-top">back to top</a>)</p>
