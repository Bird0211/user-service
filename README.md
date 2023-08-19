# User-demo

## Description
>This is a user service demo application.


### Users data


> Users data load from https://jsonplaceholder.typicode.com/users

*Each user has a*

    id

	name

    username
   
    email

    phone

    website
  
	address
  
	company


### Technology
*Java: 17*

### Function
List users: http://localhost:8080/users

List posts by userId: http://localhost:8080/posts/user/{userId}

### ErrorCode


| Error Code        | Description   | 
| --------   | -----:  |
| 0  | SUCCESS  |
| 1  | FAIL  |
| 11111  | Sys Error  |
| 118001  | DB Error  |
| 118002 | User Not Exist |
| 118003| Param Error|

### Start Local
>
> ./gradlew bootRun
> 