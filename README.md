# Amazon_Music_Unlimited

Implement Amazon's premium music subscriptions via the customers' needs, such as:

- [x] Create a new, empty playlist with a given name and a list of tags
- [x] Retrieve my playlist with a given ID.
- [x] Update my playlist name.
- [x] Add a song to the end of my playlist.
- [x] Add a song to the beginning of my playlist.
- [x] Retrieve all songs in my playlist, in a provided order (in order, reverse order, shuffled).



# Setting up
Use the following command to create a table in your DynamoDB
```
aws cloudformation create-stack --region us-west-2 --stack-name musicplaylistservice-createtables --template-body file://configurations/tables.template.yml --capabilities CAPABILITY_IAM
```
Once you've verified your tables exist on AWS, populate the album_tracks table with the following command:
```
aws dynamodb batch-write-item --request-items file://configurations/AlbumTracksData.json
```


