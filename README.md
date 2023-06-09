# Merchant Location Application
## Project Description
The idea of this project is to create an application that has a container full of merchant objects. The application allows the GET, POST, DELETE and PUT of these objects within the array.
This application also allows the user to find a list of the nearest merchants by longitude and latitude.
Here is an example of what the merchant array looks like:
```
[
    {
        "id": "64220a5ad25bdb63d31b746c",
        "coordinates": {
            "latitude": 52.986375,
            "longitude": -6.043701
        },
        "merchantId": 12,
        "merchantName": "Tesco"
    },
    {
        "id": "64220b2ad25bdb63d31b746e",
        "coordinates": {
            "latitude": 53.7530928,
            "longitude": -5.7820276
        },
        "merchantId": 13,
        "merchantName": "Centra"
    }
]
```
Each merchant is registered by using a POST method containing a Merchants JSON body. These objects are available to be viewed via a GET list or by identifying an individual object by the unique ID.
This ID can also be used to delete(DELETE) or update (PUT) a merchant that is already within the database.

## Project Set-up
This project works with a MongoDB database contained within a Docker container to hold different instances of a Merchant.
"Docker Desktop" is used as a means of storing the data, so Docker will need to be downloaded to get it up and running. Link to Docker download page - https://www.docker.com/products/docker-desktop
After Docker is downloaded, head to the docker-compose.yaml file that is part of this project. This file contains all the set-up needed to create the database. Press the two over-lapping green play buttons, and when this is completed, the database has been created. When this is all running correctly, by running the main "MerchantLocationApplication" file, the DB should be running.

## Command line prompts
docker-compose up -d
./mvnw spring-boot:run

## Project Endpoints and Examples
## GET (all)/POST
http://localhost:8080/merchants : Used with either GET or POST. GET will return a list of all merchants, and POST can be used to register a new merchant. E.g. of body for POST :
```
{
        "coordinates": {
            "latitude": 53.27196312847761,
            "longitude": -6.199368238449098
        },
        "merchantId": 99,
        "merchantName": "Londis"
}
```
## GET (individual)
http://localhost:8080/merchants/findById/{{id}} : This endpoint will return an individual merchant by ID.

## DELETE
localhost:8080/merchants/delete/{{id}} : This endpoint will delete an individual merchant by ID.

## PUT
localhost:8080/merchants/update/{{id}} : This endpoint will update an individual merchant by ID. This work by entering the merchant id into the endpoint and also sending through a JSON body. Here is an example of a JSON body that is accepted for a PUT method:
```
{
    "id": "64221422758c9d25bfa48a00",
    "coordinates": {
        "latitude": 53.53798298,
        "longitude": -7.94689283
    },
    "merchantId": 16,
    "merchantName": "Circle K"
}
```
## GET (List by nearest location)
http://localhost:8080/merchants/location?latitude={{latitude}}&longitude={{longitude}} : This endpoint will return a list of merchants based of the nearest proximity to given coordinates.
This works by QUERYing data. Two data variable called "longitude" and "latitude" and sent through with different values. The list return will be a list of the nearest merchants in order based off the proximity to the given coordinates.

