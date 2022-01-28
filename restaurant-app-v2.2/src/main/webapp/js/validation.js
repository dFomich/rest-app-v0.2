function checkCorrectRepeatPassword() {
    if (document.getElementById('password').value ===
        document.getElementById('password_repeat').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = '';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'please repeat your password correctly';
    }
}

function validateLogin() {
    var login = document.getElementById('user_login').value;
    var loginRegex = '^[A-Za-z0-9_-]{3,30}$';

    if (login.match(loginRegex)) {
        document.getElementById('messageLogin').style.color = 'green';
        document.getElementById('messageLogin').innerHTML = '';
        return true;
    } else {
        document.getElementById('messageLogin').style.color = 'red';
        document.getElementById('messageLogin').innerHTML = 'Login must contain 3 or more letter or numbers';
        return false;
    }
}

function validateEmail() {
    var emailRegex = /\S+@\S+\.\S+/;
    var email = document.getElementById('user_email').value;

    if (emailRegex.test(email)) {
        document.getElementById('messageEmail').style.color = 'green';
        document.getElementById('messageEmail').innerHTML = '';
        return true;
    } else {
        document.getElementById('messageEmail').style.color = 'red';
        document.getElementById('messageEmail').innerHTML = 'Please set correct email';
        return false
    }
}

function validatePhoneNumber() {
    var phoneRegex = /^\+375(17|29|33|44)[0-9]{7}$/;
    var phoneNumber = document.getElementById('user_phone').value;

    if (phoneRegex.test(phoneNumber)) {
        document.getElementById('messagePhone').style.color = 'green';
        document.getElementById('messagePhone').innerHTML = '';
        return true;
    } else {
        document.getElementById('messagePhone').style.color = 'red';
        document.getElementById('messagePhone').innerHTML = 'Wrong phone number format';
        return false
    }
}

function validatePassword() {
    var passwordRegex = /^[A-Za-z0-9_-]{6,100}$/;
    var password = document.getElementById('password').value;
    var repeatPassword = document.getElementById('password_repeat').value;

    if (passwordRegex.test(password) && passwordRegex.test(repeatPassword) && password === repeatPassword) {
        document.getElementById('messagePassword').style.color = 'green';
        document.getElementById('messagePassword').innerHTML = '';
        return true;
    } else {
        document.getElementById('messagePassword').style.color = 'red';
        document.getElementById('messagePassword').innerHTML = 'Wrong password format';
        return false
    }
}