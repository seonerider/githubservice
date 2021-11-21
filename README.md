# Branch Interview Challenge - Github integrator
This service will provide Github information for a given user.

## Architecture
This web service uses a layered pattern where the GithubServiceController serves as the API with a single GET endpoint.
The service layer (GithubService) handles the API calls to the Github API and maps the response into usable objects in order to return the correct response to the consumer. The service layer will also handle any errors when interacting with the Github API.  
For enhancements this is where caching could be implemented.
### Additional Information 
This web service is a spring boot application that uses Spring's webflux webclient to consumer github's api.
I chose Spring Boot for it's ability to quickly scaffold a REST web service without a lot of boilerplate code.  
I chose to use the webflux webclient to make the external REST calls.
It is currently using blocking calls which functions like RestTemplate, but can further be improved to in the future to using asynchronous reactivity if the need arises.

## Getting started
You will need JDK 11+ in order to run this service locally.

Clone this repo to a location of your choice. 
Open a terminal and run the following command:
```bash
./mvnw spring-boot:run
```
You can also run this from the built jar in a terminal from the base githubservice directory:
```bash
java -jar ./build/githubservice.jar ./src/main/java/GithubServiceApplication
```

Or import project into an IDE of your choice and run it from there.

This application will start a local server on port 9876. 
This service requires a username to be passed as a uri parameter to the /githubinfo/{username} endpoint
Get [Octocat's info here](http://localhost:9876/githubinfo/octocat) after server is started.  
Change the username to get a different user's Github info.

Endpoint:  http://localhost:9876/githubinfo/{username}

Return's HTTP status of 200 OK, 404 not found or 500 if things go badly.

Return sample stucture
```json
{
  "user_name": "octocat",
  "display_name": "The Octocat",
  "avatar": "https://avatars.githubusercontent.com/u/583231?v=4",
  "geo_location": "San Francisco",
  "email": null,
  "url": "https://api.github.com/users/octocat",
  "created_at": "2011-01-25 18:44:36",
  "repos": [
    {
      "name": "boysenberry-repo-1",
      "url": "https://api.github.com/repos/octocat/boysenberry-repo-1"
    },
    ...
  ]
}
```


