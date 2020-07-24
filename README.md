# 60-day-Training
This repository will have the testcases of 60 days API practice
Task 1

EndPoint : https://covid-19.dataflowkit.com/v1

Authorization -> No Auth

1. Find the top 5 Country with Highest New Cases 
2. Find the top 5 Country with lowest New Deaths Cases
3. Find the Statu of your Country
4. Verify the response HTTP status code = 200
5. Verify the Response Time < 600 ms
6. verify the Content Type = json

Implement the above Scenario On both PostMan and Rest Assured


#2) Paypal

Manual Steps:

1) Manually Create your own Credentials: https://developer.paypal.com/home/
2) Read the API documents from here:
	https://developer.paypal.com/docs/api/overview/
3) Create Token Manually

Automation Steps:

#1) Create a new product with hard coded value in the body [PostMan]
	a) Verify the status code
	b) Verify the response contains category and type as expected

#2) Create multiple products [using dataprovider + RestAssured]

#3) Verify that the created products are listed

Task 2

1)bestbuy.com
Manually Create your own account:
Endpoint : https://developer.bestbuy.com/
Automation Steps:
1.Find the store name, address and distance near to postal code 02864 for product 
2. Find all the canon products of price range between $1000-$1500
3. Get the regular and selling price for iPhone11 Pro
4. Find the stores having store pick-up availability of iPhone 11 Pro in stores in RI region
Manual Steps:
1) Manually Create your own Credentials: https://home.openweathermap.org/users/sign_up
2) Read the API documents from here:
    https://openweathermap.org/forecast5
3) Get the appid
Automation Steps:
- find the weather forecast of major city in india which are having Rain and Haze.
- Find the Rain volume in Mumbai for last 3 days.

https://paystack.com/docs/api/

Scenario1:
a)Create a new Payment Page b)Get all the Payment pages and verify the newly created page c)Get the newly created page and verify the details

Scenario2:
a)Create a new Subscription b)Enable the subscription c)Verify the subscription status using Fetch new subscription d)Disable the Subscription and verify the status
