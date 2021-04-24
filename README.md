## QR Payment App

Our very first android application!

This is an android application used to scan a QR code which contains a transaction information and to perform a bank transfer from a customer to a merchant.
The app is developed with Java using Android Studio.
It uses an API in order to connect to the database which is stored on Firebase.

When launching the app, you are directed to the log-in page.
The system will make sure you're using a valid e-mail address and of course will check that your password is correct.

![alt text](https://github.com/SharonMauda/Git-Pictures/blob/main/android%20homepage.jpg?raw=true)

Welcome! You are now logged-in and directed to the main page. Here you can scan a QR code or watch your transactions history.

![alt text](https://github.com/SharonMauda/Git-Pictures/blob/main/android%20logged%20in.jpg?raw=true)

When you choose to scan a QR code, the app uses your phone's camera and waits for you to direct the camera to the QR code. Once a QR code is detected, it will show you the transaction information. We used the barcode and Json libraries in order to decode the QR code and show the relevant information in a comfortable way to the customer.

![alt text](https://github.com/SharonMauda/Git-Pictures/blob/main/android%20scan.jpg?raw=true)

At the history page you can watch all the transactions made by you.

![alt text](https://github.com/SharonMauda/Git-Pictures/blob/main/android%20history.jpg?raw=true)
