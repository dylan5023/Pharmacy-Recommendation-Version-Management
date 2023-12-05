
# Pharmacy-Recommendation

The project leverages [external APIs (Kakao Address Search API)](https://developers.kakao.com/docs/latest/ko/local/dev-guide) and [public data (Pharmacy Status Information)](https://www.data.go.kr/data/15065023/fileData.do) to create a service that transcends a simple academic project and reaches the level of a real-world application.

Directions to the recommended pharmacies are provided via [Kakao Maps and Roadview Direct URL](https://apis.map.kakao.com/web/guide/#routeurl).

## Requirements Analysis

- Pharmacy Finder Service Requirements:
    - Assuming management of pharmacy status data (public data), which includes latitude and longitude information.
    - The service extracts the three closest pharmacies based on a user-inputted address.
    - Addresses can be inputted as either road or lot number addresses.
        - [Kakao Postal Code Service](https://postcode.map.daum.net/guide) is used for accurate address input.
    - Recommendations are based on addresses excluding detailed information like apartment numbers.
        - ex) Jongam-ro 10-gil, Seongbuk-gu, Seoul
    - Transforms input addresses into latitude and longitude for comparison with existing pharmacy data.
        - Earth is not flat; hence, a formula for calculating the shortest distance between two points on a sphere is required.
        - [Haversine formula](https://en.wikipedia.org/wiki/Haversine_formula) is used for this purpose.
    - Only pharmacies within a predetermined radius (10km) are recommended.
    - Recommended pharmacies are provided with a navigation URL and roadview URL.
        - ex)
          Navigation URL: https://map.kakao.com/link/map/OurCompany,37.402056,127.108212
          Roadview URL: https://map.kakao.com/link/roadview/37.402056,127.108212
    - Navigation URLs are provided as shorten URLs for readability.
    - Shorten URL keys are encoded using base62.
        - ex) http://localhost:8080/dir/nqxtX
    - The validity of shorten URLs is limited to 30 days.

### Pharmacy Recommendation System
```mermaid
sequenceDiagram
    actor Requester
    participant System
    Participant Redis
    Participant DB
    Requester->>System: Enter your address to request
    System->>Kakao_Api: Request to convert address to latitude and longitude
    System->>Redis: Request to redis Pharmacy status data
    activate Redis
    System->>DB: [Failover] Request to DB in case of Redis error
    deactivate redis
    activate DB
    
    DB->>System: Extract from the 3 nearest pharmacies
    System->>DB: Save the directions URL (pharmacy directions information)
    deactivate DB

    System->>Requester: Provides directions to recommended pharmacies

```


## Feature List

- Implement CRUD method using Spring Data JPA
- Writing test code using Spock
- Building an independent testing environment using Testcontainers
- Convert addresses to latitude and longitude by linking with Kakao Address Search API
- Provide recommended results by linking them to Kakao Map URL
- Development using public data (pharmacy status data)
- Creating a simple View using Handlebars
- Create multi-container applications using Docker
- Deploying applications to cloud services
- Implementing reprocessing using Spring retry (reprocessing for network errors in Kakao API, etc.)
- Developing shorten URL using base62 (direction URL)
- Optimizing performance using redis

## Tech Stack

- JDK 11
- Spring Boot 2.6.7
- Spring Data JPA
- Gradle
- Handlebars
- Lombok
- Github
- Docker
- AWS EC2
- Redis
- MariaDB
- Spock
- Testcontainers   
