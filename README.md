# JavaNagarro

# Application Layers:
Presentation layer: Rest Controller is used for end points.
Business layer: Service classes present in service package are used to write all business logic
Persistence layer: DAO and DAO Implementation is worked as Persistence layer where I am using JDBC Template
Database layer: MS Access and in memory database is used at Database layer.
•	Full application is divided into different packages.
•	AOP is used for Logging we can control it form property file easily
•	Spring actuator is used for performance and health check
•	Spring security is used for Authentication and Authorization
•	Spring session is used for session management
•	we can control session time out from property file
•	We can control most of the configuration from Property file
•	Junit and Mockito is used for testing 
•	AES powerful Encryption is used
•	Responses are globally handled for all controllers 
•	CORS is enable so that our endpoints easily connect with and service
•	Everything is packaged in different packages
•	Scalability and modifications are also easy because everything is distributed and controlled centrally like if we want to change in logs just change in one file automatically effect all areas same for exception handling or response or security configurations
#Life Cycle of a Request:
After hitting a URL i.e.  http://localhost:8888/api/getStatement?accountId=3
First Spring security do Authorization for every request from class SecurityConfig if user not Authorized then request redirect to Login page after Authentication according to Role configuration user will able to access Rest End points.
Using spring Security session management session of 4 mint is created and Max session size is also 1.
Then request redirect to Rest controller StatementController  , same end points is used for both users (“Admin” and “User”).
Account id is mandatory files if it is not present then error message will sent , all other field are marked as required =false and Optional.
From Authentication user role is verified id it is “user” then and amount range or date range is passed in parameters then user friendly error message is returned otherwise basic things like range, to and from values.
If only account_id is given then last 3 month statement is fetched from database otherwise with provided data.
If everything is validated then request to send to service layer StatementServices class. In this class date format are validated and SQL query is created according to business rules.
Then request sent to DAO by DAO implementation StatementDaoImp connect with MS Access Database create Statement list. Account Id in encrypted using AES strong encryption.
Request came back to Controller then went to Global response for formatting with proper status codes and all other parameters.

#Important Links:
Login to Application: http://localhost:8888/login
Logout to Application: http://localhost:8888/logout
Get last 3 month statement: http://localhost:8888/api/getStatement?accountId=3
{"statusCode":200,"status":"SUCCESS","statusMessage":"SUCCESS","data":[{"id":3,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"03.09.2021","amount":"623.461804295262"},{"id":5,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"19.05.2021","amount":"125.51573044332"},{"id":7,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"15.09.2021","amount":"87.8901139771573"},{"id":23,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"16.07.2021","amount":"320.113318991709"},{"id":36,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"15.07.2021","amount":"971.65314918067"},{"id":66,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"25.03.2021","amount":"14.9269187221028"},{"id":105,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"23.03.2021","amount":"557.533882076657"},{"id":141,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"05.07.2021","amount":"38.6996920187093"}],"additionalAttributes":null}

Get last 3 month statement for other account: http://localhost:8888/api/getStatement?accountId=2
{"statusCode":200,"status":"SUCCESS","statusMessage":"No Statements DataFound","data":[],"additionalAttributes":null}

Get statement by Amount Range: http://localhost:8888/api/getStatement?accountId=3&&fromAmount=12&&toAmount=623.461804295262

{"statusCode":200,"status":"SUCCESS","statusMessage":"SUCCESS","data":[{"id":1,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"09.08.2020","amount":"535.197875027054"},{"id":4,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"03.02.2021","amount":"330.455004587924"},{"id":5,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"19.05.2021","amount":"125.51573044332"},{"id":7,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"15.09.2021","amount":"87.8901139771573"},{"id":23,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"16.07.2021","amount":"320.113318991709"},{"id":24,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"24.01.2021","amount":"564.982890505824"},{"id":25,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"29.11.2020","amount":"350.793682741483"},{"id":40,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"22.12.2016","amount":"369.407670060882"},{"id":66,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"25.03.2021","amount":"14.9269187221028"},{"id":84,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"17.01.2021","amount":"242.216450096382"},{"id":86,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"09.03.2012","amount":"165.97566682834"},{"id":91,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"18.06.2012","amount":"327.266323023121"},{"id":105,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"23.03.2021","amount":"557.533882076657"},{"id":107,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"19.12.2012","amount":"617.614872539574"},{"id":113,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"16.12.2012","amount":"602.336431872139"},{"id":133,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"25.06.2012","amount":"314.429495198656"},{"id":135,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"08.08.2019","amount":"518.420620441823"},{"id":141,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"05.07.2021","amount":"38.6996920187093"}],"additionalAttributes":null}

Get statement by Date Range: http://localhost:8888/api/getStatement?accountId=3&&fromDate=29.11.2020&&toDate=15.09.2021
{"statusCode":200,"status":"SUCCESS","statusMessage":"SUCCESS","data":[{"id":3,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"03.09.2021","amount":"623.461804295262"},{"id":4,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"03.02.2021","amount":"330.455004587924"},{"id":5,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"19.05.2021","amount":"125.51573044332"},{"id":23,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"16.07.2021","amount":"320.113318991709"},{"id":24,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"24.01.2021","amount":"564.982890505824"},{"id":25,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"29.11.2020","amount":"350.793682741483"},{"id":36,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"15.07.2021","amount":"971.65314918067"},{"id":66,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"25.03.2021","amount":"14.9269187221028"},{"id":84,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"17.01.2021","amount":"242.216450096382"},{"id":105,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"23.03.2021","amount":"557.533882076657"},{"id":141,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"05.07.2021","amount":"38.6996920187093"}],"additionalAttributes":null}

Get statement by Amount and Date Range: http://localhost:8888/api/getStatement?accountId=3&&fromDate=29.11.2020&&toDate=15.09.2021&&fromAmount=12&&toAmount=623.461804295262

{"statusCode":200,"status":"SUCCESS","statusMessage":"SUCCESS","data":[{"id":4,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"03.02.2021","amount":"330.455004587924"},{"id":5,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"19.05.2021","amount":"125.51573044332"},{"id":23,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"16.07.2021","amount":"320.113318991709"},{"id":24,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"24.01.2021","amount":"564.982890505824"},{"id":25,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"29.11.2020","amount":"350.793682741483"},{"id":66,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"25.03.2021","amount":"14.9269187221028"},{"id":84,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"17.01.2021","amount":"242.216450096382"},{"id":105,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"23.03.2021","amount":"557.533882076657"},{"id":141,"account_id":"3pvlqkcL2Gy3M0r+9cWbgQ==","datefield":"05.07.2021","amount":"38.6996920187093"}],"additionalAttributes":null}

Actuator URLs for Health check
http://localhost:8888/actuator
beans: http://localhost:8888/actuator/beans
caches-cache: http://localhost:8888/actuator/caches/{cache}
caches: http://localhost:8888/actuator/caches
health-path: http://localhost:8888/actuator/health/{*path}
health: http://localhost:8888/actuator/health
info: http://localhost:8888/actuator/info
conditions: http://localhost:8888/actuator/conditions
shutdown: http://localhost:8888/actuator/shutdown
configprops: http://localhost:8888/actuator/configprops
configprops-prefix: http://localhost:8888/actuator/configprops/{prefix}
env: http://localhost:8888/actuator/env
env-toMatch: http://localhost:8888/actuator/env/{toMatch}
loggers: http://localhost:8888/actuator/loggers
loggers-name: http://localhost:8888/actuator/loggers/{name}
heapdump: http://localhost:8888/actuator/heapdump
threaddump: http://localhost:8888/actuator/threaddump
metrics-requiredMetricName: http://localhost:8888/actuator/metrics/{requiredMetricName}
metrics: http://localhost:8888/actuator/metrics
scheduledtasks: http://localhost:8888/actuator/scheduledtasks
mappings: http://localhost:8888/actuator/mappings
















