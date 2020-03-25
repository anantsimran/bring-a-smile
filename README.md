Welcome to bring-a-smile, Our initiative to increase smiles in this world. 

The application aims to connect Volunteers with different organisations and make process easier fot both of them. 
Feel free to extend the activity models used in this project according to your requirements.

What can this service do?
1. If you are a charity organisation/ or in contact with some organisations, it can help you broadcast your events to the volunteers.
2. Currently we only a support events which require volunteer time but this can be easily extended to donation of supplies etc.
3. We index all of the fields in elasticsearch while also using a java wrapper tp create a query
4. If you are a volunteer you can see volunteer activities nearby using different filters and sort parameters.


Service architecture uses Dropwizard and Google Guice and Elasticsearch as storage.

Database Setup:
1. Use Elasticsearch 6.0.0 for this service. Download from https://www.elastic.co/downloads/past-releases/elasticsearch-6-0-0
2. Configure cluster name as bring-a-smile
3. Create the following indices in ElasticSearch.
    a. auth
    b. volunteer
    c. coordinator
    d. activity
    e. association
    
Running the service:
1. Clone the service code using github repo.
2. Install gradle. You can find information regarding version in gradle wrapper properties.
3. run the command gradle run.
    


