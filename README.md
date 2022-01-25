# samsung.evaluation

## Realiza a conversão de moedas:


## Parametros Consulta

- Endpoint: http://localhost:8080/samsung-evaluation/conversion?documentNumber=800002014&currencyCode=BRL&startDate=2020-01-01&endDate=2020-01-03
- documentNumber 
- currencyCode
- startDate
- endDate

### Request

```shell

curl -X GET "http://localhost:8080/samsung-evaluation/conversion?documentNumber=800002014&currencyCode=BRL&startDate=2020-01-01&endDate=2020-01-03" 
-H  "accept: application/json"

```

### Response
```shell
[
  {
    "documentDate": "2020-01-03",
    "documentValue": 10000.19,
    "valueUSD": 50100.95,
    "valuePEN": 11900.23,
    "valueBRL": 10000.19,
    "documentNumber": "800002014",
    "currencyCode": "BRL",
    "currencyDesc": "Real",
    "currencyId": 3
  }
]
```

## Swagger
- http://localhost:8080/swagger-ui
