# Nasa App

In this applciation we utilize one of the most popular API's of NASA called "APOD"

## APOD
One of the most popular websites at NASA is the Astronomy Picture of the Day. In fact, this website is one of the most popular websites across all federal agencies. 
It has the popular appeal of a Justin Bieber video. 
This endpoint structures the APOD imagery and associated metadata so that it can be repurposed for other applications. 
In addition, if the concept_tags parameter is set to True, then keywords derived from the image explanation are returned.
These keywords could be used as auto-generated hashtags for twitter or instagram feeds; but generally help with discoverability of relevant imagery.

The full documentation for this API can be found in the APOD API Github repository.(https://github.com/nasa/apod-api)

# Application Walk-through

## 1 - Welcome Screen

![image](https://user-images.githubusercontent.com/77923042/216672039-f26969a1-c62b-4c13-a490-a48e3baed918.png)

Welcome screen is just splash screen, that tells us that is application is based on NASA data.

## 2 - Main Screen

![image](https://user-images.githubusercontent.com/77923042/216672119-c430f601-60a9-4864-9f3c-b8ac4f100dff.png)

Main screen will provide data that we get from APOD API, there will be just Image, Title and Copyright.

You can access detailed information by clicking on Image

## 3 - Details Screen

![image](https://user-images.githubusercontent.com/77923042/216672524-34931380-6968-4c00-8b16-e829aa6286ac.png)

On the details screen we can see image in full, with data when image was taken, title and detailed information.

## 4 - Favorites Screen

![image](https://user-images.githubusercontent.com/77923042/216672772-2c9c3ab4-139a-488a-8a02-eb3237b43942.png)

Favorites screen is identical to Main Screen, however there will be shown only selected data, which you can choose from details screen.

We store/cache this data in-memory Sqlite database.

