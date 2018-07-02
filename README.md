# com.alex.gordon.cityroutes
Routes between cities
==============================
CODE CHALLENGE
 
You’re asked to write a program in Java which determines if two cities are connected.
Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.
 
List of roads is available in a file.
File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.
 
It will be deployed as a spring-boot app and expose one endpoint:
http://localhost:8080/connected?origin=city1&destination=city2
 
Your program should respond with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2.
Any unexpected input should result in a ’no’ response.
 
Example:
city.txt content is:
Boston, New York
Philadelphia, Newark
Newark, Boston
Trenton, Albany
 
http://localhost:8080/connected?origin=Boston&destination=Newark
Should return yes
http://localhost:8080/connected?origin=Boston&destination=Philadelphia
Should return yes
http://localhost:8080/connected?origin=Philadelphia&destination=Albany
Should return no
 
———————————————
 
IMPORTANT
 
·         This exercise is a take-home assignment. It needs to be completed within 1-3 days.
·         Use Github as a repository for your solution
·         The completed assignment should be emailed back to myself – in the form of the GitHub URL - along with accompanying tests used for verification.
//////////////////// SOLUTION IS DESCRIBED BELOW  ////////////////////
Text file city.txt contains data. This data is intentionally made "dirty" - some lines are skirped, some lines contain just one term instead of two.
Dirty data is ignored by the service.
===beginning of data file====
   Boston,New York  
  Philadelphia, Newark    
  
  
  
        Newark, Boston   
   Trenton,        Albany  
     
      
     
  dfsdfsdf 
      
===end of data file====
Algorithm
-Program reads the city.txt file line by line and populates a Map which has a key as one city name and a value as a Set of all cities connected to the "key-city" by a route.
Here is data example in the Map:
Key               Set
====================================
Boston            New York, Newark
New York          Boston
.....
I am using Set (not a List) to avoid duplicates. Java Set silently ignores duplicates - very convenient in this case.

- Then I look up the first key-city in the key set of the Map and recursively lookup all the cities in the set.
- While doing this I keep a Set of cities which have been searched for already to avoid cycles and endless loops.
- Finally, I either find a match and report back a result "yes" or do not have match or get an error - in this case the result is "no" as specified in the requirements.
 
 



  

      