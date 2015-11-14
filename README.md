# cs542-for-Sun-and-Wang
Project One:A values store (a simplified version of a key-value store). The value store has just three interfaces:
(1)void Put(int key, byte[] data); stores data under the given key,
(2)byte[] Get(int key); retrieves the data and
(3)void Remove(int key); deletes the key.

Project Two:An indexing mechanism. Our index has just three interfaces:
(1)void Put(string key, Number data_value); or void Put(string key, string data_value); adds the index entry.
(2)string Get(Number data_value); or string Get(string data_value); retrieves the key given the index and
(3)void Remove(string key); deletes the index.

Project Three:A query execution engine. You may have realized that the functions of exercise 1 are actually methods of a Relation class. In a real database, we will have multiple instances of Relation, each representing one table. Modify your answer from exercise 1 to create the class and also add open(), getNext() and close() methods to it. Use the class to create and populate city and country tables. The data may be obtained from the MySQL World Database sample. Use the class to find all cities whose population is more than 40% of the population of their entire country.
