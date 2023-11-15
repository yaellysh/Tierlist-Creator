# 207_Project: Jillian Escobar, Yael Lyshkow, Terry Fu, Tiana Chan, Timothy Li

# Problem Domain

The goal of our application is to allow users to organise various items within a catagory in a tierlist. They can 
either select these catagories from an existing list, create their own personal tier list for a specific topic or 
generate a randomised tier list. Users will be able to create their own tierlists for a variety of topics, such as 
athletes, movies, music, etc. Users can then post their tierlists, and view other users lists through their feed 
and by following friends and strangers and like/ comment on other people's tier lists. 

They are also able to make their own accounts to save the tier lists they have already worked on and view them later.

<img width="200" alt="Screenshot 2023-10-23 at 5 40 27 pm" src="https://github.com/yaellysh/207_Project/assets/137076627/39a61ec0-1e29-473e-b40a-b6b37d14ec3b">

# Type of Application

The end product for this application would be an app, wherein users can easily sign up, create their own tier lists 
and alter existing tier lists, with simple commands to allow the user experience to be as simple and enjoyable as 
possible. Users can also view other users tier lists and like/comment on them.
Users can either organise existing tier lists, create their own tier list using a specific prompt or randomise their tier list.

# Chosen API(s)

We decided we would allow users to create their own tournaments by entering topics/genres. To allow
this personalisation, we can use the **ChatGPT OpenAI API** which can easily be used to generate lists of relevant words
given a prompt. E.g. when asked 'List the names of 10 famous movies', ChatGPT returns
'1. Titanic 2. The Godfather 3. Star Wars 4. Gone with the Wind 5. The Shawshank Redemption 6. Inception 7. The Lion
King 8. Forrest Gump 9.', 10 movies which could then be used to generate a tournament with the Challonge API.

- https://platform.openai.com/docs/guides/gpt

# Output from running Java code

**Output from running ChatGPTAPIExample.java along with example question**
<img width="1159" alt="Screenshot 2023-09-30 at 5 16 34 pm" src="https://github.com/yaellysh/207_Project/assets/137076627/c5aa031c-1b6d-4188-835b-29d43cf77c63">


