# Settlement for Shopping Order

# Introduction
This is a settlement program for shopping orders with the calculation by dynamic promotion rules configured in DB.

#Infrastructure
+ SpringBoot 2.1.5.RELEASE
+ ORM framework: mybatis-plus

# Test Demo
### Database(mysql)
+ See DB initialization script under project root directory: settle_shop.sql
+ Demo data has been initialized in tables.

### Request URL
http://localhost:8210/sales/buy

#### Request Body(POST) Example
+ {
	"orderItemList":[{
		"skuId": 2,
		"buyQuantity":1
	},
	{
		"skuId": 1,
		"buyQuantity":3
	},
	{
		"skuId": 3,
		"buyQuantity":3
	}
	]
}

#### Response Body Example
Result content are as expected:

+ {
    "success": true,
    "message": "Succeed!",
    "code": 20000,
    "data": {
        "actualTotalPaidAmount": 5795.62,
        "skuSettlementResultList": [
            {
                "code": "120P90",
                "name": "Google Home",
                "price": 49.99,
                "rewardedSkuList": null,
                "actualSkuQuantity": 3,
                "subTotalAmount": 99.98
            },
            {
                "code": "A304SD",
                "name": "Alexa Speaker",
                "price": 109.5,
                "rewardedSkuList": null,
                "actualSkuQuantity": 3,
                "subTotalAmount": 295.65
            },
            {
                "code": "43N23P",
                "name": "MacBook Pro",
                "price": 5399.99,
                "rewardedSkuList": [
                    {
                        "code": "234234",
                        "name": "Raspberry Pi B",
                        "price": 30,
                        "rewardedSkuList": null,
                        "actualSkuQuantity": 1,
                        "subTotalAmount": 0
                    }
                ],
                "actualSkuQuantity": 1,
                "subTotalAmount": 5399.99
            }
        ]
    }
}
