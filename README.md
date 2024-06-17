# 2024-DEV1-005-DevelopmentBooks
## Development Books Kata 
Kata description and rules found in [Stéphane Genicot's github](https://stephane-genicot.github.io/DevelopmentBooks.html])

---

### CLI Stuff 
1. Starting The Server:

   * MacOS / Linux: ***./gradlew bootRun***
   * Windows: ***gradlew.bat bootRun*** (not tested)

2. Running The Tests: 
   * MacOS / Linux: ***./gradlew clean test***
   * Windows: ***gradlew.bat clean test*** (not tested)

    
Note: the "clean" before "test" is only there to actually see the tests results each time, as for performance purposes, gradle will only rebuild / run tests if something has changed in the code.
  
Note 2: you'll probably have a better view of what the tests are actually doing in your editor of choice, mine being (and has been for years) IntelliJ IDEA, but I don't judge!

### Implementation Notes
- I have not done Kotlin in a while, I apologize in advance for the n00b stuff you'll find in this code.
- I have not setup an actual database, call me lazy. So I've kinda raped the companion object concept a little bit to make it act as my "in memory DB" to keep this part simple. 
- To keep this manageable in the short time I have to write this up, I've decided to not create multiple models as should be done. I should have entities, internal model, response model, etc. But in this case, I preferred to keep it simple, I hope you understand.

### The "API"
As it was requested to do this with Spring, I have added a couple of end points should you want to play with that.

```GET /books``` --> returns all books available to purchase

```GET /books/{id}``` --> returns details for one given book (or 404)

```POST /basket``` --> add item to the basket (or 400 if book is not available)

```GET /basket/finalize``` --> finalize the basket: calculate total price, resets the basket and returns the finalized basket and price together in response 

For your convenience, you can find a Postman collection in the /postman folder

### Final Note
I am disappointed I could not find a proper algorithm that would provide me with the same answer as the one in the Kata description.

I know my solution is correct, as you have multiple options for calculating discounts, and nowhere was it mentioned in the description that the discount had to be "the best". The rules have been respected. But I really wanted to find a better solution than the one I have, I'm sorry I didn't. 

I'll be happy to discuss this with you!