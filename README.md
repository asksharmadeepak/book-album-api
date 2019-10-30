# book-album-api
Spring boot Based microservice

Description :

    It's Spring boot based microservice which internally uses Google books api to get books details and itunes for 
    getting songs details. This service collect all data in asynchronos manner and send it back to comsumer of api.


Instruction to run :

    Execute command to run the project: 
        ./gradlew bootRun
    
    Api Docs (Swagger) : 
        http://localhost:9000/v2/api-docs
        http://localhost:9000/swagger-ui.html

    App will start on the route: 
        http://localhost:9000/api/books-albums?query=ketty
    
    For Custom Monitoring : 
        http://localhost:9000/http/trace
        Output will be generated in service_tracker.txt file
        
    Health Endpiut for Microservice:
        http://localhost:9000/actuator/health
    
    All Monitoring endpoint : 
        http://localhost:9000/actuator