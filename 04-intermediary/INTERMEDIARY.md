
# Bank Service Intermediary

The intermediary for the Bank Service does the following:
- it accepts a `tweak` header with `currency` attribute (one of `EUR`, `USD`, `CZK`, rest is treated as `EUR`)
- it applies a conversion rate from `EUR` to the selected currency
- it returns the bank account balance in the given currency
- it returns a `tweak` header with the selected currency

I started the Bank Service at `http://127.0.0.1:8000/bank`. Then I started the intermediary Servlet using Apache Tomcat 9.0 at `http://127.0.0.1:8080/bank-intermediary/bank-intermediary`. Using SoapUI, I created a new SOAP project and created a HTTP POST request. First, I needed to sign in using the Auth Service to get the auth token. I inserted the auth token to the Bank Service `getBalance` request and specified the `tweak` header with `CZK` as the currency. The response was a correctly converted `EUR` balance to `CZK` balance and the `Currency: CZK` was present in the `tweak` header in the response.