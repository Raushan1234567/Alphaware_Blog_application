# MorningBlog

## Purpose
MorningBlog is a blogging platform designed to provide both users and admins with an interface to interact with blog content.

## Video explanation: [video link](https://drive.google.com/file/d/1j4WgWeU5lgyeGbkTS91ObbjQ1Y1h6UU5/view?usp=sharing)
## Tech Stack
- Java
- MySQL
- Spring Boot
- Spring Security
- Swagger UI
## ER DIAGRAM





![Screenshot (463)](https://github.com/Raushan1234567/Alphaware_Blog_application/assets/115460955/d0e0cce5-06d4-4e8f-a063-5cb2c3367968)
## Swagger(created api)




 ![Screenshot (464)](https://github.com/Raushan1234567/Alphaware_Blog_application/assets/115460955/15e808ec-726d-420b-b666-d035e584b508)

 

![Screenshot (465)](https://github.com/Raushan1234567/Alphaware_Blog_application/assets/115460955/c95c33b8-b91b-4b1d-a5a8-8d71d12e22b1)

![Screenshot (466)](https://github.com/Raushan1234567/Alphaware_Blog_application/assets/115460955/475d94ff-c05d-489d-9ebc-b0f7e7a000f2)

## User Interface Functionality
1. **User Registration and Login**
   - Users can sign up for a customer account.
   - Registration includes fields such as username, email, and password.
   - Registered users can securely log in to their accounts.

2. **Read Blogs**
   - Users can read blogs available on the platform.

3. **Search**
   - Users can search for blogs by category and heading.

## Admin Interface Functionality
1. **Admin Registration**
   - Admins can register for an admin account.
   - Registration includes fields such as username, email, and password.

2. **Access Endpoints**
   - Admins have access to all available endpoints.

# API Endpoints

This document outlines the endpoints available in our system and their functionalities.

## 1. Register User

- **Endpoint:** `/api/user/registers`
- **Method:** POST
- **Description:** Registers a new user as both an admin and a customer.

## 2. User Login

- **Endpoint:** `/api/user/loginuser`
- **Method:** GET
- **Description:** Allows a registered user to log in.

## 3. Get All Users

- **Endpoint:** `/api/user/getAllusers`
- **Method:** GET
- **Description:** Retrieves all registered users.

## 4. Get User by Email

- **Endpoint:** `/api/user/getAlluserByemail/{email}`
- **Method:** GET
- **Description:** Retrieves a user by their email address. Customers can also access this endpoint.

## 5. Add Comment to Blog

- **Endpoint:** `/api/comment/addcomment/{blogId}`
- **Method:** POST
- **Description:** Allows users to add comments to a specific blog identified by its ID.

## 6. Get Comments by Post ID

- **Endpoint:** `/api/comment/bypostId/{postId}`
- **Method:** GET
- **Description:** Retrieves comments associated with a specific post identified by its ID.

## 7. Post a Blog

- **Endpoint:** `/api/blog/post`
- **Method:** POST
- **Description:** Allows users to create and post a blog. The user is identified by their userID.



## Hosting
- The application is hosted on Railway.app.
- Access the application through the provided host link: [MorningBlog Host Link](https://alphawareblogapplication-production.up.railway.app/swagger-ui/index.html)

## Instruction to use Swagger API Documentation
- Register as a user with the role "customer".
- Login using the login API.
- Copy the bearer token from the response body and paste it in the "Bearer token" input box (located in the top right corner by clicking on the lock symbol) to 
  authorize access.
- Access the available endpoints for user and admin interfaces.


