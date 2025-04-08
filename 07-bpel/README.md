# 07-bpel

Added two new services to `02-soap`:
- `BookStoreService` - finds book by title and returns bookId, then gets book price by bookId
- `OrderService` - creates an order, sends confirmation and makes payment

Flow is the following:
1. find book by title
2. get its price
3. create an order
4. paralelly send confirmation and make payment
5. if both are successful, send a success message, otherwise send an error message