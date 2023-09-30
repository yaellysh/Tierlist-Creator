# 207_Project: Jillian Escobar, Yael Lyshkow, Terry Fu, Tiana Chan, Timothy Li

# Problem Domain

The goal of our application is to allow people to make friends by linking people with similar interests and opinions
on various topics. Our application allows people to input their specific interests/ pick from an existing list of
suggestions, then provides them with the opportunity to judge various relevant topics in a tournament style match.

For example, if someone selected 'Movies' as one of their hobbies, they would have to compare a number of popular movies
and decide on their favourite(s) by comparing them against each other. After they picked their favourite, they would be
matched with other people with similar taste in movies. They are then able to use this as a prompt to start messaging
any of these selected people if they wish.

Users can create an account and include whatever information about themselves they wise. Upon having completed at least
one tournament, the app will start suggesting people that the algorithm thinks the user would be compatible with, and
includes a score based off the similarity of their opinions.

# Type of Application

The end product for this application would be an app, much like a typical dating app, wherein users can easily sign up,
select their interests and participate in tournament matches, with simple commands to allow the user experience to be as
simple and enjoyable as possible. Users will then be able to chat between each other, and if two users are 'connected'
(similar to following each other), they are able to see each other's interests and get to know each other better.

The tournaments can either be randomised based on the user's selected interests, or specific genres can be selected if
the user wishes.

# Chosen API(s)

To execute the tournament style challenges we plan to use the **Challonge API**. This API allows you create new
tournaments, keep track of wins and losses and set specific parameters regarding the matches.

- https://api.challonge.com/v1

As well as this, we decided we would allow users to create their own tournaments by entering topics/genres. To allow
this personalisation, we can use the **ChatGPT OpenAI API** which can easily be used to generate lists of relevant words
given a prompt. E.g. when asked 'List the names of 10 famous movies', ChatGPT returns
'1. Titanic 2. The Godfather 3. Star Wars 4. Gone with the Wind 5. The Shawshank Redemption 6. Inception 7. The Lion
King 8. Forrest Gump 9.', 10 movies which could then be used to generate a tournament with the Challonge API.

- https://platform.openai.com/docs/guides/gpt

# Using Postman to access API

**Challonge API**
<img width="1149" alt="Screenshot 2023-09-30 at 3 15 20 pm" src="https://github.com/yaellysh/207_Project/assets/137076627/e4ee28bf-2218-41cf-9d8c-3ca5966019a7">



# Output from running Java code
<img width="1161" alt="Screenshot 2023-09-30 at 4 11 57 pm" src="https://github.com/yaellysh/207_Project/assets/137076627/f3e25b57-2f26-4fa1-bc29-9e7fe864eb64">

